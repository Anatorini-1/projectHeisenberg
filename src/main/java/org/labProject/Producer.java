package org.labProject;

public class Producer extends Citizen{
    private float experience;
    private int level;
    @Override
    public void action(){};
    public Producer(){
        super();
        this.experience=0;
        this.level=0;
    }
}
