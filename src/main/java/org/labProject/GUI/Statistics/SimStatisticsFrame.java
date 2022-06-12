package org.labProject.GUI.Statistics;

import org.labProject.Core.Parameters;
import org.labProject.Core.StatisticsAggregator;

import javax.swing.*;
import java.awt.*;

public class SimStatisticsFrame extends JPanel {
    private SimStatChart simStatChart;
    private boolean showChart = false;
    public SimStatisticsFrame(){
        setPreferredSize(new Dimension(700, Parameters.mapWindowSize+50));
        setBackground(Color.white);
        setBorder(BorderFactory.createLineBorder(Color.white,3,true));
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        add(new SimClock());
        simStatChart = new SimStatChart("dummyData1","time","value", StatisticsAggregator.dummyStat1,600,300);

        var btn = new JButton("123");
       add(simStatChart);
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
    }
}
