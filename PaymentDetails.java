package event_planner;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;
import java.sql.*;

public class PaymentDetails extends JFrame {
    private Connection conn;
    private PreparedStatement pst;
    
    public PaymentDetails() {
        setTitle("Event Planner");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        //ImageIcon bgImage = new ImageIcon("/event_planner/img3.jpg");
        ImageIcon bgImage = new ImageIcon(getClass().getResource("/event_planner/img3.jpg")); 

        Image img = bgImage.getImage().getScaledInstance(
                Toolkit.getDefaultToolkit().getScreenSize().width,
                Toolkit.getDefaultToolkit().getScreenSize().height, 
                Image.SCALE_SMOOTH
        );
        JLabel background = new JLabel(new ImageIcon(img));
        background.setLayout(new GridBagLayout());

        JPanel panel = new JPanel();
        panel.setBackground(new Color(255, 255, 255, 220)); // Semi-transparent white
        panel.setLayout(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));
        panel.setPreferredSize(new Dimension(400, 300));
        panel.setOpaque(true);
        panel.setBorder(BorderFactory.createLineBorder(new Color(0, 51, 102), 2));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel upiLabel = new JLabel("Enter UPI ID:");
        upiLabel.setForeground(new Color(0, 51, 102));
        upiLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        panel.add(upiLabel, gbc);

        gbc.gridx = 1;
        JTextField upiField = new JTextField(15);
        upiField.setFont(new Font("SansSerif", Font.PLAIN, 14));
        upiField.setPreferredSize(new Dimension(200, 30));
        panel.add(upiField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel passwordLabel = new JLabel("Enter Password:");
        passwordLabel.setForeground(new Color(0, 51, 102));
        passwordLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        panel.add(passwordLabel, gbc);

        gbc.gridx = 1;
        JPasswordField passwordField = new JPasswordField(15);
        passwordField.setFont(new Font("SansSerif", Font.PLAIN, 14));
        passwordField.setPreferredSize(new Dimension(200, 30));
        panel.add(passwordField, gbc);

        gbc.gridy = 2;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;

        JButton submitButton = new JButton("✅ Confirm Payment");
        submitButton.setFont(new Font("SansSerif", Font.BOLD, 18));
        submitButton.setBackground(new Color(0, 153, 51));
        submitButton.setForeground(Color.WHITE);
        submitButton.setFocusPainted(false);
        submitButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        submitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        submitButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                submitButton.setBackground(new Color(0, 120, 40));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                submitButton.setBackground(new Color(0, 153, 51));
            }
        });
        submitButton.addActionListener(e -> {
            String upi = upiField.getText();
            String password = new String(passwordField.getPassword());

            if (!isValidUpi(upi)) {
                JOptionPane.showMessageDialog(this, "⚠ Please enter a valid UPI ID (e.g., example@upi).", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "⚠ Please enter a password.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            savePaymentDetToDb(upi, password);

            int result = JOptionPane.showConfirmDialog(this, "✅ Payment Successful! Click OK to proceed.", 
                                                       "Success", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);

            if (result == JOptionPane.OK_OPTION) {
                dispose();
                SwingUtilities.invokeLater(Event_Planner::new);
            }
        });
        panel.add(submitButton, gbc);
        
        background.add(panel);
        add(background);

        setVisible(true);
    }

    private boolean isValidUpi(String upi) {
        String regex = "^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+$";
        return Pattern.matches(regex, upi);
    }

    private void savePaymentDetToDb(String upi, String password) {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Event_Planner", "root", "");
            String query = "INSERT INTO payment (upi_id, upi_pass) VALUES (?, ?)";
            pst = conn.prepareStatement(query);
            pst.setString(1, upi);
            pst.setString(2, password);

            pst.executeUpdate();

            pst.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error while saving payment details.", "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(PaymentDetails::new);
    }
}
