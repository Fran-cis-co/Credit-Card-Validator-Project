package creditCardValidatorMultithread;

public class CheckCardLength extends Thread{

	private volatile String card;
	private volatile boolean isValid;
	public CheckCardLength(String card) {
		this.card = card;
	}
	
	@Override
	public void run() {
		int length = this.card.length();

		if ((length >= 13) && (length <= 19)) {
			this.isValid = true;
		} else {
			this.isValid = false;
		}
	}

	public boolean isValid() {
		return isValid;
	}

}
