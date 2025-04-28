import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class RulesMenu extends JPanel {
public static final String introText =
            "                 Survive as long as you can.\n" +
            "Move with W, A, S, D – shoot with your mouse.\n" +
            "                 Press ESC to pause the game.\n" +
            "           Stay sharp. Every second counts.\n" +
            "                        Good luck out there!";

    private JLabel rulesHeadline;
    private JTextArea rulesDescription;


    public RulesMenu(int x, int y, int width, int height){
        Font headLineFont = null;
        Font descriptionFont = null;
        try {
            headLineFont = Font.createFont(Font.TRUETYPE_FONT, new File("resources/Fonts/MeltedMonster.ttf")).deriveFont(55f);
            descriptionFont = Font.createFont(Font.TRUETYPE_FONT, new File("resources/Fonts/GoodBrush.ttf")).deriveFont(38f);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
            headLineFont = new Font("Arial", Font.BOLD, 40);
            descriptionFont = new Font("Arial", Font.BOLD, 30);
        }

        ImageManager.loadRulesBackground();


        this.setBounds(x, y, width, height);
        this.setLayout(null);
        ImageIcon backIcon = new ImageIcon("resources/RulesMenu/backButton.png");

        this.rulesHeadline = new JLabel();
        this.rulesHeadline.setBounds(width / 2 - 205, 340, 400, 50);
        this.rulesHeadline.setText("Survival Guide");
        this.rulesHeadline.setForeground(new Color(0x7E7811));
        this.rulesHeadline.setFont(headLineFont);


        this.rulesDescription = new JTextArea();
        this.rulesDescription.setFocusable(false);
        this.rulesDescription.setEditable(false);
        this.rulesDescription.setOpaque(false);
        this.rulesDescription.setText(introText);
        this.rulesDescription.setForeground(new Color(0xBFB4B4));
        this.rulesDescription.setBounds(width/2 - 380,420,1500,300);
        this.rulesDescription.setFont(descriptionFont);

        this.add(this.rulesHeadline);
        this.add(this.rulesDescription);

        JButton backButton = new JButton(backIcon);
        backButton.setBounds(0, getHeight() - 140, 230, 90);
        backButton.setBorderPainted(false);
        backButton.setContentAreaFilled(false);
        backButton.setFocusPainted(false);
        backButton.setOpaque(false);
        backButton.setRolloverIcon(new ImageIcon("resources/RulesMenu/2ndBackButton.png"));

        backButton.addActionListener(e -> {
            JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
            topFrame.getContentPane().removeAll();
            MainMenu mainMenu = new MainMenu(0, 0, topFrame.getWidth(), topFrame.getHeight());
            topFrame.add(mainMenu);
            topFrame.revalidate();
            topFrame.repaint();
            mainMenu.requestFocusInWindow();
        });

        this.add(backButton);
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        BufferedImage rulesBackground = ImageManager.getRulesBackground();
        if (rulesBackground != null) {
            g.drawImage(rulesBackground, 0, 0, null);
        }

    }

}
