package org.labProject.Agents;

public class TownVisitor extends Citizen{
    private float lawfulLevel;
    @Override
    public void action(){}

    @Override
    public void create() {}

    @Override
    public void delete() {}

    ;
    public TownVisitor(){
        super();
        this.lawfulLevel =  (float)Math.floor(Math.random()*100);
    }
}
