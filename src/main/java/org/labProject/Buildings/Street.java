package org.labProject.Buildings;

import org.labProject.Agents.Citizen;

import java.awt.*;

/**
 * This implementation of the {@link Building} superclass represents an object which is the Street.
 * Here takes place all the movement of all agents. Else it's just a street.
 */
public class Street extends Building{
    public Street(int x, int y){
        super(x,y,Color.black);
    }
}
