package org.labProject.Core;


import org.labProject.Agents.*;
import org.labProject.GUI.SimMainFrame;


import java.util.List;

public class Simulation{
    private static Map map;
    private static List<Citizen> units;
    private static SimMainFrame gui = null;
    private static boolean showGUI = true;
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
            if(units.get(i).markedForDeath) {units.get(i).delete();units.remove(i);}
            else units.get(i).action(map);
        }
        Parameters.currentTime++;
    }
    public static void main(String[] args) throws InterruptedException {
        init();
        while(true){
                if(units.stream().filter(e -> {return e.getClass().getSimpleName().equals("Dealer");}).toArray().length < 1) Parameters.isOver = true;
                if(!Parameters.isPaused && Parameters.isInitialized && !Parameters.isOver) tick();
                Thread.sleep(1000 / Parameters.tickSpeed);
                if(showGUI) gui.refresh();
        }
    }
}
