package com.example.kamegatze.GameField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;
/**
 *  Class for create game field
 */

public class GameField extends JPanel implements ActionListener {
    private final int SIZE = 320;
    private final int DOT_SIZE = 16;// px aplle and node shnake
    private final int ALL_DOTS = 400; // all px
    private Image dot; // image node shnake
    private Image apple; // image apple
    private int appleX; //coordinates apple
    private int appleY;
    private int[] X = new int[ALL_DOTS]; // coordinates shnake
    private int[] Y = new int[ALL_DOTS];
    private int sizeShnake;
    private Timer timer;
    private boolean left = false;
    private boolean right = true;
    private boolean up = false;
    private boolean down = false;
    private boolean inGame = true;
    public GameField(){
        setBackground(Color.black); // color field
        loadImages();
        initGamme(); // draw shake
        addKeyListener(new FieldKeyListener()); // add control shake
        setFocusable(true);
    }
    public void checkApple(){
        if(this.X[0] == this.appleX && this.Y[0] == this.appleY){
            sizeShnake++;
            createApple();
        }
    }
    public void initGamme(){
        this.sizeShnake = 3;
        for(int i = 0; i < this.sizeShnake; i++){
            this.X[i] = 48 - i * this.DOT_SIZE;
            this.Y[i] = 48;
        }
        timer = new Timer(200, this);
        timer.start();
        createApple();
    }
    public void createApple(){
        this.appleX = new Random().nextInt(20) * this.DOT_SIZE;
        this.appleY = new Random().nextInt(20) * this.DOT_SIZE;
    }
    private void loadImages(){
        ImageIcon apple = new ImageIcon("apple.png");
        this.apple = apple.getImage();
        ImageIcon node = new ImageIcon("dot.png");
        this.dot = node.getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(inGame){
            g.drawImage(this.apple, this.appleX, this.appleY, this);
            for (int i = 0; i < this.sizeShnake; i++) {
                g.drawImage(this.dot, this.X[i], this.Y[i], this);
            }
        }
        else{
            String messege = "Game Over";
            g.setColor(Color.WHITE);
            g.drawString(messege, 125, this.SIZE/2);
        }
    }

    public void move(){
        for(int i = sizeShnake; i > 0; i--){
            this.X[i] = this.X[i - 1];
            this.Y[i] = this.Y[i - 1];
        }
        if(this.left){
            this.X[0] -= this.DOT_SIZE;
        }
        if(this.right){
            this.X[0] += this.DOT_SIZE;
        }
        if(this.up){
            this.Y[0] -= this.DOT_SIZE;
        }
        if(this.down){
            this.Y[0] += this.DOT_SIZE;
        }
    }

    public void checkColiziuns(){
        for (int i = sizeShnake; i > 0 ; i--) {
            if(i > 4 && this.X[0] == this.X[i] && this.Y[0] == this.Y[i]){
                this.inGame = false;
            }
        }
        if(this.X[0] > this.SIZE){
            inGame = false;
        }
        if(this.X[0] < 0){
            inGame = false;
        }
        if(this.Y[0] > this.SIZE){
            inGame = false;
        }
        if(this.Y[0] < 0){
            inGame = false;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(inGame){
            checkApple();
            checkColiziuns();
            move();
        }
        repaint();
    }

    /**
     * class for control shake
     */
    class FieldKeyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
            int key = e.getKeyCode();
            if(key == KeyEvent.VK_LEFT && !right){
                left = true;
                up = false;
                down = false;
            }
            if(key == KeyEvent.VK_RIGHT && !left){
                right = true;
                up = false;
                down = false;
            }
            if(key == KeyEvent.VK_UP && !down){
                left = false;
                right = false;
                up = true;
            }
            if(key == KeyEvent.VK_DOWN && !up){
                left = false;
                down = true;
                right = false;
            }
        }
    }
}
