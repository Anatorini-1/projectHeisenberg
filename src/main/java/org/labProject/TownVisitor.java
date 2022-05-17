package org.labProject;

public class TownVisitor extends Citizen{
    private float lawfulLevel;
    @Override
    public void action(){};
    public TownVisitor(){
        super();
        this.lawfulLevel =  (float)Math.floor(Math.random()*100);
    }
}
