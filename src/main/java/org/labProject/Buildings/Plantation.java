package org.labProject.Buildings;

import org.labProject.Agents.Courier;
import org.labProject.Agents.Producer;
import org.labProject.Core.Parameters;

import java.awt.*;

public class Plantation extends Building{
    private int plantCount;
    private int operatingCost;
    public int readyProduct;
    public Plantation(int x, int y){
        super(x,y,Color.MAGENTA); //Was gonna be yellow, but Michał is a daltonist :/
        this.plantCount = (int) (Math.random() * (25 - 20) + 20);
        this.readyProduct = (int) (Math.random() * (250 - 50) + 50);
        this.operatingCost =  (int) (Math.random() * (6 - 2) + 2); //Cost of 1 gram production
    }
    public int handingProduct(int quantity){
        if(readyProduct>=quantity){
            this.readyProduct = this.readyProduct - quantity;
            return quantity;
        }
        quantity = this.readyProduct;
        this.readyProduct = 0;
        return quantity;
    }
    public void harvest(Producer producer){
        this.readyProduct += this.plantCount * Parameters.dealerCount + Math.floor(this.plantCount*producer.experience);
    }
}
