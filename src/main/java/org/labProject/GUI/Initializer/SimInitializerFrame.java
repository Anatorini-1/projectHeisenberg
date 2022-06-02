package org.labProject.GUI.Initializer;

import org.labProject.Core.Parameters;
import org.labProject.Core.Simulation;
import org.labProject.GUI.Initializer.InitializerParamContainer;

import javax.swing.*;
import java.awt.*;

public class SimInitializerFrame extends JPanel {
    public SimInitializerFrame(){
        /*
        *   //Police Related Params
                public static int numberOfPoliceStations = 1;
                public static int patrolsPerDayPerStations = 1;
                public static int policeCorruptionLevel = 1;
            //Gang Related Params
                public static int numberOfPlantations = 1;
                public static int drugSellPrice = 1;
                public static int drugOperationRange = 1;
                public static int bossProfitCut = 1;
            //Town related params
                public static int visitorsPerDay = 1;
                public static int townPopulation = 1;
                * */
        setPreferredSize(new Dimension(250, Parameters.mapWindowSize+60));
        var numberOfPoliceStations = new InitializerParamContainer(
                "Number of Police Stations: ",
                new Dimension(200,50),
                () -> {return Parameters.numberOfPoliceStations;},
                (e) -> {Parameters.numberOfPoliceStations = e;}
        );
        var patrolsPerDayPerStation = new InitializerParamContainer(
                "Patrols per day per station: ",
                new Dimension(200,50),
                () -> {return Parameters.patrolsPerDayPerStations;},
                (e) -> {Parameters.patrolsPerDayPerStations = e;}
        );
        var policemanPerStation = new InitializerParamContainer(
                "Policeman per station: ",
                new Dimension(200,50),
                () -> {return Parameters.policemanPerStation;},
                (e) -> {Parameters.policemanPerStation = e;}
        );
        var policeCorruptionLevel = new InitializerParamContainer(
                "Police Corruption[%]: ",
                new Dimension(200,50),
                () -> {return Parameters.policeCorruptionLevel;},
                (e) -> {Parameters.policeCorruptionLevel = e;},
                100
        );
        var numberOfPlantations = new InitializerParamContainer(
                "Number of plantations: ",
                new Dimension(200,50),
                () -> {return Parameters.numberOfPlantations;},
                (e) -> {Parameters.numberOfPlantations = e;}
        );
        var drugSellPrice = new InitializerParamContainer(
                "Drug selling price: ",
                new Dimension(200,50),
                () -> {return Parameters.drugSellPrice;},
                (e) -> {Parameters.drugSellPrice = e;}
        );
        var drugOperationRange = new InitializerParamContainer(
                "Drug operation range: ",
                new Dimension(200,50),
                () -> {return Parameters.drugOperationRange;},
                (e) -> {Parameters.drugOperationRange = e;}
        );
        var bossProfitCut = new InitializerParamContainer(
                "Boss profit cut[%]: ",
                new Dimension(200,50),
                () -> {return Parameters.bossProfitCut;},
                (e) -> {Parameters.bossProfitCut = e;},
                100
        );
        var visitorsPerDay = new InitializerParamContainer(
                "Town visitors per day: ",
                new Dimension(200,50),
                () -> {return Parameters.visitorsPerDay;},
                (e) -> {Parameters.visitorsPerDay = e;}
        );
        var townPopulation = new InitializerParamContainer(
                "Initial town population: ",
                new Dimension(200,50),
                () -> {return Parameters.townPopulation;},
                (e) -> {Parameters.townPopulation = e;}
        );

        var initButton = new JButton();
        initButton.setText("Initialize");
        initButton.addActionListener(e -> {
            Parameters.init();
            Simulation.init();
        });
        initButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
                /*
        *   //Police Related Params
                public static int numberOfPoliceStations = 1;
                public static int patrolsPerDayPerStations = 1;
                public static int policeCorruptionLevel = 1;
            //Gang Related Params
                public static int numberOfPlantations = 1;
                public static int drugSellPrice = 1;
                public static int drugOperationRange = 1;
                public static int bossProfitCut = 1;
            //Town related params
                public static int visitorsPerDay = 1;
                public static int townPopulation = 1;
                * */
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        add(numberOfPoliceStations);
        add(policemanPerStation);
        add(patrolsPerDayPerStation);
        add(policeCorruptionLevel);
        add(numberOfPlantations);
        add(drugSellPrice);
        add(drugOperationRange);
        add(bossProfitCut);
        add(visitorsPerDay);
        add(townPopulation);
        add(initButton);
        setBackground(Color.WHITE);
    }
}
