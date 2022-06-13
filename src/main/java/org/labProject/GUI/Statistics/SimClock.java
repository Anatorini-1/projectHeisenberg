package org.labProject.GUI.Statistics;

import org.labProject.Core.Parameters;
import org.labProject.Core.StatisticsAggregator;

import javax.swing.*;
import java.awt.*;

public class SimClock extends JPanel {
    public SimClock(){
        setBackground(Color.white);
        setPreferredSize(new Dimension(350,200));
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
        //g2d.drawLine(75,75,(int)(72 - (60*(Math.cos(Math.PI/2+Math.PI/180*(360 * ((float)(Parameters.currentTime%60)/60)))))),(int)(72 - (60*(Math.sin(Math.PI/2+Math.PI/180*(360 * ((float)(Parameters.currentTime%60)/60)))))));
        g2d.drawLine(75,75,(int)(75 + (int)(70*Math.cos((float)Parameters.currentTime%60/60*Math.PI*2-Math.PI/2-0.1))),(int)(75 + (int)(70*Math.sin((float)Parameters.currentTime%60/60*Math.PI*2-Math.PI/2))));
        g2d.drawLine(75,75,(int)(75 + (int)(50*Math.cos((float)(Parameters.currentTime+360)%720/720*Math.PI*2-Math.PI/2))),(int)(75 + (int)(50*(-1)* Math.sin((float)Parameters.currentTime%720/720*Math.PI*2-Math.PI/2))));
        //g2d.drawLine(75,75,(int)(72 - (20*(Math.cos(Math.PI/2+Math.PI/180*(360 * ((float)(Parameters.currentTime%720)/720)))))),(int)(72 - (20*(Math.sin(Math.PI/2+Math.PI/180*(360 * ((float)(Parameters.currentTime%720)/720)))))));
        int time = Parameters.currentTime;
        String dateTimeString = (time / 60 + 6) % 24 +
                ":" +
                time % 60;

        g2d.setFont(g2d.getFont().deriveFont(20.0f));
        g2d.drawString(dateTimeString,30,200);
        g2d.drawString("Day " +
                (time / 60 / 24),30,230);
        g2d.setFont(g2d.getFont().deriveFont(20.0f));
        g2d.setColor(Color.black);
        g2d.drawString("Police statistics (caught/arrested):\n",330,100);
        g2d.drawString("\n|Dealers "+String.valueOf(StatisticsAggregator.caughtDealers)+"/"+String.valueOf(StatisticsAggregator.arrestedDealers),330,130);
        g2d.drawString("\n|Citizens "+String.valueOf(StatisticsAggregator.caughtCitizens)+"/"+String.valueOf(StatisticsAggregator.arrestedCitizens),330,160);
        g2d.drawString( "\n|Couriers "+String.valueOf(StatisticsAggregator.caughtCouriers)+"/"+String.valueOf(StatisticsAggregator.arrestedCouriers),330,190);
    }
}
