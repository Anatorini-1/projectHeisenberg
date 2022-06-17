package org.labProject.Agents;

import org.labProject.Buildings.MobHeadquarters;
import org.labProject.Buildings.Plantation;
import org.labProject.Core.Map;

import java.awt.*;

/**
 * This implementation of the {@link Citizen} superclass represents an agent tasked
 * with delivering the product between the location where it is produced, {@link Plantation}, and {@link MobHeadquarters}, where it is
 * distributed to {@link Dealer} class agents.
 */
public class Courier extends Citizen{

    /**
     * A {@link MobHeadquarters}  this Courier is assigned to
     */
    private MobHeadquarters mob;

    /**
     *
     * @param mob  A {@link MobHeadquarters}  this Courier is assigned to
     */
    public Courier(MobHeadquarters mob){
        super();
        this.c = Color.MAGENTA;
        this.mob = mob;
        this.inventory.add(new Item(0,0,"Weed"));
        this.carryCapacity =  (int) (Math.random() * (100 - 50) + 50);
    }

    /**
     * The behavior of this agent goes as follows:<br />
     * <ul>
     *     <li>If there is enough product in {@link MobHeadquarters}, the Courier simply waits around</li>
     *     <li>If there is a need to resupply, the Courier goes to the {@link Plantation}
     *          <ul>
     *              <li> If the plantation has enough product, Courier stocks up and returns to {@link MobHeadquarters}</li>
     *              <li> If there is not enough product, Courier waits until there is, and resumes his actions</li>
     *          </ul>
     *     </li>
     * </ul>
     * @param map An anchor to the {@link Map} object
     */
    @Override
    public void action(Map map) {
        if(this.mob == null) this.mob = map.mob;
        if(this.mob.delivery(this) > 0 && this.inventory.get(0).quantity == 0 && this.currentLocation == this.home){
            int quantity = this.mob.delivery(this);
            if(quantity>0){
                Plantation home = (Plantation) this.home;
                this.inventory.get(0).quantity = home.handingProduct(quantity);
            }
        }
        if(this.inventory.get(0).quantity > 0 && this.currentLocation != mob) goLocation(map, mob);
        else if(this.inventory.get(0).quantity>0 && this.currentLocation == mob) this.mob.handingProduct(this);
        else goLocation(map, home);
    }

}
