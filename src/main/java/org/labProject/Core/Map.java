package org.labProject.Core;

import org.labProject.Agents.*;
import org.labProject.Buildings.*;


import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Map {

    //grid size made public for test reasons
    public int gridSize;
    public List<Citizen> units;

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
    private Integer[] GetCoords(int size){
        Integer[] coords = new Integer[2];
        coords[0] = (int) (Math.random() * (size-1)+1);
        coords[1] = (int) (Math.random() * (size-1)+1);
        for(int i = 0; i<2; i++){
            if(coords[i]%3==0) coords[i]--;
        }
        return coords;
    }
    private ArrayList<Integer[]> BuildingsRandomCoords(int size, int quantity){
        ArrayList<Integer[]> coordsList = new ArrayList<>(new ArrayList<>());
            while(quantity>0){
                Integer[] coordsH = GetCoords(size);
                if(!coordsList.contains(coordsH)){
                    coordsList.add(coordsH);
                    quantity--;
                }
            }
            return coordsList;
    }


    public Map(int size){
        this.gridSize = size;
        this.toRender = new ArrayList<>();
        this.units = new ArrayList<>();
        int quantintyOfBuildings = (int) (3 + Math.floor(size/21));
        ArrayList<Integer[]>  BuildingsRandomCoords = BuildingsRandomCoords(size, quantintyOfBuildings);
        Integer[] coords = new Integer[2];
        int whichBuilding = 1;
        for(var i=0;i<gridSize;i++){
            toRender.add(new ArrayList<>());
            for(var j=0;j<gridSize;j++){
                coords[0] = i; coords[1] = j;
                boolean checkCoords = false;
                for(int x = 0;x<quantintyOfBuildings;++x){
                    if(BuildingsRandomCoords.get(x)[0].equals(coords[0]) && BuildingsRandomCoords.get(x)[1].equals(coords[1])) checkCoords = true;
                }
                if(i%3 == 0 || j%3 == 0){
                    toRender.get(i).add(new Street(i,j,Color.DARK_GRAY));
                }
                else if(checkCoords){
                    switch(whichBuilding){
                        case 1: toRender.get(i).add(new MobHeadquarters(i,j, Color.GREEN)); break;
                        case 2: toRender.get(i).add(new PoliceStation((int) (Math.random() * (10-1)+1), (int) (Math.random() * (10-1)+1), i,j, Color.BLACK)); break;
                        case 3: toRender.get(i).add(new Plantation(i,j, Color.ORANGE)); break;
                    }
                    whichBuilding++;
                    if(whichBuilding>3) whichBuilding=whichBuilding-2;
                }
                else{
                    ApartmentBuilding apartmentBuilding = new ApartmentBuilding(i,j, Color.MAGENTA);
                    toRender.get(i).add(apartmentBuilding);
                    int random = (int)Math.floor(Math.random()*15)+1;
                    if(random==2) {
                        RegularCitizen newCitizen = new RegularCitizen();
                        newCitizen.home = apartmentBuilding;
                        newCitizen.currentLocation = apartmentBuilding;
                        newCitizen.home.guests.add(newCitizen);
                        units.add(newCitizen);
                    }
                }
            }
        }
    }

    /*public static void main(String[] args) {
        Map map = new Map(10);
        for(int x = 0;x<10;x++){
            for(int y = 0;y<10;y++){
                System.out.println(map.toRender.get(x).get(y).getClass());
            }
        }
        System.out.println(map.units.get(0).home.x);
        System.out.println(map.units.get(0).home.y);
        }*/

    }


