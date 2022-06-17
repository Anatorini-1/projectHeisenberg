package org.labProject.GUI.Controls;

import org.labProject.Core.Parameters;

import javax.swing.*;
import javax.swing.plaf.metal.MetalSliderUI;
import java.awt.*;

/**
 * A slider, working together with a text-field to control the tickspeed of the simulation.
 * By default, the range is 1-1000, for more detail, check {@link TickSpeedController}
 */
public class TickSpeedSlider extends JSlider {
    /**
     * @param box a {@link JTextField} with witch this slider works in pair, passed as an argument to ensure
     *            consistency between values presented on the slider and in the text-field
     */
    public TickSpeedSlider(JTextField box){
        setMinimum(1);
        setMaximum(1000);
        setValue(Parameters.tickSpeed);
        setPaintLabels(true);
        setLabelTable(this.createStandardLabels(100));
        setBackground(Color.white);
        setPreferredSize(new Dimension(300,50));
        addChangeListener(e -> {
            Parameters.tickSpeed = getValue() > 0 ? getValue() : 1;
            box.setText(String.valueOf(Parameters.tickSpeed));

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
