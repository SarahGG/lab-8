import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Player {
    private Scanner scnr = new Scanner(System.in);
    private int totalBases = 0;
    private int totalBaseHits = 0;
    private Double slugPerc;
    private Double batAvg;
    private String playerLastName;
    private String playerFirstName;
    private ArrayList<Integer> basesPerBat = new ArrayList<>();

    public void setPlayerInfo(){
        int atBats;

        //receives and validates the player's first name
        playerFirstName = validateName("What is the player's first name?: ",
                "^[a-z'A-Z-\\s]+$");

        //receives and validates the player's last name
        playerLastName = validateName("What is the player's last name?: ",
                "^[a-z'A-Z-\\s]+$");

        //receives atBats and validates that it's ABOVE 0
        atBats = validateInt();

        //gets bases for each at-bat, validates that it's a number between BETWEEN -1 and 5 (noninclusive)
        setBats(atBats);

        //adds spacing after the bases have been entered
        System.out.println();

        // calculates averages, then truncates them to 0.000 decimals
        slugPerc = ((double)((int)(((double) totalBases / atBats) * 1000))) / 1000;
        batAvg = ((double)((int)(((double) totalBaseHits / atBats) * 1000))) / 1000;
    }

    private void setBats(int atBats) {
        int currentAtBat;

        System.out.println("Please enter the bases earned for each time batting: ");

        for (int i = 1; i <= atBats; i++) {
            //gets an at-bat number from user and validates it
            currentAtBat = validateBases(i + ": ");

            //adds the number to a flexible array
            basesPerBat.add(currentAtBat);

            //adjusts my total base number
            totalBases += currentAtBat;

            //if player did the thing, total base hits gets incremented
            if (currentAtBat > 0) {
                ++totalBaseHits;
            }
        }
        if (scnr.hasNextLine()) {
            scnr.nextLine();
        }
    }

    private int validateBases(String prompt) {
        boolean wrongInput = true;
        int returnedNum = 0;

        while(wrongInput) {
            System.out.print(prompt);
            try {
                returnedNum = scnr.nextInt();

                if(returnedNum < 0 || 4 < returnedNum) {
                    throw new InputMismatchException("This is not in range.");
                } else {
                    return returnedNum;
                }
            } catch (InputMismatchException wrongType) {
                System.out.println("Error!" + wrongType.getMessage());
            }
        }
        return returnedNum;
    }

    private int validateInt() {
        boolean wrongInput = true;
        int returnedNum = 0;

        while(wrongInput) {
            System.out.print("How many times did " + playerFirstName + " go to bat?: ");
            try {
                returnedNum = scnr.nextInt();

                if(returnedNum < 0) {
                    throw new InputMismatchException("This is not in range.");
                } else {
                    wrongInput = false;
                    return returnedNum;
                }
            } catch (InputMismatchException wrongType) {
                System.out.println("Error!" + wrongType.getMessage());
            }
        }
        return returnedNum;
    }

    private String validateName(String methodPrompt, String regexCode) {
        boolean wrongInput = true;
        String stringName = "";

        while(wrongInput) {
            System.out.print(methodPrompt);
            try {
                wrongInput = true;
                stringName = scnr.nextLine();

                // if the input is anything but a name with letters, hyphens, apostrophes, and spaces.
                if (!(stringName.matches(regexCode))) {
                    // pushes back if this is an email, or other non-name word
                    throw new InputMismatchException("This is not an appropriate response.");
                } else {
                    // pushes out of our while loop
                    wrongInput = false;
                }
            } catch (InputMismatchException wrongFirstName) {
                System.out.println(wrongFirstName.getMessage());
            }
        }
        return stringName;
    }

    public String getPlayerFirstName() {
        return playerFirstName;
    }
    public String getPlayerLastName() {
        return playerLastName;
    }
    public Double getSlugPerc() {
        return slugPerc;
    }
    public Double getBatAvg() {
        return batAvg;
    }
    public ArrayList getBasesPerBat() {
        return basesPerBat;
    }
}
