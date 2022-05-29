package org.labProject.Buildings;

import org.labProject.Agents.Courier;
import org.labProject.Agents.Kingpin;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MobHeadquarters extends Building{
    private float stock;
    private float storageCapacity;
    private Kingpin boss;
    private List<Courier> logistics;
    public MobHeadquarters(int x, int y){
        super(x,y, Color.GREEN);
        this.stock = 0;
        this.storageCapacity = (float)(Math.random()*1000+100);
        this.boss = new Kingpin(10,10,0.5f);
        this.logistics = new ArrayList<>();
    }
}
