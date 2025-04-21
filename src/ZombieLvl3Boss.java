public class ZombieLvl3Boss extends Zombie{

    public ZombieLvl3Boss(int x, int y, int width, int height){
        super(x, y, width, height, 50, 50, 50, 100,
                0.26, 116, 116, 100,
                ImageManager.loadZombieLvl3BossImage(),
                ImageManager.loadZombieLvl3BossDeathImage());
    }
}
