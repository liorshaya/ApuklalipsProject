public class ZombieLvl1 extends Zombie{

    public ZombieLvl1(int x, int y, int width, int height){
        super(x, y, width, height, 5, 5, 10, 50,
                0.5, 64, 64, 50,
                ImageManager.loadZombieImage(),
                ImageManager.loadZombieDeathImage());
    }

}
