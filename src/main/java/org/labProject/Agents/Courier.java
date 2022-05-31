package org.labProject.Agents;

import org.labProject.Buildings.Building;
import org.labProject.Buildings.MobHeadquarters;
import org.labProject.Core.Map;

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
        if(this.currentLocation.equals(mob)){}
        else{
            goLocation(this, map, mob);
        }
    }

    @Override
    public void create() {}

    @Override
    public void delete() {}

}
