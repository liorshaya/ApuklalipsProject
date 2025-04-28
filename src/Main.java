import javax.swing.*;

public class Main {
    public static final int WINDOW_WIDTH = 1280;
    public static final int WINDOW_HEIGHT = 720;


    public static void main(String[] args) {
        JFrame window = new JFrame("Apocalypse");
        window.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);
        window.setResizable(false);
        window.setLayout(null);


        MainMenu mainMenu = new MainMenu(0,0,WINDOW_WIDTH,WINDOW_HEIGHT);
        window.add(mainMenu);

        ImageManager.loadAll();


        window.setVisible(true);
    }

}