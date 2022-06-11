package org.labProject.Core;

import org.labProject.Agents.*;
import org.labProject.Buildings.*;


import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Map {

    //grid size made public for test reasons
    public int gridSize;
    public List<Citizen> units;
    public MobHeadquarters mob;
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
                Integer[] coordsR = GetCoords(size);
                boolean isInArray = false;
                for(Integer[] coordsCheck : coordsList){
                if(Arrays.equals(coordsCheck, coordsR)) {
                    isInArray = true;
                }
                }
                if(!isInArray){
                    coordsList.add(coordsR);
                    quantity--;
                }
            }
            return coordsList;
    }


    public Map(int size){
        this.gridSize = size;
        this.toRender = new ArrayList<>();
        this.units = new ArrayList<>();
        int quantityOfBuildings = Parameters.numberOfPlantations+Parameters.numberOfPoliceStations+1; //number of special buildings
        ArrayList<Integer[]>  specialBuildingsCoords = BuildingsRandomCoords(size, quantityOfBuildings); //Getting coords for special buildings
        ArrayList<Integer[]>  citizenApartmentCoords = BuildingsRandomCoords(size, Parameters.townPopulation); // Getting coords for citizens home
        int whichBuilding = 0;
        for(var i=0;i<gridSize;i++){
            toRender.add(new ArrayList<>());
            for(var j=0;j<gridSize;j++){
                //checking if current coords are on list of specialBuildingCoords
                boolean checkCoords = false;
                for(int x = 0;x<quantityOfBuildings;++x){
                    if(specialBuildingsCoords.get(x)[0].equals(i) && specialBuildingsCoords.get(x)[1].equals(j)) checkCoords = true;
                }
                if(i%3 == 0 || j%3 == 0){
                    toRender.get(i).add(new Street(i,j));
                }
                else if(checkCoords){

                        if(whichBuilding==0){ //There is just one Headquarter
                            MobHeadquarters mobHeadquarters = new MobHeadquarters(i, j);
                            this.mob = mobHeadquarters;
                            Dealer dealer = new Dealer(2, (int) (Math.random() * (100 - 1) + 1), (int) (Math.random() * (100 - 1) + 1));
                            mobHeadquarters.guests.add(dealer);
                            dealer.home = mobHeadquarters;
                            dealer.currentLocation = mobHeadquarters;
                            units.add(dealer);
                            toRender.get(i).add(mobHeadquarters);
                        }else if(whichBuilding < Parameters.numberOfPoliceStations+1){
                            PoliceStation policeStation = new PoliceStation(Parameters.patrolsPerDayPerStations, (int) (Math.random() * (100 - 1) + 1), i, j);
                            toRender.get(i).add(policeStation);
                            for (int p = 0; p < 2; p++) { // number of policemen
                                Police newPolice = new Police(2, (int) (Math.random() * (100 - 1) + 1), (int) (Math.random() * (100 - 1) + 1), policeStation);
                                newPolice.home = policeStation;
                                newPolice.currentLocation = policeStation;
                                policeStation.guests.add(newPolice);
                                units.add(newPolice);
                            }
                        }else if(whichBuilding<quantityOfBuildings) {
                            Plantation plantation = new Plantation(i,j);
                            toRender.get(i).add(plantation);
                            Courier newCourier = new Courier(2, 2, this.mob);
                            newCourier.home = plantation;
                            newCourier.currentLocation = plantation;
                            plantation.guests.add(newCourier);
                            units.add(newCourier);
                        }
                    whichBuilding++;
                }
                else{
                    ApartmentBuilding apartmentBuilding = new ApartmentBuilding(i,j);
                    toRender.get(i).add(apartmentBuilding);
                    for(int x = 0;x<Parameters.townPopulation;++x) {
                        if (citizenApartmentCoords.get(x)[0].equals(i) && citizenApartmentCoords.get(x)[1].equals(j)) {
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
    }
}

//    public static void main(String[] args) {
//        Map map = new Map(10);
//        for(int x = 0;x<10;x++){
//            for(int y = 0;y<10;y++){
//                System.out.println(map.toRender.get(x).get(y).getClass());
//            }
//        }
//        System.out.println(map.units.get(0).home.x);
//        }




