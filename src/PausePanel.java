import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PausePanel extends JPanel {
    private MainScene mainScene;
    public PausePanel(int x , int y , int width , int height, MainScene mainScene){
        this.mainScene = mainScene;

        this.setBounds(x, y, width, height);
        this.setLayout(null);
//        this.setBackground(Color.BLACK);

        ImageManager.loadPauseBackground();

        Font descriptionFont = null;
        try {
            descriptionFont = Font.createFont(Font.TRUETYPE_FONT, new File("resources/Fonts/GoodBrush.ttf")).deriveFont(32f);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
            descriptionFont = new Font("Arial", Font.BOLD, 20);
        }

        JButton button = new JButton();
        button.setBorderPainted(false);
        button.setOpaque(true);
        button.setText("RESUME!");
        button.setBackground(new Color(80, 85, 78));
        button.setBounds(getWidth() / 2 - 130, getHeight() - 80, 260, 50);

        JLabel label = new JLabel("Are you scared? Or...");
        label.setBounds(getWidth() / 2 - 165, getHeight() - 170, 330, 60);
        label.setFont(descriptionFont);

        button.addActionListener(e -> {

            Container parent = this.getParent();
            if (parent != null) {
                parent.remove(this);
                parent.repaint();
                parent.revalidate();
            }

            this.mainScene.setPaused(false);
            this.mainScene.mainGameLoop();
            this.mainScene.zombieSpawner();
        });

        this.add(label);
        this.add(button);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        BufferedImage background = ImageManager.getPauseBackground();
        if (background != null) {
            g.drawImage(background, 0, 0, null);
        }
    }
}
