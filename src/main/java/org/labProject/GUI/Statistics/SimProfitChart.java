package org.labProject.GUI.Statistics;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.labProject.Core.LogEntry;
import org.labProject.Core.Parameters;
import org.labProject.Core.StatisticsAggregator;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class SimProfitChart extends JPanel {
    private static XYSeriesCollection mainCollection = new XYSeriesCollection();
    private static XYSeries profit = new XYSeries("Profit");
    private static XYSeries cost = new XYSeries("Cost");
    private static XYSeries loss = new XYSeries("Loss");
    private static XYSeries income = new XYSeries("Income");
    private static int incomeSum;
    private static int costSum;
    private static int lossSum;
    static {
        profit.add(0,0);
        cost.add(0,0);
        loss.add(0,0);
        income.add(0,0);
        mainCollection.addSeries(profit);
        mainCollection.addSeries(cost);
        mainCollection.addSeries(loss);
        mainCollection.addSeries(income);
        incomeSum = 0;
        costSum = 0;
        lossSum = 0;
    }
    private JFreeChart chart;
    private ChartPanel panel;
    public SimProfitChart(String label, String xAxisLabel, String yAxisLabel, int width, int height){
        setPreferredSize(new Dimension(width,height));
        setBackground(new Color(234, 207, 72));
        chart = ChartFactory.createXYLineChart(label,xAxisLabel,yAxisLabel,mainCollection, PlotOrientation.VERTICAL,true,true,false);
        panel = new ChartPanel(chart);
        if(Parameters.currentTime%60 == 0){
            StatisticsAggregator.drugProductionCost.forEach(entry -> {
                if(entry.timeStamp > (Parameters.currentTime - 60)){
                    costSum += entry.value;
                    cost.add(entry.timeStamp,costSum);
                }
            });
            StatisticsAggregator.soldDrugs.forEach(entry -> {
                if(entry.timeStamp > (Parameters.currentTime - 60)){
                    incomeSum += entry.value;
                    income.add(entry.timeStamp,costSum);
                }
            });
            StatisticsAggregator.losses.forEach(entry -> {
                if(entry.timeStamp > (Parameters.currentTime - 60)){
                    lossSum += entry.value;
                    loss.add(entry.timeStamp,costSum);
                }
            });
            profit.add(Parameters.currentTime,incomeSum-costSum-lossSum);
            mainCollection.removeAllSeries();
            mainCollection.addSeries(profit);
            mainCollection.addSeries(cost);
            mainCollection.addSeries(loss);
            mainCollection.addSeries(income);

        }
        if(panel != null)
            add(panel);
       // add(new SimChartPanel(dataSet, width, height),constraints);

    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
    }

}
