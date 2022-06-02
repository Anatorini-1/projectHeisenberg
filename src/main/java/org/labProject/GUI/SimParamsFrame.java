package org.labProject.GUI;

import org.labProject.Core.Parameters;

import javax.swing.*;
import java.awt.*;

public class SimParamsFrame extends JPanel {
    public SimParamsFrame(){
        setPreferredSize(new Dimension(Parameters.mapWindowSize,Parameters.mapWindowSize/3));
        setBackground(Color.GRAY);
        var pauseButton = new JButton();
        pauseButton.setText("Pause");
        pauseButton.addActionListener(e -> {
            Parameters.isPaused = true;
        });
        var resumeButton = new JButton();
        resumeButton.setText("Resume");
        resumeButton.addActionListener(e -> {
            Parameters.isPaused = false;
        });
        pauseButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        resumeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));

        var tickSpeedSlider = new JSlider(JSlider.HORIZONTAL,1,20,Parameters.tickSpeed);
        tickSpeedSlider.setMinorTickSpacing(1);
        tickSpeedSlider.addChangeListener(e -> {
            Parameters.tickSpeed = tickSpeedSlider.getValue();
        });
        tickSpeedSlider.setPaintTicks(true);
        tickSpeedSlider.setPaintLabels(true);
        tickSpeedSlider.setLabelTable(tickSpeedSlider.createStandardLabels(1));
        tickSpeedSlider.setAlignmentY(Component.BOTTOM_ALIGNMENT);

        var sliderLabel = new Label("Ticks per second: "+Parameters.tickSpeed);
        sliderLabel.setAlignment(Label.CENTER);
        sliderLabel.setBackground(Color.white);

        add(pauseButton);
        add(resumeButton);
        add(sliderLabel);
        add(tickSpeedSlider);
        add(new Label());

    }

}
