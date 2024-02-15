package creditCardValidatorMultithread;

public class GetFirstDigitThread extends Thread{
	private volatile String card;
	private volatile boolean isValid;
	private volatile String cardProvider;
	public GetFirstDigitThread(String card) {
		this.card = card;
	}
	
	@Override
	public void run() {
		// use switch case for finding out what card provider is given depending on the first digit
		switch (Integer.parseInt(card.substring(0, 1))) {
		case 3:
			this.cardProvider = "American Express Card\n";
			this.isValid = true;
			break;
		case 6:
			this.cardProvider = "Discovery Card\n";
			this.isValid = true;
			break;
		case 5:
			this.cardProvider = "Master Card\n";
			this.isValid = true;
			break;
		case 4:
			this.cardProvider = "Visa Card\n";
			this.isValid = true;
			break;
		default:
			this.isValid = false;
			break;
		}
	}

	public boolean isValid() {
		return isValid;
	}
	
	public String getCardProvider(){
		return cardProvider;
	}

}
