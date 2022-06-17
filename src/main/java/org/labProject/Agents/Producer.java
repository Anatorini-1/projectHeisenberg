package org.labProject.Agents;

import org.labProject.Buildings.Plantation;
import org.labProject.Core.Map;
import org.labProject.Core.Parameters;

public class Producer extends Citizen{
    public double experience;

    @Override
    public void action(Map map) {
        int time = Parameters.currentTime%1440; //Current time during day
        if(time%1400==0){
            Plantation plantation = (Plantation) this.home;
            plantation.harvest(this);
        }
    }
    public Producer(){
        this.experience = Math.random();
    }
}
