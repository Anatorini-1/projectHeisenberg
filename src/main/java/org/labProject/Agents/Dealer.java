package org.labProject.Agents;

import org.labProject.Buildings.Building;
import org.labProject.Buildings.MobHeadquarters;
import org.labProject.Buildings.Plantation;
import org.labProject.Core.Map;
import org.labProject.Core.Parameters;

import java.awt.*;

/**
 * This implementation of the {@link Citizen} superclass represents an agent tasked
 * with selling the product to {@link RegularCitizen} or {@link TownVisitor}. He stocks up on drugs
 * at the {@link MobHeadquarters} and sells them at the {@link org.labProject.Buildings.Street}.
 */
public class Dealer extends Citizen{

    /**
     * Each product sell makes the {@link Dealer} more proficient, which increases his
     * selling skills.
     */
    private float proficiency;
    /**
     * Determines whether the {@link Dealer} sells drugs to a underage {@link RegularCitizen} or {@link TownVisitor}.
     */
    private final int morale;
    /**
     * Keeps coords of {@link org.labProject.Buildings.Street} where {@link Dealer} goes selling drugs.
     */
    private int[] location = new int[2];
    /**
     * A {@link MobHeadquarters}  this  {@link Dealer} is assigned to.
     */
    private final MobHeadquarters mob;
    public Dealer(int m, MobHeadquarters mob){
        super();
        this.c = Color.ORANGE;
        this.morale = m;
        this.mob = mob;
        this.proficiency = (int) (Math.random() * (100 - 1) + 1);
        this.inventory.add(new Item(0,20,"Weed"));
    }

    /**
     * Similar to {@link Citizen#goLocation(Map, Building)}, but the {@link Dealer} goes to random {@link org.labProject.Buildings.Street}
     * coords and not to a specified {@link Building}.
     * @param map
     * @return
     */
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
    /**
     * The behavior of this agent goes as follows:<br />
     * <ul>
     *     <li>If the time is right {@link Dealer} goes out selling drugs.</li>
     *     <li>If the {@link Dealer} has no drugs in his inventory, he goes to {@link MobHeadquarters}
     *          <ul>
     *              <li> If there is enough drugs in the {@link MobHeadquarters} he stocks up on them and goes selling.</li>
     *              <li> If there is not enough drugs, {@link Dealer} waits until there is, and resumes his actions</li>
     *          </ul>
     *      </li>
     * </ul>
     * @param map An anchor to the {@link Map} object
     */
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

    /**
     * A function to sell weed to {@link RegularCitizen} or {@link TownVisitor}.
     * <ul>
     *         <li>If the time is right {@link Dealer} goes out selling drugs.</li>
     *         <li>The process occurs as follows:
     *                <ol>
     *                    <li> The function determines how much the client can buy.</li>
     *                    <li> It determines the total sell price.</li>
     *                    <li> The proficiency of the {@link Dealer} is risen.</li>
     *                    <li> If it is a {@link RegularCitizen}, his addiction level is risen.</li>
     *                    <li> The money is transferred from the customer to the {@link Dealer}</li>
     *                    <li> The drugs are transferred from the {@link Dealer} to the customer</li>
     *                </ol>
     *          </li>
     *       </ul>
     *
     * @param citizen
     */
    //function to sell weed to citizens
    private void sellWeed(Citizen citizen){
        //do we sell?
        float toSell = (float) ((Math.random() * 100)) + this.proficiency;
        if(citizen.getClass().getSimpleName().equals("RegularCitizen")){
            RegularCitizen regularCitizen = (RegularCitizen) citizen;
            if(regularCitizen.recklessness > toSell)
                return;
        }
        //how much to sell?
        int canBuy = (int) Math.floor((float)citizen.budget / Parameters.drugSellPrice);
        int sellQuantity = 0;
        int canLift;
        if (citizen.inventory.size() > 0)
            canLift = citizen.carryCapacity - citizen.inventory.get(0).quantity;
        else
            canLift = citizen.carryCapacity;

        if (citizen.getClass().getSimpleName().equals("RegularCitizen")) {
            RegularCitizen regularCitizen = (RegularCitizen) citizen;
            if (regularCitizen.addictionLevel > 0 && regularCitizen.addictionLevel < 20)
                sellQuantity = 1;
            else if (regularCitizen.addictionLevel > 19 && regularCitizen.addictionLevel < 40)
                sellQuantity = 2;
            else if (regularCitizen.addictionLevel > 39 && regularCitizen.addictionLevel < 60)
                sellQuantity = 3;
            else if (regularCitizen.addictionLevel > 59 && regularCitizen.addictionLevel < 80)
                sellQuantity = 4;
            else if (regularCitizen.addictionLevel > 79 && regularCitizen.addictionLevel < 101)
                sellQuantity = 5;
        } else if (citizen.getClass().getSimpleName().equals("TownVisitor")) {
            sellQuantity = 1;
        }
        if (sellQuantity > this.inventory.get(0).quantity)
            sellQuantity = this.inventory.get(0).quantity;
        if (sellQuantity > canBuy)
            sellQuantity = canBuy;
        if (sellQuantity > canLift)
            sellQuantity = canLift;

        int totalSellPrice = Parameters.drugSellPrice * sellQuantity;
        if (sellQuantity > 0) {
            this.proficiency += 0.1;
            if (citizen.getClass().getSimpleName().equals("RegularCitizen")) {
                RegularCitizen regularCitizen = (RegularCitizen) citizen;
                double addAddiction = regularCitizen.addictionLevel * 0.1;
                double overflow = regularCitizen.addictionLevel + addAddiction;
                if (overflow < 100)
                    regularCitizen.addictionLevel += addAddiction;
            }
            citizen.budget -= totalSellPrice;
            this.budget += totalSellPrice;
            this.inventory.get(0).quantity -= sellQuantity;
            if (citizen.inventory.size() > 0) {
                citizen.inventory.get(0).quantity += sellQuantity;
            } else {
                citizen.inventory.add(new Item(0, sellQuantity, "Weed"));
            }
        }
    }

}
