import java.util.Scanner; // Importing the scanner class for user input
import java.util.ArrayList; // ArrayList for capturing user guesses
import java.util.HashMap; // HashMap for creating a map of my data
import java.util.Map;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) {
        // Establish 2D Array with states and their corresponding capitals
        String[][] stateCapitalArray = {
            {"Alabama", "Alaska", "Arizona", "Arkansas", "California", "Colorado", "Connecticut", "Delaware", "Florida", "Georgia", "Hawaii", "Idaho", "Illinois", "Indiana", "Iowa", "Kansas", "Kentucky", "Louisiana", "Maine", "Maryland", "Massachusetts", "Michigan", "Minnesota", "Mississippi", "Missouri", "Montana", "Nebraska", "Nevada", "New Hampshire", "New Jersey", "New Mexico", "New York", "North Carolina", "North Dakota", "Ohio", "Oklahoma", "Oregon", "Pennsylvania", "Rhode Island", "South Carolina", "South Dakota", "Tennessee", "Texas", "Utah", "Vermont", "Virginia", "Washington", "West Virginia", "Wisconsin", "Wyoming"},
            {"Montgomery", "Juneau", "Phoenix", "Little Rock", "Sacramento", "Denver", "Hartford", "Dover", "Tallahassee", "Atlanta", "Honolulu", "Boise", "Springfield", "Indianapolis", "Des Moines", "Topeka", "Frankfort", "Baton Rouge", "Augusta", "Annapolis", "Boston", "Lansing", "Saint Paul", "Jackson", "Jefferson City", "Helena", "Lincoln", "Carson City", "Concord", "Trenton", "Santa Fe", "Albany", "Raleigh", "Bismarck", "Columbus", "Oklahoma City", "Salem", "Harrisburg", "Providence", "Columbia", "Pierre", "Nashville", "Austin", "Salt Lake City", "Montpelier", "Richmond", "Olympia", "Charleston", "Madison", "Cheyenne"}
        };
       
        // Asks the user to enter a capital city name using Scanner method
        Scanner sc = new Scanner(System.in);
        System.out.print("Please enter the name of any capital city: ");
        String userCapitalCity = sc.nextLine();
       
        // Calls checkIfValidCapitalCity method with userCapitalCity and twoDArray as arguments
        boolean found = checkIfValidCapitalCity(userCapitalCity, stateCapitalArray);
        // If city is not found print this
        if (!found) {
            System.out.println("That is incorrect. " + userCapitalCity + " is not a capital city.");
        }
       
        // Calls printSortedArrayByState to print original 2D Array
        printSortedArrayByState(stateCapitalArray);

        // Calls bubbleSortByCity to bubble sort the array
        bubbleSortByCity(stateCapitalArray);
        // Calls printSortedArrayByCity to print out newly bubble sorted array
        printSortedArrayByCity(stateCapitalArray);
       
        // User guess as many state capitals as they can section
        ArrayList<String> userGuessList = playStateCapitalGame(sc);
       
        System.out.println("-----------------------------------------------------");
        System.out.println("Your guesses: " + userGuessList);
        int score = calculateScore(userGuessList, stateCapitalArray);
        System.out.println("You guessed " + score + " state capitals correctly!");
       
        // Create map and Convert 2D Array to a map
        Map<String, String> stateCapitalHashMap = new HashMap<>();
        // Adding twoDArray elements to stateCapitalHashMap
        for (int i = 0; i < stateCapitalArray[0].length; i++) {
            String state = stateCapitalArray[0][i];
            String capital = stateCapitalArray[1][i];
            stateCapitalHashMap.put(state, capital);
        }
        // Prints out HashMap
        System.out.println("-----------------------------------------------------");
        System.out.println("HashMap: " + stateCapitalHashMap);
       
        // Create a TreeMap (stateCapitalTreeMap) out of the HashMap data
        Map<String, String> stateCapitalTreeMap = new TreeMap<>(stateCapitalHashMap);
        System.out.println("-----------------------------------------------------");
        System.out.println("TreeMap: " + stateCapitalTreeMap);
       
        // Ask user to enter a state then display corresponding capital city
        System.out.println("-----------------------------------------------------");
        System.out.println("Enter any state: (Please capitalize the first letter)");
        String userState = sc.next();
       
        String stateCapital = stateCapitalTreeMap.get(userState);
       
        if (stateCapital != null) {
            System.out.println("You chose " + userState + ". The corresponding capital city is: " + stateCapital);
        } else {
            System.out.println("That is not a valid state.");
        }
    }
   
    // Method to check if the user inputed a valid capital city
    public static boolean checkIfValidCapitalCity(String city, String[][] arr) {
        for (int i = 0; i < arr[1].length; i++) {
            if (arr[1][i].equalsIgnoreCase(city)) {
                System.out.println("You are correct! " + arr[1][i] + " is a capital city.");
                return true;
            }
        }
        return false;
    }
   
    // Method to print original array
    public static void printSortedArrayByState(String[][] arr) {
        System.out.println("-----------------------------------------------------");
        System.out.println("This is the contents of the 2D Array sorted by State:");
        printArray(arr);
    }
   
    // Method to print bubble sorted array
    public static void printSortedArrayByCity(String[][] arr) {
        System.out.println("-----------------------------------------------------");
        System.out.println("This is the contents of the 2D Array bubble sorted by Capital City:");
        printArray(arr);
    }
   
    // Bubble sort method
    public static void bubbleSortByCity(String[][] arr) {
        int n = arr[0].length;
        boolean swapped;

        do {
            swapped = false;
            for (int i = 0; i < n - 1; i++) {
                if (arr[1][i].compareTo(arr[1][i + 1]) > 0) {
                    swap(arr[1], i, i + 1);
                    swap(arr[0], i, i + 1);
                    swapped = true;
                }
            }
        } while (swapped);
    }

    // Swap method
    public static void swap(String[] arr, int i, int j) {
        String temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    //Method for iterating through the array and printing its values
    public static void printArray(String[][] arr) {
        for (int i = 0; i < arr[0].length; i++) {
            System.out.println(arr[0][i] + " - " + arr[1][i]);
        }
    }
   
    //User guess State Capitals game
    public static ArrayList<String> playStateCapitalGame(Scanner scanner) {
        System.out.println("-----------------------------------------------------");
        System.out.println("Now, you will be guessing as many state capitals as you can.");
        System.out.println("Make sure to press enter after every guess.");
        System.out.println("-----------------------------------------------------");
        System.out.println("Enter your guesses: (Type exit when finished)");

        ArrayList<String> userGuessList = new ArrayList<>();
        boolean active = true;
       
        // Loop for user input, if user types "exit" then loop ends
        while (active) {
            String userGuess = scanner.nextLine();
            if (userGuess.equalsIgnoreCase("exit")) {
                active = false;
            } else {
                userGuessList.add(userGuess);
            }
        }
        return userGuessList;
    }

    // Method to calculate score for User guess State Capitals game
    public static int calculateScore(ArrayList<String> userGuessList, String[][] twoDArray) {
        int score = 0;
        for (String guess : userGuessList) {
            for (int i = 0; i < twoDArray[1].length; i++) {
                if (guess.equalsIgnoreCase(twoDArray[1][i])) {
                    score++;
                }
            }
        }
        return score;
    }
}
