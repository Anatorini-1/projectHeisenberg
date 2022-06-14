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
    public Jail jail;
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
        ArrayList<Integer[]> coordsList = new ArrayList<>();
        int police = quantity - 2;
        int numberOfPolice = quantity - Parameters.numberOfPoliceStations - 2;
            while(quantity>0){
                boolean mobPoliceDistance = true;
                Integer[] coordsR = GetCoords(size);
                boolean isInArray = false;
                for(Integer[] coordsCheck : coordsList){
                if(Arrays.equals(coordsCheck, coordsR)) {
                    isInArray = true;
                }
                }
                if(!isInArray && quantity==police && police>numberOfPolice){
                int distance =  Math.abs(coordsList.get(0)[0] - coordsR[0]) + Math.abs(coordsList.get(0)[1] - coordsR[1]);
                if(distance != Parameters.mobToPoliceDistance*3){
                    mobPoliceDistance = false;
                }else{
                    police--;
                }
                }
                if(mobPoliceDistance){
                    if(!isInArray){
                        coordsList.add(coordsR);
                        quantity--;
                    }
                }
            }
            return coordsList;
    }
    private ArrayList<Integer[]> ApartmentBuildingsRandomCoords(int size, int quantity, ArrayList<Integer[]> specialBuildings){
        ArrayList<Integer[]> coordsList = new ArrayList<>(new ArrayList<>());
        while(quantity>0){
            Integer[] coordsR = GetCoords(size);
            boolean isInArray = false;
            for(Integer[] coordsCheck : coordsList){
                if(Arrays.equals(coordsCheck, coordsR)) {
                    isInArray = true;
                }
            }
            for(Integer[] coordsCheck :specialBuildings){
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

    public Map(){
        this.gridSize = Parameters.mapSize*3 + 1;
        this.toRender = new ArrayList<>();
        this.units = new ArrayList<>();
        int quantityOfBuildings = Parameters.numberOfPlantations+Parameters.numberOfPoliceStations+2; //number of special buildings
        ArrayList<Integer[]>  specialBuildingsCoords = BuildingsRandomCoords(gridSize, quantityOfBuildings); //Getting coords for special buildings
        ArrayList<Integer[]>  citizenApartmentCoords = ApartmentBuildingsRandomCoords(gridSize, Parameters.townPopulation, specialBuildingsCoords); // Getting coords for citizens home
        for(var i=0;i<gridSize;i++){
            toRender.add(new ArrayList<>());
            for(var j=0;j<gridSize;j++){
                //checking if current coords are on list of specialBuildingCoords
                boolean checkCoords = false;
                int coordsOfSpecialVuiling = 0;
                for(int x = 0;x<quantityOfBuildings;++x){
                    if(specialBuildingsCoords.get(x)[0].equals(i) && specialBuildingsCoords.get(x)[1].equals(j)) checkCoords = true;
                    if(checkCoords){coordsOfSpecialVuiling = x; break;}
                }
                if(i%3 == 0 || j%3 == 0){
                    toRender.get(i).add(new Street(i,j));
                }
                else if(checkCoords){
                        if(coordsOfSpecialVuiling==0){ //There is just one Headquarter
                            MobHeadquarters mobHeadquarters = new MobHeadquarters(i, j);
                            this.mob = mobHeadquarters;
                            for(int d = 0; d < Parameters.dealerCount; ++d) {
                                Dealer dealer = new Dealer((int) (Math.random() * (100 - 1) + 1), this.mob);
                                mobHeadquarters.guests.add(dealer);
                                dealer.home = mobHeadquarters;
                                dealer.currentLocation = mobHeadquarters;
                                units.add(dealer);
                                toRender.get(i).add(mobHeadquarters);
                            }
                        }else if(coordsOfSpecialVuiling == 1){
                            this.jail = new Jail(i, j);
                            toRender.get(i).add(jail);
                        }else if(coordsOfSpecialVuiling < Parameters.numberOfPoliceStations+2){
                            PoliceStation policeStation = new PoliceStation(Parameters.patrolsPerDayPerStations, (int) (Math.random() * (100 - 1) + 1), i, j);
                            toRender.get(i).add(policeStation);
                            for (int p = 0; p < Parameters.policemanPerStation; p++) { // number of policemen
                                Police newPolice = new Police((int) (Math.random() * (100 - 1) + 1), policeStation);
                                newPolice.home = policeStation;
                                newPolice.currentLocation = policeStation;
                                policeStation.guests.add(newPolice);
                                units.add(newPolice);
                            }
                        }else if(coordsOfSpecialVuiling<quantityOfBuildings) {
                            Plantation plantation = new Plantation(i,j);
                            toRender.get(i).add(plantation);
                            //Courier
                            Courier newCourier = new Courier(this.mob);
                            newCourier.home = plantation;
                            newCourier.currentLocation = plantation;
                            plantation.guests.add(newCourier);
                            units.add(newCourier);
                            //Producer
                            Producer newProducer = new Producer();
                            newProducer.c = Color.MAGENTA;
                            newProducer.home = plantation;
                            newProducer.currentLocation = plantation;
                            units.add(newProducer);
                        }
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





