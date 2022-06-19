package org.labProject.GUI.Map;

import org.labProject.Agents.Citizen;
import org.labProject.Core.Map;
import org.labProject.Core.Parameters;
import org.labProject.GUI.Map.SimMapFrame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * A wrapper class for {@link SimMapFrame}. The main purpose of this class is to decide whether to render the
 * map or a placeholder image, if the map has yet to be initialized.
 */
public class MapWrapper extends JPanel {
    /**
     * @param map A {@link Map} object to be rendered by the underlying {@link SimMapFrame}
     * @param units List of agents to be rendered on the map
     */
    public MapWrapper(Map map, List<Citizen> units) {
        setPreferredSize(new Dimension(Parameters.mapWindowSize, Parameters.mapWindowSize));
        setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255), 3, false));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        if (Parameters.isInitialized) {
            add(new SimMapFrame(map, units));
        }
    }

    /**
     * @param g the <code>Graphics</code> object to protect
     */
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
