package org.labProject.Buildings;

import org.labProject.Agents.Citizen;
import org.labProject.Core.Renderable;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Main class from which all buildings (including {@link Street}) in the simulation inherit.
 * This class is abstract, as it by itself has no defined behavior in the simulation,
 * and so, an object of this class would be pointless.
 */
public abstract class Building extends Renderable {
    /**
     * A list of all agents present at the current {@link Building}.
     */
    public List<Citizen> guests;

    /**
     * A function which allows an agent to enter a {@link Building}.
     * @param c
     */
    public void enter(Citizen c){
        guests.add(c);
        c.currentLocation = this;
    }

    /**
     * A function which allows an agent to leave a {@link Building}.
     * @param c
     */
    public void leave(Citizen c){
        guests.remove(c);
    }
    public Building(int x, int y, Color c){
        super(x,y,c);
        this.guests = new ArrayList<>();
    }

}
