import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseListenerEvents extends MouseAdapter {
    private Player player;
    private Bullet[] bullets;

    public MouseListenerEvents(Player player, Bullet[] bullets){
        this.player = player;
        this.bullets = bullets;
    }

    @Override
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
}
