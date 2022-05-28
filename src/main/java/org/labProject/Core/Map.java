package org.labProject.Core;

import org.labProject.Buildings.ApartmentBuilding;
import org.labProject.Buildings.Building;
import org.labProject.Buildings.Street;



import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Map {

    //grid size made public for test reasons
    public int gridSize;

    public List<List<Renderable>> toRender;
    //Method for printing the current map state to the console window
    public void dumpInfo(){
        toRender.forEach(row -> {
            row.forEach(cell -> {
                System.out.print(cell.x+" "+cell.y+" "+cell.c.getRGB()+" | ");
            });
            System.out.println("");
        });
    }
    public Map(int size){
        Stack<Building> toPutOnMap = new Stack<>();
        this.gridSize = size;
        this.toRender = new ArrayList<>();
        for(var i=0;i<gridSize;i++){
            toRender.add(new ArrayList<>());
            for(var j=0;j<gridSize;j++){
                if(i%3 == 0 || j%3 == 0) toRender.get(i).add(new Street(i,j,Color.BLUE));
                else if(!toPutOnMap.empty()) toRender.get(i).add(toPutOnMap.pop());
                else toRender.get(i).add(new ApartmentBuilding(i,j, Color.MAGENTA));
            }
        }

    }
}
