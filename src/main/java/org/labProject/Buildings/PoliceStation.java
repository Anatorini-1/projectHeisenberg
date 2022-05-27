package org.labProject.Buildings;

import org.labProject.Agents.Police;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PoliceStation extends Building{
    private List<Police> staff;
    private int patrolsPerDay;
    private int corruptionLevel;

    public PoliceStation(int p,int c,int x, int y, Color color){
        super(x,y,color);
        this.patrolsPerDay = p;
        this.corruptionLevel = c;
        this.staff = new ArrayList<>();
    }
}
