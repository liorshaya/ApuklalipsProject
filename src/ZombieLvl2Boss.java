public class ZombieLvl2Boss extends Zombie {

    public ZombieLvl2Boss(int x, int y, int width, int height){
        super(x, y, width, height, 40, 40, 40, 80,
                0.4, 96, 96, 80,
                ImageManager.loadZombieLvl2BossImage(),
                ImageManager.loadZombieLvl2BossDeathImage());
    }

}
