package org.labProject.Core;

import java.awt.*;

/**
 * Gives our map nice colors.
 */
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
