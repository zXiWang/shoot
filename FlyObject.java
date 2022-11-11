package 项目.shoot;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public abstract class FlyObject {
    public static int Live = 1;
    public static int Dead = 0;
    private int state = Live;
    int x;
    int y;
    int width;
    int height;
    int speed;
    int fire;
    int life;

    FlyObject(int width, int height) {
        Random rand = new Random();
//        speed = (int) (Math.random() *2+1);
        x = (int) (Math.random() * (World.WIDTH - width));
        y = -height;
        speed = -2;
        this.width = width;
        this.height = height;
//        x = (int) (Math.random() * 2 + 1);
//        if (x == 1) {
//            x = -width;
//
//        }
//        else {
//            x = World.WIDTH ;
//            speed=-speed;
//        }
//        y = (int) (Math.random() * (World.HEIGHT / 3));
//        if (y <= 100) {
//            x = (int) (Math.random() * (World.WIDTH + width) - width);
//            y=-height;
//        }


    }

    FlyObject(int x, int y, int width, int height, int speed) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.speed = speed;
    }

    public FlyObject shootBullet() {
        return new Bullet(x + width / 2 - 4, y);
    }
    public FlyObject shootDoubleBullet(){
        return new Bullet(x + width-24 , y+width/2);
    }
    public FlyObject shootThreeBullet(){
        return new Bullet(x+14 , y+width/2);
    }


    abstract void step();

    public abstract ImageIcon getImage();

    public boolean isOutOfBounds() {
        return this.y >= World.HEIGHT;
    }

    public boolean isHit(FlyObject other) {
//        int x1 = this.x-other.width;
//        int y1 = this.y-other.height;
//        int x2 = this.x + this.width+other.width;
//        int y2 = this.y + this.height;
//        int x = other.x;
//        int y = other.y;
//
//        return x >= x1 && x <= x2 && y <= y2 && y >= y1;
        int x1 = other.x - this.width / 2;
        int x2 = other.x + other.width + this.width / 2;
        int y1 = other.y - this.height / 2;
        int y2 = other.y + other.height + this.height / 2;
        return this.x + this.width / 2 > x1 && this.x + this.width / 2 < x2
                && this.y + this.height / 2 > y1
                && this.y + this.width / 2 < y2;
    }

    public boolean isHitHero(FlyObject hero) {
        int x1 = this.x-(hero.width)+this.width/2;
        int y1 = this.y- (hero.height)/2;
        int x2 = this.x+this.width/2;
        int y2 = this.y + this.height/2;
        int x = hero.x;
        int y = hero.y;
        return x >= x1 && x <= x2 && y <= y2 && y >= y1;
    }

    public void goDead() {
        this.state = Dead;
    }

    public boolean isDead() {
        return this.state == Dead;
    }

    public boolean isLive() {
        return this.state == Live;
    }

    public void paintImage(Graphics g) {
        if (this.getImage() != null) { //判断当前对象获取的图片不能为空
            ImageIcon s = this.getImage(); //获取当前对象的图片
            //2.绘制的坐标
            s.paintIcon(null, g, this.x, this.y); //绘制当前对象的图片
        }
    }

    public void doubleStep() {

    }
}
