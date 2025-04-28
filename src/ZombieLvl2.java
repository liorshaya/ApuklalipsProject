public class ZombieLvl2 extends Zombie{

    public ZombieLvl2(int x, int y, int width, int height){
        super(x, y, width, height, 2, 2, 10, 50,
                1.8, 64, 64, 50,
                ImageManager.getZombieLvl2Image(),
                ImageManager.getZombieLvl2DeathImage());
    }

}
