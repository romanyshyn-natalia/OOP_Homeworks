package ua.edu.ucu.tempseries;

import java.util.Arrays;
import java.util.InputMismatchException;

public class TemperatureSeriesAnalysis {
    private static final int MIN_TEMP = -273;
    private static final double DELTA = 0.00001;

    private double[] series;
    private int capacity;
    private int tempStatistics; //розмір масиву


    public TemperatureSeriesAnalysis() {
        series = new double[1];
        capacity = 1;
        tempStatistics = 0;
    }

    public TemperatureSeriesAnalysis(double[] temperatureSeries) {
        this();
        checkMinTemp(temperatureSeries);
        series = temperatureSeries;
        capacity = series.length;
        tempStatistics = capacity;

    }

    public int tempsSize(){
        return tempStatistics;
    }


    public void checkMinTemp(double[] temps) {
        for (double elem : temps) {
            if (elem < MIN_TEMP) {
                throw new InputMismatchException();
            }
        }
    }

    public double[] getTemperatures() {
        return Arrays.copyOfRange(series, 0, tempsSize ());
    }

    public double average() {

        illArgException();

        double average = 0;
        for (double elem : series) {
            average += elem;
        }
        return average / series.length;
    }

    private void illArgException() {
        if (capacity == 0) {
            throw new IllegalArgumentException();
        }
    }

    public double deviation() {
        illArgException();
        double mean = average();
        double deviation = 0;
        for (double elem : series) {
            deviation += (elem - mean) * (elem - mean);
        }
        return Math.sqrt(deviation / series.length);
    }

    public double min() {
        illArgException();
        double currentMin = series[0];
        for (int i = 1; i < series.length; i++) {
            if (series[i] < currentMin) {
                currentMin = series[i];
            }
        }
        return currentMin;
    }

    public double max() {
        illArgException();
        double currentMax = series[0];
        for (int i = 1; i < series.length; i++) {
            if (series[i] > currentMax) {
                currentMax = series[i];
            }
        }
        return currentMax;
    }


    public double findTempClosestToZero() {
        return findTempClosestToValue(0);
    }

    public double findTempClosestToValue(double tempValue) {
        illArgException();
        double closest = series[0];
        double absMin = Math.abs(closest - tempValue);
        for (int i = 1; i < series.length; i++) {
            double currentCls = series[i] - tempValue;
            if (absMin - currentCls > DELTA || Math.abs(absMin - currentCls)
                    < DELTA && series[i] - tempValue > 0) {
                closest = series[i];
            }
        }
        return closest;
    }

    private double[] findTempLessGreater(double tempValue, boolean indicator) {
        illArgException();
        int counter = 0;
        for (double elem : series) {
            if (indicator) {
                if (elem < tempValue) {
                    counter++;
                }
            } else {
                if (elem > tempValue) {
                    counter++;
                }
            }
        }
        double[] result = new double[counter];
        counter = 0;
        for (double elem : series) {
            if (indicator) {
                if (elem < tempValue) {
                    result[counter++] = elem;
                }
            } else {
                if (elem > tempValue) {
                    result[counter++] = elem;
                }
            }
        }
        return result;
    }

    public double[] findTempsLessThen(double tempValue) {
        return findTempLessGreater(tempValue, true);
    }

    public double[] findTempsGreaterThen(double tempValue) {
        return findTempLessGreater(tempValue, false);
    }

    public TempSummaryStatistics summaryStatistics() {
        illArgException();
        return new TempSummaryStatistics(this);
    }

    public void addTemp(double tempValue) {
        if (capacity == tempStatistics) {
            double[] newSeries = new double[capacity * 2];
            System.arraycopy(series, 0, newSeries, 0, tempStatistics);
            series = newSeries;
            capacity *= 2;
        }
        series[tempStatistics] = tempValue;
        tempStatistics++;
    }

    public int addTemps(double... temps) {
        illArgException();
        checkMinTemp(temps);
        for (double elem : temps) {
            addTemp(elem);
        }
        return tempStatistics;
    }

    private double[] increaseArray() {
        capacity *= 2;
        double[] newArr = new double[capacity];
        System.arraycopy(series, 0, newArr, 0, tempStatistics);
        return newArr;
    }

    public int addTemperat(double... temps){
        illArgException ();
        for (double temp: temps){
            series[tempStatistics++] = temp;
            if (tempStatistics == capacity){
                series = increaseArray ();
            }

        }
        return tempStatistics;
    }
}
