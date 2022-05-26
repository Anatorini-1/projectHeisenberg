package org.labProject;

import java.awt.*;

public abstract class Renderable {
    //To be implemented with GUI support
    public int x,y;
    public Color c;
    public Renderable(int x,int y, Color c){
        this.x = x;
        this.y = y;
        this.c = c;
    }
}
