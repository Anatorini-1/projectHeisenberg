package org.labProject.GUI;

import org.labProject.Agents.Citizen;
import org.labProject.Core.Map;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SimMainFrame extends JFrame {
    public SimMainFrame(Map mainMap, List<Citizen> units){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(Toolkit.getDefaultToolkit().getScreenSize());
        setResizable(false);
        //setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
        setTitle("Project Heisenberg");
        setLayout(new GridBagLayout());
        var layout = new GridBagConstraints();
        JPanel params = new SimParamsFrame();
        layout.gridx = 0;
        layout.gridy = 0;
        layout.anchor = GridBagConstraints.FIRST_LINE_START;
        add(params,layout);

        JPanel map = new SimMapFrame(mainMap,units);
        layout.gridx = 1;
        layout.gridy = 0;
        layout.gridheight=2;
        layout.gridwidth=2;
        add(map,layout);

        JPanel statisctics = new SimStatisticsFrame();
        layout.gridx = 0;
        layout.gridy = 1;
        add(statisctics,layout);

        pack();
        setVisible(true);
    }
    public void refresh(){
        repaint();
    };
}
