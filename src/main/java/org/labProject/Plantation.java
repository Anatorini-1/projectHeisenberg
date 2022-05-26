package org.labProject;

import java.awt.*;

public class Plantation extends Building{
    private int plantCount;
    private float operatingCost;
    public Plantation(int x, int y, Color c){
        super(x,y,c);
        this.plantCount = (int)Math.floor(Math.random()*20+5);
        this.operatingCost =  (int)Math.floor(Math.random()*20+5);
    }
}
