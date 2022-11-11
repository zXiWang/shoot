package 项目.shoot;

import javax.swing.*;

/**
 * 天空
 */
public class Sky extends FlyObject {


    Sky(int y) {
        super(0, -700, 400, 700, 1);
        this.y = y;
    }

    @Override
    void step() {
        y += speed;
    }

    @Override
    public ImageIcon getImage() {
        return ImageResources.Sky;
    }


}
