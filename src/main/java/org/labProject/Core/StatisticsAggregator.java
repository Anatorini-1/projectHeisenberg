package org.labProject.Core;

import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.util.LinkedList;
import java.util.List;

public class StatisticsAggregator {
    public static int caughtCitizens;
    public static int arrestedCitizens;
    public static int caughtDealers;
    public static int arrestedDealers;
    public static int caughtCouriers;
    public static int arrestedCouriers;

    public static XYSeries profit =new XYSeries("Profit");
    public static XYSeries income =new XYSeries("Income");
    public static XYSeries cost = new XYSeries("Cost");
    public static XYSeries loss = new XYSeries("Losses");


    static{
        profit.add(0,0);
        cost.add(0,0);
        loss.add(0,0);
        income.add(0,0);

    }

    public static void log(String stat, int value, int timeStamp){
        switch(stat){
            case "soldDrugs":
                StatisticsAggregator.profit.add(timeStamp,(double)StatisticsAggregator.profit.getY(StatisticsAggregator.profit.getItemCount()-1) + value);
                StatisticsAggregator.income.add(timeStamp,(double)StatisticsAggregator.income.getY(StatisticsAggregator.income.getItemCount()-1) + value);
                break;
            case "drugProductionCost":
                StatisticsAggregator.profit.add(timeStamp,(double)StatisticsAggregator.profit.getY(StatisticsAggregator.profit.getItemCount()-1) - value);
                StatisticsAggregator.cost.add(timeStamp,(double)StatisticsAggregator.cost.getY(StatisticsAggregator.cost.getItemCount()-1) + value);
                break;
            case "losses":
                StatisticsAggregator.profit.add(timeStamp,(double)StatisticsAggregator.profit.getY(StatisticsAggregator.profit.getItemCount()-1) - value);
                StatisticsAggregator.loss.add(timeStamp,(double)StatisticsAggregator.loss.getY(StatisticsAggregator.loss.getItemCount()-1) + value);
            break;
        }
    }
    public static void init(){
        profit.clear();
        income.clear();
        cost.clear();
        loss.clear();
        profit.add(0,0);
        cost.add(0,0);
        loss.add(0,0);
        income.add(0,0);
        caughtCitizens = 0;
        arrestedCitizens = 0;
        caughtDealers = 0;
        arrestedDealers = 0;
        caughtCouriers = 0;
        arrestedCouriers = 0;
    }




}
