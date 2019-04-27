package com.company;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Running {
    private int[][] player1;
    private int[][] player2;


    public Running(Ship player1, Ship player2) {
        this.player1 = player1.getShips();
        this.player2 = player2.getShips();
    }

    //use this method to hit a place of rival map (shoot to ships) in game with another player
    public void shooting(int[][] rival) {
        System.out.println("shoot:");
        System.out.println("enter x position:");
        Scanner a = new Scanner(System.in);
        String xInput = a.nextLine();
        System.out.println("enter y position:");
        Scanner b = new Scanner(System.in);
        String yInput = b.nextLine();
        int x = Integer.parseInt(xInput);
        int y = Integer.parseInt(yInput);
        if (x < 0 || x > 9 || y < 0 || y > 9) {
            System.out.println("out of range please try again!!");
            shooting(rival);
        } else {

            if (rival[x][y] == 2 || rival[x][y] == 3) {
                System.out.println("you already shoot to this place , choose another one! ");
                shooting(rival);
            } else {
                if (rival[x][y] == 1)
                    //2 means you shoot correct
                    rival[x][y] = 2;
                else
                    //3 means you shoot wrong
                    rival[x][y] = 3;
            }
        }
    }

    //use this method to hit a place of rival map (shoot to ships) in game with computer
    public void shootingForComputer(int[][] rival) {

        int x = ThreadLocalRandom.current().nextInt(0, 10);
        int y = ThreadLocalRandom.current().nextInt(0, 10);
        if (x < 0 || x > 9 || y < 0 || y > 9) {
            shootingForComputer(rival);
        } else {
            if (rival[x][y] == 2 || rival[x][y] == 3) {
                shootingForComputer(rival);
            } else {
                if (rival[x][y] == 1)
                    //2 means you shoot correct
                    rival[x][y] = 2;
                else
                    //3 means you shoot wrong
                    rival[x][y] = 3;
            }
        }
    }

    // this method use for shooting in random way
    public void randomShooting(int[][] rival) {
        System.out.println("shoot:");
        System.out.println("enter x position:");
        Scanner a = new Scanner(System.in);
        String xInput = a.nextLine();
        System.out.println("enter y position:");
        Scanner b = new Scanner(System.in);
        String yInput = b.nextLine();
        int originalX = Integer.parseInt(xInput);
        int originalY = Integer.parseInt(yInput);

        if (checkAroundPlacesIsFull(rival, originalX, originalY)) {
            System.out.println("all around places of this place is full please insert another one!");
            randomShooting(rival);
        }
        int randomX = makeRandomPlace(originalX);
        int randomY = makeRandomPlace(originalY);
        while (rival[randomX][randomY] == 2 || rival[randomX][randomY] == 3) {
            randomX = makeRandomPlace(originalX);
            randomY = makeRandomPlace(originalY);
        }

        if (rival[randomX][randomY] == 1) {
            //2 means you shoot correct
            rival[randomX][randomY] = 2;
        } else
            //3 means you shoot wrong
            rival[randomX][randomY] = 3;
    }

    //this method get a number and return a random number in range [number-1,number+1]
    public int makeRandomPlace(int num) {
        int randomX;
        if (num == 0) {
            randomX = ThreadLocalRandom.current().nextInt(0, 2);
        } else if (num == 9) {
            randomX = ThreadLocalRandom.current().nextInt(8, 10);
        } else {
            randomX = ThreadLocalRandom.current().nextInt(num - 1, num + 2);
        }
        return randomX;
    }

    //for random shooting if player choose a place that all of the around places have been shoot before return true
    public boolean checkAroundPlacesIsFull(int[][] map, int x, int y) {
        boolean temp = true;
        if (x == 0 && y == 0) {
            if (!((map[0][0] == 2 || map[0][0] == 3) && (map[0][1] == 2 || map[0][1] == 3) && (map[1][0] == 2 || map[1][0] == 3) && (map[1][1] == 3 || map[1][1] == 2)))
                temp = false;
        } else if (x == 9 && y == 9) {
            if (!(((map[8][8] == 2 || map[8][8] == 3) && (map[8][9] == 2 || map[8][9] == 3) && (map[9][8] == 2 || map[9][8] == 3) && (map[9][9] == 2 || map[9][9] == 3))))
                temp = false;
        } else if (x == 0) {
            for (int i = y - 1; i < y + 2; i++) {
                if (map[0][i] == 2 || map[0][i] == 3) {
                    temp = temp & true;
                } else
                    temp = temp & false;
            }
        } else if (y == 9) {
            for (int i = x - 1; i < x + 2; i++) {
                if (map[x][9] == 2 || map[x][9] == 3) {
                    temp = temp & true;
                } else
                    temp = temp & false;
            }
        } else if (x == 9) {
            for (int i = y - 1; i < y + 2; i++) {
                if (map[9][i] == 2 || map[9][i] == 3) {
                    temp = temp & true;
                } else
                    temp = temp & false;
            }
        } else if (y == 0) {
            for (int i = x - 1; i < x + 2; i++) {
                if (map[x][0] == 2 || map[x][0] == 3) {
                    temp = temp & true;
                } else
                    temp = temp & false;
            }
        } else {
            for (int i = x - 1; i < x + 2; i++) {
                for (int j = y - 1; j < y + 2; j++) {
                    if (map[i][j] == 2 || map[i][j] == 3) {
                        temp = temp & true;
                    } else
                        temp = temp & false;
                }
            }
        }
        return temp;
    }

    //check is game over or not
    public boolean isGameOver(int[][] player) {
        boolean flag = true;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (player[i][j] == 1) {
                    flag = false;
                    break;
                }
            }
        }
        return flag;
    }

    // we have two type of shooting 1-random 2- ordinary
    public boolean typeOfShoot() {
        boolean flag = true;
        System.out.println("choose your shoot type(if ordinary shoot enter 1 or if random shoot enter 0)");
        Scanner shootType1 = new Scanner(System.in);
        String playerShotType = shootType1.nextLine();
        if (playerShotType.equals("0"))
            flag = false;
        return flag;
    }

    //start game here in game with another player
    public void startGame() {
        ArrayList<Boolean> playersShootType = new ArrayList<>();
        ArrayList<int[][]> players = new ArrayList<>();

        boolean shootType1 = typeOfShoot();
        boolean shootType2 = typeOfShoot();
        playersShootType.add(shootType1);
        playersShootType.add(shootType2);
        players.add(player1);
        players.add(player2);
        int turn = 0;
        while (!(isGameOver(player1) || isGameOver(player2))) {
            printPlayerMap(players.get(turn % 2));
            printRivalMap(players.get((turn + 1) % 2));
            if (playersShootType.get(turn % 2))
                shooting(players.get((turn + 1) % 2));
            else
                randomShooting(players.get((turn + 1) % 2));
            System.out.println("\n ****Result after shooting****\n");
            printPlayerMap(players.get(turn % 2));
            printRivalMap(players.get((turn + 1) % 2));
            System.out.println("####################################################################");
            turn++;
        }
        System.out.println("game is over, player " + (turn + 1 % 2) + 1 + " wins");
    }

    public void printPlayerMap(int[][] ship) {
        System.out.println("*****player map*****");
        System.out.println("  | 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 |");
        System.out.println("-------------------------------------------");
        for (int i = 0; i < 10; i++) {
            System.out.print(i + " |");
            for (int j = 0; j < 10; j++) {
                if (ship[i][j] == 1)
                    System.out.print(" @ |");
                if (ship[i][j] == 2)
                    System.out.print(" # |");
                if (ship[i][j] == 3 || ship[i][j] == 0)
                    System.out.print("   |");
            }
            System.out.println("\n-------------------------------------------\n");
        }

    }

    public void printRivalMap(int[][] ship) {

        System.out.println("*****players's rival map*****");
        System.out.println("  | 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 |");
        System.out.println("-------------------------------------------");
        for (int k = 0; k < 10; k++) {
            System.out.print(k + " |");
            for (int m = 0; m < 10; m++) {
                if (ship[k][m] == 3)
                    System.out.print(" X |");
                if (ship[k][m] == 2)
                    System.out.print(" & |");
                if (ship[k][m] == 1 || ship[k][m] == 0)
                    System.out.print("   |");
            }
            System.out.println("\n-------------------------------------------\n");
        }
    }

    //start game here in game with computer
    public void startGameWithComputer() {
        ArrayList<int[][]> players = new ArrayList<>();
        boolean shootType1 = typeOfShoot();
        players.add(player1);
        players.add(player2);
        int turn = 0;
        while (!(isGameOver(player1) || isGameOver(player2))) {
            if (turn % 2 == 0) {
                printPlayerMap(players.get(0));
                printRivalMap(players.get(1));
                if (shootType1)
                    shooting(players.get(1));
                else
                    randomShooting(players.get(1));
                System.out.println("\n ****Result after shooting****\n");
                printPlayerMap(players.get(0));
                printRivalMap(players.get(1));
                System.out.println("####################################################################");
            } else {
                shootingForComputer(players.get(0));
            }
            turn++;
        }
        System.out.println("game is over, player " + (turn + 1 % 2) + 1 + " wins");
    }
}

