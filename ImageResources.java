package 项目.shoot;

import javax.swing.*;

/**
 * 图片资源类 : 主要用来加载项目中所需要的图片资源
 */
public class ImageResources {
    //ImageIcon 用来存图片类型
    public static ImageIcon AirPlane;
    public static ImageIcon Bee;
    public static ImageIcon gameover;
    public static ImageIcon BigAirplane;
    public static ImageIcon Bullet;
    public static ImageIcon Hero;
    public static ImageIcon Sky;
    public static ImageIcon start;
    public static ImageIcon pause;
    public static ImageIcon HeroFire;
    public static ImageIcon Bom1;
    public static ImageIcon Bom2;
    public static ImageIcon Bom3;
    public static ImageIcon Bom4;

    static { //静态代码块,用于为上面静态变量赋值具体的图片(路径)
        AirPlane = new ImageIcon("src/项目/img/airplane.png");
        Bee = new ImageIcon("src/项目/img/bee.png");
        gameover = new ImageIcon("src/项目/shoot/img/gameover.png");
        BigAirplane = new ImageIcon("src/项目/img/bigairplane.png");
        Bullet = new ImageIcon("src/项目/img/bullet.png");
        Hero = new ImageIcon("src/项目/img/hero0.png");
        HeroFire = new ImageIcon("src/项目/img/hero1.png");
        Sky = new ImageIcon("src/项目/img/background.png");
        start = new ImageIcon("src/项目/shoot/img/start.png");
        pause = new ImageIcon("src/项目/shoot/img/pause.png");
        Bom1 = new ImageIcon("src/项目/img/bom1.png");
        Bom2 = new ImageIcon("src/项目/img/bom2.png");
        Bom3 = new ImageIcon("src/项目/img/bom3.png");
        Bom4 = new ImageIcon("src/项目/img/bom4.png");
    }
}
