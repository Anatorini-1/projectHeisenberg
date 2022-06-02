package org.labProject.GUI.Statistics;

import org.labProject.Core.Parameters;

import javax.swing.*;
import java.awt.*;

public class SimStatisticsFrame extends JPanel {
    public SimStatisticsFrame(){
        setPreferredSize(new Dimension(200, Parameters.mapWindowSize+50));
        setBackground(Color.white);
        setBorder(BorderFactory.createLineBorder(Color.white,3,true));
        setLayout(new FlowLayout());
        add(new SimClock());

    }
}
