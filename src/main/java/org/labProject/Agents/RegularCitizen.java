package org.labProject.Agents;

import org.labProject.Core.Map;
import org.labProject.Core.Parameters;

public class RegularCitizen extends Citizen{
    public float addictionLevel,recklessness, lawfulLevel;
    public void action(Map map){
        int time = Parameters.currentTime%1440; //Current time during day
        if(this.goJail == Parameters.currentTime){
            this.goJail = 0;
        }
        if(this.goJail != 0){
            goLocation(map, map.jail);
        }
        else {
            if (time < 960) {
                if (time % 120 == 0) {
                    this.randomMovement = (int) Math.floor(Math.random() * 100) + 1 >= this.age;
                }
            } else {
                if (time % (this.age + (int) Math.floor((float) this.age / 2)) == 0) {
                    this.randomMovement = (int) Math.floor(Math.random() * 100) + 1 < (int) Math.floor(this.addictionLevel / (((float)time - 960) / 60));
                }
            }
            if (this.randomMovement) {
                randomMovement(map);
            } else {
                goLocation(map, this.home);
                if (currentLocation.equals(home)) {
                    if (this.inventory.size() > 0 && this.inventory.get(0).quantity > 0) {
                        this.inventory.get(0).quantity = 0;
                    }
                }
            }
            if (time % 1440 == 0) {
                if (age < 21) {
                    this.budget += (int) Math.floor(Math.random() * 20) + 10;
                } else if (age < 50) {
                    this.budget += (int) Math.floor(Math.random() * 50) + 20;
                } else {
                    this.budget += (int) Math.floor(Math.random() * 70) + 50;
                }
            }
        }
    }
    public RegularCitizen(){
        super();
        this.addictionLevel = (int)Math.floor(Math.random()*100)+1;
        this.recklessness = (int)Math.floor(Math.random()*100)+1;
        this.lawfulLevel = (int)Math.floor(Math.random()*100)+1;
    }

}
