package org.labProject.Agents;

import org.labProject.Buildings.Building;
import org.labProject.Core.Map;
import org.labProject.Core.Renderable;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class Citizen extends Renderable implements SimAgent {
    public List<Item> inventory;
    public int age;
    public int budget;
    public int carryCapacity;
    public int goJail;
    public Building currentLocation;
    public Building home;
    public boolean randomMovement = true;
    public Citizen(){
        super(0,0, Color.WHITE);
        this.inventory = new ArrayList<>();
        this.age = (int) (Math.random() * (90-15)+15);
        this.budget = (int) (Math.random() * (500-50)+50);
        this.carryCapacity = (int)Math.floor(Math.random()*100);
        this.home = null;
        this.goJail = 0;
    }

    @Override
    public void goLocation(Map map, Building building){
            int y = (building.y % 3 == 1) ? building.y - 1 : building.y + 1;
            if(this.currentLocation.x == building.x && this.currentLocation.y == y) {
                ((Building) map.toRender.get(this.currentLocation.x).get(this.currentLocation.y)).leave(this);
                ((Building) map.toRender.get(building.x).get(building.y)).enter(this);
            }else if(this.currentLocation.x % 3 == 0 && this.currentLocation.y != y) {
                if (this.currentLocation.y > y) {
                    ((Building) map.toRender.get(this.currentLocation.x).get(this.currentLocation.y)).leave(this);
                    ((Building) map.toRender.get(this.currentLocation.x).get(this.currentLocation.y - 1)).enter(this);
                } else{
                    ((Building) map.toRender.get(this.currentLocation.x).get(this.currentLocation.y)).leave(this);
                    ((Building) map.toRender.get(this.currentLocation.x).get(this.currentLocation.y + 1)).enter(this);
                }
            }else if(this.currentLocation.y % 3 == 0 && this.currentLocation.x != building.x) {
                if (this.currentLocation.x < building.x) {
                    ((Building) map.toRender.get(this.currentLocation.x).get(this.currentLocation.y)).leave(this);
                    ((Building) map.toRender.get(this.currentLocation.x + 1).get(this.currentLocation.y)).enter(this);
                } else{
                    ((Building) map.toRender.get(this.currentLocation.x).get(this.currentLocation.y)).leave(this);
                    ((Building) map.toRender.get(this.currentLocation.x - 1).get(this.currentLocation.y)).enter(this);
                }
            }else if(this.currentLocation.y % 3 == 0){
                if(this.currentLocation.x%3==1){
                    ((Building) map.toRender.get(this.currentLocation.x).get(this.currentLocation.y)).leave(this);
                    ((Building) map.toRender.get(this.currentLocation.x - 1).get(this.currentLocation.y)).enter(this);
                }else{
                    ((Building) map.toRender.get(this.currentLocation.x).get(this.currentLocation.y)).leave(this);
                    ((Building) map.toRender.get(this.currentLocation.x + 1).get(this.currentLocation.y)).enter(this);
                }
            }else if(this.currentLocation != building && !this.currentLocation.getClass().getSimpleName().equals("Street")){
                this.randomMovement(map);
            }
    }
    public void goToStreetLocation(Map map, Building street){
        if(this.currentLocation.x % 3 == 0 && this.currentLocation.y != street.y) {
            if (this.currentLocation.y > street.y) {
                ((Building) map.toRender.get(this.currentLocation.x).get(this.currentLocation.y)).leave(this);
                ((Building) map.toRender.get(this.currentLocation.x).get(this.currentLocation.y - 1)).enter(this);
            } else{
                ((Building) map.toRender.get(this.currentLocation.x).get(this.currentLocation.y)).leave(this);
                ((Building) map.toRender.get(this.currentLocation.x).get(this.currentLocation.y + 1)).enter(this);
            }
        }else if(this.currentLocation.y % 3 == 0 && this.currentLocation.x != street.x) {
            if (this.currentLocation.x < street.x) {
                ((Building) map.toRender.get(this.currentLocation.x).get(this.currentLocation.y)).leave(this);
                ((Building) map.toRender.get(this.currentLocation.x + 1).get(this.currentLocation.y)).enter(this);
            } else{
                ((Building) map.toRender.get(this.currentLocation.x).get(this.currentLocation.y)).leave(this);
                ((Building) map.toRender.get(this.currentLocation.x - 1).get(this.currentLocation.y)).enter(this);
            }
        }else if(this.currentLocation.y % 3 == 0){
            if(this.currentLocation.x%3==1){
                ((Building) map.toRender.get(this.currentLocation.x).get(this.currentLocation.y)).leave(this);
                ((Building) map.toRender.get(this.currentLocation.x - 1).get(this.currentLocation.y)).enter(this);
            }else if(this.currentLocation.x%3==2){
                ((Building) map.toRender.get(this.currentLocation.x).get(this.currentLocation.y)).leave(this);
                ((Building) map.toRender.get(this.currentLocation.x + 1).get(this.currentLocation.y)).enter(this);
            }
        }else if(!this.currentLocation.getClass().getSimpleName().equals("Street")){
            this.randomMovement(map);
        }
    }
    protected void randomMovement(Map map){
        ArrayList<Building> moveOptions = new ArrayList<>();

        //Can we go right?
        if(this.currentLocation.x < map.gridSize-1)
                if(map.toRender.get(this.currentLocation.x+1).get(this.currentLocation.y).getClass().getSimpleName().equals("Street"))
                    moveOptions.add((Building) map.toRender.get(this.currentLocation.x+1).get(this.currentLocation.y));
        //Can we go left?
        if(this.currentLocation.x > 0)
            if(map.toRender.get(this.currentLocation.x-1).get(this.currentLocation.y).getClass().getSimpleName().equals("Street"))
                moveOptions.add((Building) map.toRender.get(this.currentLocation.x-1).get(this.currentLocation.y));
        //Can we go up?
        if(this.currentLocation.y > 0)
            if(map.toRender.get(this.currentLocation.x).get(this.currentLocation.y-1).getClass().getSimpleName().equals("Street"))
                moveOptions.add((Building) map.toRender.get(this.currentLocation.x).get(this.currentLocation.y-1));
        //Can we go down?
        if(this.currentLocation.y < map.gridSize-1)
            if(map.toRender.get(this.currentLocation.x).get(this.currentLocation.y+1).getClass().getSimpleName().equals("Street"))
                moveOptions.add((Building) map.toRender.get(this.currentLocation.x).get(this.currentLocation.y+1));

        //Pick random direction
        if(moveOptions.size()>0){
            Building targetBuilding = moveOptions.get((int)Math.floor(Math.random()*moveOptions.size()));
            this.currentLocation.leave(this);
            targetBuilding.enter(this);
            this.currentLocation = targetBuilding;
            this.x = currentLocation.x;
            this.y = currentLocation.y;
        }

    }

    @Override
    public void action(Map map) {
        this.randomMovement(map);
    }
}
