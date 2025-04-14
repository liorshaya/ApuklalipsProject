public class ZombieLvl1Boss extends Zombie{

    public ZombieLvl1Boss(int x, int y, int width, int height){
        super(x, y, width, height, 20, 20, 30, 80,
                0.4, 96, 96, 80,
                ImageManager.loadZombieBossLvl1Image(),
                ImageManager.loadZombieDeathBossLvl1Image());
    }
}
