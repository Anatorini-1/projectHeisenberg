package org.labProject.Agents;

import org.labProject.Buildings.PoliceStation;
import org.labProject.Core.Map;
import org.labProject.Core.Parameters;
import org.labProject.Core.StatisticsAggregator;


import java.awt.*;
import java.util.ConcurrentModificationException;

public class Police extends Citizen{
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
            int whenPatrol = 1440 / parentStation.patrolsPerDay;
            if (time % whenPatrol < whenPatrol - 60) {
                this.randomMovement(map);

                //Catching the weed addicted
                int toSearch = (int) (Math.random() * 100);
                try {
                    if (this.currentLocation.guests.size() > 1 && toSearch < 11) {
                        for (Citizen citizen : this.currentLocation.guests) {
                            if (Parameters.permaDeath) {
                                if (citizen.inventory.size() > 0 && Parameters.policeCorruptionLevel < this.morale && citizen.inventory.get(0).quantity > 0)
                                    PunishmentRemove(map, citizen);
                                else if (citizen.inventory.size() > 0 && Parameters.policeCorruptionLevel > this.morale && citizen.inventory.get(0).quantity > 0) {
                                    if (citizen.budget >= 100)
                                        getBribed(citizen);
                                    else
                                        PunishmentRemove(map, citizen);
                                }
                            } else {
                                if (citizen.inventory.size() > 0 && Parameters.policeCorruptionLevel < this.morale && citizen.inventory.get(0).quantity > 0)
                                    Punishment(map, citizen);
                                else if (citizen.inventory.size() > 0 && Parameters.policeCorruptionLevel > this.morale && citizen.inventory.get(0).quantity > 0) {
                                    if (citizen.budget >= 100)
                                        getBribed(citizen);
                                    else
                                        Punishment(map, citizen);
                                }
                            }
                        }
                    }
                }
                catch(Exception e){
                    //Oh no :O
                    //Anyway
                }
            } else{
                this.goLocation(map, this.parentStation);
            }
        }else{
            this.randomMovement(map);
        }
    }

    private void Punishment(Map map, Citizen citizen){
        switch (citizen.getClass().getSimpleName()){
            case "RegularCitizen":
                StatisticsAggregator.arrestedCitizens += 1;
                StatisticsAggregator.caughtCitizens += 1;
                citizen.goJail += Parameters.currentTime + (1440*citizen.inventory.get(0).quantity);
                citizen.inventory.get(0).quantity = 0;
                citizen.budget = 0;
                break;
            case "TownVisitor":
                map.units.remove(citizen);
                break;
            case "Dealer":
                StatisticsAggregator.arrestedDealers += 1;
                StatisticsAggregator.caughtDealers += 1;
                StatisticsAggregator.log("losses", citizen.budget, Parameters.currentTime );
                citizen.inventory.get(0).quantity = 0;
                citizen.budget = 0;
                break;
            case "Courier":
                StatisticsAggregator.arrestedCouriers += 1;
                StatisticsAggregator.caughtCouriers += 1;
                StatisticsAggregator.log("losses", citizen.budget, Parameters.currentTime );
                citizen.inventory.get(0).quantity = 0;
                citizen.budget = 0;
                break;
        };
    }
    private void PunishmentRemove(Map map, Citizen citizen){
        switch (citizen.getClass().getSimpleName()){
            case "RegularCitizen":
            case "TownVisitor":
                StatisticsAggregator.caughtCitizens += 1;
                StatisticsAggregator.arrestedCitizens += 1;
                citizen.currentLocation.leave(citizen);
                map.units.remove(citizen);
                break;
            case "Dealer":
                StatisticsAggregator.log("losses", citizen.budget, Parameters.currentTime );
                StatisticsAggregator.caughtDealers += 1;
                StatisticsAggregator.arrestedDealers += 1;
                citizen.currentLocation.leave(citizen);
                map.units.remove(citizen);
                break;
            case "Courier":
                StatisticsAggregator.log("losses", citizen.budget, Parameters.currentTime );
                StatisticsAggregator.caughtCouriers += 1;
                StatisticsAggregator.arrestedCouriers += 1;
                citizen.currentLocation.leave(citizen);
                map.units.remove(citizen);
                break;
        }
    }

    private void getBribed(Citizen citizen){
        switch (citizen.getClass().getSimpleName()){
            case "RegularCitizen":
                StatisticsAggregator.caughtCitizens += 1;
                citizen.budget = citizen.budget - 100;
                break;
            case "TownVisitor":
               /* StatisticsAggregator.caughtCitizens += 1;*/
                citizen.budget = citizen.budget - 100;
                break;
            case "Dealer":
                StatisticsAggregator.log("losses", 100, Parameters.currentTime );
                StatisticsAggregator.caughtDealers += 1;
                citizen.budget = citizen.budget - 100;
                break;
            case "Courier":
                StatisticsAggregator.log("losses", 100, Parameters.currentTime );
                StatisticsAggregator.caughtCouriers += 1;
                citizen.budget = citizen.budget - 100;
                break;
        }
    }

    public Police(int m, PoliceStation ps){
        this.c = Color.BLUE;
        this.parentStation = ps;
        this.morale = m;
    }
}
