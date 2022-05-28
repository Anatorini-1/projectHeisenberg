package org.labProject.Agents;

import org.labProject.Buildings.Building;
import org.labProject.Core.Map;
import org.labProject.Core.Renderable;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class Citizen extends Renderable implements SimAgent {
    public List<Item> inventory;
    public String name;
    public int age;
    public float budget;
    public float carryCapacity;
    public Building currentLocation;
    public Building home;
    public Citizen(){
        super(0,0, Color.WHITE);
        this.inventory = new ArrayList<>();
        this.age = (int)Math.floor(Math.random()*80);
        this.budget = (float)Math.random()*100;
        this.carryCapacity = (int)Math.floor(Math.random()*100);
        this.home = null;
    }

    public static void goLeft(Citizen citizen, Map  map, int x, int y){
        citizen.currentLocation = (Building) map.toRender.get(x-1).get(y);
        ((Building) map.toRender.get(x).get(y)).leave(citizen);
        ((Building) map.toRender.get(x-1).get(y)).enter(citizen);
    }
    public static void goRight(Citizen citizen, Map  map, int x, int y){
        citizen.currentLocation = (Building) map.toRender.get(x+1).get(y);
        ((Building) map.toRender.get(x).get(y)).leave(citizen);
        ((Building) map.toRender.get(x+1).get(y)).enter(citizen);
    }
    public static void goUp(Citizen citizen, Map  map, int x, int y){
        citizen.currentLocation = (Building) map.toRender.get(x).get(y-1);
        ((Building) map.toRender.get(x).get(y)).leave(citizen);
        ((Building) map.toRender.get(x).get(y-1)).enter(citizen);
    }
    public static void goDown(Citizen citizen, Map  map, int x, int y){
        citizen.currentLocation = (Building) map.toRender.get(x).get(y+1);
        ((Building) map.toRender.get(x).get(y)).leave(citizen);
        ((Building) map.toRender.get(x).get(y+1)).enter(citizen);
    }

    @Override
    public void action(Citizen citizen, Map map) {
        int x = citizen.currentLocation.x;
        int y = citizen.currentLocation.y;
        int toMove = (int)Math.floor(Math.random()*4)+1;
        if(x > 0 && y > 0 && x < map.gridSize-1 && y < map.gridSize-1){
            String buildingLeft = String.valueOf(map.toRender.get(x-1).get(y));
            String buildingDown = String.valueOf(map.toRender.get(x).get(y+1));
            String buildingRight = String.valueOf(map.toRender.get(x+1).get(y));
            String buildingUp = String.valueOf(map.toRender.get(x).get(y-1));

            if (buildingLeft.contains("Street") && toMove == 1) {
                goLeft(citizen, map, x, y);
            }
            else if(buildingDown.contains("Street") && toMove == 2){
                goDown(citizen, map, x, y);
            }
            else if(buildingRight.contains("Street") && toMove == 3){
                goRight(citizen, map, x, y);
            }
            else if(buildingUp.contains("Street") && toMove == 4){
                goUp(citizen, map, x, y);
            }
        }
        else if(x == map.gridSize-1 && y == map.gridSize-1){
            String buildingLeft = String.valueOf(map.toRender.get(x-1).get(y));
            String buildingUp = String.valueOf(map.toRender.get(x).get(y-1));
            if (buildingLeft.contains("Street") && toMove == 1) {
                goLeft(citizen, map, x, y);
            }
            else if(buildingUp.contains("Street") && toMove == 4){
                goUp(citizen, map, x, y);
            }
        }
        else if(x == map.gridSize-1 && y == 0){
            String buildingLeft = String.valueOf(map.toRender.get(x-1).get(y));
            String buildingDown = String.valueOf(map.toRender.get(x).get(y+1));
            if (buildingLeft.contains("Street") && toMove == 1) {
                goLeft(citizen, map, x, y);
            }
            else if(buildingDown.contains("Street") && toMove == 2){
                goDown(citizen, map, x, y);
            }
        }
        else if(x == 0 && y == map.gridSize-1){
            String buildingRight = String.valueOf(map.toRender.get(x+1).get(y));
            String buildingUp = String.valueOf(map.toRender.get(x).get(y-1));
            if(buildingRight.contains("Street") && toMove == 3){
                goRight(citizen, map, x, y);
            }
            else if(buildingUp.contains("Street") && toMove == 4){
                goUp(citizen, map, x, y);
            }
        }
        else if(x == 0 && y == 0){
            String buildingDown = String.valueOf(map.toRender.get(x).get(y+1));
            String buildingRight = String.valueOf(map.toRender.get(x+1).get(y));
            if(buildingDown.contains("Street") && toMove == 2){
                goDown(citizen, map, x, y);
            }
            else if(buildingRight.contains("Street") && toMove == 3){
                goRight(citizen, map, x, y);
            }
        }
        else if(x == 0 && y != 0 && y != map.gridSize-1){
            String buildingDown = String.valueOf(map.toRender.get(x).get(y-1));
            String buildingRight = String.valueOf(map.toRender.get(x+1).get(y));
            String buildingUp = String.valueOf(map.toRender.get(x).get(y-1));
            if(buildingDown.contains("Street") && toMove == 2){
                goDown(citizen, map, x, y);
            }
            else if(buildingRight.contains("Street") && toMove == 3){
                goRight(citizen, map, x, y);
            }
            else if(buildingUp.contains("Street") && toMove == 4){
                goUp(citizen, map, x, y);
            }
        }
        else if(x != 0 && y == 0 && x != map.gridSize-1){
            String buildingLeft = String.valueOf(map.toRender.get(x-1).get(y));
            String buildingDown = String.valueOf(map.toRender.get(x).get(y+1));
            String buildingRight = String.valueOf(map.toRender.get(x+1).get(y));
            if (buildingLeft.contains("Street") && toMove == 1) {
                goLeft(citizen, map, x, y);
            }
            else if(buildingDown.contains("Street") && toMove == 2){
                goDown(citizen, map, x, y);
            }
            else if(buildingRight.contains("Street") && toMove == 3){
                goRight(citizen, map, x, y);
            }
        }
        else if(x == map.gridSize-1 && y != map.gridSize-1 && y != 0){
            String buildingLeft = String.valueOf(map.toRender.get(x-1).get(y));
            String buildingDown = String.valueOf(map.toRender.get(x).get(y+1));
            String buildingUp = String.valueOf(map.toRender.get(x).get(y-1));
            if (buildingLeft.contains("Street") && toMove == 1) {
                goLeft(citizen, map, x, y);
            }
            else if(buildingDown.contains("Street") && toMove == 2){
                goDown(citizen, map, x, y);
            }
            else if(buildingUp.contains("Street") && toMove == 4){
                goUp(citizen, map, x, y);
            }
        }
        else if(x != map.gridSize-1 && y == map.gridSize-1 && x != 0){
            String buildingLeft = String.valueOf(map.toRender.get(x-1).get(y));
            String buildingRight = String.valueOf(map.toRender.get(x+1).get(y));
            String buildingUp = String.valueOf(map.toRender.get(x).get(y-1));
            if (buildingLeft.contains("Street") && toMove == 1) {
                goLeft(citizen, map, x, y);
            }
            else if(buildingUp.contains("Street") && toMove == 4){
                goUp(citizen, map, x, y);
            }
            else if(buildingRight.contains("Street") && toMove == 3){
                goRight(citizen, map, x, y);
            }
        }
    }
}
