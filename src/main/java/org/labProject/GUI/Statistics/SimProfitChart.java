package org.labProject.GUI.Statistics;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeriesCollection;
import org.labProject.Core.StatisticsAggregator;

import javax.swing.*;
import java.awt.*;

public class SimProfitChart extends JPanel {
    private JFreeChart chart;
    private ChartPanel panel;
    public SimProfitChart(String label, String xAxisLabel, String yAxisLabel, int width, int height){
        setPreferredSize(new Dimension(width,height));
        setBackground(new Color(255, 255, 255));
        var x = new XYSeriesCollection();
        x.addSeries(StatisticsAggregator.income);
        x.addSeries(StatisticsAggregator.profit);
        x.addSeries(StatisticsAggregator.cost);
        x.addSeries(StatisticsAggregator.loss);
        var c = ChartFactory.createXYLineChart(label,xAxisLabel,yAxisLabel,x, PlotOrientation.VERTICAL,true,true,false);
        var z = new ChartPanel(c);
        c.setAntiAlias(true);
        add(z);
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
    }
}
