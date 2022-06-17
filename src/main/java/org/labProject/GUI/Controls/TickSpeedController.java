package org.labProject.GUI.Controls;

import org.labProject.Core.Parameters;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * A component controlling the tickspeed of the simulation. Contains a slider and an input field, both of witch can be used independently
 * and changing one of them automatically adjusts the other  one so that the both accurately represent the current tickspeed
 */
public class TickSpeedController extends JPanel {
    /**
     * Used to check whether the newly provided value is a valid tickspeed (an integer)
     */
    private boolean isValid = true;

    /**
     *
     */
    private void refresh(){this.repaint();}

    /**
     *
     */
    public TickSpeedController(){
        setLayout(new FlowLayout());

        var box = new JTextField();
        box.setColumns(3);
        box.setText(String.valueOf(Parameters.tickSpeed));
        box.setBackground(isValid ? Color.WHITE : Color.red);
        box.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {

            }

            @Override
            public void keyPressed(KeyEvent keyEvent) {

            }

            @Override
            public void keyReleased(KeyEvent keyEvent) {
                try {
                    Parameters.tickSpeed = Integer.parseInt(box.getText());
                    isValid = true;
                    Parameters.tickSpeed = Parameters.tickSpeed > 0 ? Parameters.tickSpeed : 1;
                    refresh();
                }
                catch(NumberFormatException e){
                    isValid = false;
                    refresh();
                }
            }
        });
        add(new TickSpeedSlider(box));
        add(box);
    }
}
