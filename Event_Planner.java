
package event_planner;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;


public class Event_Planner {
    private static final int MARGIN_LEFT = 100; 
    private static final int VERTICAL_SHIFT = 50; 

    public static void main(String[] args) {
        JFrame frame = new JFrame("Event Planner");
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        JLayeredPane layeredPane = new JLayeredPane();
        frame.setContentPane(layeredPane);

        ImageIcon bgImageIcon = new ImageIcon(Event_Planner.class.getResource("/event_planner/bg1.jpg"));
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

        case "SERVICES":
            label.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    openFormPage(); 
                }
            });
            break;

        case "ABOUT US":
            label.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    openAboutUs(); 
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
            
            default:
            break;
    }
    
    navLinks.add(label);
}


        navBar.add(logoLabel);
        navBar.add(plannerName);
        navBar.add(Box.createHorizontalGlue());
        navBar.add(navLinks);

        JLabel tripText = new JLabel("Take a trip onto paradise");
        tripText.setForeground(Color.LIGHT_GRAY);
        tripText.setFont(new Font("Arial", Font.ITALIC, 24));
        layeredPane.add(tripText, Integer.valueOf(3));

        JLabel title = new JLabel("Partner with Melodia Event Management in Kerala");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 36));
        layeredPane.add(title, Integer.valueOf(4));

        JLabel subTitle = new JLabel("KERALA'S #1 EXCLUSIVE EVENT COMPANY");
        subTitle.setForeground(Color.WHITE);
        subTitle.setFont(new Font("Arial", Font.PLAIN, 18));
        layeredPane.add(subTitle, Integer.valueOf(5));

        JPanel socialPanel = new JPanel();
        socialPanel.setOpaque(false);
        socialPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        layeredPane.add(socialPanel, Integer.valueOf(6));

        String[] icons = {"/event_planner/twitter.png", "/event_planner/instagram.png", 
                          "/event_planner/linkedin.png", "/event_planner/whatsapp.png", 
                          "/event_planner/facebook.png"};

        for (String iconPath : icons) {
            ImageIcon icon = new ImageIcon(Event_Planner.class.getResource(iconPath));
            Image scaledImage = icon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
            JLabel iconLabel = new JLabel(new ImageIcon(scaledImage));
            socialPanel.add(iconLabel);
        }

        JButton ourWorkButton = new JButton("Our Work");
        ourWorkButton.setFont(new Font("Arial", Font.BOLD, 14));
        ourWorkButton.setForeground(Color.WHITE);
        ourWorkButton.setBackground(new Color(128, 0, 128)); 
        ourWorkButton.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        ourWorkButton.setFocusPainted(false);

        ourWorkButton.addActionListener(e -> openImageGalleryPage());
        layeredPane.add(ourWorkButton, Integer.valueOf(7));

        frame.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                int width = frame.getWidth();
                int height = frame.getHeight();

                bgLabel.setBounds(0, 0, width, height);
                Image img = bgImageIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
                bgLabel.setIcon(new ImageIcon(img));

                overlayPanel.setBounds(0, 0, width, height);
                navBar.setBounds(0, 0, width, 60);

                int centerY = height / 2 + VERTICAL_SHIFT;

                tripText.setBounds(MARGIN_LEFT, centerY - 100, tripText.getPreferredSize().width, 
                                   tripText.getPreferredSize().height);

                title.setBounds(MARGIN_LEFT, centerY - 50, title.getPreferredSize().width, 
                                title.getPreferredSize().height);

                subTitle.setBounds(MARGIN_LEFT, centerY, subTitle.getPreferredSize().width, 
                                   subTitle.getPreferredSize().height);

                socialPanel.setBounds(MARGIN_LEFT, centerY + 50, socialPanel.getPreferredSize().width, 
                                      socialPanel.getPreferredSize().height);
                ourWorkButton.setBounds(MARGIN_LEFT, centerY + 150, 150, 40); 
            }
        });

        frame.setVisible(true);
    }

    private static void openVenuePage() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                Venue.main(new String[0]); 
            }
        });
    }
    private static void openFormPage() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                formpage.main(new String[0]); 
            }
        });
    }
    private static void openImageGalleryPage() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                ImageGallery.main(new String[0]); 
            }
        });
    }
    private static void openAboutUs() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                AboutUs.main(new String[0]); 
            }
        });
    }
    private static void openReviews() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                homepage2.main(new String[0]);
            }
        });
    }
    
}
