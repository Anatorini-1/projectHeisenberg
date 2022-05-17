package org.labProject;

import java.util.ArrayList;
import java.util.List;

public abstract class Building extends Renderable{
    public List<Citizen> guests;
    public void enter(Citizen c){};
    public void leave(Citizen c){};
    public Building(){
        this.guests = new ArrayList<>();
    }
}
