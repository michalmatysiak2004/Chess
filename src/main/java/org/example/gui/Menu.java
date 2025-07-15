package org.example.gui;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.*;
public class Menu extends JFrame {
    private JButton start, setting, exit, about;

    public Menu(){
        super("Menu");
        setLayout(new FlowLayout());
        start = new JButton("    Start Game    ");
        setting = new JButton("   Setting ");
        exit = new JButton(" exit");
        add(start);
        add(setting);
        add(exit);


    }


}
