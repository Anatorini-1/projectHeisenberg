package org.labProject.GUI;

import org.labProject.Agents.Citizen;
import org.labProject.Buildings.Street;
import org.labProject.Core.Map;
import org.labProject.Core.Parameters;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.image.BufferedImage;
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
                    int timeOfDay = (Parameters.currentTime+6)/60%24;
                    // Add a fixed value to all RGB components, rendering the image brighter depending on the time of day
                    // Colors vary from black+5 to black+40, following the curve: https://www.wolframalpha.com/input?i2d=true&i=f%5C%2840%29x%5C%2841%29+%3D+-Power%5B%5C%2840%29Divide%5Bx%2C2%5D-6%5C%2841%29%2C2%5D%2B40+from+0+to+24
                    int red = g2d.getColor().getRed();
                    int green = g2d.getColor().getGreen();
                    int blue = g2d.getColor().getBlue();
                    int offset = (int)((-1) * Math.pow((((float)Parameters.currentTime+6)/60)%24 / 2 - 6,2))+40;
                    g2d.setColor(new Color((red+offset)%255,(green+offset)%255,(blue+offset)%255));
                    g2d.fillRect(cell.x*cellSize,cell.y*cellSize,cellSize,cellSize);
                    g2d.setColor(Color.white);
                    if(cell.x%3 == 0)
                        g2d.fillRect(cell.x*cellSize+cellSize/2 - cellSize/40,cell.y*cellSize+cellSize/2 -cellSize/10,cellSize/20,cellSize/5);
                    if(cell.y%3 ==0)
                        g2d.fillRect(cell.x*cellSize+cellSize/2 -cellSize/10,cell.y*cellSize+cellSize/2 - cellSize/40,cellSize/5,cellSize/20);

                }
                else{
                    g2d.fillRect(cell.x*cellSize,cell.y*cellSize,cellSize,cellSize);
                    g2d.setColor(Color.black);
                    g2d.drawRect(cell.x*cellSize,cell.y*cellSize,cellSize,cellSize);
                    String label;
                    switch (cell.getClass().getSimpleName()){
                        case "ApartmentBuilding": label = ""; break;
                        case "PoliceStation": label = "PS"; break;
                        case "Plantation" : label = "PL"; break;
                        case "MobHeadquarters": label = "MH"; break;
                        case "Jail": label = "Jail"; break;
                        default: label = "???";break;
                    }
                    g2d.drawString(label,cell.x*cellSize,cell.y*cellSize+cellSize/2);
                }
            });
        });
        unitsAnchor.forEach(unit -> {
            g2d.setColor(unit.c);
            if(unit.currentLocation != null)
                g2d.fillRect(cellSize*unit.currentLocation.x+ cellSize/2, cellSize*unit.currentLocation.y+cellSize/2,cellSize/2,cellSize/2);
        });
    }
}
