package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Ship player1 = new Ship();
        Ship player2 = new Ship();
        int number = 0;
        System.out.println("Do yoy Want to play with computer or another player??\n For playing with computer enter \"0\" and" +
                " for playing with another player enter \"1\"");
        Scanner a = new Scanner(System.in);
        String gameType = a.nextLine();
        System.out.println("insert the place of each ship (5 times) in this template::\n" +
                " for example if you want to define a ship with size 3 on" +
                " x and y axis you should insert for x : 1,2,3 and for y: 2 it means {(1,2)(2,2)(3,2)} ");
        if (gameType.equals("0")) {
            for (int i = 0; i < 2; i++) {
                number = i + 1;
                System.out.println("make ship number \"" + number + "\"");
                player1.makeShip();
            }
            for (int i = 0; i < 1; i++) {
                player2.makeShipRandomForComputer();
            }
            Running newGame = new Running(player1, player2);
            newGame.startGameWithComputer();
        } else {
            for (int i = 0; i < 1; i++) {
                number = i + 1;
                System.out.println("make ship number \"" + number + "\"");
                player1.makeShip();
            }
            System.out.println("\n for second ship\n");
            for (int i = 0; i < 1; i++) {
                number = i + 1;
                System.out.println("make ship number \"" + number + "\"");
                player2.makeShip();
            }
            Running newGame = new Running(player1, player2);
            newGame.startGame();

        }
    }
}
