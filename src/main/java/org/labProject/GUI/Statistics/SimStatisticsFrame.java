package org.labProject.GUI.Statistics;

import org.labProject.Core.Parameters;
import org.labProject.Core.StatisticsAggregator;

import javax.swing.*;
import java.awt.*;

/**
 * A wrapper class grouping all components related to the statistics of the running simulation
 * @see StatisticsAggregator
 * @see SimClock
 * @see SimProfitChart
 */
public class SimStatisticsFrame extends JPanel {
    public SimStatisticsFrame(){
        setPreferredSize(new Dimension(700, Parameters.mapWindowSize+50));
        setBackground(Color.white);
        setBorder(BorderFactory.createLineBorder(Color.white,3,true));
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        SimProfitChart simStatChart = new SimProfitChart("Economy Statistics", "time", "value", 600, 300);
        add(new SimClock());
        add(simStatChart);
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
    }
}
