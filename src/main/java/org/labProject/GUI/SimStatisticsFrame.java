package org.labProject.GUI;

import org.labProject.Core.Parameters;

import javax.swing.*;
import java.awt.*;

public class SimStatisticsFrame extends JPanel {
    public SimStatisticsFrame(){
        setPreferredSize(new Dimension(Parameters.mapWindowSize, Parameters.mapWindowSize*2/3));
        setBackground(Color.RED);
        add(new Label("Statistics"));
    }
}
