package org.labProject.GUI.Controls;

import org.labProject.Core.Parameters;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GuiToggle extends JPanel {
    private boolean isHovered = false;
    public GuiToggle(){
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                Parameters.showGui = !Parameters.showGui;
                repaint();
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                isHovered = true;
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                isHovered = false;
                repaint();
            }
        });
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(55,55));
        setAlignmentX(JPanel.LEFT_ALIGNMENT);
    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        var g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        if(!Parameters.showGui){
            g2d.setColor(Color.green);
        }
        else{
            g2d.setColor(Color.red);
        }
        if(isHovered){
            g2d.setColor(g2d.getColor().darker());
        }
        g2d.fillOval(1,1,47,47);

        g2d.setColor(Color.black);
        g2d.setStroke(new BasicStroke(2));
        g2d.drawOval(2,2,45,45);

    }
}
