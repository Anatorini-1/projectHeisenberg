package org.labProject.Agents;

import org.labProject.Buildings.MobHeadquarters;
import org.labProject.Buildings.Plantation;
import org.labProject.Core.Map;
import org.labProject.Core.Parameters;

import java.awt.*;

public class Courier extends Citizen{
    private int speed;
    private int perception;
    int setOffTime;
    private MobHeadquarters mob;
    public Courier(int speed, int perception, MobHeadquarters mob){
        super();
        this.c = Color.GREEN;
        this.speed = speed;
        this.perception = perception;
        this.mob = mob;
        this.inventory.add(new Item(0,0,"Weed"));
        this.carryCapacity =  (int) (Math.random() * (100 - 50) + 50);
    }
    @Override
    public void action(Map map) {
        int time = Parameters.currentTime%1440; //Current time during day
        if(this.mob.delivery(this) > 0 && this.inventory.get(0).quantity == 0 && this.currentLocation == this.home){
            int quantity = this.mob.delivery(this);
            if(quantity>0){
                Plantation home = (Plantation) this.home;
                this.inventory.get(0).quantity = home.handingProduct(quantity);
            }
        }
        if(this.inventory.get(0).quantity > 0 && this.currentLocation != mob){
            goLocation(map, mob);
        }else if(this.inventory.get(0).quantity>0 && this.currentLocation == mob){
            this.mob.handingProduct(this);
        }else{
            goLocation(map, home);
        }
    }

    @Override
    public void create() {}

    @Override
    public void delete() {}

}
