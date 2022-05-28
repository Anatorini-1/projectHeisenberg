package org.labProject.Core;


import org.labProject.Agents.Citizen;
import org.labProject.Agents.Item;
import org.labProject.GUI.SimMainFrame;
import org.labProject.Agents.RegularCitizen;
import org.labProject.Agents.SimAgent;
import org.labProject.Buildings.Building;
import org.labProject.GUI.SimMapFrame;

import javax.swing.*;
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
        map = new Map(8);
        units = new ArrayList<>();
        RegularCitizen Citizen1 = new RegularCitizen();
        RegularCitizen Citizen2 = new RegularCitizen();
        RegularCitizen Citizen3 = new RegularCitizen();
        Citizen1.currentLocation = (Building) map.toRender.get(1).get(1);
        Citizen2.currentLocation = (Building) map.toRender.get(1).get(1);
        Citizen3.currentLocation = (Building) map.toRender.get(1).get(1);
        units.add(Citizen1);
        units.add(Citizen2);
        units.add(Citizen3);
        if(showGUI) gui = new SimMainFrame(map,units);
    }

    private static void tick(){
        //only to show results
        int c1[] = new int[2];
        int c2[]= new int[2];
        int c3[]= new int[2];
        c1[0] = units.get(0).currentLocation.x;
        c1[1] = units.get(0).currentLocation.y;
        c2[0] = units.get(1).currentLocation.x;
        c2[1] = units.get(1).currentLocation.y;
        c3[0] = units.get(2).currentLocation.x;
        c3[1] = units.get(2).currentLocation.y;
        if(showConsole){
            System.out.println("Citizen1: " + Arrays.toString(c1));
            System.out.println("Citizen2: " + Arrays.toString(c2));
            System.out.println("Citizen3: " + Arrays.toString(c3));
        }

        //actual code needed
        RegularCitizen.actionn(units.get(0), map);
        RegularCitizen.actionn(units.get(1), map);
        RegularCitizen.actionn(units.get(2), map);
    };
    public static void main(String[] args) throws InterruptedException {
        init();
        while(true){
            Thread.sleep(100);
            tick();
            units.forEach(Citizen::action);
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
            }


        }
    }
}