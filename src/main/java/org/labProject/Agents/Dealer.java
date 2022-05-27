package org.labProject.Agents;

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
    public void action(){};
}
