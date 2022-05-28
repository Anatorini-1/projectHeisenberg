package org.labProject.Agents;

import org.labProject.Core.Map;

public class Producer extends Citizen{
    private float experience;
    private int level;
    @Override
    public void action(Citizen citizen, Map map) {

    }

    @Override
    public void create() {}

    @Override
    public void delete() {}

    public Producer(){
        super();
        this.experience=0;
        this.level=0;
    }
}
