package org.labProject.GUI.Controls;

import org.labProject.Core.Parameters;

import javax.swing.*;
import java.awt.*;

/**
 * A class used to wrap all simulation controls into one component
 * @see GuiToggle
 * @see PausePlayButton
 * @see TickSpeedController
 */
public class SimControlFrame extends JPanel {
    public SimControlFrame(){
        setAlignmentX(JPanel.LEFT_ALIGNMENT);
        setPreferredSize(new Dimension(Parameters.mapWindowSize,60));
        setBackground(Color.WHITE);
        add(new PausePlayButton());
        add(new GuiToggle());
        add(new TickSpeedController());

    }

}
