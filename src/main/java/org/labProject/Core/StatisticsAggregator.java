package org.labProject.Core;

import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.util.LinkedList;
import java.util.List;

public class StatisticsAggregator {
    public static List<Integer> dummyStat1;

    public static List<LogEntry> soldDrugs;
    public static int caughtCitizens;
    public static int arrestedCitizens;
    public static int caughtDealers;
    public static int arrestedDealers;
    public static int caughtCouriers;
    public static int arrestedCouriers;
    public static List<LogEntry> drugProductionCost;
    public static List<LogEntry> losses;
    static{
        soldDrugs = new LinkedList<>();
        drugProductionCost = new LinkedList<>();
        losses = new LinkedList<>();
    }

    public static void log(String stat, int value, int timeStamp){
        switch(stat){
            case "dummyStat1": dummyStat1.add(value);break;
            case "soldDrugs": soldDrugs.add(new LogEntry(value,timeStamp)); break;
            case "drugProductionCost": drugProductionCost.add(new LogEntry(value,timeStamp)); break;
            case "losses": losses.add(new LogEntry(value,timeStamp)); break;
        }
    }
    public static void init(){
        dummyStat1 = new LinkedList<>();
        dummyStat1.add(0);

    }
}
