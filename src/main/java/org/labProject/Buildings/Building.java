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
    };
    public void leave(Citizen c){
        if(guests.contains(c)){
            int index = guests.indexOf(c);
            guests.remove(index);
        }
    };
    public Building(int x, int y, Color c){
        super(x,y,c);
        this.guests = new ArrayList<>();
    }

}
