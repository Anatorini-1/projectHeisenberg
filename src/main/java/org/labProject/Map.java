package org.labProject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Map {
    private int gridSize;
    private Renderable[][] toRender;
    public void render(){
        for(var i=0;i<gridSize;i++){
            for(var j=0;j<gridSize;j++){
                if(toRender[i][j] == null)
                    System.out.print(" [ ] ");
                else System.out.print(" ["+toRender[i][j].getClass().getName().charAt(15)+"] ");
            }
            System.out.print("\n");
        }
        System.out.println("----------------");

    };
    public Map(int size){
        Stack<Building> toPutOnMap = new Stack<>();
        toPutOnMap.push(new PoliceStation(10,10));
        toPutOnMap.push(new Plantation());
        toPutOnMap.push(new MobHeadquarters());
        this.gridSize = size;
        this.toRender = new Renderable[size][size];
        for(var i=0;i<gridSize;i++){
            for(var j=0;j<gridSize;j++){
                if(i%3 == 0 || j%3 == 0) toRender[i][j] = new Street();
                else if(!toPutOnMap.empty()) toRender[i][j] = toPutOnMap.pop();
                else toRender[i][j] = new ApartmentBuilding();
            }
        }
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
