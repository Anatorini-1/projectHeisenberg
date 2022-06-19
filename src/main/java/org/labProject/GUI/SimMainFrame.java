package org.labProject.GUI;

import org.labProject.Agents.Citizen;
import org.labProject.Core.Map;
import org.labProject.GUI.Controls.SimControlFrame;
import org.labProject.GUI.Initializer.SimInitializerFrame;
import org.labProject.GUI.Map.MapWrapper;
import org.labProject.GUI.Statistics.SimStatisticsFrame;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * The main GUI class, wrapping all other components into a (semi?) organised window
 * <br />
 * Please, for the love of God, don't use a display smaller than 1920x1080 with a 100% scale,or it will break.
 * <br />
 * You have been warned
 */
public class SimMainFrame extends JFrame {
    /**
     * @param mainMap <code>Map</code> of the simulation, to be displayed in the GUI
     * @param units A <code>List</code> of agents to be displayed in the GUi
     */
    public SimMainFrame(Map mainMap, List<Citizen> units){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(Toolkit.getDefaultToolkit().getScreenSize());

        setResizable(false);
        getRootPane().setBorder(BorderFactory.createLineBorder(new Color(20,100,20),10,true));
        //setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
        setTitle("Project Heisenberg");
        setLayout(new GridBagLayout());
        var layout = new GridBagConstraints();
        JPanel controlFrame = new SimControlFrame();
        layout.gridx = 1;
        layout.gridy = 1;
        layout.anchor = GridBagConstraints.FIRST_LINE_START;
        add(controlFrame,layout);

        JPanel map = new MapWrapper(mainMap,units);
        layout.gridx = 1;
        layout.gridy = 0;
        layout.gridheight=1;
        layout.gridwidth=2;
        add(map,layout);

        JPanel statistics = new SimStatisticsFrame();
        layout.gridx = 2;
        layout.gridy = 0;
        layout.gridheight=2;
        layout.gridwidth=1;
        add(statistics,layout);

        JPanel initializer = new SimInitializerFrame();
        layout.gridx = 0;
        layout.gridy = 0;
        layout.gridheight=2;
        layout.gridwidth=1;
        add(initializer,layout);
        pack();
        setVisible(true);
    }

    /**
     * A wrapper for the {@link JFrame#repaint()} method
     */
    public void refresh(){
        repaint();
    };
}
