package org.labProject.GUI.Statistics;

import org.labProject.Core.StatisticsAggregator;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class SimChartPanel extends JPanel {
    private List<Integer> dataSet;
    private Dimension dimension;

    public SimChartPanel(List<Integer> dataSet, int width, int height){
        this.dataSet = dataSet;
        this.dimension = new Dimension(width,height);
        this.setPreferredSize(dimension);
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        var g2d = (Graphics2D)g;
        super.paintComponent(g2d);
        g2d.setColor(Color.black);
        /*g2d.scale(1,-1);
        g2d.translate(0, -getHeight());*/

        //drawXAxis
        g2d.drawLine(10,getHeight()/2,getWidth()-20,getHeight()/2);

        //drawYAxis
        g2d.drawLine(20,20,20,getHeight()-20);

        for(int i=1;i<StatisticsAggregator.dummyStat1.size();i++){
            g2d.drawLine(i-1,StatisticsAggregator.dummyStat1.get(i-1)+getHeight()/2,i,StatisticsAggregator.dummyStat1.get(i)+getHeight()/2);
        }


    }
}
