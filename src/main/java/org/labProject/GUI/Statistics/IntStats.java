package org.labProject.GUI.Statistics;

import javax.swing.*;

import org.labProject.Core.Parameters;
import org.labProject.Core.StatisticsAggregator;

import java.awt.*;

public class IntStats extends JPanel {
    String policeStats;
    public IntStats(){
        setBackground(Color.white);
        setPreferredSize(new Dimension(150,220));
        setAlignmentX(JPanel.CENTER_ALIGNMENT);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        var g2d = (Graphics2D)g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);

    }
}
