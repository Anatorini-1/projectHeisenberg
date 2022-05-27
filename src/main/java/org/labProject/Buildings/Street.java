package org.labProject.Buildings;

import org.labProject.Agents.Citizen;

import java.awt.*;

public class Street extends Building{
    public Citizen[][] pedestrians;
    public Street(int x, int y, Color c){
        super(x,y,c);
        this.pedestrians = new Citizen[3][2];
    };

}
