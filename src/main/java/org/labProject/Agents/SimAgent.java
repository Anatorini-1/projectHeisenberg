package org.labProject.Agents;

import org.labProject.Buildings.Building;
import org.labProject.Buildings.Street;
import org.labProject.Core.Map;

public interface SimAgent {
     void action(Map map);
     void delete();

     /**
      * Calling this function causes a given agent to go directly to the building given as an argument
      * @param map An anchor to the {@link Map} object.
      * @param building the {@link Building} an agent should go to.
      * CAN NOT BE A {@link Street}!
      */
     void goLocation(Map map, Building building);
}
