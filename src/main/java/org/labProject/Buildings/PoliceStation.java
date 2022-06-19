package org.labProject.Buildings;

import org.labProject.Agents.Police;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This implementation of the {@link Building} superclass represents an object which is the Police Station.
 * Here are the {@link Police} spawned and its {@link Police#home} assigned.
 */
public class PoliceStation extends Building{
    /**
     * Number of patrols per day.
     */
    public int patrolsPerDay;

    public PoliceStation(int p,int c,int x, int y){
        super(x,y,new Color(150,150,255));
        this.patrolsPerDay = p;
    }
}
