package creditCardValidatorMultithread;

public class AddEveryOddDigitThread extends Thread{
	private volatile String card;
	private volatile int sum = 0;
	
	public AddEveryOddDigitThread(String card) {
		this.card = card;
	}
	
	@Override
	public void run() {
		for (int i = this.card.length() - 1; i >= 0; i--) {
			int number = this.card.charAt(i) - 48; // subtract the char by 48 to get the ascii value of the integer of
													// the digit
			if (i % 2 != 0) { // if the digit is an odd one
				this.sum += number; // add number to the sum
			}
		}
	}
	
	public int getSum() {
		return sum;
	}
}
