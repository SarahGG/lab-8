import java.util.ArrayList;
import java.util.Scanner;

public class LabEight {

    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        boolean doAgain;
        int numPlayers;
        ArrayList<Player> team = new ArrayList<>();

        do {
            //resetting my loop check
            doAgain = false;

            System.out.print("Please enter the number of players on the team: ");
            try {
                // accepts the number of players on the team
                numPlayers = scnr.nextInt();

                // spacing between players
                System.out.println();

                for(int i = 0; i < numPlayers; i++) {
                    // initializes numPlayers amount of elements in ArrayList team
                    team.add(new Player());
                    // sets and stores information for each player
                    team.get(i).setPlayerInfo();
                }

                System.out.println("Here are your player statistics: ");
                for (int i = 0; i < numPlayers; i++) {
                    System.out.println("Player " + i + ": "
                            + team.get(i).getPlayerFirstName() + " "
                            + team.get(i).getPlayerLastName());
                    System.out.println("\t Slugging Percentage: " + team.get(i).getSlugPerc()
                            + "\t\tBatting Average: " + team.get(i).getBatAvg());
                    System.out.println("\t\tBases Earned: \n\t\t" + team.get(i).getBasesPerBat());
                    System.out.println();
                }

            } catch (Exception isNotInt) {
                System.out.println(isNotInt.getMessage() + " This try failed");
                doAgain = true;
            } finally {
                scnr.next();
            }
        } while(doAgain);
        System.out.println("Goodbye!");
    }
}