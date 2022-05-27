package org.labProject.Core;


import org.labProject.Agents.Citizen;
import org.labProject.GUI.SimMainFrame;
import org.labProject.GUI.SimMapFrame;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Simulation{
    private static Map map;
    private static List<Citizen> units;
    private static SimMainFrame gui;
    private static void init(){
        /*var sc = new Scanner(System.in);
        System.out.println("Map size: ");*/
        //Static values for presentation purposes
        map = new Map(8);
        gui = new SimMainFrame(map);
        units = new ArrayList<>();
    }
    private static void tick(){};
    public static void main(String[] args) throws InterruptedException {
        init();
        while(true){
            Thread.sleep(100);
            units.forEach(Citizen::action);
            gui.refresh();
        }

    }
}
