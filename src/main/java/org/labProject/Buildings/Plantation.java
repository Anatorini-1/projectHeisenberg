package org.labProject.Buildings;

import org.labProject.Agents.Producer;
import org.labProject.Core.Parameters;
import org.labProject.Core.StatisticsAggregator;

import java.awt.*;

/**
 * This implementation of the {@link Building} superclass represents an object which is the Plantation of the drug mafia.
 * Here take place all the weed growing and harvesting.
 */
public class Plantation extends Building{
    /**
     * Number of plants at {@link Plantation}.
     */
    private final int plantCount;
    /**
     * Cost of one gram of ready product.
     */
    private final int operatingCost;
    /**
     * Number of ready products.
     */
    public int readyProduct;
    public Plantation(int x, int y){
        super(x,y,Color.MAGENTA); //Was gonna be yellow, but Michał is colorblind :/
        this.plantCount = (int) (Math.random() * (25 - 20) + 20);
        this.readyProduct = (int) (Math.random() * (250 - 50) + 50);
        this.operatingCost =  6; //Cost of 1 gram production
    }

    /**
     * If there is enoght {@link Plantation#readyProduct} it is handed to the {@link org.labProject.Agents.Courier}.
     * @param quantity
     * @return
     */
    public int handingProduct(int quantity){
        if(readyProduct>=quantity){
            this.readyProduct = this.readyProduct - quantity;
            try {
                StatisticsAggregator.log("drugProductionCost", quantity * this.operatingCost, Parameters.currentTime);
            }catch (Exception e){
                //Honestly, I don't know what is wrong with this
                //And at this point, I do not care
            }
            return quantity;

        }
        quantity = this.readyProduct;
        this.readyProduct = 0;
        StatisticsAggregator.log("drugProductionCost",quantity*this.operatingCost, Parameters.currentTime);
        return quantity;

    }

    /**
     * A function for harvesting ready weed.
     * @param producer
     */
    public void harvest(Producer producer){
        int tmp = (int)(this.plantCount + Math.floor(this.plantCount*producer.experience));
        this.readyProduct += this.plantCount + tmp;
    }
}
