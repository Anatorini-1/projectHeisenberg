package org.labProject.Agents;

import org.labProject.Buildings.Building;
import org.labProject.Buildings.MobHeadquarters;
import org.labProject.Core.Map;
import org.labProject.Core.Parameters;

import java.awt.*;

public class Dealer extends Citizen{
    private float profficiency;
    private int perception;
    private int morale;
    private int randomMovement;
    private int[] location = new int[2];
    private MobHeadquarters mob;
    public Dealer(float p,int pe, int m, MobHeadquarters mob){
        super();
        this.c = Color.ORANGE;
        this.perception = pe;
        this.profficiency = p;
        this.morale = m;
        this.mob = mob;
        this.inventory.add(new Item(0,50,"Weed"));
    }

    private int[] streetLocation(Map map){
        int[] coords = new int[2];
        if(this.home.x%3==1){
            if((int) (Math.random() * (2) + 1)>1) {
                coords[0] = Math.max((this.home.x - 1 - (((int) (Math.random() * (Parameters.drugOperationRange) + 1) - 1) * 3)), 0);
            }else {
                coords[0] = Math.min(this.home.x + 2 + (((int) (Math.random() * (Parameters.drugOperationRange) + 1) - 1) * 3), map.toRender.size() - 1);
            }
        }else{
            if((int) (Math.random() * (2) + 1)>1) {
                coords[0] = Math.max((this.home.x - 2 - (((int) (Math.random() * (Parameters.drugOperationRange) + 1) - 1) * 3)), 0);
            }else {
                coords[0] = Math.min(this.home.x + 1 + (((int) (Math.random() * (Parameters.drugOperationRange) + 1) - 1) * 3), map.toRender.size() - 1);
            }
        }
        if(this.home.y%3==1){
            if((int) (Math.random() * (2) + 1)>1) {
                coords[1] = Math.max((this.home.y - 1 - (((int) (Math.random() * (Parameters.drugOperationRange) + 1) - 1) * 3)), 0);
            }else {
                coords[1] = Math.min(this.home.y + 2 + (((int) (Math.random() * (Parameters.drugOperationRange) + 1) - 1) * 3), map.toRender.size() - 1);
            }
        }else{
            if((int) (Math.random() * (2) + 1)>1) {
                coords[1] = Math.max((this.home.y - 2 - (((int) (Math.random() * (Parameters.drugOperationRange) + 1) - 1) * 3)), 0);
            }else {
                coords[1] = Math.min(this.home.y + 1 + (((int) (Math.random() * (Parameters.drugOperationRange) + 1) - 1) * 3), map.toRender.size() - 1);
            }
        }
        return coords;
    }
    private void goSell(Map map){
        if(this.currentLocation.x % 3 == 0 && this.currentLocation.y != this.location[1]) {
            if (this.currentLocation.y > this.location[1]) {
                ((Building) map.toRender.get(this.currentLocation.x).get(this.currentLocation.y)).leave(this);
                ((Building) map.toRender.get(this.currentLocation.x).get(this.currentLocation.y - 1)).enter(this);
            } else{
                ((Building) map.toRender.get(this.currentLocation.x).get(this.currentLocation.y)).leave(this);
                ((Building) map.toRender.get(this.currentLocation.x).get(this.currentLocation.y + 1)).enter(this);
            }
        }else if(this.currentLocation.y % 3 == 0 && this.currentLocation.x != this.location[0]) {
            if (this.currentLocation.x < this.location[0]) {
                ((Building) map.toRender.get(this.currentLocation.x).get(this.currentLocation.y)).leave(this);
                ((Building) map.toRender.get(this.currentLocation.x + 1).get(this.currentLocation.y)).enter(this);
            } else{
                ((Building) map.toRender.get(this.currentLocation.x).get(this.currentLocation.y)).leave(this);
                ((Building) map.toRender.get(this.currentLocation.x - 1).get(this.currentLocation.y)).enter(this);
            }
        }else if(!this.currentLocation.getClass().getSimpleName().equals("Street")){
            this.randomMovement(map);
        }
    }
    @Override
    public void action( Map map) {
        int time = Parameters.currentTime%1440; //Current time during day
        if(time<960) {
            if(time%60==0){
                if((int) (Math.random() * 100) + 1 <= 50) {
                    this.location = streetLocation(map);
                }else{
                    this.location[0] = -1;
                }
            }
        }else{
            if(time%60==0){
                if((int) (Math.random() * 100) + 1 <= 75) {
                    this.location = streetLocation(map);
                }else{
                    this.location[0] = -1;
                }
            }
        }
        if(this.location[0] == -1){
            this.goLocation(map, home);
        }else{
            goSell(map);
            if(this.currentLocation.guests.size() > 1 && this.inventory.get(0).quantity > 0){
                for (Citizen citizen : this.currentLocation.guests) {
                   if(citizen.age < 21 && this.morale < 20){sellWeed(citizen);}
                   else if(citizen.age > 20){sellWeed(citizen);}
                }
            }
            if(this.inventory.get(0).quantity == 0){
                this.location[0] = -1;
            }
        }
        if(this.currentLocation == home){
            this.mob.handingToDealer(this);
        }
    }

    //function to sell weed to citizens
    private void sellWeed(Citizen citizen){
        if(citizen.getClass().getSimpleName().equals("RegularCitizen") && citizen.budget > 5){
            citizen.budget -= 5;
            this.budget += 5;
            this.inventory.get(0).quantity -= 5;
            if(citizen.inventory.size() > 0){
                citizen.inventory.get(0).quantity += 5;
            }
            else{
                citizen.inventory.add(new Item(0,5,"Weed"));
            }
        }
    }
    @Override
    public void create() {}

    @Override
    public void delete() {}

}
