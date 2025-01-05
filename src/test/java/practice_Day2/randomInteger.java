package practice_Day2;

import java.util.Random;

public class randomInteger {
	
	    private static int lastID = -1;
	    private static Random random = new Random();

	    public static int generateNewID() {
	        int newID;
	        do {
	            newID = random.nextInt(10); // Generates a random number between 0 and 9
	        } while (newID == lastID);
	        lastID = newID;
	        return newID;
	    }

	    public static void main(String[] args) {
	        // Example usage
	        for (int i = 0; i < 10; i++) {
	            System.out.println("Generated ID: " + generateNewID());
	        }
	    }
	}

