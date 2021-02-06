package com.company;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

interface DoubleArrayReaderInterface {
    double[] readOneDimensionalArray(File file);
    double[] readOneDimensionalArray(String fileName);
    double[][] readTwoDimensionalArray(File file);
    double[][] readTwoDimensionalArray(String fileName);
}

public class DoubleArrayReader implements DoubleArrayReaderInterface{

    @Override
    public double[] readOneDimensionalArray(File file) { return readOneDimensionalArray(file.getPath()); }

    @Override
    public double[] readOneDimensionalArray(String fileName){
        try {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);
            int length = Integer.parseInt(scanner.nextLine());
            double[] array = new double[length];
            StringBuilder line = new StringBuilder();
            while(scanner.hasNextLine())
                line.append(scanner.nextLine());
            scanner.close();
            String[] numbersString = line.toString().trim().split(" +");
            int counter = 0;
            for(String tmp : numbersString)
                array[counter++] = Double.parseDouble(tmp);
            return array;
        } catch (IOException e) {
            System.out.println("Error: " + e.toString());
            return null;
        }
    }

    @Override
    public double[][] readTwoDimensionalArray(File file) { return readTwoDimensionalArray(file.getPath()); }

    @Override
    public double[][] readTwoDimensionalArray(String fileName) {
        try {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);
            int length = Integer.parseInt(scanner.nextLine());
            double[][] array = new double[length][length];
            String[] lines = new String[length];
            for (int i = 0; scanner.hasNext(); i++) {
                lines[i]= scanner.nextLine();
            }
            String[][] linesTwoDimensionalArray = new String[length][length];
            for (int i = 0; i < length; i++) {
                linesTwoDimensionalArray[i] = lines[i].trim().split(" +");
            }
            for (int i = 0; i < length; i++) {
                for (int j = 0; j < length; j++) {
                    array[i][j] = Double.parseDouble(linesTwoDimensionalArray[i][j]);
                }
            }
            return array;
        } catch (IOException e) {
            System.out.println("Error: " + e.toString());
            return null;
        }
    }
}
