import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class MouseListenerEvents extends MouseAdapter implements MouseMotionListener {
    private Player player;
    private Bullet[] bullets;
    private HudPanel hudPanel;

    public MouseListenerEvents(Player player, Bullet[] bullets){
        this.player = player;
        this.bullets = bullets;
        this.hudPanel = hudPanel;
    }

    public void mousePressed(MouseEvent e) {
        int mouseX = e.getX();
        int mouseY = e.getY();

        for (int i = 0; i < bullets.length; i++) {
            if (bullets[i] == null){
                bullets[i] = new Bullet(player.getPlayerCenterX(), player.getPlayerCenterY(), mouseX, mouseY);
                break;
            }
        }
    }

    public void mouseMoved(MouseEvent e) {
        player.setMouseLocation(e.getPoint());
    }
}
