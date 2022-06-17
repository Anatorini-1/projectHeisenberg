package org.labProject.Buildings;

import org.labProject.Agents.Police;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PoliceStation extends Building{
    public int patrolsPerDay;

    public PoliceStation(int p,int c,int x, int y){
        super(x,y,new Color(150,150,255));
        this.patrolsPerDay = p;
    }
}
