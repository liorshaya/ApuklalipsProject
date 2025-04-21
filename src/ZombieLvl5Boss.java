public class ZombieLvl5Boss extends Zombie{

    public ZombieLvl5Boss(int x, int y, int width, int height){
        super(x, y, width, height, 10, 10, 75, 200,
                0.1, 186, 186, 200,
                ImageManager.loadZombieLvl5BossImage(),
                ImageManager.loadZombieLvl5BossDeathImage());
    }

}
