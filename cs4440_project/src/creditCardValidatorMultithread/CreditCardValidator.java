package creditCardValidatorMultithread;

import java.util.Scanner;

// Francisco Contreras
// CS4440-02
// Project
// Comments: The CheckCardLength class is a thread implemented class but I forgot to put Thread at the end of the class name

public class CreditCardValidator {
	static String cardProvider;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String cardNumber;
		boolean cardLengthValid;
		boolean validIDNumber;
		boolean validCard;
		int sumOfNumbers;

		while (true) {
			System.out.println("--------------------------------------------------");
			System.out.println("Enter a Credit Card Number or type 'x' to leave the program : ");
			cardNumber = sc.next(); // get the card number from user input
				
			if(cardNumber.contains("x")) {
				break;
			}
			
			final long start = System.currentTimeMillis();
			
			cardLengthValid = checkCardLength(cardNumber); // STEP I: check if the card number has 13 to 19 digits	
			validIDNumber = getFirstDigit(cardNumber); // STEP II: Get the first digit of the card to check if it's a valid credit card provider
			sumOfNumbers = doubleEverySecondDigit(cardNumber) + addEveryOddDigit(cardNumber); // STEP III, IV, and V: get the sum of every doubled even placed digit and every odd placed digit then add them together
			validCard = isValidCard(sumOfNumbers, cardNumber); // STEP VI: check to see if the result of step V is divisible by 10
			
			
			if((!cardLengthValid) || (!validIDNumber) || (!validCard)) {
				System.out.println("\n" + cardNumber + " is not a valid card.\n");
			} else {
				System.out.println("\n" + cardNumber + " is a valid " + cardProvider);
			}
			
			final long end = System.currentTimeMillis();
			System.out.println("\nExecution time of the program with threads: " + (end - start) + " milliseconds\n");
			sc.nextLine();
		}
		
		sc.close();
	}

	// function to check of the sum of step 3 and 4 is divisible by 10 which will
	// make it a valid card
	public static boolean isValidCard(int sumOfNumbers, String cardNumber) {
		if (sumOfNumbers % 10 == 0) {
			return true;
		} else {
			return false;
		}
	}

	// function to check if the card length is valid
	public static boolean checkCardLength(String card) {
		CheckCardLength thread = new CheckCardLength(card);
		thread.start();
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return thread.isValid();
	}

	// function to get the first digit of the card and return true or false if it's a valid provider
	public static boolean getFirstDigit(String card) {	
		GetFirstDigitThread thread = new GetFirstDigitThread(card);
		thread.start();
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		cardProvider = thread.getCardProvider();
		return thread.isValid();
	}

	// function to add every odd digit from the card number from right to left
	public static int addEveryOddDigit(String card) {
		AddEveryOddDigitThread thread = new AddEveryOddDigitThread(card);
		thread.start();
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return thread.getSum();
	}

	// functions to double every second digit of the card from right to left
	public static int doubleEverySecondDigit(String card) {
		DoubleEverySecondDigitThread thread = new DoubleEverySecondDigitThread(card);
		thread.start();
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return thread.getSum();
	}


}
