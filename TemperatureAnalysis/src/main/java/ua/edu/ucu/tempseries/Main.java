package ua.edu.ucu.tempseries;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
//
//
//            TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis();
//
//            for (int i = 0; i < 256; i++) {
//                seriesAnalysis.addTemp(1);
//                System.out.println(seriesAnalysis.average());
//            }
        double[] temps = {4, 5, 5};
        TemperatureSeriesAnalysis sa = new TemperatureSeriesAnalysis ( temps );

        System.out.println ( sa.addTemperat ( 4.5, 6.0, 6.7 ) );


        }
    }



