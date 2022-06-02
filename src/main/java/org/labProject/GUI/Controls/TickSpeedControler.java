package org.labProject.GUI.Controls;

import org.labProject.Core.Parameters;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TickSpeedControler extends JPanel {
    private boolean isValid = true;
    private void refresh(){this.repaint();}
    public TickSpeedControler(){
        setLayout(new FlowLayout());

        var box = new JTextField();
        box.setColumns(3);
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
                    refresh();
                }
                catch(NumberFormatException e){
                    isValid = false;
                    refresh();
                }
            }
        });
        add(new TickSpeedSlider());
        add(box);
    }
}
