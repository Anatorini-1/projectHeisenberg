package org.labProject.GUI.Statistics;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.labProject.Core.LogEntry;
import org.labProject.Core.StatisticsAggregator;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class SimStatChart extends JPanel {
    public SimStatChart(String label, String xAxisLabel, String yAxisLabel, List<LogEntry> dataSet, int width, int height){
        setPreferredSize(new Dimension(width,height));
        setBackground(new Color(234, 207, 72));
        var collection = new XYSeriesCollection();
        var series = new XYSeries("Operation profit");
        collection.addSeries(series);
        var c = ChartFactory.createXYLineChart("123","123","123",collection, PlotOrientation.VERTICAL,true,true,false);
        var z = new ChartPanel(c);
        add(z);
       // add(new SimChartPanel(dataSet, width, height),constraints);

    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
    }

}
