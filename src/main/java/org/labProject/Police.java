package org.labProject;

public class Police extends Citizen{
    private int perception;
    private int stubbornes;
    private int morale;
    private PoliceStation parentStation;
    @Override
    public void action(){};
    public Police(int p,int s,int m,PoliceStation ps){
        super();
        this.parentStation =ps;
        this.morale = m;
        this.stubbornes = s;
        this.perception = p;
    }
}
