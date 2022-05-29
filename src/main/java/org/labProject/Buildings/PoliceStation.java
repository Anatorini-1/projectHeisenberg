package org.labProject.Buildings;

import org.labProject.Agents.Police;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PoliceStation extends Building{
    private List<Police> staff;
    private int patrolsPerDay;
    private int corruptionLevel;

    public PoliceStation(int p,int c,int x, int y){
        super(x,y,Color.blue);
        this.patrolsPerDay = p;
        this.corruptionLevel = c;
        this.staff = new ArrayList<>();
    }
}
