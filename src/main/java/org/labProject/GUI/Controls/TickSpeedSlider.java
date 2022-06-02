package org.labProject.GUI.Controls;

import org.labProject.Core.Parameters;

import javax.swing.*;
import javax.swing.plaf.metal.MetalSliderUI;
import java.awt.*;

public class TickSpeedSlider extends JSlider {
    public TickSpeedSlider(){
        setMinimum(0);
        setMaximum(20);
        setValue(Parameters.tickSpeed);
        setPaintLabels(true);
        setLabelTable(this.createStandardLabels(5));
        setBackground(Color.white);
        setPreferredSize(new Dimension(300,50));
        addChangeListener(e -> {
            Parameters.tickSpeed = getValue();
        });
        setUI(new MetalSliderUI(){
            @Override
            public void paintThumb(Graphics g) {
                Rectangle r = thumbRect;
                var g2d = (Graphics2D)g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(new Color(20,100,20));
                g.fillOval(r.x,r.y,r.width,r.height);
            }
        });
    }
}
