package org.labProject.GUI;

import javax.swing.*;
import java.awt.*;

public class SimStatisticsFrame extends JPanel {
    public SimStatisticsFrame(){
        setPreferredSize(new Dimension(200,200));
        setBackground(Color.RED);
        add(new Label("Statistics"));
    }
}