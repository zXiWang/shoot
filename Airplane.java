package 项目.shoot;

import javax.swing.*;
import java.util.Random;

/**
 * 小敌机
 */
public class Airplane extends FlyObject implements EnemyScore {

    boolean stepState=false;
    Airplane() {
        super(48, 50);
        Random random = new Random();
        if(random.nextBoolean()){
            stepState = true;
        }
    }

    @Override
    void step() {

        if(y>=300){
            if(stepState){
                x+=speed;
            }else {
                x-=speed;
            }
            if (x <= 0 || x + width >= World.WIDTH) {
                speed = -speed;
            }

        }
        y += Math.abs(speed);
    }

    @Override
    public ImageIcon getImage() {
        if (this.isLive()) {
            return ImageResources.AirPlane;
        }
        return null;
    }

    @Override
    public int getScore() {
        return 1;
    }
}
