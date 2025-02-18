package event_planner;

import javax.swing.*;
import java.awt.*;

public class homepage2 {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Event Planner");
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout(20, 20)); 
        frame.getContentPane().setBackground(new Color(255, 248, 236)); 

        JPanel navbar = new JPanel();
        navbar.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10)); 
        navbar.setBackground(new Color(0, 0, 0, 180)); 

        ImageIcon originalLogo = new ImageIcon(homepage2.class.getResource("/event_planner/logo.png"));
        Image resizedLogo = originalLogo.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
        JLabel logoLabel = new JLabel(new ImageIcon(resizedLogo));

        JLabel plannerName = new JLabel("DistinctlyStyled");
        plannerName.setForeground(Color.WHITE);
        plannerName.setFont(new Font("Arial", Font.BOLD, 20));
        JPanel navLinks = new JPanel();
        navLinks.setOpaque(false);
        String[] navItems = {"HOME","ABOUT US","SERVICES","PLANS","REVIEWS"};
        for (String item : navItems) {
            JLabel label = new JLabel(item);
            label.setForeground(Color.WHITE);
            label.setFont(new Font("Arial", Font.BOLD, 14));
            label.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

            switch (item) {
                case "HOME":
                    label.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                            openHomepage();  
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
                case "SERVICES":
                    label.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                            openFormPage(); 
                        }
                    });
                    break;
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
                default:
                    break;
            }
            navLinks.add(label);
        }
        navbar.add(logoLabel);
        navbar.add(plannerName);
        navbar.add(Box.createHorizontalGlue()); 
        navbar.add(navLinks);

        JPanel imagePanel = new JPanel(new GridBagLayout());
        imagePanel.setBackground(new Color(255, 248, 236));
        
        ImageIcon img1 = loadImage("/event_planner/img1.jpg");
        ImageIcon img2 = loadImage("/event_planner/img7.jpg");
        ImageIcon img3 = loadImage("/event_planner/img5.jpg");
        ImageIcon img4 = loadImage("/event_planner/img3.jpg");

        JLabel imgLabel1 = new JLabel(img1);
        JLabel imgLabel2 = new JLabel(img2);
        JLabel imgLabel3 = new JLabel(img3);
        JLabel imgLabel4 = new JLabel(img4);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); 
        gbc.gridx = 0; gbc.gridy = 0;
        imagePanel.add(imgLabel1, gbc);
        gbc.gridx = 1;
        imagePanel.add(imgLabel2, gbc);
        gbc.gridx = 0; gbc.gridy = 1;
        imagePanel.add(imgLabel3, gbc);
        gbc.gridx = 1;
        imagePanel.add(imgLabel4, gbc);

        JPanel reviewPanel = new JPanel();
        reviewPanel.setLayout(new BoxLayout(reviewPanel, BoxLayout.Y_AXIS));
        reviewPanel.setBackground(new Color(255, 248, 236));
        reviewPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 30, 20)); 

        reviewPanel.add(Box.createVerticalStrut(30)); 

        String[][] reviews = {
                {"Ashok Kumar", "Excellent service! The event was well-organized.", "2023-06-10"},
                {"Priya Sharma", "Amazing experience, loved every moment!", "2023-07-15"},
                {"Rahul Verma", "The team handled everything perfectly!", "2023-08-05"},
                {"Sneha Iyer", "Professional and reliable, highly recommended!", "2023-09-01"}
        };

        for (String[] review : reviews) {
            reviewPanel.add(createReviewCard(review[0], review[1], review[2]));
            reviewPanel.add(Box.createRigidArea(new Dimension(0, 10))); 
        }

        frame.add(navbar, BorderLayout.NORTH); 
    
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(reviewPanel, BorderLayout.WEST); 
        mainPanel.add(imagePanel, BorderLayout.CENTER); 

        frame.add(mainPanel, BorderLayout.CENTER); 

        frame.setVisible(true);
    }
    private static JPanel createReviewCard(String name, String review, String date) {
        JPanel card = new JPanel();
        card.setLayout(new BorderLayout());
        card.setBorder(BorderFactory.createLineBorder(new Color(0, 51, 102), 2, true));
        card.setBackground(Color.WHITE);
        card.setPreferredSize(new Dimension(380, 90));
        card.setMaximumSize(new Dimension(400, 100));

        JLabel stars = new JLabel("⭐⭐⭐⭐⭐");
        stars.setFont(new Font("SansSerif", Font.BOLD, 14));
        stars.setForeground(new Color(255, 165, 0));

        JLabel nameLabel = new JLabel(name);
        nameLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        nameLabel.setForeground(new Color(0, 51, 102));

        JLabel reviewLabel = new JLabel("<html><p style='text-align:left;width:350px;'>" + review + "</p></html>");
        reviewLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
        reviewLabel.setHorizontalAlignment(SwingConstants.LEFT);  

        JLabel dateLabel = new JLabel(date);
        dateLabel.setFont(new Font("SansSerif", Font.ITALIC, 10));
        dateLabel.setForeground(Color.GRAY);

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.setBackground(Color.WHITE);
        topPanel.add(stars);
        topPanel.add(nameLabel);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        bottomPanel.setBackground(Color.WHITE);
        bottomPanel.add(dateLabel);

        card.add(topPanel, BorderLayout.NORTH);
        card.add(reviewLabel, BorderLayout.CENTER);
        card.add(bottomPanel, BorderLayout.SOUTH);

        return card;
    }

    private static ImageIcon loadImage(String path) {
        return new ImageIcon(new ImageIcon(homepage2.class.getResource(path))
                .getImage().getScaledInstance(300, 200, Image.SCALE_SMOOTH));
    }

    private static void openHomepage() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                Event_Planner.main(new String[0]); 
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
 private static void openFormPage() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                formpage.main(new String[0]); 
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
