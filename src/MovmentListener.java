import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MovmentListener implements KeyListener {
    private Player player;
    private MainScene mainScene;


    public MovmentListener(MainScene mainScene,Player player){
        this.player = player;
        this.mainScene = mainScene;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W){
            mainScene.setUpPressed(true);
        }
        if (e.getKeyCode() == KeyEvent.VK_D){
            mainScene.setRightPressed(true);
        }
        if (e.getKeyCode() == KeyEvent.VK_S){
            mainScene.setDownPressed(true);
        }
        if (e.getKeyCode() == KeyEvent.VK_A){
            mainScene.setLeftPressed(true);
        }
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
            mainScene.setPaused(true);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W){
            mainScene.setUpPressed(false);
        }
        if (e.getKeyCode() == KeyEvent.VK_D){
            mainScene.setRightPressed(false);
        }
        if (e.getKeyCode() == KeyEvent.VK_S){
            mainScene.setDownPressed(false);
        }
        if (e.getKeyCode() == KeyEvent.VK_A) {
            mainScene.setLeftPressed(false);
        }
    }
}
