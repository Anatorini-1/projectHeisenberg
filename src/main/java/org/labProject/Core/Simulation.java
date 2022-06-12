package org.labProject.Core;


import org.labProject.Agents.*;
import org.labProject.GUI.SimMainFrame;

import java.util.Arrays;
import java.util.List;

public class Simulation{
    private static Map map;
    private static List<Citizen> units;
    private static SimMainFrame gui = null;
    private static boolean showGUI = true;
    private static boolean showConsole = false;
    public static void init(){
        //Static values for presentation purposes
        map = new Map();
        units = map.units;
        if(gui != null) gui.dispose();
        if(showGUI) gui = new SimMainFrame(map,units);
    }

    private static void tick(){
        TownVisitor.newTownVisitors(map);
        for (int i = 0; i < units.size(); i++) {
            units.get(i).action(map);


        }
    }
    private static void goBackTick(){
        for (int i = 0; i < units.size(); i++) {
            units.get(i).goLocation(map, units.get(i).home);
        }
    }
    public static void main(String[] args) throws InterruptedException {
        init();
        while(true){
            //Checking if dealer exists or ending simulation
            int dealerExists = 0;
            for (Citizen unit : units) {
                if(unit.getClass().getSimpleName().equals("Dealer")){dealerExists += 1;}
            }
            if(dealerExists == 0){
                System.out.println("Dealer został złapany, koniec symulacji");
                break;
            }

            if(!Parameters.isPaused && Parameters.isInitialized){
                tick();
                Parameters.currentTime++;
            }
            Thread.sleep(1000 / Parameters.tickSpeed);

            if(showGUI){
                gui.refresh();
            }
            //code showing map on console
            if(showConsole){
                for(int i = 0; i < map.gridSize; i++){
                    String[] line = new String[map.gridSize];
                    for(int j = 0; j < map.gridSize; j++) {
                        String buildingName = String.valueOf(map.toRender.get(i).get(j));
                        if (buildingName.contains("Street")) {
                            line[j] = "S";
                        } else {
                            line[j] = "A";
                        }
                    }
                    System.out.println(Arrays.toString(line));
                }
            }
        }
    }
}
