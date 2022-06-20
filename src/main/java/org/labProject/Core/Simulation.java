package org.labProject.Core;


import org.labProject.Agents.*;
import org.labProject.GUI.SimMainFrame;


import java.util.List;

/**
 * The main class of our simulation. It holds the whole map and list of agents.
 * Here are all the actions executed.
 */
public class Simulation{
    /**
     * The {@link Map} object of the simulation.
     */
    private static Map map;
    /**
     * List of agents.
     */
    private static List<Citizen> units;
    /**
     * The main frame of the GUI.
     */
    private static SimMainFrame gui = null;
    /**
     * Variable for enabling GUI
     */
    private static boolean showGUI = true;

    /**
     * Method for initializing the simulation.
     */
    public static void init(){
        //Static values for presentation purposes
        map = new Map();
        units = map.units;
        if(gui != null) gui.dispose();
        if(showGUI) gui = new SimMainFrame(map,units);
    }

    /**
     * Method for running actions during one tick of the simulation.
     */
    private static void tick(){
        TownVisitor.newTownVisitors(map);
        for (int i = 0; i < units.size(); i++) {
            if(units.get(i).markedForDeath) {units.get(i).delete();units.remove(i);}
            else units.get(i).action(map);
        }
        Parameters.currentTime++;
    }

    /**
     * Main method of the simulation. Here is the whole code executed.
     * @param args
     * @throws InterruptedException
     */
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
