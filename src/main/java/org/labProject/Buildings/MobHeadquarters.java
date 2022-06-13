package org.labProject.Buildings;

import org.labProject.Agents.Courier;
import org.labProject.Agents.Dealer;
import org.labProject.Agents.Kingpin;
import org.labProject.Core.Parameters;

import java.awt.*;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

public class MobHeadquarters extends Building{
    private int stock;
    private int storageCapacity;
    public int operationRange;
    public int productQuantity;
    private static int totalMoney;
    private Kingpin boss;

    public MobHeadquarters(int x, int y){
        super(x,y, Color.GREEN);
        this.stock = 0;
        this.storageCapacity = (int) (Math.random() * (1000 - 500) + 500);
        this.productQuantity = (int) (Math.random() * (200 - 50) + 50);
        this.boss = new Kingpin();
        this.operationRange = Parameters.drugOperationRange;
        this.totalMoney = 0;
    }
    //Checking demand
    public int delivery(Courier courier){
        if(this.productQuantity<=this.storageCapacity && this.productQuantity < 200){
            return Math.min(this.storageCapacity - this.productQuantity, courier.carryCapacity);
        }
        return 0;
    }
    public void handingProduct(Courier courier){
        this.productQuantity += courier.inventory.get(0).quantity;
        courier.inventory.get(0).quantity = 0;
    }
    public void handingToDealer(Dealer dealer){
        if(dealer.inventory.get(0).quantity == 0 && this.productQuantity >= 50){
            this.totalMoney += dealer.budget*Parameters.bossProfitCut;
            dealer.budget -= dealer.budget*Parameters.bossProfitCut;
            this.productQuantity -= 50;
            dealer.inventory.get(0).quantity = 50;
        }
    }
}
