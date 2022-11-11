package 项目.shoot;

import javax.swing.*;

/**
 * 大敌机
 */
public class BigAirplane extends FlyObject implements EnemyScore {

    BigAirplane() {
        super(66, 89);
    }

    @Override
    void step() {
        y += Math.abs(speed);
    }

    @Override
    public ImageIcon getImage() {
        if (this.isLive()) {
            return ImageResources.BigAirplane;
        }
        return null;
    }


    @Override
    public int getScore() {
        return 3;
    }
}
