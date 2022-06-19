package org.labProject.Agents;

import org.labProject.Buildings.MobHeadquarters;
import org.labProject.Buildings.PoliceStation;
import org.labProject.Core.Map;
import org.labProject.Core.Parameters;
import org.labProject.Core.StatisticsAggregator;


import java.awt.*;

/**
 * This implementation of the {@link Citizen} superclass represents an agent tasked
 * with catching the {@link Dealer} and punishing {@link RegularCitizen} or {@link TownVisitor}
 * for drug possession. At the end of his watch he returns to his {@link PoliceStation}.
 */
public class Police extends Citizen{
    /**
     * Determines whether the {@link Police} punishes or takes a brobe from {@link RegularCitizen} or {@link TownVisitor}.
     */
    private final int morale;
    /**
     * A {@link PoliceStation}  this  {@link Police} is assigned to.
     */
    private final PoliceStation parentStation;

    /**
     * The behavior of this agent goes as follows:<br />
     * <ul>
     *     <li>If the time is right {@link Police} goes out for a patrol.</li>
     *     <li>If the {@link Police} catches a {@link Citizen} with a funny substation:
     *          <ul>
     *              <li> If the morale of the {@link Police} is bigger than the current corruption he punishes the {@link Citizen}</li>
     *              <li> Else he can get bribed.</li>
     *          </ul>
     *      </li>
     * </ul>
     * @param map An anchor to the {@link Map} object
     */
    @Override
    public void action(Map map) {
        int time = Parameters.currentTime%1440; //Current time during day
        if(time!=0) {
            int whenPatrol = 1440 / parentStation.patrolsPerDay;
            if (time % whenPatrol < whenPatrol - 60) {
                this.randomMovement(map);
                //Catching the weed addicted
                int toSearch = (int) (Math.random() * 100);
                    if (this.currentLocation.guests.size() > 1 && toSearch < 11) {
                        for (Citizen citizen : this.currentLocation.guests) {
                            if (Parameters.permaDeath) {
                                if (citizen.inventory.size() > 0 && Parameters.policeCorruptionLevel < this.morale && citizen.inventory.get(0).quantity > 0)
                                    PunishmentRemove(citizen);
                                else if (citizen.inventory.size() > 0 && Parameters.policeCorruptionLevel > this.morale && citizen.inventory.get(0).quantity > 0) {
                                    if (citizen.budget >= 100)
                                        getBribed(citizen);
                                    else
                                        PunishmentRemove(citizen);
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
            } else{
                this.goLocation(map, this.parentStation);
            }
        }else{
            this.randomMovement(map);
        }
    }

    /**
     * A function which determines the {@link Citizen} subclass and punishes it accordingly. <br>
     * It also aggregates the given statistic.
     * @param map
     * @param citizen
     */
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
                citizen.markedForDeath = true;
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
        }
    }

    /**
     * A function which determines the {@link Citizen} subclass and removes it from the simulation. <br>
     * (PermaDeath ON UwU)<br>
     * It also aggregates the given statistic.
     * @param citizen
     */
    private void PunishmentRemove( Citizen citizen){
        citizen.markedForDeath = true;
        switch (citizen.getClass().getSimpleName()){
            case "RegularCitizen":
                StatisticsAggregator.caughtCitizens += 1;
                StatisticsAggregator.arrestedCitizens += 1;
            case "Dealer":
                StatisticsAggregator.log("losses", citizen.budget, Parameters.currentTime );
                StatisticsAggregator.caughtDealers += 1;
                StatisticsAggregator.arrestedDealers += 1;
                break;
            case "Courier":
                StatisticsAggregator.log("losses", citizen.budget, Parameters.currentTime );
                StatisticsAggregator.caughtCouriers += 1;
                StatisticsAggregator.arrestedCouriers += 1;
                break;
        }
    }

    /**
     * A function which determines the {@link Citizen} subclass and hands a static budged from it to the {@link Police}. <br>
     *  It also aggregates the given statistic.
     * @param citizen
     */
    private void getBribed(Citizen citizen){
        switch (citizen.getClass().getSimpleName()){
            case "RegularCitizen":
                StatisticsAggregator.caughtCitizens += 1;
                citizen.budget = citizen.budget - 100;
                break;
            case "TownVisitor":
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
