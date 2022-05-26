package org.labProject;

import javax.swing.*;
import java.awt.*;

public class BuildingDrawer {
    public static JPanel draw(int x, int y, int size,Color c){
        return new JPanel(){
            @Override
            public void paint(Graphics g){
                g.setColor(c);
                g.fillRect(x,y,size,size);
            }
        };
    }
}
