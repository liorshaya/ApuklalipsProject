import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class MouseListenerEvents extends MouseAdapter implements MouseMotionListener {
    private Player player;
    private Bullet[] bullets;
    private HudPanel hudPanel;

    public MouseListenerEvents(Player player, Bullet[] bullets, HudPanel hudPanel){
        this.player = player;
        this.bullets = bullets;
        this.hudPanel = hudPanel;
    }

    public void mousePressed(MouseEvent e) {
        int mouseX = e.getX();
        int mouseY = e.getY();

        for (int i = 0; i < bullets.length; i++) {
            if (bullets[i] == null){
                if (!player.getIsReloading()){
                    bullets[i] = new Bullet(player.getPlayerCenterX(), player.getPlayerCenterY(), mouseX, mouseY);
                    player.shoot();
                    hudPanel.shoot();
                }
                break;
            }
        }
    }

    public void mouseMoved(MouseEvent e) {
        player.setMouseLocation(e.getPoint());
    }
}
