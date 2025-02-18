package event_planner;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Venue {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Event Plans");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel navBar = new JPanel();
        navBar.setBackground(new Color(0, 0, 0, 180));
        navBar.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));

        ImageIcon originalLogo = new ImageIcon(Venue.class.getResource("/event_planner/logo.png"));
        Image resizedLogo = originalLogo.getImage().getScaledInstance(70, 40, Image.SCALE_SMOOTH);
        JLabel logoLabel = new JLabel(new ImageIcon(resizedLogo));

        JLabel plannerName = new JLabel("Melodia Event Planner");
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
                case "SERVICES":
                    label.addMouseListener(new MouseAdapter() {
                        public void mouseClicked(MouseEvent evt) {
                            openFormPage(); 
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

        JPanel eventPanel = new JPanel(new GridLayout(0, 3, 20, 20));
        eventPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        String[][] venues = {
            {"Wedding Bliss", 
             "<html>A perfect venue for your dream wedding.<br>Enjoy a romantic atmosphere with top-tier services.</html>", 
             "Rs. 45000", "/event_planner/img4.jpg"},
        
            {"Corporate Gala", 
             "<html>A professional space for business events.<br>Offering modern amenities for meetings and conferences.</html>", 
             "Rs 9000", "/event_planner/img8.jpg"},
        
            {"Birthday Bash", 
             "<html>Celebrate your special day in style.<br>Spacious venue with customizable decorations.</html>", 
             "Rs.12000", "/event_planner/img15.jpg"},
        
            {"Concert Night", 
             "<html>An open space for musical performances.<br>Complete with state-of-the-art sound systems.</html>", 
             "Rs.22000", "/event_planner/img1.jpg"},
        
            {"Outdoor Festival", 
             "<html>Spacious venue for outdoor events.<br>Enjoy a beautiful view and ample room for crowds.</html>", 
             "Rs.18000", "/event_planner/img16.jpg"},
        
            {"Luxury Banquet", 
             "<html>A premium setting for elite gatherings.<br>Exquisite dining and entertainment options.</html>", 
             "Rs.40000", "/event_planner/img7.jpg"}
        };

        for (String[] venue : venues) {
            JPanel card = new JPanel(new BorderLayout());
            card.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
            card.setBackground(Color.WHITE);
            card.setPreferredSize(new Dimension(280, 220));
            String imagePath = venue[3];  
            JPanel overlayPanel = new ImagePanel(imagePath, venue[0], venue[1]);
            overlayPanel.setLayout(new BorderLayout());

            JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 0, 0));
            JButton bookingButton = new JButton("Booking");
            JButton priceButton = new JButton(venue[2]); 

            bookingButton.setBackground(new Color(126, 87, 194));
            bookingButton.setForeground(Color.WHITE);
            priceButton.setBackground(new Color(76, 175, 80));
            priceButton.setForeground(Color.WHITE);

            final String eventName = venue[0];
            final double eventPrice = Double.parseDouble(venue[2].replaceAll("[^0-9.]", ""));

            bookingButton.addActionListener(e -> {
                new Booking(eventName, eventPrice); 
            });

            buttonPanel.add(bookingButton);
            buttonPanel.add(priceButton);

            card.add(overlayPanel, BorderLayout.CENTER);
            card.add(buttonPanel, BorderLayout.SOUTH);
            eventPanel.add(card);
        }

        JScrollPane scrollPane = new JScrollPane(eventPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        mainPanel.add(navBar, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        frame.add(mainPanel);
        frame.setVisible(true);
    }
    static class ImagePanel extends JPanel {
        private Image image;
        private String title;
        private String description;

        public ImagePanel(String imagePath, String title, String description) {
            this.image = new ImageIcon(Venue.class.getResource(imagePath)).getImage();
            this.title = title;
            this.description = description;
        }
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));
            g2d.setColor(Color.BLACK);
            g2d.fillRect(0, 0, getWidth(), getHeight());
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
            g2d.setColor(Color.WHITE);
            g2d.setFont(new Font("Arial", Font.BOLD, 18));

            FontMetrics fm = g2d.getFontMetrics();
            int titleWidth = fm.stringWidth(title);
            int xTitle = (getWidth() - titleWidth) / 2;
            int yTitle = getHeight() / 3;
            g2d.drawString(title, xTitle, yTitle);

            JLabel descriptionLabel = new JLabel(description);
            descriptionLabel.setForeground(Color.WHITE);
            descriptionLabel.setFont(new Font("Arial", Font.PLAIN, 14));
            descriptionLabel.setHorizontalAlignment(SwingConstants.CENTER);
            descriptionLabel.setBounds(0, yTitle + 25, getWidth(), getHeight() - (yTitle + 25));

            this.add(descriptionLabel);
        }
    }

    private static void openHomePage() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                Event_Planner.main(new String[0]);
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