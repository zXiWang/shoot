package 项目.shoot;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.MemoryImageSource;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

public class World extends JPanel {
    public static final int WIDTH = 400;
    public static final int HEIGHT = 700;
    public static final int START = 0;
    public static final int RUNNING = 1;
    public static final int PAUSE = 2;
    public static final int GAME_OVER = 3;
    Sky skyOne = new Sky(0);
    Sky skyTwo = new Sky(-700);
    FlyObject[] planes = {};
    FlyObject[] bullets = {};
    FlyObject[] booms = {};
    Hero hero = new Hero();
    int score = 0;
    private int state;
    private int planeEnterIndex = 0;
    private int shootBulletIndex = 0;
    private int bombDisappearsIndex = 0;

    public static void main(String[] args) {
        World world = new World();
        world.paintWorld();
        world.action();
    }

    public FlyObject createPlane() {
        int temp = (int) (Math.random() * 20);
        if (temp < 5) {
            return new Bee();
        } else if (temp < 13) {
            return new Airplane();
        } else return new BigAirplane();
    }

    public void planeEnterAction() {
        planeEnterIndex++;
        if (hero.getFire() >= 200) {
            if (planeEnterIndex % 2 == 0) {
                FlyObject plane = createPlane();
                planes = Arrays.copyOf(planes, planes.length + 1);
                planes[planes.length - 1] = plane;
            }
        } else {
            if (planeEnterIndex % 20 == 0) {
                FlyObject plane = createPlane();
                planes = Arrays.copyOf(planes, planes.length + 1);
                planes[planes.length - 1] = plane;
            }
        }

    }

    public void shootBulletAction() {
        shootBulletIndex++;
        if (hero.getFire() > 1000) {
            if (shootBulletIndex % 2 == 0) {
                createBombs();
            }
        }
        if (hero.getFire() > 500) {
            if (shootBulletIndex % 10 == 0) {
                createBombs();
            }
        } else if (hero.getFire() > 300) {
            if (shootBulletIndex % 20 == 0) {
                createBombs();
            }
        } else if (hero.getFire() > 200) {
            if (shootBulletIndex % 30 == 0) {
                createBombs();
            }
        } else if (hero.getFire() > 100) {
            if (shootBulletIndex % 40 == 0) {
                createBombs();
            }
        } else if (hero.getFire() >= 40 || hero.getFire() <= 40) {
            if (shootBulletIndex % 60 == 0) {
                createBombs();
            }
        }

    }

    private void createBombs() {
        FlyObject bullet;
        if (hero.getFire() >= 200) {
            bullet = hero.shootDoubleBullet();
            bullets = Arrays.copyOf(bullets, bullets.length + 1);
            bullets[bullets.length - 1] = bullet;
            bullet = hero.shootThreeBullet();
            bullets = Arrays.copyOf(bullets, bullets.length + 1);
            bullets[bullets.length - 1] = bullet;
        }
        bullet = hero.shootBullet();
        bullets = Arrays.copyOf(bullets, bullets.length + 1);
        bullets[bullets.length - 1] = bullet;
//        bulletStep(bullet);
    }

    //    public void bulletStep(FlyObject bullet) {
//        for (int i = 0; i < planes.length; i++) {
//            if (bullet.x > planes[0].x) bullet.x -= bullet.speed;
//            else {
//                bullet.x += planes[0].x;
//                if (bullet.y > planes[0].y) bullet.y -= bullet.speed;
//                else {
//                    bullet.y += planes[0].y;
//                }
//
//            }
//        }
//    }
    public void shootBombAction() {
        for (FlyObject bullet : bullets) {
            for (FlyObject plane : planes) {
                if (plane.isLive() && bullet.isLive() && plane.isHit(bullet)) {
                    if (plane instanceof EnemyScore) {
                        EnemyScore es = (EnemyScore) plane;
                        score += es.getScore();
                    }
                    if (plane instanceof EnemyLife) {
                        EnemyLife el = (EnemyLife) plane;
                        int num = el.getLifeOfFire();
                        if (num == 40) {
                            hero.fire += num;
                        } else if (num == 1)
                            hero.addLift(num);
                    }
                    bomBang(plane);
                    plane.goDead();
                    bullet.goDead();

                } else if (plane.isLive() && plane.isHitHero(hero)) {
                    bomBang(plane);
                    plane.goDead();
                    hero.lossLife(1);
                    hero.fire = 0;
                    score -= 10;
                    if (hero.getLife() == 0) {
                        state = GAME_OVER;
                    }
                }
            }

        }
    }

    public void bomBang(FlyObject plane) {
        Bom bom = new Bom(plane.x, plane.y, plane.width, plane.height);
        booms = Arrays.copyOf(booms, booms.length + 1);
        booms[booms.length - 1] = bom;
    }

    public void bomLost() {
        bombDisappearsIndex++;
        for (int i = 0; i < booms.length; i++) {
            if (bombDisappearsIndex % 60 == 0) {
                System.arraycopy(booms, 1, booms, 0, booms.length - 1);
            }
        }
    }

