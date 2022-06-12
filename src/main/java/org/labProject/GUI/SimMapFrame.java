package org.labProject.GUI;

import org.labProject.Agents.Citizen;
import org.labProject.Buildings.Street;
import org.labProject.Core.Map;
import org.labProject.Core.Parameters;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.List;

public class SimMapFrame extends JPanel {
    Map mapAnchor;
    List<Citizen> unitsAnchor;
    int cellSize;
    public SimMapFrame(Map map, List<Citizen> units){
        this.mapAnchor = map;
        this.unitsAnchor = units;
        this.cellSize = (int)(Parameters.mapWindowSize / map.gridSize);
        setPreferredSize(new Dimension(Parameters.mapWindowSize,Parameters.mapWindowSize));
        setAlignmentX(JPanel.LEFT_ALIGNMENT);
        setAlignmentY(JPanel.TOP_ALIGNMENT);
    }
    @Override public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        var g2d = (Graphics2D)g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0,0,this.getWidth()-1,this.getHeight()-1);
        g2d.translate( (Parameters.mapWindowSize - mapAnchor.gridSize*cellSize)/2, (Parameters.mapWindowSize - mapAnchor.gridSize*cellSize)/2);
       // cellSize += (Parameters.mapWindowSize - mapAnchor.gridSize*cellSize)/mapAnchor.toRender.size();
        mapAnchor.toRender.forEach(row -> {
            row.forEach(cell -> {
                g2d.setColor(cell.c);
                if(cell.getClass().getSimpleName().equals("Street")){
                    g2d.fillRect(cell.x*cellSize,cell.y*cellSize,cellSize,cellSize);
                    for(int i=0;i<2;i++){
                        for(int j=0;j<3;j++){
                            g2d.setColor(Color.white);
                            //g2d.drawRect(cell.x*cellSize+cellSize/2*i,cell.y*cellSize+cellSize/3*j,cellSize/2,cellSize/3);
                        }
                    }
                }
                else{
                    g2d.fillRect(cell.x*cellSize,cell.y*cellSize,cellSize,cellSize);
                    g2d.setColor(Color.black);
                    g2d.drawRect(cell.x*cellSize,cell.y*cellSize,cellSize,cellSize);
                    String label;
                    switch (cell.getClass().getSimpleName()){
                        case "ApartmentBuilding": label = "AB"; break;
                        case "PoliceStation": label = "PS"; break;
                        case "Plantation" : label = "PL"; break;
                        case "MobHeadquarters": label = "MH"; break;
                        default: label = "???";break;
                    }
                    g2d.drawString(label,cell.x*cellSize,cell.y*cellSize+cellSize/2);
                }
            });
        });
        unitsAnchor.forEach(unit -> {
            g2d.setColor(unit.c);
            g2d.fillRect(cellSize*unit.currentLocation.x+ cellSize/2, cellSize*unit.currentLocation.y+cellSize/2,cellSize/2,cellSize/2);
        });
    }
}
