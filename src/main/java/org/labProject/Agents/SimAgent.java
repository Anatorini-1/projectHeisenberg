package org.labProject.Agents;

import org.labProject.Core.Map;

public interface SimAgent {
    public void action(Citizen citizen , Map map);
    public void create();
    public void delete();
}
