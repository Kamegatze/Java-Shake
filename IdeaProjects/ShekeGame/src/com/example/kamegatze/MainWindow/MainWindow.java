package com.example.kamegatze.MainWindow;

import com.example.kamegatze.GameField.*;
import javax.swing.*;

/**
 * create window
 */
public class MainWindow extends JFrame {
    public MainWindow(){
        setTitle("Змейка");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(320, 345);
        setLocation(400, 400);
        add(new GameField());
        setVisible(true);
    }
}
