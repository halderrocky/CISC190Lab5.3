package edu.sdccd.cisc190;

import java.util.Scanner;

/**
 * This application continually prompts the user to input
 * player names and their lap times. When complete, the user
 * enters x for the player name, then a table is printed
 * containing the Name and Lap Times sorted in ascending
 * lap time order. Here is an example run:
 *
 *Enter player name (x when complete): Bob
 *Enter player's lap time: 2.0
 *Enter player name (x when complete): Steve
 *Enter player's lap time: 1.0
 *Enter player name (x when complete): Chris
 *Enter player's lap time: 0.5
 *Enter player name (x when complete): x
 *-------------------------------------------------
 *| Name                             | Lap Time   |
 *-------------------------------------------------
 *| Chris                            | 0.5        |
 *| Steve                            | 1.0        |
 *| Bob                              | 2.0        |
 *-------------------------------------------------
 * @author Rocky Halder
 */
public class LapTimeSorter {
    public static final int EXTEND_ARRAY_INCREMENT = 5;
    private static String[] playerNames;
    private static float[] playerTimes;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        boolean exit = false;
        String playerName;
        float playerTime;
        int currentEmptyIndex = 0;

        extendArrays();

        while(!exit) {
            System.out.print("Enter player name (x when complete): ");
            playerName = input.next();

            if(playerName.equals("x")) break;

            System.out.print("Enter player's lap time (secs): ");
            playerTime = input.nextFloat();

            add(currentEmptyIndex, playerName, playerTime);
            currentEmptyIndex++;
        }

        sortArraysByLapTime();

        printArrays();
    }

    public static void printArrays() {
        // print table using the provided printTableLine() and printTableRow()
        printTableLine();
        printTableRow("Name", "Lap Time");
        printTableLine();
        for(int i = 0; i < playerTimes.length; i++) {
            if(playerNames[i] != null) {
                printTableRow(playerNames[i], String.valueOf(playerTimes[i]));
            }
        }
        printTableLine();
    }

    public static void printTableLine() {
        System.out.printf("-------------------------------------------------%n");
    }

    public static void printTableRow(String col1, String col2) {
        System.out.printf("| %-32s | %-10s |%n", col1, col2);
    }

    public static void sortArraysByLapTime() {
        // sort playerNames and playerLapTimes by asending lap times
        for(int i = 0; i < playerNames.length; i++) {
            float curPlayerTime = playerTimes[i];
            String curPlayerName = playerNames[i];

            // shift larger laptimes to the right
            int insertAtIndex = i;
            while(insertAtIndex >0 && playerTimes[insertAtIndex - 1] > curPlayerTime ) {
                playerTimes[insertAtIndex] = playerTimes[insertAtIndex - 1];
                playerNames[insertAtIndex] = playerNames[insertAtIndex - 1];
                insertAtIndex--;
            }
            playerTimes[insertAtIndex] = curPlayerTime;
            playerNames[insertAtIndex] = curPlayerName;
        }
    }

    /**
     * Adds a new player name and lap time pair into their respective arrays
     * at the same index
     * @param atIndex index to insert the passed in playerName and playerTime
     * @param playerName the new player name to insert in playerNames array
     * @param playerTime the new player time to insert into the playerTimes array
     */
    public static void add(int atIndex, String playerName, float playerTime) {
        // TODO call extendArrays() if arrays are not initialized or if index is beyond the size of the arrays

        if(playerNames == null || atIndex >= playerNames.length) {
            extendArrays();
        }
        playerTimes[atIndex] = playerTime;
        playerNames[atIndex] = playerName;
    }

    public static void extendArrays() {
        // initialize arrays with EXTEND_ARRAY_INCREMENT size if they are not yet initialized
        if(playerNames == null) {
            playerNames = new String[EXTEND_ARRAY_INCREMENT];
        } else {
            String[] newPlayerNames = new String[playerNames.length + EXTEND_ARRAY_INCREMENT];
            copyArray(playerNames, newPlayerNames);
            playerNames = newPlayerNames;
        }
         if (playerTimes == null) {
             playerTimes = new float[EXTEND_ARRAY_INCREMENT];
         } else {
             float[] newPlayerTimes = new float[playerTimes.length + EXTEND_ARRAY_INCREMENT];
             copyArray(playerTimes, newPlayerTimes);
             playerTimes = newPlayerTimes;
         }
    }
    public static void copyArray(String[] fromArr, String[] toArr) {
        // copy String arrays used after extending
        for(int i = 0; i < fromArr.length; i++) {
            toArr[i] = fromArr[i];
        }
    }
    public static void copyArray(float[] fromArr, float[] toArr) {
        // copy float arrays used after extending
    }
}
