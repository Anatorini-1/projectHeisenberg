package org.labProject.GUI;

import org.labProject.Buildings.Street;
import org.labProject.Core.Map;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class SimMapFrame extends JPanel {
    Map mapAnchor;
    int cellSize;
    public SimMapFrame(Map map){
        this.mapAnchor = map;
        this.cellSize = (int)(400 / map.toRender.size());
        setPreferredSize(new Dimension(400,400));
    }
    @Override public void paintComponent(Graphics g)
    {
        var g2d = (Graphics2D)g;
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0,0,this.getWidth()-1,this.getHeight()-1);
        mapAnchor.toRender.forEach(row -> {
            row.forEach(cell -> {
                switch (cell.getClass().getSimpleName()){
                    case "ApartmentBuilding":
                        g2d.setColor(cell.c);
                        g2d.fillRect(cell.x*cellSize,cell.y*cellSize,cellSize,cellSize);
                        g2d.setColor(Color.black);
                        g2d.drawRect(cell.x*cellSize,cell.y*cellSize,cellSize,cellSize);
                        g2d.drawString("AB",cell.x*cellSize + cellSize/2,cell.y*cellSize + cellSize/2);
                        break;
                    case "Street":
                        g2d.setColor(cell.c);
                        g2d.fillRect(cell.x*cellSize,cell.y*cellSize,cellSize,cellSize);
                        for(int i=0;i<2;i++){
                            for(int j=0;j<3;j++){
                                g2d.setColor(Color.black);
                                g2d.drawRect(cell.x*cellSize+cellSize/2*i,cell.y*cellSize+cellSize/3*j,cellSize/2,cellSize/3);
                            }
                        }
                        break;

                    default:
                        break;
                }


            });
        });
    }
}
