package org.labProject.Agents;

import org.labProject.Core.Map;

public class Dealer extends Citizen{
    private float profficiency;
    private int perception;
    private int morale;
    public Dealer(float p,int pe, int m){
        super();
        this.perception = pe;
        this.profficiency = p;
        this.morale = m;
    }
    @Override
    public void action( Map map) {

    }

    @Override
    public void create() {}

    @Override
    public void delete() {}

    ;
}
