import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class endGameText extends JLabel {

    public endGameText(int x , int y , int width , int height){
        Font endGameFont = null;
        try {
            endGameFont = Font.createFont(Font.TRUETYPE_FONT, new File("resources/Fonts/MeltedMonster.ttf")).deriveFont(100f);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
            endGameFont = new Font("Arial", Font.BOLD, 40);
        }

        this.setFont(endGameFont);
        this.setForeground(new Color(0x320C0C));
        this.setBounds(x,y,width,height);
        this.setText("YOU LOST!");
        this.setVisible(false);
    }

    public void ChangeVisible(boolean visible){
        this.setVisible(visible);
    }
}