package pers.loren.coderhub.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class snakeWin extends JPanel implements ActionListener, KeyListener, Runnable {
    private JButton newGame, stopGame;
    private int Grade = 0, Speed = 0;
    private boolean start = false;
    private Random r = new Random();
    private int rx = 0, ry = 0;
    private List<snakeAct> list = new ArrayList<snakeAct>();
    private int temp = 0, tempeat1 = 0, tempeat2 = 0;
    private JDialog dialog = new JDialog();
    private JLabel label = new JLabel("label" + Grade);
    private JButton ok = new JButton("ok");
    private Thread nThread;

    snakeWin() {
        newGame = new JButton("new game");
        stopGame = new JButton("stop game");
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        newGame.addActionListener(this);
        stopGame.addActionListener(this);
        this.addKeyListener(this);
        this.add(newGame);
        this.add(stopGame);
        ok.addActionListener(this);
        dialog.setLayout(new GridLayout(2, 1));
        dialog.add(label);
        dialog.add(ok);
        dialog.setTitle("dialog");
        dialog.setSize(200, 200);
        dialog.setLocation(300, 200);
        dialog.setVisible(false);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawRect(10, 40, 400, 300);
        g.drawString("分数:" + Grade, 150, 20);
        g.drawString("速度:" + Speed, 150, 35);
        g.setColor(new Color(0, 255, 0));
        if (start) {
            g.fillRect(10 + rx * 10, 40 + ry * 10, 10, 10);
            for (snakeAct aList : list) {
                g.setColor(new Color(255, 0, 0));
                g.fillRect(10 + aList.getX() * 10, 40 + aList.getY() * 10, 10, 10);
            }
        }
    }

    private boolean minYes(int x, int y) {
        return maxYes(list.get(0).getX() + x, list.get(0).getY() + y);
    }

    private boolean maxYes(int x, int y) {
        if (x < 0 || x >= 40 || y < 0 || y >= 30) {
            return false;
        }
        for (int i = 0; i < list.size(); i++) {
            if (i > 1 && list.get(0).getX() == list.get(i).getX() && list.get(0).getY() == list.get(i).getY()) {
                return false;
            }
        }
        return true;

    }

    private void eat() {
        if (rx == list.get(0).getX() && ry == list.get(0).getY()) {
            rx = r.nextInt(40);
            ry = r.nextInt(30);
            snakeAct tempAct = new snakeAct();
            tempAct.setX(list.get(list.size() - 1).getX());
            tempAct.setY(list.get(list.size() - 1).getY());
            list.add(tempAct);
            Grade += 100 + 10 * Speed;
            tempeat1 += 1;
            if (tempeat1 - tempeat2 >= 10) {
                tempeat2 = tempeat1;
                if (Speed <= 9) {
                    Speed += 1;
                }
            }
        }
    }

    private void otherMove() {
        snakeAct tempAct;
        for (int i = 0; i < list.size(); i++) {
            if (i == 1) {
                list.get(i).setX(list.get(0).getX());
                list.get(i).setY(list.get(0).getY());
            } else if (i > 1) {
                tempAct = list.get(i - 1);
                list.set(i - 1, list.get(i));
                list.set(i, tempAct);

            }
        }
    }

    public void move(int x, int y) {
        if (minYes(x, y)) {
            otherMove();
            list.get(0).setX(list.get(0).getX() + x);
            list.get(0).setY(list.get(0).getY() + y);
            eat();
            repaint();
        } else {
            nThread = null;
            label.setText("游戏结束! 分数为:" + Grade);
            dialog.setVisible(true);

        }
    }

    public void keyPressed(KeyEvent e) {
        if (start) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_UP:
                    move(0, -1);
                    temp = 1;
                    break;
                case KeyEvent.VK_DOWN:
                    move(0, 1);
                    temp = 2;
                    break;
                case KeyEvent.VK_LEFT:
                    move(-1, 0);
                    temp = 3;
                    break;
                case KeyEvent.VK_RIGHT:
                    move(1, 0);
                    temp = 4;
                    break;
                default:
                    break;
            }
        }

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == newGame) {
            newGame.setEnabled(false);
            start = true;
            rx = r.nextInt(40);
            ry = r.nextInt(30);
            snakeAct tempAct = new snakeAct();
            tempAct.setX(20);
            tempAct.setY(15);
            list.add(tempAct);
            requestFocus(true);
            nThread = new Thread(this);
            nThread.start();
            repaint();
        }
        if (e.getSource() == stopGame) {
            System.exit(0);
        }
        if (e.getSource() == ok) {
            list.clear();
            Grade = 0;
            Speed = 0;
            start = false;
            newGame.setEnabled(true);
            dialog.setVisible(false);
            repaint();
        }

    }

    public void keyReleased(KeyEvent arg0) {
    }

    public void keyTyped(KeyEvent arg0) {
    }

    public void run() {
        while (start) {
            switch (temp) {
                case 1:
                    move(0, -1);
                    break;
                case 2:
                    move(0, 1);
                    break;
                case 3:
                    move(-1, 0);
                    break;
                case 4:
                    move(1, 0);
                    break;
                default:
                    move(1, 0);
                    break;
            }
            Grade += 10 * Speed;
            repaint();
            try {
                Thread.sleep(500 - (50 * Speed));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}