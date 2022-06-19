package org.labProject.Buildings;

import org.labProject.Agents.Citizen;

import java.awt.*;

/**
 * This implementation of the {@link Building} superclass represents an object which is an apartment building.
 * There is a change a {@link org.labProject.Agents.RegularCitizen} can be spawned there
 * and be assigned as his {@link org.labProject.Agents.RegularCitizen#home}.
 */
public class ApartmentBuilding extends Building{
    public ApartmentBuilding(int x,int y){super(x,y,new Color(160,82,45));}
}
