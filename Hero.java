package 项目.shoot;

import javax.swing.*;

/**
 * 英雄机
 */
public class Hero extends FlyObject {
    int fire = 40;
    int life = 4;
    boolean moveState=false;

    Hero() {
        super((World.WIDTH) / 2 - 97 / 2, 500, 97, 139, 20);
    }

//    @Override
//    void step() {
//        x+=speed/20;
//        if(x>=400-width)speed=-speed;
//        else if(x<=0)speed=-speed;
//    }

    @Override
    void step() {
        if(moveState){
            x+=speed;
            if(x>=400-width)speed=-speed;
            else if(x<=0)speed=-speed;
        }

    }

    @Override
    public ImageIcon getImage() {
        if (this.isLive()) {
            if (fire >= 100) return ImageResources.HeroFire;
            return ImageResources.Hero;
        }
        return null;
    }
//    public void moveLeft() {
//        x -= speed;
//    }
//
//    public void moveRight() {
//        x += speed;
//    }

    public int getLife() {
        return life;
    }

    public void addLift(int num) {
        life += num;
    }

    public void lossLife(int num) {
        life -= num;
    }

    public void addFire(int num) {
        fire += num;
    }

    public int getFire() {
        return fire;
    }

    public void moveTo(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
