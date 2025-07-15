package org.example.gui;

import java.awt.*;
import javax.swing.*;

public class DotIcon implements Icon {
    private final int size;
    private final Color color;

    public DotIcon(int size, Color color) {
        this.size = size;
        this.color = color;
    }

    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        g.setColor(color);
        g.fillOval(x + (getIconWidth() - size) / 2, y + (getIconHeight() - size) / 2, size, size);
    }

    @Override
    public int getIconWidth() {
        return size;
    }

    @Override
    public int getIconHeight() {
        return size;
    }
}
