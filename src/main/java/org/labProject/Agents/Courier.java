package org.labProject.Agents;

import org.labProject.Buildings.Building;
import org.labProject.Buildings.MobHeadquarters;
import org.labProject.Core.Map;
import org.labProject.Core.Parameters;

import java.awt.*;

public class Courier extends Citizen{
    private int speed;
    private int perception;
    private MobHeadquarters mob;
    public Courier(int speed, int perception, MobHeadquarters mob){
        super();
        this.c = Color.GREEN;
        this.speed = speed;
        this.perception = perception;
        this.mob = mob;
    }
    @Override
    public void action(Map map) {
        int time = Parameters.currentTime%1440; //Current time during day
        if(time%144<60){
            goLocation(map, home);
        }else{
            goLocation(map, mob);
        }
    }

    @Override
    public void create() {}

    @Override
    public void delete() {}

}
