package org.labProject.Agents;

import org.labProject.Buildings.Building;
import org.labProject.Core.Map;

public interface SimAgent {
    public void action(Citizen citizen , Map map);
    public void create();
    public void delete();
    public void goLocation(Citizen citizen, Map map, Building building);
}
