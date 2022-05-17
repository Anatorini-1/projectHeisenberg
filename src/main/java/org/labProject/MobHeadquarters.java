package org.labProject;

import java.util.ArrayList;
import java.util.List;

public class MobHeadquarters extends Building{
    private float stock;
    private float storageCapacity;
    private Kingpin boss;
    private List<Courier> logistics;
    public MobHeadquarters(){
        super();
        this.stock = 0;
        this.storageCapacity = (float)(Math.random()*1000+100);
        this.boss = new Kingpin(10,10,0.5f);
        this.logistics = new ArrayList<>();
    }
}
