package pers.loren.coderhub.game;

import javax.swing.*;


public class snakeMain extends JFrame {
    private snakeMain() {
        snakeWin win = new snakeWin();
        add(win);
        setTitle("贪吃蛇v1.0");
        setSize(435, 390);
        setLocation(200, 200);
        setVisible(true);
    }

    public static void main(String[] args) {
        new snakeMain();
    }

}
