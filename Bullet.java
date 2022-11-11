package 项目.shoot;

import javax.swing.*;

/**
 * 子弹
 */
public class Bullet extends FlyObject {

    Bullet(int x, int y) {
        super(x, y, 8, 20, 3);
    }

    @Override
    void step() {
        y -= Math.abs(speed);
    }
    public void doubleStep(){
        y-=Math.abs(speed);
        x-=speed/2;
    }

    @Override
    public ImageIcon getImage() {

        if (this.isLive()) {

            return ImageResources.Bullet;
        }
        return null;
    }


}
