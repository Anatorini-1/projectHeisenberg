package org.labProject;


import java.util.Scanner;
import javax.swing.*;
public class Simulation{
    private static Map map;
    private static Citizen[] units;
    private static void init(){
        /*var sc = new Scanner(System.in);
        System.out.println("Map size: ");*/
        //Static values for presentation purposes
        map = new Map(4);

    }
    private static void tick(){};
    public static void main(String[] args){
        init();
        map.init();
        map.dumpInfo();
        while(true){
            map.update();
           // map.render();
        }

    }
}
