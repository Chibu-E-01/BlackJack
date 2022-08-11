package blackjackpkg;

import java.util.Scanner;

public class BlackJack {
	
	public static void main(String[] args) {
		System.out.println("Welcome to Blackjack via Java!\n");
//		Creating a playing deck of 52 cards
		Deck playingDeck = new Deck();
		playingDeck.createFullDeck();
		playingDeck.shuffle(); //shuffles the deck of cards
		
//		Creating the cards that the dealer and player have.
		Deck playerDeck = new Deck();
		Deck dealerDeck = new Deck();
		
		double playerMoney = 500.00;
		
		Scanner input = new Scanner(System.in);
		
//		Game Loop
		while (playerMoney >= 0) {
			System.out.print("Do you want to start the game? [1] Yes and [2] No.: ");
			playingDeck.shuffle();
			int startGame = input.nextInt();
			if (playerMoney == 0) {
				playerMoney = playerMoney + 500.00;
			}
			if (startGame == 1) {
				while (playerMoney > 0) { // While the player still has money, the game continues
			System.out.print("\nYou have $" + playerMoney + ". How much do you want to bet?: ");
			double playerBet = input.nextDouble();
			if (playerBet > playerMoney) {
				System.out.println("You don't even have that much. Leave our casino!");
				break;
			}
			if (playerBet <= 0) {
				System.out.println("Please bet something...");
				break;
			}
			
			boolean endRound = false;
			
//			We can start dealing now.
//			The player starts with 2 cards.
			playerDeck.draw(playingDeck); //so we're drawing an index (a card) from the shuffled playing deck and putting it in the player's deck.
			playerDeck.draw(playingDeck); //since the player starts with 2 cards, we run the command twice.
			
//			The dealer also starts with 2 cards.
			dealerDeck.draw(playingDeck);
			dealerDeck.draw(playingDeck);
			
			while (true) {
				System.out.println("Player's Hand: ");
				System.out.print(playerDeck.toString() + "\n");
				System.out.println("\nThe value of your deck is " + playerDeck.cardsValue());
//				Displaying the dealer's deck
				System.out.println("\nThe Dealer's Hand is " + dealerDeck.getCard(0).toString() + " and (Hidden).");
//				Asking the player to hit or stand
				System.out.print("Do you want to [1]Hit or [2]Stand?: ");
				int HitOrStand = input.nextInt(); 
//				Player decides to hit.
				if (HitOrStand == 1) {
					playerDeck.draw(playingDeck);
					System.out.println("You drew a " + playerDeck.getCard(playerDeck.deckSize()-1).toString() + "."); //we need a -1 included to get the proper index value.
//					Player bust if total card value > 21
					if (playerDeck.cardsValue() > 21) {
						System.out.println("\nBust! Lol! Your cards are valued at " + playerDeck.cardsValue() + ".");
						playerMoney -= playerBet;
						endRound = true; //ends the round when the player busts.
						break;
					}
				}
				if (HitOrStand == 2) {
					break;
				}
				
			}
			
//			Revealing dealer's cards
			System.out.println("\nDealer's cards are " + dealerDeck.toString());
			
//			Dealer draws from 1 to 16 and stands at 17 (or more).
			while (dealerDeck.cardsValue() < 17 && endRound == false) {
				dealerDeck.draw(playingDeck);
				System.out.println("The Dealer drew a " + dealerDeck.getCard(dealerDeck.deckSize() - 1).toString() + ".");
			}
			
//			Displaying the value of the dealer's cards
			System.out.println("\nThe value of the Dealer's hand is " + dealerDeck.cardsValue() + ".");
			
//			If dealer busts
			if (dealerDeck.cardsValue() > 21 && endRound == false) {
				System.out.println("The Dealer busts! Lol! You win!");
				playerMoney += playerBet;
				endRound = true;
			}
			
//			If there is a tie
			if (playerDeck.cardsValue() == dealerDeck.cardsValue() && endRound == false) {
				System.out.println("\nPush!");
				endRound = true;
			}
			
//			If the player wins
			if (playerDeck.cardsValue() > dealerDeck.cardsValue() && endRound == false) {
				System.out.println("\nYou win the hand!");
				playerMoney += playerBet;
				endRound = true;
			}
			
//			Checking if dealer's cards' total value is more than the player's
			if (dealerDeck.cardsValue() > playerDeck.cardsValue() && endRound == false) {
				System.out.println("\nThe Dealer beat you! Lol!");
				playerMoney -= playerBet;
				endRound = true;
			}
			
//			End of hand has been reached, all the player's and dealer's cards must be moved back into the deck.
			playerDeck.moveAllToDeck(playingDeck);
			dealerDeck.moveAllToDeck(playingDeck);
			System.out.println("\nEnd of Hand!");
		}
			}
			else if (startGame == 2){
				System.out.println("\nGame over! Thank you for playing!\n");
				break;
			}
			if (playerMoney <= 0) {
				System.out.println("\nGame over! You lost all your money lol >:)\n");
			}
			else {
				System.out.println("\nGame over!\n");
			}
				
			
		}
		
		
		
	}

}
