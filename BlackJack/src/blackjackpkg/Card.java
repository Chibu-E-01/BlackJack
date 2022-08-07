package blackjackpkg;

public class Card {
	
	private Suit suit;
	private Values value;
	
	public Card(Suit suit, Values value) { 
//		this is the constructor
		this.value = value;
		this.suit = suit;
	}
	
	public String toString() { 
//		a method that returns the below values
		return this.suit.toString() + "-" + this.value.toString();
	}
	
	public Values getValue() {
		return this.value;
	}

}
