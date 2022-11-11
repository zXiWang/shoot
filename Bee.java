package 项目.shoot;

import javax.swing.*;
import java.util.Random;

/**
 * 小蜜蜂
 */
public class Bee extends FlyObject implements EnemyLife {

    Bee() {
        super(60, 51);
    }

    @Override
    void step() {
        if (x <= 0 || x + width >= World.WIDTH) {
            speed = -speed;
        }
        x += speed;
        y += Math.abs(speed * 2);
    }


    @Override
    public ImageIcon getImage() {
        if (this.isLive()) {
            return ImageResources.Bee;
        }
        return null;
    }


    @Override
    public int getLifeOfFire() {
        Random rand = new Random();
        if (rand.nextBoolean()) {
            return 1;
        }
        return 40;
    }

}
