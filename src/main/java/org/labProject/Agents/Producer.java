package org.labProject.Agents;

import org.labProject.Buildings.Plantation;
import org.labProject.Core.Map;
import org.labProject.Core.Parameters;

/**
 * This implementation of the {@link Citizen} superclass represents an agent tasked
 * with producing weed.
 */
public class Producer extends Citizen{

    /**
     * The current experience of the {@link Producer}.
     */
    public double experience;

    /**
     * At the end of the day, the {@link Producer} collects all ready plants from the {@link Plantation}.
     * @param map An anchor to the {@link Map} object
     */
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
