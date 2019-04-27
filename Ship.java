package com.company;

import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;


public class Ship {
    private int[][] ships = new int[10][10];

    //use this method to insert the numbers that user entered as x axis and y axis to 2d array in this method we check
    //the vertical or horizontal , check that the inserted numbers are like a rectangle (width is on) and also check that
// inserted numbers don't have any overlap with each others
    public void insertShipToArray(int[] x, int[] y) {
        if (x.length == 1 || y.length == 1) {
            if (validateRange(x) && validateRange(y)) {
                if (isVertical(x)) {
                    if (checkSize(y)) {
                        if (!hasOverLap(ships, x, y)) {
                            for (int i = 0; i < y.length; i++) {
                                ships[x[0]][y[i]] = 1;
                            }
                        } else {
                            System.out.println("the numbers you entered has overlap with previous ones,please try again!");
                            makeShip();
                        }
                    } else {
                        System.out.println("size of ship should be between \"2 to 5\"");
                        makeShip();
                    }
                } else {
                    if (checkSize(x)) {
                        if (!hasOverLap(ships, x, y)) {
                            for (int i = 0; i < x.length; i++) {
                                ships[x[i]][y[0]] = 1;
                            }
                        } else {
                            System.out.println("the numbers you entered has overlap with previous ones,please try again!");
                            makeShip();
                        }
                    } else {
                        System.out.println("size of ship should be between \"2 to 5\"");
                        makeShip();
                    }
                }
            } else {
                System.out.println("please insert number in range \"0 to 9\"");
                makeShip();
            }

        } else {
            System.out.println("invalid input for making ship");
            makeShip();
        }
    }

    //this method is like above method except is for random making ship for
    // computer side (if the game is with computer not another player)
    public void insertRandomShipToArray(int[] x, int[] y) {
        if (x.length == 1 || y.length == 1) {
            if (validateRange(x) && validateRange(y)) {
                if (isVertical(x)) {
                    if (checkSize(y)) {
                        if (!hasOverLap(ships, x, y)) {
                            for (int i = 0; i < y.length; i++) {
                                ships[x[0]][y[i]] = 1;
                            }
                        } else {
                            makeShipRandomForComputer();
                        }
                    } else {
                        makeShipRandomForComputer();
                    }
                } else {
                    if (checkSize(x)) {
                        if (!hasOverLap(ships, x, y)) {
                            for (int i = 0; i < x.length; i++) {
                                ships[x[i]][y[0]] = 1;
                            }
                        } else {
                            makeShipRandomForComputer();
                        }
                    } else {
                        makeShipRandomForComputer();
                    }
                }
            } else {
                makeShipRandomForComputer();
            }

        } else {
            makeShip();
        }
    }

    // checking that inserted number should be between 0 to 9 because our array is 10*10
    public boolean validateRange(int[] x) {
        boolean flag = false;
        for (int i = 0; i < x.length; i++) {
            if (x[i] < 0 || x[i] > 9)
                flag = false;
            else flag = true;
        }
        return flag;
    }

    //for find out ship is vertical or horizontal
    public boolean isVertical(int[] x) {
        boolean flag = false;
        if (x.length == 1)
            flag = true;
        return flag;
    }

    //checking the size of ship that should be between 2 and 5 no more no less
    public boolean checkSize(int[] x) {
        boolean flag = true;
        if (x.length < 2 || x.length > 5)
            flag = false;
        return flag;
    }

    public void print() {
        System.out.println("  | 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 |");
        System.out.println("-------------------------------------------");
        for (int i = 0; i < 10; i++) {
            System.out.print(i + " |");
            for (int j = 0; j < 10; j++) {
                if (ships[i][j] == 1)
                    System.out.print(" @ |");
                else
                    System.out.print("   |");
            }
            System.out.println("\n-------------------------------------------");
        }
    }

    //this method making ship by user and at the end call the insertShipToArray to save it
    public void makeShip() {
        System.out.println("please insert X place(s)");
        Scanner inputX = new Scanner(System.in);
        String StringX = inputX.nextLine();
        System.out.println("please insert Y place(s)");
        Scanner inputY = new Scanner(System.in);
        String StringY = inputY.nextLine();
        String[] spiltX = StringX.split(",");
        String[] spiltY = StringY.split(",");
        int[] x = new int[spiltX.length];
        int[] y = new int[spiltY.length];
        for (int i = 0; i < spiltX.length; i++) {
            x[i] = Integer.parseInt(spiltX[i]);
        }
        for (int i = 0; i < spiltY.length; i++) {
            y[i] = Integer.parseInt(spiltY[i]);
        }
        insertShipToArray(x, y);

    }

    //like above method except for computer side (if the game is with computer not another player)
    public void makeShipRandomForComputer() {
        String randX = "";
        String randY = "";
        if (ThreadLocalRandom.current().nextInt(0, 2) == 0) {
            randX = String.valueOf(ThreadLocalRandom.current().nextInt(0, 10));
            int sizeY = ThreadLocalRandom.current().nextInt(2, 6);
            int y = ThreadLocalRandom.current().nextInt(0, 5);
            for (int i = 1; i <= sizeY; i++) {
                if (i == 1)
                    randY += String.valueOf(y + i);
                else
                    randY = randY + "," + String.valueOf(y + i);
            }
        } else {
            randY = String.valueOf(ThreadLocalRandom.current().nextInt(0, 10));
            int sizeX = ThreadLocalRandom.current().nextInt(2, 6);
            int x = ThreadLocalRandom.current().nextInt(0, 5);
            for (int i = 1; i <= sizeX; i++) {
                if (i == 1)
                    randX += String.valueOf(x + i);
                else
                    randX = randX + "," + String.valueOf(x + i);

            }
        }

        String[] spiltX = randX.split(",");
        String[] spiltY = randY.split(",");
        int[] x = new int[spiltX.length];
        int[] y = new int[spiltY.length];
        for (int i = 0; i < spiltX.length; i++) {
            x[i] = Integer.parseInt(spiltX[i]);
        }
        for (int i = 0; i < spiltY.length; i++) {
            y[i] = Integer.parseInt(spiltY[i]);
        }
        insertRandomShipToArray(x, y);

    }

    //checking that inserted number has overlap with previous ones or not
    public boolean hasOverLap(int[][] ships, int[] x, int[] y) {
        boolean flag = false;
        if (isVertical(x)) {
            for (int i = 0; i < y.length; i++) {
                if (ships[x[0]][y[i]] == 1)
                    flag = true;
            }
        } else {

            for (int i = 0; i < x.length; i++) {
                if (ships[x[i]][y[0]] == 1)
                    flag = true;
            }
        }
        return flag;
    }


    public int[][] getShips() {
        return ships;
    }
}