package org.labProject.Core;

public class Parameters {
    public static boolean isPaused = false;
    public static int tickSpeed = 10;
    public static int currentTime = 1;
    public static int mapWindowSize = 900;
    //Police Related Params
    public static int numberOfPoliceStations = 1;
    public static int patrolsPerDayPerStations = 1;
    public static int policeCorruptionLevel = 1;
    public static int policemanPerStation= 2;
    public static int dealerCount = 3;
    //Gang Related Params
    public static int numberOfPlantations = 1;
    public static int drugSellPrice = 1;
    public static int drugOperationRange = 4;
    public static int bossProfitCut = 1;
    //Town related params
    public static int visitorsPerDay = 1;
    public static int townPopulation = 10;

    public static boolean isInitialized = false;

    public static int mapSize = 10;

    public static boolean permaDeath = false;

    public static void init(){
        isPaused = false;
        tickSpeed =10;
        currentTime = 1;
        isInitialized = true;

    };
    /*
    * more to be added as needed
    * */
}
