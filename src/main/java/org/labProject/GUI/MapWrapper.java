package org.labProject.GUI;

import org.labProject.Agents.Citizen;
import org.labProject.Core.Map;
import org.labProject.Core.Parameters;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class MapWrapper extends JPanel {
    public MapWrapper(Map map, List<Citizen> units) {
        setPreferredSize(new Dimension(Parameters.mapWindowSize, Parameters.mapWindowSize));
        setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255), 3, false));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        if (Parameters.isInitialized) {
            add(new SimMapFrame(map, units));
        }
    }
    @Override
    public void paintComponent(Graphics g){
        if(Parameters.isInitialized){
            super.paintComponent(g);
        }
        else{
            try{
                var g2d = (Graphics2D)g;
                File imgFile = new File("./src/main/resources/heisenberg.jpg");
                if(imgFile.canRead()){
                    final BufferedImage img = ImageIO.read(imgFile);
                    g2d.drawImage(img,0,0,Parameters.mapWindowSize,Parameters.mapWindowSize,null);
                }
                else{
                    System.out.println(imgFile.toString());
                }
            }
            catch(IOException e){
                e.printStackTrace();
            }

        }


    }
}
