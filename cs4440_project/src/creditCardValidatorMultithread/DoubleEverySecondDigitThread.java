package creditCardValidatorMultithread;

public class DoubleEverySecondDigitThread extends Thread{

	private volatile String card;
	private volatile int sum = 0;
	
	public DoubleEverySecondDigitThread(String card) {
		this.card = card;
	}
	
	@Override
	public void run() {
		for (int i = this.card.length() - 1; i >= 0; i--) {
			int number = this.card.charAt(i) - 48;
			if (i % 2 == 0) { // if the digit is an even one
				number = checkIfDoubleDigits(number * 2); // method to check doubling the digit will result in a dobule
															// digit number
				this.sum += number; // multiply it by 2 and add it to the sum
			}
		}
	}
	
	private synchronized static int checkIfDoubleDigits(int num) {
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

	public int getSum() {
		return sum;
	}

}
