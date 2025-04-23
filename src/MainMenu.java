import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MainMenu extends JPanel {
    private JLabel headLine;


    public MainMenu(int x, int y, int width, int height){
        Font headLineFont = null;
        Font descriptionFont = null;
        try {
            headLineFont = Font.createFont(Font.TRUETYPE_FONT, new File("resources/Fonts/MeltedMonster.ttf")).deriveFont(90f);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
            headLineFont = new Font("Arial", Font.BOLD, 40);
            descriptionFont = new Font("Arial", Font.BOLD, 30);
        }
        ImageIcon startIcon = new ImageIcon("resources/MainMenu/StartButton.png");
        ImageIcon rulesIcon = new ImageIcon("resources/MainMenu/HowToPlayButton.png");


        this.setBounds(x, y, width, height);
        this.setLayout(null);
        ImageManager.loadMenuBackground();


        this.headLine = new JLabel();
        this.headLine.setText("Welcome to Apocalypse!");
        this.headLine.setBounds(width/2-500, 40, 1000, 80);
        this.headLine.setForeground(new Color(0x7E7811));
        this.headLine.setFont(headLineFont);


        JButton startButton = new JButton(startIcon);
        startButton.setBounds(getWidth()/2 - 200, getHeight() - 200, 400, 170);
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




        JButton rulesButton = new JButton(rulesIcon);
        rulesButton.setBounds(0, getHeight() - 140, 230, 90);
        rulesButton.setBorderPainted(false);
        rulesButton.setContentAreaFilled(false);
        rulesButton.setFocusPainted(false);
        rulesButton.setOpaque(false);
        rulesButton.setRolloverIcon(new ImageIcon("resources/MainMenu/2ndHowToPlayButton1.png"));

        rulesButton.addActionListener(e -> {
            JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
            topFrame.getContentPane().removeAll();
            RulesMenu rulesMenu = new RulesMenu(0, 0, topFrame.getWidth(), topFrame.getHeight());
            topFrame.add(rulesMenu);
            topFrame.revalidate();
            topFrame.repaint();
            rulesMenu.requestFocusInWindow();
        });


        this.add(this.headLine);
        this.add(rulesButton);
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