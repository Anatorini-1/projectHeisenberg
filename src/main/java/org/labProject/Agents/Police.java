package org.labProject.Agents;

import org.labProject.Buildings.Building;
import org.labProject.Buildings.PoliceStation;
import org.labProject.Core.Map;
import org.labProject.Core.Parameters;


import java.awt.*;

import static java.lang.Math.floor;

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
