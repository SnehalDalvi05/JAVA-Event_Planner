package event_planner;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AboutUs {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Event Planner");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        JPanel navBar = new JPanel();
        navBar.setBackground(new Color(0, 0, 0, 180));
        navBar.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        ImageIcon originalLogo = new ImageIcon(AboutUs.class.getResource("/event_planner/logo.png"));
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

         label.addMouseListener(new MouseAdapter() {
    public void mouseClicked(MouseEvent evt) {
        switch (item) {
            case "HOME":
                openHomePage();
                break;
            case "ABOUT US":
                openAboutUsPage();
                break;
            case "SERVICES":
                openServicesPage();
                break;
            case "PLANS":
                openVenuePage();
                break;
            default:
                break;
                case "REVIEWS":
            label.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    openReviews(); 
                }
            });
            break;

        }
    }
});
         navLinks.add(label);
        }

        navBar.add(logoLabel);
        navBar.add(plannerName);
        navBar.add(Box.createHorizontalGlue());
        navBar.add(navLinks);

        frame.add(navBar, BorderLayout.NORTH);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        HeroPanel heroPanel = new HeroPanel("/event_planner/img1.jpg");
        heroPanel.setLayout(new GridBagLayout());
        heroPanel.setPreferredSize(new Dimension(1200, 450));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(20, 0, 10, 0);

        JLabel titleLabel = new JLabel("About Us");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Georgia", Font.BOLD, 50));
        titleLabel.setBorder(new EmptyBorder(0, 0, 5, 0));
        heroPanel.add(titleLabel, gbc);

        JPanel socialPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 5));
        socialPanel.setOpaque(false);
        socialPanel.add(createSocialIcon("/event_planner/facebook.png", "https://www.facebook.com"));
        socialPanel.add(createSocialIcon("/event_planner/instagram.png", "https://www.instagram.com"));
        socialPanel.add(createSocialIcon("/event_planner/twitter.png", "https://www.twitter.com"));
        socialPanel.add(createSocialIcon("/event_planner/linkedin.png", "https://www.linkedin.com"));

        gbc.gridy = 1;
        heroPanel.add(socialPanel, gbc);

        mainPanel.add(heroPanel);

        JPanel aboutUsPanel = new JPanel();
        aboutUsPanel.setLayout(new BoxLayout(aboutUsPanel, BoxLayout.Y_AXIS));
        aboutUsPanel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));
        aboutUsPanel.setBackground(new Color(248, 248, 248));

        JLabel aboutTitle = new JLabel("Experiences");
        aboutTitle.setFont(new Font("Arial", Font.BOLD, 32));
        aboutTitle.setForeground(new Color(70, 70, 70));
        aboutTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        aboutUsPanel.add(aboutTitle);
        aboutUsPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        JPanel contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setBackground(new Color(248, 248, 248));

        JLabel servicesLabel = new JLabel(
                "<html><div style='text-align: center; font-size:18px;'>"
                        + "<b>Our Services:</b><br>"
                        + "✔ Wedding Planning<br>"
                        + "✔ Corporate Conferences<br>"
                        + "✔ Concerts & Live Performances<br>"
                        + "✔ Luxury Banquets<br>"
                        + "✔ Themed Events<br>"
                        + "</div></html>"
        );
        servicesLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        servicesLabel.setForeground(Color.DARK_GRAY);

        JLabel whyChooseLabel = new JLabel(
                "<html><div style='text-align: center; font-size:18px;'>"
                        + "<b>Why Choose Us?</b><br>"
                        + "✔ Experienced Team<br>"
                        + "✔ Personalized Approach<br>"
                        + "✔ End-to-End Event Management<br>"
                        + "✔ Trusted Vendor Network"
                        + "</div></html>"
        );
        whyChooseLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        whyChooseLabel.setForeground(Color.DARK_GRAY);

        JPanel textContainer = new JPanel(new GridLayout(1, 2, 50, 0));
        textContainer.setOpaque(false);
        textContainer.add(servicesLabel);
        textContainer.add(whyChooseLabel);
        contentPanel.add(textContainer);
        aboutUsPanel.add(contentPanel);

        mainPanel.add(aboutUsPanel);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.setVisible(true);
    }
    
    private static JLabel createSocialIcon(String imagePath, String url) {
        ImageIcon icon = new ImageIcon(AboutUs.class.getResource(imagePath));
        Image resizedImage = icon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        JLabel label = new JLabel(new ImageIcon(resizedImage));

        label.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return label;
    }
    private static void openHomePage() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                Event_Planner.main(new String[0]); 
            }
        });
    }
    private static void openAboutUsPage() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                AboutUs.main(new String[0]); 
            }
        });
    }
    private static void openServicesPage() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                formpage.main(new String[0]); 
            }
        });
    }
    private static void openVenuePage() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                Venue.main(new String[0]); 
            }
        });
    }
    static class HeroPanel extends JPanel {
        private Image backgroundImage;

        public HeroPanel(String imagePath) {
            try {
                this.backgroundImage = new ImageIcon(getClass().getResource(imagePath)).getImage();
            } catch (Exception e) {
                System.err.println("Error loading background image: " + imagePath);
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            if (backgroundImage != null) {
                g2d.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
            GradientPaint gradient = new GradientPaint(0, 0, new Color(0, 0, 0, 150), 0, getHeight(), new Color(0, 0, 0, 60));
            g2d.setPaint(gradient);
            g2d.fillRect(0, 0, getWidth(), getHeight());
        }
    }
     private static void openReviews() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                homepage2.main(new String[0]); 
            }
        });
    }
}
