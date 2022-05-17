package org.labProject;


import java.util.Scanner;

public class Simulation{
    private static Map map;
    private static Citizen[] units;
    private static void init(){
        /*var sc = new Scanner(System.in);
        System.out.println("Map size: ");*/
        //Static values for presentation purposes
        map = new Map(4);

    }
    public static void main(String[] args){
        init();
        while(true){
            map.render();
        }
    }
}
