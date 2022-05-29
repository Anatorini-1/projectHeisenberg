package org.labProject.Buildings;

import java.awt.*;

public class Plantation extends Building{
    private int plantCount;
    private float operatingCost;
    public Plantation(int x, int y){
        super(x,y,Color.MAGENTA); //Was gonna be yellow, but Michał is a daltonist :/
        this.plantCount = (int)Math.floor(Math.random()*20+5);
        this.operatingCost =  (int)Math.floor(Math.random()*20+5);
    }
}
