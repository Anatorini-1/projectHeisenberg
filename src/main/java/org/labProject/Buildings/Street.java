package org.labProject.Buildings;

import org.labProject.Agents.Citizen;

import java.awt.*;

public class Street extends Building{
    public Citizen[][] pedestrians;
    private int innerX,innerY;
    public Street(int x, int y, Color c){
        super(x,y,c);
        this.pedestrians = new Citizen[2][3];
        this.innerX=0;
        this.innerY=0;
    };


}
