package org.labProject.Agents;

import org.labProject.Buildings.PoliceStation;

public class Police extends Citizen{
    private int perception;
    private int stubbornes;
    private int morale;
    private PoliceStation parentStation;
    @Override
    public void action(){}

    @Override
    public void create() {}

    @Override
    public void delete() {}

    public Police(int p,int s,int m,PoliceStation ps){
        super();
        this.parentStation =ps;
        this.morale = m;
        this.stubbornes = s;
        this.perception = p;
    }
}
