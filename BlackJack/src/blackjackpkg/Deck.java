package blackjackpkg;

import java.util.ArrayList;
import java.util.Random;

public class Deck {
	
	private ArrayList<Card> cards;
	
	public Deck() { 
//		this is the constructor
		this.cards = new ArrayList<Card>();
	}
	
	public void createFullDeck() {
//		A void method that generates cards
		for (Suit cardSuit:Suit.values()) { //this line returns the four values in the Suit enum as an array, hence the .values modifier
//			However, we want 52 cards (because there are 52 cards in a deck.
			for (Values cardValue:Values.values()) { //this line returns the 13 values in the Values enum as an array, hence the .values modifier
//				We have 4 Suit enum values and 13 Values enum values, therefore, 13 times 4 = 52 values for our deck of cards.
				this.cards.add(new Card(cardSuit,cardValue));
			}
		}
	}
	
	public void shuffle () {
		ArrayList<Card> tempDeck = new ArrayList<Card>(); //creates a new temporary deck where we store shuffled cards
//		Using the Random class to shuffle
		Random random = new Random();
		int randomCardIndex = 0;
		int originalSize = this.cards.size(); //gets the size of the deck
		for (int i = 0; i < originalSize; i++) { //loops for the 52 indices of our card array
//			generating a random index with function random.nextInt((max - min) + 1) + min;
			randomCardIndex = random.nextInt((this.cards.size()- 1 - 0) + 1) + 0;
			tempDeck.add(this.cards.get(randomCardIndex)); //adds a new, random card to the tempDeck.
			this.cards.remove(randomCardIndex); //removes the card that was added to the tempDeck from the original deck of cards.
		}
		this.cards = tempDeck; // this puts the shuffled cards back into our original deck.
	}
	
	public String toString() { //returns the cards in a string format
		String cardListOutput = "";
		for (Card aCard:this.cards) {
//			Loop that creates a string with all card values (and their indices) in it
			cardListOutput += "\n " + aCard.toString();
		}
		return cardListOutput; //prints the above for loop.
	}
	
	public void removeCard(int i) { //removes an index (a card) from out array list of cards
		this.cards.remove(i);
	}
	
	public Card getCard(int i) { //returns a random card
		return this.cards.get(i);
	}
	
	public void addCard(Card addCard) { //adds a card to the deck of cards
		this.cards.add(addCard);
	}
	
	//Methods for drawing a card from the deck. Moves a card from one deck to another.
	public void draw(Deck comingFrom) { //the deck the card is coming from
		this.cards.add(comingFrom.getCard(0)); //gets the first card in the "coming from" deck and adds it to a new deck.
		comingFrom.removeCard(0); //removes that first card from the deck that the card came from.
	}
	
	public int deckSize () { //returns the amount of cards in the deck.
		return this.cards.size();
	}
	
	public void moveAllToDeck (Deck moveTo) { //moves player's and dealer's cards back to the playing deck.
		int thisDeckSize = this.cards.size();
		for (int i = 0; i < thisDeckSize; i++) {
			moveTo.addCard(this.getCard(i));
		}
		for (int i = 0; i < thisDeckSize; i++) { //removes the card from the player's and/or dealer's deck after adding it back to the playing deck.
			this.removeCard(0);
		}
	}
	
	public int cardsValue() { //returns the total value of the cards in a player's or dealer's deck.
		int totalValue = 0;
		int aces = 0;
		for (Card aCard : this.cards) { //this line reads as "for every card in the deck of cards on which this method is run".
			switch(aCard.getValue()) { //checks the value of every card
			case TWO: totalValue += 2; break;
			case THREE: totalValue += 3; break;
			case FOUR: totalValue += 4; break;
			case FIVE: totalValue += 5; break;
			case SIX: totalValue += 6; break;
			case SEVEN: totalValue += 7; break;
			case EIGHT: totalValue += 8; break;
			case NINE: totalValue += 9; break;
			case TEN: totalValue += 10; break;
			case JACK: totalValue += 10; break;
			case QUEEN: totalValue += 10; break;
			case KING: totalValue += 10; break;
			case ACE: aces += 1; break;
			}
		}
		for (int i = 0; i < aces; i++) { //a for loop to determine whether an ace has a value of 1 or 11 depending on the number of aces the player has.
			if (totalValue > 10) { //if the total value of the player's deck is greater than 10, the value of the ace is 1.
				totalValue += 1;
			}
			else { // else, the value of the ace is 11.
				totalValue += 11;
			}
		}
		return totalValue;
	}

}
