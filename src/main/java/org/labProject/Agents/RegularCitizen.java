package org.labProject.Agents;

import org.labProject.Buildings.ApartmentBuilding;
import org.labProject.Buildings.Building;
import org.labProject.Buildings.Street;
import org.labProject.Core.Map;

public class RegularCitizen extends Citizen{
    public float addictionLevel,recklessness,lawfullLevel;
    @Override
    public void create() {}
    @Override
    public void delete() {}

    public RegularCitizen(){
        super();
        this.addictionLevel = (int)Math.floor(Math.random()*100)+1;
        this.recklessness = (int)Math.floor(Math.random()*100)+1;
        this.lawfullLevel = (int)Math.floor(Math.random()*100)+1;
    }

}
