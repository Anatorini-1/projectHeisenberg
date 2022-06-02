package org.labProject.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InitializerParamContainer extends JPanel {
    private JSlider slider;
    private JLabel fieldLabel;
    private JTextField field;
    private ParamSetter ps;
    private ParamGetter pg;
    private Dimension dimensions;
    private String label;

    private void prepare(){
        setPreferredSize(dimensions);
        setLayout(new GridBagLayout());
        this.fieldLabel = new JLabel(label);
        this.field = new JTextField();
        this.slider = new JSlider(0, 10, 1);
        slider.setPaintTicks(true);
        slider.addChangeListener(e -> {
            ps.set(slider.getValue());
            field.setText(String.valueOf(slider.getValue()));

        });
        slider.setPaintLabels(false);
        slider.setLabelTable(slider.createStandardLabels(1));


        field.setText(String.valueOf(pg.get()));
        field.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {
            }

            @Override
            public void keyPressed(KeyEvent keyEvent) {
            }

            @Override
            public void keyReleased(KeyEvent keyEvent) {
                int newValue;
                try {
                    newValue = Integer.parseInt(field.getText());
                    ps.set(newValue);
                    field.setBackground(Color.white);
                    slider.setValue(newValue);


                } catch (NumberFormatException e) {
                    field.setBackground(new Color(255, 96, 96));
                }
            }
        });
        field.setColumns(3);

    }
    private void pack(){
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.FIRST_LINE_START;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        add(this.fieldLabel, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 4;
        constraints.gridheight = 1;
        constraints.anchor = GridBagConstraints.LAST_LINE_START;
        add(slider, constraints);

        constraints.gridx = 3;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.FIRST_LINE_END;
        add(field, constraints);
    }
    public  InitializerParamContainer(String label, Dimension dimensions, ParamGetter g, ParamSetter s) {
        this.label = label;
        this.dimensions = dimensions;
        this.pg = g;
        this.ps = s;
        prepare();
        pack();

    }

    public InitializerParamContainer(String label, Dimension dimensions, ParamGetter g, ParamSetter s,int max) {
        this.label = label;
        this.dimensions = dimensions;
        this.pg = g;
        this.ps = s;
        prepare();
        this.slider.setMaximum(max);
        pack();
    }
}