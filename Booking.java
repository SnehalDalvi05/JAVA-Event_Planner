package event_planner;

import javax.swing.*;
import java.awt.*;

public class Booking extends JFrame {
    public Booking(String eventName, double price) {
        setTitle("Event Planner");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        try {
            UIManager.setLookAndFeel(new com.formdev.flatlaf.FlatIntelliJLaf());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = screenSize.width;
        int height = screenSize.height;
           ImageIcon bgImage = new ImageIcon(getClass().getResource("/event_planner/img3.jpg"));
        Image img = bgImage.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        JLabel background = new JLabel(new ImageIcon(img));
        background.setBounds(0, 0, width, height);
       
        JPanel panel = new JPanel();
        panel.setBounds(width / 3, height / 3, 500, 300);
        panel.setBackground(new Color(0, 0, 0, 120)); 
        panel.setLayout(new GridLayout(3, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel eventLabel = new JLabel("🎉 Event: " + eventName, SwingConstants.CENTER);
        eventLabel.setForeground(Color.WHITE);
        eventLabel.setFont(new Font("SansSerif", Font.BOLD, 28));

        JLabel priceLabel = new JLabel("💰 Price: Rs " + price, SwingConstants.CENTER);
        priceLabel.setForeground(Color.WHITE);
        priceLabel.setFont(new Font("SansSerif", Font.BOLD, 22));

        JButton payButton = new JButton("💳 Proceed to Payment");
        payButton.setFont(new Font("SansSerif", Font.BOLD, 20));
        payButton.setBackground(new Color(102, 0, 204));
        payButton.setForeground(Color.WHITE);
        payButton.setFocusPainted(false);
        payButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        payButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                payButton.setBackground(new Color(153, 51, 255));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                payButton.setBackground(new Color(102, 0, 204));
            }
        });
        payButton.addActionListener(e -> openPaymentDetails());
        panel.add(eventLabel);
        panel.add(priceLabel);
        panel.add(payButton);

        JLayeredPane layeredPane = getLayeredPane();
        layeredPane.add(background, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(panel, JLayeredPane.PALETTE_LAYER);

        setVisible(true);
    }

    private void openPaymentDetails() {
        new PaymentDetails();
        dispose(); 
    }
}