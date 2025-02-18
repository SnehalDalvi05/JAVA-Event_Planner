/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package event_planner;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class formpage {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Event Planner");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setLayout(null);

        JLayeredPane layeredPane = new JLayeredPane();
        frame.setContentPane(layeredPane);

        ImageIcon bgImageIcon = new ImageIcon(formpage.class.getResource("/event_planner/img2.jpg"));
        JLabel bgLabel = new JLabel(bgImageIcon);
        layeredPane.add(bgLabel, Integer.valueOf(0));

        JPanel overlayPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f)); 
                g2d.setColor(Color.BLACK);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        overlayPanel.setOpaque(false);
        layeredPane.add(overlayPanel, Integer.valueOf(1));

        JPanel navBar = new JPanel();
        navBar.setBackground(new Color(0, 0, 0, 180));
        navBar.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        layeredPane.add(navBar, Integer.valueOf(2));

        ImageIcon originalLogo = new ImageIcon(Event_Planner.class.getResource("/event_planner/logo.png"));
        Image resizedLogo = originalLogo.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
        JLabel logoLabel = new JLabel(new ImageIcon(resizedLogo));

        JLabel plannerName = new JLabel("DistinctlyStyled");
        plannerName.setForeground(Color.WHITE);
        plannerName.setFont(new Font("Arial", Font.BOLD, 20));

        JPanel navLinks = new JPanel();
        navLinks.setOpaque(false);
        String[] navItems = {"HOME", "ABOUT US", "SERVICES", "PLANS","REVIEWS"};
        for (String item : navItems) {
            JLabel label = new JLabel(item);
            label.setForeground(Color.WHITE);
            label.setFont(new Font("Arial", Font.BOLD, 14));
            label.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
            switch (item) {
   
                    case "PLANS":
                    label.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                            openVenuePage(); 
                        }
                    });
                    break;
                case "REVIEWS":
                    label.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                            openReviews(); 
                        }
                    });
                    break;
                case "HOME":
                    label.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                            openHomepage();                         }
                    });
                    break;
                    case "ABOUT US":
                    label.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                            openAboutUs();                         }
                    });
                    break;
                    
                default:
                    break;
            }
            navLinks.add(label);
        }

        navBar.add(logoLabel);
        navBar.add(plannerName);
        navBar.add(Box.createHorizontalGlue());
        navBar.add(navLinks);

        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.setOpaque(false);
        layeredPane.add(textPanel, Integer.valueOf(3));

        JLabel heading = new JLabel("<html><b>Looking for the Most Creative & Innovative Event Planners in Mumbai?</b></html>");
        heading.setFont(new Font("Arial", Font.BOLD, 35)); 
        heading.setForeground(Color.WHITE);

        JLabel description = new JLabel("<html>"
                + "Imagine celebrating the most special moments of your life without any stress. "
                + "Melodia Events is here to handle everything, ensuring a seamless experience for you."
                + "<br><br>"
                + "We specialize in weddings, corporate events, birthdays, and social gatherings, "
                + "offering creative themes, stunning décor, and flawless coordination."
                + "</html>");
        description.setFont(new Font("Arial", Font.PLAIN, 18));
        description.setForeground(Color.WHITE);

        textPanel.add(heading);
        textPanel.add(Box.createVerticalStrut(15));
        textPanel.add(description);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(5, 1, 10, 15));
        formPanel.setOpaque(false);
        layeredPane.add(formPanel, Integer.valueOf(4));

        JTextField nameField = createPlaceholderField("Enter your Full Name");
        JTextField phoneField = createPlaceholderField("Enter your Phone Number");
        JTextField emailField = createPlaceholderField("Enter your Email ID");
        JTextArea messageArea = createPlaceholderTextArea("Message");

        JButton submitButton = new JButton("SUBMIT");
        submitButton.setFont(new Font("Arial", Font.BOLD, 16));
        submitButton.setBackground(new Color(102, 0, 153));
        submitButton.setForeground(Color.WHITE);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String phone = phoneField.getText();
                String email = emailField.getText();
                String message = messageArea.getText();

                insertDataToDatabase(name, phone, email, message);
            }
        });

        formPanel.add(nameField);
        formPanel.add(phoneField);
        formPanel.add(emailField);
        formPanel.add(new JScrollPane(messageArea));
        formPanel.add(submitButton);

        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int width = frame.getWidth();
                int height = frame.getHeight();

                bgLabel.setBounds(0, 0, width, height);
                Image img = bgImageIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
                bgLabel.setIcon(new ImageIcon(img));

                overlayPanel.setBounds(0, 0, width, height);
                navBar.setBounds(0, 0, width, 60);

                textPanel.setBounds(50, height / 3, 700, 400);
                formPanel.setBounds(width - 550, height / 3, 500, 350);
            }
        });

        frame.setVisible(true);
    }

    private static JTextField createPlaceholderField(String placeholder) {
        JTextField field = new JTextField(placeholder, 20);
        field.setFont(new Font("Arial", Font.PLAIN, 16));
        field.setForeground(Color.GRAY);
        field.setPreferredSize(new Dimension(300, 30));

        field.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (field.getText().equals(placeholder)) {
                    field.setText("");
                    field.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (field.getText().isEmpty()) {
                    field.setText(placeholder);
                    field.setForeground(Color.GRAY);
                }
            }
        });

        return field;
    }
    private static JTextArea createPlaceholderTextArea(String placeholder) {
        JTextArea area = new JTextArea(placeholder, 5, 30);
        area.setFont(new Font("Arial", Font.PLAIN, 16));
        area.setForeground(Color.GRAY);
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        area.setPreferredSize(new Dimension(300, 80));

        area.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (area.getText().equals(placeholder)) {
                    area.setText("");
                    area.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (area.getText().isEmpty()) {
                    area.setText(placeholder);
                    area.setForeground(Color.GRAY);
                }
            }
        });
        return area;
    }

    private static void insertDataToDatabase(String name, String phone, String email, String message) {
        String url = "jdbc:mysql://localhost:3306/Event_Planner"; 
        String username = "root";
        String password = ""; 

        String query = "INSERT INTO services (user_name, user_no, user_email, user_msg) VALUES (?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, phone);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, message);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Form submitted successfully!");
            } else {
                JOptionPane.showMessageDialog(null, "Form submission failed. Please try again.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
    }
    private static void openVenuePage() {
        EventQueue.invokeLater(() -> Venue.main(new String[0]));
    }
    private static void openHomepage() {
        EventQueue.invokeLater(() -> Event_Planner.main(new String[0]));
    }
    private static void openAboutUs() {
        EventQueue.invokeLater(() -> AboutUs.main(new String[0]));
    }
     private static void openReviews() {
        EventQueue.invokeLater(() -> homepage2.main(new String[0]));
    }
}  