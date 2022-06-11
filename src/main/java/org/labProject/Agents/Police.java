package org.labProject.Agents;

import org.labProject.Buildings.PoliceStation;
import org.labProject.Core.Map;
import org.labProject.Core.Parameters;


import java.awt.*;

public class Police extends Citizen{
    private int perception;
    private int stubbornes;
    private int morale;
    private PoliceStation parentStation;

    @Override
    public void create() {}

    @Override
    public void delete() {}

    @Override
    public void action(Map map) {
        int time = Parameters.currentTime%1440; //Current time during day
        if(time!=0) {
            int whenPatrol = (int) Math.floor(1440 / parentStation.patrolsPerDay);
            if (time % whenPatrol < whenPatrol - 60) {
                this.randomMovement(map);
                //Catching the weed addicted
                int toSearch = (int) (Math.random() * 100);
                if(this.currentLocation.guests.size() > 1 && toSearch < 11){
                    for (Citizen citizen : this.currentLocation.guests) {
                        int toBribe = (int) (Math.random() * 100);
                        if(citizen.inventory.size() > 0 && toBribe < morale && citizen.inventory.get(0).quantity > 0){
                            map.units.remove(citizen);
                        }
                        else if(citizen.inventory.size() > 0 && toBribe > morale && citizen.inventory.get(0).quantity > 0){citizen.budget = citizen.budget - 10;}
                    }
                }
            } else{
                this.goLocation(map, this.parentStation);
            }
        }else{
            this.randomMovement(map);
        }
    }

    public Police(int p,int s,int m, PoliceStation ps){
        this.c = Color.BLUE;
        this.parentStation = ps;
        this.morale = m;
        this.stubbornes = s;
        this.perception = p;
    }
}
