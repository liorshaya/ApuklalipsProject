public class ZombieLvl1 extends Zombie{

    public ZombieLvl1(int x, int y, int width, int height){
        super(x, y, width, height, 4, 4, 10, 52,
                0.5, 64, 64, 52,
                ImageManager.getZombieLvl1(),
                ImageManager.getZombieLvl1Death());
    }

}
