package org.labProject.Buildings;

import org.labProject.Agents.Citizen;
import org.labProject.Core.Renderable;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class Building extends Renderable {
    public List<Citizen> guests;
    public void enter(Citizen c){
        guests.add(c);
        c.currentLocation = this;
    };
    public void leave(Citizen c){
        guests.remove(c);
    };
    public Building(int x, int y, Color c){
        super(x,y,c);
        this.guests = new ArrayList<>();
    }

}
