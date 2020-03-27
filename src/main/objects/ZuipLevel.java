package objects;

import java.util.Random;

public class ZuipLevel {
    private int level;

    public ZuipLevel(){
        level = 0;
    }

    public void setLevel(int level){
        this.level = level;
    }

    /**
     * The randomizer for spiesjes
     * @return true means Spies, false is Not spies
     */
    public boolean randomise(){
        Random random = new Random();
        switch(level){
            case 1:
                return random.nextInt(300) == 0;
            case 2:
                return random.nextInt(100) == 0;
            case 3:
                return random.nextInt(25) == 0;
            case 4:
                return random.nextInt(10) == 0;
            default:
                return false;
        }
    }
}
