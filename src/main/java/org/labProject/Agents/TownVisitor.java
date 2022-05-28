package org.labProject.Agents;

import org.labProject.Core.Map;

public class TownVisitor extends Citizen{
    private float lawfulLevel;
    @Override
    public void action(Citizen citizen, Map map) {}

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
