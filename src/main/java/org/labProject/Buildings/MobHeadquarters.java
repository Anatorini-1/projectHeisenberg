package org.labProject.Buildings;

import org.labProject.Agents.Courier;
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
    private int productQuantity;
    private Kingpin boss;

    public MobHeadquarters(int x, int y){
        super(x,y, Color.GREEN);
        this.stock = 0;
        this.storageCapacity = (int) (Math.random() * (1000 - 500) + 500);
        this.productQuantity = (int) (Math.random() * (200 - 50) + 50);
        this.boss = new Kingpin();
        this.operationRange = Parameters.drugOperationRange;
    }
    //Checking demand
    public int delivery(Courier courier){
        if(this.productQuantity<=this.storageCapacity){
            return Math.min(this.storageCapacity - this.productQuantity, courier.carryCapacity);
        }
        return 0;
    }
    public boolean handingProduct(Courier courier){
        this.productQuantity += courier.inventory.get(0).quantity;
        courier.inventory.get(0).quantity = 0;
        return true;
    }
}
