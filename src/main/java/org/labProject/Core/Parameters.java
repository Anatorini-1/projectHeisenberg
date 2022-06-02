package org.labProject.Core;

public class Parameters {
    public static boolean isPaused = false;
    public static int tickSpeed = 10;
    public static int currentTime = 1;
    public static int mapWindowSize = 600;
    //Police Related Params
    public static int numberOfPoliceStations = 1;
    public static int patrolsPerDayPerStations = 1;
    public static int policeCorruptionLevel = 1;
    //Gang Related Params
    public static int numberOfPlantations = 1;
    public static int drugSellPrice = 1;
    public static int drugOperationRange = 1;
    public static int bossProfitCut = 1;
    //Town related params
    public static int visitorsPerDay = 1;
    public static int townPopulation = 1;


    public static void init(){
        isPaused = false;
        tickSpeed =10;
        currentTime = 1;
    };
    /*
    * more to be added as needed
    * */
}
