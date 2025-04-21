public class ZombieLvl4Boss extends Zombie{

    public ZombieLvl4Boss(int x, int y, int width, int height){
        super(x, y, width, height, 60, 60, 60, 120,
                0.15, 116, 116, 120,
                ImageManager.loadZombieLvl4BossImage(),
                ImageManager.loadZombieLvl4BossDeathImage());
    }

}
