package org.labProject;

public class Plantation extends Building{
    private int plantCount;
    private float operatingCost;
    public Plantation(){
        super();
        this.plantCount = (int)Math.floor(Math.random()*20+5);
        this.operatingCost =  (int)Math.floor(Math.random()*20+5);
    }
}
