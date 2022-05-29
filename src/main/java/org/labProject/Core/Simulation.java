package org.labProject.Core;


import org.labProject.Agents.*;
import org.labProject.GUI.SimMainFrame;
import org.labProject.Buildings.Building;
import org.labProject.GUI.SimMapFrame;

import javax.swing.*;
import java.security.spec.RSAOtherPrimeInfo;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Simulation{
    private static Map map;
    private static List<Citizen> units;
    private static SimMainFrame gui;
    private static boolean showGUI = true;
    private static boolean showConsole = false;
    private static void init(){
        /*var sc = new Scanner(System.in);
        System.out.println("Map size: ");*/
        //Static values for presentation purposes
        map = new Map(19);

        units = map.units;
        if(showGUI) gui = new SimMainFrame(map,units);
    }

    private static void tick(){
            for (int i = 0; i < units.size(); i++) {
                units.get(i).action(units.get(i), map);

            }
    }
    private static void goBackTick(){
        for (int i = 0; i < units.size(); i++) {
            units.get(i).goLocation(units.get(i), map, units.get(i).home);
        }
    }
    public static void main(String[] args) throws InterruptedException {
        init();
        int t = 0;
        while(true){
            Thread.sleep(100);
            if(t<100) {
                tick();
            }else{
                goBackTick();
                if(t == 150){t = 0;};
            }
            t++;
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
                System.out.println("\n");
                System.out.println(units.get(6).currentLocation.x);
            }
        }
    }
}
