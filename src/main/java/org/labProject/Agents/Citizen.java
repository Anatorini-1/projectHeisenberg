package org.labProject.Agents;

import org.labProject.Buildings.Building;
import org.labProject.Core.Renderable;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class Citizen extends Renderable implements SimAgent {
    public List<Item> inventory;
    public String name;
    public int age;
    public float budget;
    public float carryCapacity;
    public Building currentLocation;
    public Citizen(){
        super(0,0, Color.WHITE);
        this.inventory = new ArrayList<>();
        this.age = (int)Math.floor(Math.random()*80);
        this.budget = (float)Math.random()*100;
        this.carryCapacity = (int)Math.floor(Math.random()*100);

    }
}