    public void outOfBoundsAction() {
        for (int i = 0; i < planes.length; i++) {
            if (planes[i].isOutOfBounds()) {
                planes[i] = planes[planes.length - 1];
                planes = Arrays.copyOf(planes, planes.length - 1);
            }
        }
        for (int i = 0; i < bullets.length; i++) {
            if (bullets[i].isOutOfBounds()) {
                bullets[i] = bullets[bullets.length - 1];
                bullets = Arrays.copyOf(bullets, bullets.length - 1);
            }
        }
    }

    public void stepAction() {
        for (FlyObject plane : planes) {
            plane.step();
        }
        for (FlyObject bullet : bullets) {
            bullet.step();
//                bullet.doubleStep();
        }
        skyOne.step();
        if (skyOne.y >= 700) skyOne.y = -HEIGHT;
        skyTwo.step();
        if (skyTwo.y == HEIGHT) skyTwo.y = -HEIGHT;
        hero.step();

    }

    void action() {
        MouseAdapter l = new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) { // 鼠标移动
                if (state == RUNNING) { // 运行时移动英雄机
                    int x = e.getX() - hero.width / 2;
                    int y = e.getY() - hero.height / 2;
                    hero.moveTo(x, y);
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) { // 鼠标进入
                if (state == PAUSE) { // 暂停时运行
                    state = RUNNING;
                }
            }

            @Override
            public void mouseExited(MouseEvent e) { // 鼠标退出
                if (state != GAME_OVER) {
                    state = PAUSE; // 游戏未结束，则设置其为暂停
                }
            }

            @Override
            public void mouseClicked(MouseEvent e) { // 鼠标点击
                if (state == RUNNING && !hero.moveState) {
                    hero.moveState = true;
                } else if (state == RUNNING && hero.moveState) {
                    for (FlyObject plane : planes) {
                        bomBang(plane);
                        plane.goDead();
                    }
                    hero.moveState = false;
                }
                switch (state) {
                    case START -> state = RUNNING;
                    case GAME_OVER -> { // 游戏结束，清理现场
                        planes = new FlyObject[0];
                        bullets = new Bullet[0];
                        booms = new Bom[0];
                        hero = new Hero();
                        score = 0;
                        state = START;
                    }
                }

            }
        };
        this.addMouseListener(l); // 处理鼠标点击操作
        this.addMouseMotionListener(l); // 处理鼠标滑动操作
//        KeyAdapter adapter = new KeyAdapter() {
//            @Override
//            public void keyPressed(KeyEvent e) {
//                if (e.getKeyCode() == KeyEvent.VK_A) {
//                    hero.moveLeft();
//                } else if (e.getKeyCode() == KeyEvent.VK_D) {
//                    hero.moveRight();
//                }
//            }
//        };
//        this.addKeyListener(adapter); //将键盘的侦听的具体子类,添加到侦测组件中

        java.util.Timer timer = new Timer(); //创建具体的定时器
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (state != PAUSE && state != GAME_OVER && state != START) {
                    planeEnterAction();
                    shootBulletAction();
                    stepAction();
                    outOfBoundsAction();
                    shootBombAction();
                    bomLost();
                }

                repaint();
            }
        };
        timer.schedule(task, 3000, 10);
    }

    void paintWorld() {
        JFrame frame = new JFrame(); //创建窗口
        setFocusable(true); //可聚焦的
        frame.add(this); //把面板添加到窗口中
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //关闭窗口结束程序
        frame.setSize(WIDTH + 16, HEIGHT + 39); //窗口尺寸
        frame.setLocationRelativeTo(null); //窗口居中呈现
        frame.setVisible(true); //可视化
        Image image = Toolkit.getDefaultToolkit().createImage(new MemoryImageSource(0, 0, new int[0], 0, 0));
        this.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(image, new Point(0, 0), null));

    }

    @Override
    public void paint(Graphics g) {

        skyOne.paintImage(g);
        skyTwo.paintImage(g);
        hero.paintImage(g);

        for (FlyObject plane : planes) {
            plane.paintImage(g);
        }
        for (FlyObject bullet : bullets) {
            bullet.paintImage(g);
        }
        for (FlyObject bom : booms) {
            bom.paintImage(g);
        }
        paintState(g); // 画游戏状态

        g.setFont(new Font("", Font.BOLD, 20));
        g.drawString("分数:" + score, 20, 20);
        g.drawString("生命:" + hero.getLife(), 20, 40);
        g.drawString("火力:" + hero.getFire(), 20, 60);

    }

    private void paintState(Graphics g) {
        ImageIcon s;
        switch (state) {
            case START -> {
                s = ImageResources.start; //获取当前对象的图片
                s.paintIcon(null, g, 0, 0); //绘制当前对象的图片
            }
            case PAUSE -> {
                s = ImageResources.pause;
                s.paintIcon(null, g, 0, 0);
            }
            case GAME_OVER -> {
                s = ImageResources.gameover;
                s.paintIcon(null, g, 0, 0);
            }
        }
    }
}
