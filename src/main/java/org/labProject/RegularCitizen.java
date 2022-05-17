package org.labProject;

public class RegularCitizen extends Citizen{
    private float addictionLevel,recklessness,lawfullLevel;
    private ApartmentBuilding home;
    @Override
    public void action(){};
    public RegularCitizen(ApartmentBuilding h){
        this.addictionLevel = (int)Math.floor(Math.random()*100);
        this.recklessness = (int)Math.floor(Math.random()*100);
        this.lawfullLevel = (int)Math.floor(Math.random()*100);
        this.home = h;
    }
}
