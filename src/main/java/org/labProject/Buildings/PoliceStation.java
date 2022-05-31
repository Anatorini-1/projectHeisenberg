package org.labProject.Buildings;

import org.labProject.Agents.Police;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PoliceStation extends Building{
    private List<Police> staff;
    public int patrolsPerDay;
    private int corruptionLevel;

    public PoliceStation(int p,int c,int x, int y){
        super(x,y,new Color(150,150,255));
        this.patrolsPerDay = p;
        this.corruptionLevel = c;
        this.staff = new ArrayList<>();
    }
}
