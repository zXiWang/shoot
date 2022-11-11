//package 项目.shoot;
//
//import java.awt.event.MouseEvent;
//import java.awt.event.MouseListener;
//
//public class DrawListener implements MouseListener {
//    @Override
//    public void mouseClicked(MouseEvent e) {
//        int x = e.getX();
//        int y = e.getY();
//
//        System.out.println("dianji");
//    }
//
//    @Override
//    public void mousePressed(MouseEvent e) {
//
//    }
//
//    @Override
//    public void mouseReleased(MouseEvent e) {
//
//    }
//
//    @Override
//    public void mouseEntered(MouseEvent e) {
//        System.out.println("进入");
//        notify();
//    }
//
//    @Override
//    public void mouseExited(MouseEvent e) {
//        System.out.println("离开");
//        try {
//            wait();
//        } catch (InterruptedException interruptedException) {
//            interruptedException.printStackTrace();
//        }
//    }
//}
