package 项目.shoot;

import javax.swing.*;

public class Bom extends FlyObject {

    private int bom;

    Bom(int x, int y, int width, int height) {
        super(39, 36);
        if (width == 66 && height == 89) {
            bom = 2;
        } else if (width == 60 && height == 51) {
            bom = 1;
        }
        this.x = x + (this.width) / 2;
        this.y = y + (this.height) / 2;
    }

    @Override
    void step() {

    }

    @Override
    public ImageIcon getImage() {
        return switch (bom) {
            case 0 -> ImageResources.Bom1;
            case 1 -> ImageResources.Bom2;
            case 2 -> ImageResources.Bom3;
            case 3 -> ImageResources.Bom4;
            default -> null;
        };


    }

}
