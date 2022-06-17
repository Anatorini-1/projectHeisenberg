package org.labProject.GUI.Initializer;

import javax.swing.*;
import javax.swing.plaf.metal.MetalSliderUI;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * A class used to generate input fields for all simulation parameters. An instance of this class
 * consists of a {@link JPanel} containing a {@link JLabel}, {@link JTextField} and a {@link JSlider}, all linked to a single simulation parameter
 */
public class InitializerParamContainer extends JPanel {
    /**
     * The {@link JSlider} component of this {@link JPanel}
     */
    private JSlider slider;
    /**
     * The {@link JLabel} component of this {@link JPanel}
     */
    private JLabel fieldLabel;
    /**
     * The {@link JTextField} component of this {@link JPanel}
     */
    private JTextField field;
    /**
     * An implementation of the {@link ParamSetter} interface, passed in the constructor, used for modifying the
     * parameter field associated with this object
     */
    private final ParamSetter ps;
    /**
     * An implementation of the {@link ParamGetter} interface, passed in the constructor, used for reading the
     * parameter field associated with this object
     */
    private final ParamGetter pg;
    /**
     * How much space should this component occupy
     * @see Dimension
     */
    private final Dimension dimensions;
    /**
     * The title of this panel, will be displayed above the slider
     */
    private final String label;
    /**
     * Indicates whether the parameter associated with this panel is limited in some way
     */
    private boolean isLimited = false;

    /**
     * If the parameter associated with this panel is limited by some way, the implementation of
     * the {@link Constraint} interface should be a function taking a single argument and returning true if the passed value
     * does not break the limit, and false if it does
     */
    private Constraint constraint = (e) -> {return true;};

    /**
     * Generates all components of this panel
     */
    private void prepare(){
        setBackground(Color.WHITE);
        setPreferredSize(dimensions);
        setLayout(new GridBagLayout());
        this.fieldLabel = new JLabel(label);
        this.field = new JTextField();
        this.slider = new JSlider(0, pg.get()+10,pg.get());
        this.slider.setBackground(Color.white);
        slider.setPaintTicks(true);
        slider.addChangeListener(e -> {
            if(isLimited && !constraint.check(slider.getValue())) return;
            ps.set(slider.getValue());
            field.setText(String.valueOf(slider.getValue()));
        });
        slider.setPaintLabels(false);
        slider.setLabelTable(slider.createStandardLabels(1));
        slider.setUI(new MetalSliderUI(){
            @Override
            public void paintThumb(Graphics g) {
                Rectangle r = thumbRect;
                var g2d = (Graphics2D)g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(new Color(20,100,20));
                g.fillOval(r.x,r.y,r.width,r.height);
            }
        });

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
                    if(newValue < 1) throw new NumberFormatException();
                    if(isLimited && !constraint.check(newValue)) throw new NumberFormatException();
                    ps.set(newValue);
                    field.setBackground(Color.white);
                    if(slider.getMaximum() < newValue)
                        slider.setMaximum(newValue*2);
                    slider.setValue(newValue);


                } catch (NumberFormatException e) {
                    field.setBackground(new Color(250, 75, 75));
                }
            }
        });
        field.setColumns(3);

    }

    /**
     * Lays out all the generated components in a proper way
     */
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

    /**
     * @param label The text to be displayed at the top of this panel
     * @param dimensions The {@link Dimension} class object, representing the size this panel should be
     * @param g A function for reading the parameter associated with this panel
     * @param s A function for setting the parameter associated with this panel
     */
    public  InitializerParamContainer(String label, Dimension dimensions, ParamGetter g, ParamSetter s) {
        this.label = label;
        this.dimensions = dimensions;
        this.pg = g;
        this.ps = s;
        prepare();
        pack();

    }

    /**
     * @param label The text to be displayed at the top of this panel
     * @param dimensions The {@link Dimension} class object, representing the size this panel should be
     * @param g A function for reading the parameter associated with this panel
     * @param s A function for setting the parameter associated with this panel
     * @param max The maximum value of associated parameter
     * @param constraint If the associated parameter is limited be some min/max values, the function passed
     *                   as a constraint should take one argument, and return true if the argument meets the limitations, and false otherwise
     */
    public InitializerParamContainer(String label, Dimension dimensions, ParamGetter g, ParamSetter s,int max, Constraint constraint) {
        this.label = label;
        this.dimensions = dimensions;
        this.pg = g;
        this.ps = s;
        prepare();
        this.slider.setMaximum(max);
        this.constraint = constraint;
        this.isLimited = true;
        pack();
    }


}