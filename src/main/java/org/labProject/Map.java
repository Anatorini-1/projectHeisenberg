package org.labProject;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Map {
    private int gridSize;
    private JFrame mainFrame;
    private List<List<Renderable>> toRender;
    public void render(){
        if(mainFrame.getGraphics() != null){
            mainFrame.getContentPane().getGraphics().clearRect(0,0,mainFrame.getWidth(),mainFrame.getHeight());
        }
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        var cellSize = mainFrame.getSize().height / toRender.size();
        toRender.forEach(row -> {
            row.forEach(cell -> {
                mainFrame.add(BuildingDrawer.draw(cell.x*cellSize,cell.y*cellSize,cellSize, cell.c));
            });
        });
        mainFrame.setVisible(true);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    };
    public void init(){
        mainFrame = new JFrame("Project Heisenberg");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(1920,1080);
        mainFrame.add(BuildingDrawer.draw(0,0,1,Color.MAGENTA));
    };
    public void update(){

        mainFrame.add(BuildingDrawer.draw(0,0,100,Color.blue));
        mainFrame.add(BuildingDrawer.draw(100,100,100,Color.blue));
        mainFrame.add(BuildingDrawer.draw(200,200,100,Color.blue));
        mainFrame.setVisible(true);
    };
    public void dumpInfo(){
        toRender.forEach(row -> {
            row.forEach(cell -> {
                System.out.print(cell.x+" "+cell.y+" "+cell.c.getRGB()+" | ");
            });
            System.out.println("");
        });
    }
    public Map(int size){
        Stack<Building> toPutOnMap = new Stack<>();
        this.gridSize = size;
        this.toRender = new ArrayList<>();
        for(var i=0;i<gridSize;i++){
            toRender.add(new ArrayList<>());
            for(var j=0;j<gridSize;j++){
                if(i%3 == 0 || j%3 == 0) toRender.get(i).add(new Street(i,j,Color.BLUE));
                else if(!toPutOnMap.empty()) toRender.get(i).add(toPutOnMap.pop());
                else toRender.get(i).add(new ApartmentBuilding(i,j,Color.MAGENTA));
            }
        }

    }
}
