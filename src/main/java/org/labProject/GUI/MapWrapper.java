package org.labProject.GUI;

import org.labProject.Agents.Citizen;
import org.labProject.Core.Map;
import org.labProject.Core.Parameters;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MapWrapper extends JPanel {
    public MapWrapper(Map map, List<Citizen> units){
        setPreferredSize(new Dimension(Parameters.mapWindowSize,Parameters.mapWindowSize));
        setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255),3,false));

        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        add(new SimMapFrame(map ,units));
    }
}
