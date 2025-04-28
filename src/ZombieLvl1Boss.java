public class ZombieLvl1Boss extends Zombie{

    public ZombieLvl1Boss(int x, int y, int width, int height){
        super(x, y, width, height, 15, 15, 30, 75,
                0.4, 96, 96, 75,
                ImageManager.getZombieBossLvl1Image(),
                ImageManager.getZombieDeathBossLvl1Image());
    }
}
