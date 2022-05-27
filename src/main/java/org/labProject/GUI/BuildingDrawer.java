package org.labProject.GUI;

import javax.swing.*;
import java.awt.*;

public class BuildingDrawer extends JPanel{
    private int x;
    private int y;
    public BuildingDrawer(int x,int y) {
        setPreferredSize(new Dimension(400, 400));
        this.x = x;
        this.y = y;
        setBackground(new Color(0,0,0,0));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        ((Graphics2D) g).setBackground(new Color(0,0,0,0));
        g2d.setBackground(new Color(0,0,0,0));
        // prostokat
        g2d.drawRect(x, y, 380, 380);
        // kolo
        g2d.drawOval(x, y, 380, 380);
        g2d.dispose();
        g.dispose();
    }
}
