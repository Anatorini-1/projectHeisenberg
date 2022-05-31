package org.labProject.Agents;

import org.labProject.Buildings.ApartmentBuilding;
import org.labProject.Buildings.Building;
import org.labProject.Buildings.Street;
import org.labProject.Core.Map;
import org.labProject.Core.Parameters;

public class RegularCitizen extends Citizen{
    public float addictionLevel,recklessness,lawfullLevel;
    @Override
    public void create() {}
    @Override
    public void delete() {}

    @Override
    public void action(Map map){
        int time = Parameters.currentTime%1440; //Current time during day
        if(time<960 && time%96 > this.age){
            this.randomMovement(map);
        }else{
            this.goLocation(map, this.home);
        }
    }
    public RegularCitizen(){
        super();
        this.addictionLevel = (int)Math.floor(Math.random()*100)+1;
        this.recklessness = (int)Math.floor(Math.random()*100)+1;
        this.lawfullLevel = (int)Math.floor(Math.random()*100)+1;
    }

}
