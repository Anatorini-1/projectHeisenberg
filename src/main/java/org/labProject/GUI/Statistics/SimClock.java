package org.labProject.GUI.Statistics;

import org.labProject.Core.Parameters;

import javax.swing.*;
import java.awt.*;

public class SimClock extends JPanel {
    public SimClock(){
        setBackground(Color.white);
        setPreferredSize(new Dimension(150,250));
        setAlignmentX(JPanel.CENTER_ALIGNMENT);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        var g2d = (Graphics2D)g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setStroke(new BasicStroke(4));
        g2d.setColor(Color.black);
        g2d.drawOval(5,5,140,140);
        g2d.setColor(new Color(20,100,20));
        g2d.fillRect(73,10,5,20);
        g2d.fillRect(73,120,5,20);
        g2d.fillRect(10,73,20,5);
        g2d.fillRect(120,73,20,5);
        g2d.fillOval(72,72,6,6);
        g2d.drawLine(75,75,(int)(72 - (60*(Math.cos(Math.PI/2+Math.PI/180*(360 * ((float)(Parameters.currentTime%60)/60)))))),(int)(72 - (60*(Math.sin(Math.PI/2+Math.PI/180*(360 * ((float)(Parameters.currentTime%60)/60)))))));
        g2d.drawLine(75,75,(int)(72 - (20*(Math.cos(Math.PI/2+Math.PI/180*(360 * ((float)(Parameters.currentTime%720)/720)))))),(int)(72 - (20*(Math.sin(Math.PI/2+Math.PI/180*(360 * ((float)(Parameters.currentTime%720)/720)))))));
        int time = Parameters.currentTime;
        StringBuilder dateTimeString = new StringBuilder();
        dateTimeString.append((time/60)%24);
        dateTimeString.append(":");
        dateTimeString.append(time%60);
        dateTimeString.append(" Day ");
        dateTimeString.append((time/60/24));
        g2d.setFont(g2d.getFont().deriveFont(20.0f));
        g2d.drawString(dateTimeString.toString(),30,200);
    }
}
