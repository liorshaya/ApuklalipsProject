public class ZombieLvl5Boss extends Zombie{

    public ZombieLvl5Boss(int x, int y, int width, int height){
        super(x, y, width, height, 100, 100, 75, 200,
                0.1, 186, 186, 200,
                ImageManager.getZombieLvl5BossImage(),
                ImageManager.getZombieLvl5BossDeathImage());
    }

}
