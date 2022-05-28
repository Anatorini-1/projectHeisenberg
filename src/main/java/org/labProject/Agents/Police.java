package org.labProject.Agents;

import org.labProject.Buildings.Building;
import org.labProject.Buildings.PoliceStation;
import org.labProject.Core.Map;

public class Police extends Citizen{
    private int perception;
    private int stubbornes;
    private int morale;
//    private PoliceStation parentStation;

    @Override
    public void create() {}

    @Override
    public void delete() {}

    public Police(int p,int s,int m){
        super();
//        this.parentStation =ps;
        this.morale = m;
        this.stubbornes = s;
        this.perception = p;
    }
}
