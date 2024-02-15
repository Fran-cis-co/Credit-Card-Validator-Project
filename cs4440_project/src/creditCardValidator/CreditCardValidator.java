package creditCardValidator;

import java.util.Scanner;

public class CreditCardValidator {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String cardNumber;
		int sumOfNumbers;

		while (true) {
			System.out.println("Enter a Credit Card Number or type 'exit' to leave the program : ");
			cardNumber = sc.next();
			
			if(cardNumber.contains("exit")) {
				break;
			}
			
			long start = System.currentTimeMillis();

			// check if the card number has 13 to 19 digits
			if (checkCardLength(cardNumber)) {
				// Get the first digit of the card to check if it's a valid credit card provider
				if(!getFirstDigit(cardNumber)) {
					continue;
				}
			} else { // if not, ask the user to enter a correct digits length
				System.out.println("/nInvalid card length. Please input a correct card length value between 13 through 19/n");
				sc.nextLine();
				continue;
			}

			// add the sum of every even and odd digit
			sumOfNumbers = doubleEverySecondDigit(cardNumber) + addEveryOddDigit(cardNumber);
			
			
			long end = System.currentTimeMillis();
			isValidCard(sumOfNumbers, cardNumber); // check to see if the card is valid
			
			System.out.println("\nExecution time of the program without threads: " + (end - start) + " milliseconds\n");
			sc.nextLine();
		}
		
		sc.close();
	}

	// function to check of the sum of step 3 and 4 is divisible by 10 which will
	// make it a valid card
	public static void isValidCard(int sumOfNumbers, String cardNumber) {
		if (sumOfNumbers % 10 == 0) {
			System.out.println(cardNumber + " is a valid card");
		} else {
			System.out.println(cardNumber + " is a invalid card");
		}
	}

	// function to check if the card length is valid
	public static boolean checkCardLength(String cardLength) {
		int length = cardLength.length();

		if ((length >= 13) && (length <= 19)) {
			return true;
		} else {
			return false;
		}
	}

	// function to get the first digit of the card and return true or false if it's a valid provider
	public static boolean getFirstDigit(String card) {	
		// use switch case for finding out what card provider is given depending on the first digit
		switch (Integer.parseInt(card.substring(0, 1))) {
		case 3:
			System.out.println("\nYou have a American Express Card\n");
			return true;
		case 6:
			System.out.println("\nYou have a Discovery Card\n");
			return true;
		case 5:
			System.out.println("\nYou have a Master Card\n");
			return true;
		case 4:
			System.out.println("\nYou have a Visa Card\n");
			return true;
		default:
			System.out.println("\nYou do not have a card provider we accept, try another card.\n");
			return false;
		}
	}

	// function to add every odd digit from the card number from right to left
	public static int addEveryOddDigit(String cardNumber) {
		int sum = 0;

		for (int i = cardNumber.length() - 1; i >= 0; i--) {
			int number = cardNumber.charAt(i) - 48; // subtract the char by 48 to get the ascii value of the integer of
													// the digit
			if (i % 2 != 0) { // if the digit is an odd one
				sum += number; // add number to the sum
			}
		}

		return sum; // return all the added odd numbers
	}

	// functions to double every second digit of the card from right to left
	public static int doubleEverySecondDigit(String cardNumber) {
		int sum = 0;

		for (int i = cardNumber.length() - 1; i >= 0; i--) {
			int number = cardNumber.charAt(i) - 48;
			if (i % 2 == 0) { // if the digit is an even one
				number = checkIfDoubleDigits(number * 2); // method to check doubling the digit will result in a dobule
															// digit number
				sum += number; // multiply it by 2 and add it to the sum
			}
		}

		return sum; // return sum
	}

	private static int checkIfDoubleDigits(int num) {
		String numLength = Integer.toString(num);
		int newNum = 0;

		// if doubled digit has two digits, split the number up and add digit 1 and
		// digit 2
		if (numLength.length() > 1) {
			int digit1 = numLength.charAt(0) - 48;
			int digit2 = numLength.charAt(1) - 48;

			newNum += digit1 + digit2;
			return newNum;
		} else {
			return num;
		}

	}

}
