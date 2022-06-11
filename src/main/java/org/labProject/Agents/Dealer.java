package org.labProject.Agents;

import org.labProject.Core.Map;
import org.labProject.Core.Parameters;

import java.awt.*;

public class Dealer extends Citizen{
    private float profficiency;
    private int perception;
    private int morale;
    private int randomMovement;
    public Dealer(float p,int pe, int m){
        super();
        this.c = Color.ORANGE;
        this.perception = pe;
        this.profficiency = p;
        this.morale = m;
    }
    @Override
    public void action( Map map) {
        int time = Parameters.currentTime%1440; //Current time during day
        int decision;
        if(time<960) {
            if (time % 60 == 0) {
                decision = (int) Math.floor(Math.random() * 3) + 1;
                System.out.println(decision);
                switch (decision) {
                    case 1:
                        this.randomMovement = 1;
                        break;
                    case 2:
                        this.randomMovement = 2;
                        break;
                    default:
                        this.randomMovement = 3;
                        break;
                }
            }
        }else{
            if (time % 30 == 0) {
                decision = (int) Math.floor(Math.random() * 3) + 1;
                System.out.println(decision);
                switch (decision) {
                    case 1:
                        this.randomMovement = 1;
                        break;
                    case 2:
                        this.randomMovement = 2;
                        break;
                    case 3:
                        this.randomMovement = 3;
                        break;
                }
            }
        }
        if(this.randomMovement == 1){
            randomMovement(map);
        }else if(this.randomMovement == 2){
            goLocation(map,this.home);
        }

    }

    @Override
    public void create() {}

    @Override
    public void delete() {}

}
