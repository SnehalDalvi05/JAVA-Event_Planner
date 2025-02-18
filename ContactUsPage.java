/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package event_planner;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ContactUsPage {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Contact Us - Melodia Event Planner");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Full screen
            frame.setLayout(null);

            JLayeredPane layeredPane = new JLayeredPane();
            frame.setContentPane(layeredPane);

            // ----------------- BACKGROUND PANEL (FULLY FITS THE SCREEN) -----------------
            JLabel bgLabel = new JLabel();
            bgLabel.setHorizontalAlignment(JLabel.CENTER);
            layeredPane.add(bgLabel, Integer.valueOf(0));

            // Dynamically set background image
            ImageIcon bgImageIcon = new ImageIcon(ContactUsPage.class.getResource("/event_planner/background1.jpg"));
            Image bgImage = bgImageIcon.getImage();

            // ----------------- OVERLAY PANEL FOR DARKENING EFFECT -----------------
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

            // ----------------- NAVBAR -----------------
            JPanel navBar = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
            navBar.setBackground(new Color(0, 0, 0, 180));
            layeredPane.add(navBar, Integer.valueOf(2));

            ImageIcon logoIcon = new ImageIcon(ContactUsPage.class.getResource("/event_planner/logo.png"));
            Image resizedLogo = logoIcon.getImage().getScaledInstance(70, 40, Image.SCALE_SMOOTH);
            JLabel logoLabel = new JLabel(new ImageIcon(resizedLogo));

            JLabel plannerName = new JLabel("Melodia Event Planner");
            plannerName.setForeground(Color.WHITE);
            plannerName.setFont(new Font("Arial", Font.BOLD, 20));

            JPanel navLinks = new JPanel();
            navLinks.setOpaque(false);
            String[] navItems = {"HOME", "ABOUT US", "SERVICES", "VENUE", "CONTACT US","REVIEWS"};

            for (String item : navItems) {
                JLabel label = new JLabel(item);
                label.setForeground(Color.WHITE);
                label.setFont(new Font("Arial", Font.BOLD, 14));
                label.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

                // Navigation Actions using switch-case to clean up logic
                switch (item) {
                    case "HOME":
                        label.addMouseListener(new MouseAdapter() {
                            public void mouseClicked(MouseEvent evt) {
                                openHomePage();
                            }
                        });
                        break;
                    case "ABOUT US":
                        label.addMouseListener(new MouseAdapter() {
                            public void mouseClicked(MouseEvent evt) {
                                openAboutUs();
                            }
                        });
                        break;
                    case "VENUE":
                        label.addMouseListener(new MouseAdapter() {
                            public void mouseClicked(MouseEvent evt) {
                                openVenuePage();
                            }
                        });
                        break;
                    case "SERVICES":
                        label.addMouseListener(new MouseAdapter() {
                            public void mouseClicked(MouseEvent evt) {
                                openFormPage();
                            }
                        });
                        break;
                    case "CONTACT US":
                        label.addMouseListener(new MouseAdapter() {
                            public void mouseClicked(MouseEvent evt) {
                                openContactUsPage();
                            }
                        });
                        break;
                        
                        case "REVIEWS":
            label.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    openReviews(); // This method will open the About Us page
                }
            });
            break;

                }

                navLinks.add(label);
            }

            navBar.add(logoLabel);
            navBar.add(plannerName);
            navBar.add(Box.createHorizontalGlue());
            navBar.add(navLinks);

            // ----------------- FORM PANEL -----------------
            JPanel formPanel = new JPanel(new GridBagLayout());
            formPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
            formPanel.setOpaque(false);
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.insets = new Insets(5, 5, 5, 5);

            formPanel.add(new JLabel("Full Name:"), gbc);
            gbc.gridx = 1;
            JTextField nameField = new JTextField(20);
            formPanel.add(nameField, gbc);

            gbc.gridx = 0;
            gbc.gridy = 1;
            formPanel.add(new JLabel("Phone Number:"), gbc);
            gbc.gridx = 1;
            JTextField phoneField = new JTextField(20);
            formPanel.add(phoneField, gbc);

            gbc.gridx = 0;
            gbc.gridy = 2;
            formPanel.add(new JLabel("Email ID:"), gbc);
            gbc.gridx = 1;
            JTextField emailField = new JTextField(20);
            formPanel.add(emailField, gbc);

            gbc.gridx = 0;
            gbc.gridy = 3;
            formPanel.add(new JLabel("Message:"), gbc);
            gbc.gridx = 1;
            JTextArea messageArea = new JTextArea(3, 20);
            formPanel.add(new JScrollPane(messageArea), gbc);

            gbc.gridx = 1;
            gbc.gridy = 4;
            JButton submitButton = new JButton("Submit");
            submitButton.setBackground(new Color(128, 0, 128));
            submitButton.setForeground(Color.WHITE);
            formPanel.add(submitButton, gbc);

            layeredPane.add(formPanel, Integer.valueOf(4));

            // ----------------- FLOATING WHATSAPP BUTTON -----------------
            JLabel whatsappButton = new JLabel(new ImageIcon("whatsapp.png"));
            layeredPane.add(whatsappButton, JLayeredPane.POPUP_LAYER);

            // Adjust Components on Resize
            frame.addComponentListener(new ComponentAdapter() {
                public void componentResized(ComponentEvent e) {
                    int width = frame.getWidth();
                    int height = frame.getHeight();

                    // Update background size dynamically only after frame is resized
                    if (width > 0 && height > 0) {
                        bgLabel.setIcon(new ImageIcon(bgImage.getScaledInstance(width, height, Image.SCALE_SMOOTH)));
                        bgLabel.setBounds(0, 0, width, height);
                        overlayPanel.setBounds(0, 0, width, height);
                        navBar.setBounds(0, 0, width, 60);
                        formPanel.setBounds(width / 2 - 250, height / 3, 500, 350);
                        whatsappButton.setBounds(width - 100, height - 120, 50, 50);
                    }
                }
            });

            frame.setVisible(true);
        });
    }

    // Navigation Methods
    private static void openHomePage() { Event_Planner.main(new String[0]); }
    private static void openVenuePage() { Venue.main(new String[0]); }
    private static void openFormPage() { formpage.main(new String[0]); }
    private static void openContactUsPage() { ContactUsPage.main(new String[0]); }
    private static void openAboutUs() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                AboutUs.main(new String[0]); // This will call the main method of AboutUs.java
            }
        });
    }
     private static void openReviews() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                homepage2.main(new String[0]); // This will call the main method of AboutUs.java
            }
        });
    }
}
