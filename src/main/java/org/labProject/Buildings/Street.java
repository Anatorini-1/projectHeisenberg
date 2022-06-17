package org.labProject.Buildings;

import org.labProject.Agents.Citizen;

import java.awt.*;

public class Street extends Building{
    public Citizen[][] pedestrians;
    public Street(int x, int y){
        super(x,y,Color.black);
        this.pedestrians = new Citizen[2][3];
    }


}
