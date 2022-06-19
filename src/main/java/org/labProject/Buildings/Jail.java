package org.labProject.Buildings;

import java.awt.*;

/**
 * This implementation of the {@link Building} superclass represents an object which is a Jail.
 * If a {@link org.labProject.Agents.RegularCitizen} gets arrested he goes there for a certain amount of time
 * depending on the current drug amount in his inventory.
 */
public class Jail extends Building {

    public Jail(int x, int y){
        super(x,y,new Color(114, 125, 143));
    }
}
