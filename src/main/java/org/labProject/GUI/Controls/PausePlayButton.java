package org.labProject.GUI.Controls;

import org.labProject.Core.Parameters;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class PausePlayButton extends JPanel {
    private boolean isHovered = false;
    public PausePlayButton(){
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                Parameters.isPaused = !Parameters.isPaused;
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

        if(!Parameters.isPaused){
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
        if(!Parameters.isPaused){
            g2d.fillRect(10,10,10,30);
            g2d.fillRect(30,10,10,30);
        }
        else{
            int[] x = new int[3];
            x[0] = 12;
            x[1] = 45;
            x[2] = 12;

            int[] y = new int[3];
            y[0] = 10;
            y[1] = 25;
            y[2] = 40;

            g2d.fillPolygon(x,y,3);

        }
        g2d.setColor(Color.black);
        g2d.setStroke(new BasicStroke(2));
        g2d.drawOval(2,2,45,45);

    }
}
