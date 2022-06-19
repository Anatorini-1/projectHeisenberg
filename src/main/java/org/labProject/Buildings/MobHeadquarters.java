package org.labProject.Buildings;

import org.labProject.Agents.Courier;
import org.labProject.Agents.Dealer;
import org.labProject.Agents.Kingpin;
import org.labProject.Core.Parameters;
import org.labProject.Core.StatisticsAggregator;

import java.awt.*;

/**
 * This implementation of the {@link Building} superclass represents an object which is the Headquarters of the drug mafia.
 * Here take place all the deliveries, handing of products and money allocation.
 */
public class MobHeadquarters extends Building{
    /**
     * Maximum storage capacity of drugs.
     */
    private final int storageCapacity;
    /**
     * Range in which the {@link Dealer} operates.
     */
    public int operationRange;
    /**
     * Current quantity of drugs in storage.
     */
    public int productQuantity;
    /**
     * Total money made by the mafia Boss.
     */
    private int totalMoney;
    /**
     * So, it turns out, this variable is completely pointless. Too bad
     */
    private final Kingpin boss;

    public MobHeadquarters(int x, int y){
        super(x,y, Color.GREEN);
        this.storageCapacity = (int) (Math.random() * (1000 - 500) + 500);
        this.productQuantity = (int) (Math.random() * (200 - 50) + 50);
        this.boss = new Kingpin();
        this.operationRange = Parameters.drugOperationRange;
        this.totalMoney = 0;
    }

    /**
     * Function for checking if delivery is needed.
     * @param courier
     * @return
     */
    public int delivery(Courier courier){
        if(this.productQuantity<=this.storageCapacity && this.productQuantity < 200){
            return Math.min(this.storageCapacity - this.productQuantity, courier.carryCapacity);
        }
        return 0;
    }

    /**
     * Function for handing product to storage.
     * @param courier
     */
    public void handingProduct(Courier courier){
        this.productQuantity += courier.inventory.get(0).quantity;
        courier.inventory.get(0).quantity = 0;

    }

    /**
     * Function for handing product to dealer and getting the money cut from the sells he made.
     * @param dealer
     */
    public void handingToDealer(Dealer dealer){
        if(dealer.inventory.get(0).quantity == 0 && this.productQuantity >= 20){
            this.totalMoney += dealer.budget;
            StatisticsAggregator.log("soldDrugs", dealer.budget, Parameters.currentTime);
            dealer.budget = 0;
            this.productQuantity -= 20;
            dealer.inventory.get(0).quantity = 20;
        }
    }
}
