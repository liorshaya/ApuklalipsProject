import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MainMenu extends JPanel {
    public static final String introText =
            "                     Your goal is to survive as long as possible.\n" +
            "              Use the W, A, S, D keys to navigate the battlefield.\n" +
            "  Aim and shoot with your mouse to fend off the zombie horde.\n" +
            "     Every second counts—stay alert and fight for your life!\n" +
            "       Good luck on your journey to survive the apocalypse!";

    private JTextArea rules;
    private JLabel headLine;


    public MainMenu(int x, int y, int width, int height){
        Font headLineFont = null;
        Font descriptionFont = null;
        try {
            headLineFont = Font.createFont(Font.TRUETYPE_FONT, new File("resources/Fonts/MeltedMonster.ttf")).deriveFont(70f);
            descriptionFont = Font.createFont(Font.TRUETYPE_FONT, new File("resources/Fonts/GoodBrush.ttf")).deriveFont(40f);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
            headLineFont = new Font("Arial", Font.BOLD, 40);
            descriptionFont = new Font("Arial", Font.BOLD, 30);
        }
        ImageIcon startIcon = new ImageIcon("resources/MainMenu/startButton.png");


        this.setBounds(x, y, width, height);
        this.setLayout(null);
        ImageManager.loadMenuBackground();


        this.headLine = new JLabel();
        this.headLine.setText("Welcome to Apocalypse!");
        this.headLine.setBounds(width/2-390, 20, 780, 60);
        this.headLine.setForeground(new Color(0x320C0C));
        this.headLine.setFont(headLineFont);

        this.rules = new JTextArea();
        this.rules.setFocusable(false);
        this.rules.setEditable(false);
        this.rules.setOpaque(false);
        this.rules.setText(introText);
        this.rules.setBounds(width/2 - 570,150,1500,250);
        this.rules.setFont(descriptionFont);

        JButton startButton = new JButton(startIcon);
        startButton.setBounds(getWidth()/2 - 460, getHeight() - 200, 921, 170);
        startButton.setBorderPainted(false);
        startButton.setContentAreaFilled(false);
        startButton.setFocusPainted(false);
        startButton.setOpaque(false);
        startButton.setRolloverIcon(new ImageIcon("resources/MainMenu/2ndStartButton.png"));

        // TRY
        startButton.addActionListener(e -> {
            JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
            topFrame.getContentPane().removeAll();
            MainScene mainScene = new MainScene(0, 0, topFrame.getWidth(), topFrame.getHeight());
            topFrame.add(mainScene);
            topFrame.revalidate();
            topFrame.repaint();
            mainScene.requestFocusInWindow();
        });
        startButton.setOpaque(false);
        // TRY

        this.add(this.headLine);
        this.add(this.rules);
        this.add(startButton);


    }




    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        BufferedImage menuBackground = ImageManager.getMenuBackground();
        if (menuBackground != null) {
            g.drawImage(menuBackground, 0, 0, null);
        }

    }
}