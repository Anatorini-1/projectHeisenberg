package org.labProject;

import java.util.ArrayList;
import java.util.List;

public class PoliceStation extends Building{
    private List<Police> staff;
    private int patrolsPerDay;
    private int corruptionLevel;

    public PoliceStation(int p,int c){
        super();
        this.patrolsPerDay = p;
        this.corruptionLevel = c;
        this.staff = new ArrayList<>();
    }
}