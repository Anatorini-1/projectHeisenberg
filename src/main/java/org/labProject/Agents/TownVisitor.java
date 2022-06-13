package org.labProject.Agents;

import org.labProject.Buildings.Building;
import org.labProject.Core.Map;
import org.labProject.Core.Parameters;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TownVisitor extends Citizen{
    private int spawnTime;
    private int lifeTime;
    public static List<TownVisitor> listOfTownVisitors = new ArrayList<>();

    private Building edgeStreet(Map map){
       int mapSize = map.gridSize-1;
       return (Building) map.toRender.get(((int) (Math.random() * (2) + 1) == 1) ? 0 : mapSize).get(((int) (Math.random() * (2) + 1) == 1) ? 0 : mapSize);
    }

    public static void newTownVisitors(Map map){
        int time = Parameters.currentTime%1440; //Current time during day
        if(time==1){
            if(!listOfTownVisitors.isEmpty()) {
                for (TownVisitor x : listOfTownVisitors) {
                    map.units.remove(x);
                }
                listOfTownVisitors.clear();
            }
            for(int i = 0; Parameters.visitorsPerDay > i; ++i){
                listOfTownVisitors.add(new TownVisitor());
                map.units.add(listOfTownVisitors.get(i));
                System.out.println("Miecio has arrived"+String.valueOf(Parameters.currentTime/(24*60)));
            }
        }
    }

    @Override
    public void action( Map map) {
        int time = Parameters.currentTime%1440; //Current time during day

            if(time == this.spawnTime){
                this.currentLocation = edgeStreet(map);
                this.home = edgeStreet(map);
            }else if(time < (this.spawnTime + this.lifeTime - Parameters.mapSize*3 + 1) && time > this.spawnTime){
                randomMovement(map);
            }else if(this.currentLocation!=null){
                goToStreetLocation(map, this.home);
                if(this.currentLocation.equals(this.home)){
                    this.currentLocation = null;
                }
            }
    }

    @Override
    public void create() {}

    @Override
    public void delete() {}

    public TownVisitor(){
        super();
        this.c = Color.red;
        this.spawnTime = (int) (Math.random() * (1200-1)+1);
        this.lifeTime = (int) (Math.random() * ((1440-this.spawnTime)-200)+200);
    }
}
