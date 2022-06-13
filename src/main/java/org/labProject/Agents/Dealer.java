package org.labProject.Agents;

import org.labProject.Buildings.Building;
import org.labProject.Buildings.MobHeadquarters;
import org.labProject.Core.Map;
import org.labProject.Core.Parameters;
import org.labProject.Core.StatisticsAggregator;

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
    @Override
    public void action( Map map) {
        int time = Parameters.currentTime%1440; //Current time during day
        if(this.inventory.get(0).quantity != 0 || map.mob.productQuantity >= 50) {
            if (time < 960) {
                if (time % 60 == 1) {
                    if ((int) (Math.random() * 100) + 1 <= 50) {
                        this.location = streetLocation(map);
                    } else {
                        this.location[0] = -1;
                    }
                }
            } else {
                if (time % 60 == 1) {
                    if ((int) (Math.random() * 100) + 1 <= 75) {
                        this.location = streetLocation(map);
                    } else {
                        this.location[0] = -1;
                    }
                }
            }
            if (this.location[0] == -1) {
                this.goLocation(map, home);
            } else {
                goToStreetLocation(map, (Building) map.toRender.get(location[0]).get(location[1]));
                if (this.currentLocation.guests.size() > 1 && this.inventory.get(0).quantity > 0) {
                    for (Citizen citizen : this.currentLocation.guests) {
                        if (citizen.age < 21 && this.morale < 20 && (citizen.getClass().getSimpleName().equals("RegularCitizen") || citizen.getClass().getSimpleName().equals("TownVisitor"))) {
                            sellWeed(citizen);
                        } else if (citizen.age > 20 && (citizen.getClass().getSimpleName().equals("RegularCitizen") || citizen.getClass().getSimpleName().equals("TownVisitor"))) {
                            sellWeed(citizen);
                        }
                    }
                }
                if (this.inventory.get(0).quantity == 0) {
                    this.location[0] = -1;
                }
            }
            if (this.currentLocation == home) {
                this.mob.handingToDealer(this);
            }
        }else{
            this.goLocation(map, this.home);
        }
    }

    //function to sell weed to citizens
    private void sellWeed(Citizen citizen){
        //how much to sell?
        int canBuy = (int)Math.floor(citizen.budget/Parameters.drugSellPrice);
        int sellQuantity = 0;
        int canLift = 0;
        if(citizen.inventory.size() > 0)
            canLift = citizen.carryCapacity - citizen.inventory.get(0).quantity;
        else
            canLift = citizen.carryCapacity;

        if(citizen.getClass().getSimpleName().equals("RegularCitizen")){
            RegularCitizen regularCitizen = (RegularCitizen) citizen;
            if(regularCitizen.addictionLevel > 0 && regularCitizen.addictionLevel < 20)
                sellQuantity = 1;
            else if(regularCitizen.addictionLevel > 19 && regularCitizen.addictionLevel < 40)
                sellQuantity = 2;
            else if(regularCitizen.addictionLevel > 39 && regularCitizen.addictionLevel < 60)
                sellQuantity = 3;
            else if(regularCitizen.addictionLevel > 59 && regularCitizen.addictionLevel < 80)
                sellQuantity = 4;
            else if(regularCitizen.addictionLevel > 79 && regularCitizen.addictionLevel < 101)
                sellQuantity = 5;
        }
        else if(citizen.getClass().getSimpleName().equals("TownVisitor")){
            sellQuantity = 1;
        }
        if(sellQuantity > this.inventory.get(0).quantity)
            sellQuantity = this.inventory.get(0).quantity;
        if(sellQuantity > canBuy)
            sellQuantity = canBuy;
        if(sellQuantity > canLift)
            sellQuantity = canLift;

        int totalSellPrice = Parameters.drugSellPrice * sellQuantity;
        if(sellQuantity > 0){
            StatisticsAggregator.log("soldDrugs", totalSellPrice, Parameters.currentTime);
            if(citizen.getClass().getSimpleName().equals("RegularCitizen")){
                RegularCitizen regularCitizen = (RegularCitizen) citizen;
                double addAddiciton = regularCitizen.addictionLevel*0.1;
                double overflow = regularCitizen.addictionLevel + addAddiciton;
                if(overflow < 100)
                    regularCitizen.addictionLevel += addAddiciton;
            }
            citizen.budget -= totalSellPrice;
            this.budget += totalSellPrice;
                this.inventory.get(0).quantity -= sellQuantity;
                if(citizen.inventory.size() > 0){
                    citizen.inventory.get(0).quantity += sellQuantity;
                }
                else{
                    citizen.inventory.add(new Item(0,sellQuantity,"Weed"));
                }
        }
    }
    @Override
    public void create() {}

    @Override
    public void delete() {}

}
