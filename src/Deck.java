import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*Author: Patrick Maley
 * 
 *Class: CSC 335
 * 
 *Project: Arizona Hold Em
 * 
 *Date: February 2, 2016
 *
 *Professor: Dr. Mercer
 *
 *Section Lead: Cindy Trieu
 *
 *Class Description: Deck creates a 52-card deck. From this deck, it randomly distributes cards 
 *depending on the amount of players. 
 *
 */
public class Deck {
	private List<Card> completeDeck = new ArrayList<>();
	private List<Card> communityCards = new ArrayList<>();
	
	//Creates the deck.
	public Deck(){
		for (Suit suit : Suit.values()) {
			for (Rank rank : Rank.values()) {
				completeDeck.add(new Card(rank, suit));
			}
		}
	}

	//The drawCards method distributes 2 cards per player and 5 cards to the community
	//hand based on a random number generator. The numbers cannot be duplicated.
	public void drawCards(int numberOfPlayers, List<Player> players) {
		int cardsPerPlayer = 2;
		int communityCardsTotal = 5;
		int cardsToDraw = (numberOfPlayers * 2) + 5;
		int randomNumber;
		
		List<Integer> numbersList = new ArrayList<>();
		Random rng = new Random();
		
		//Clears the hands for multiple play throughs for garbage collection.
		clearAllCards(players);
		
		//Generates the random numbers and adds them to an arraylist until
		//the cards drawn equals the cardsToDraw variable.
		for (int i = 0; i < cardsToDraw; i++) {
			randomNumber = rng.nextInt(52);
			if(!numbersList.contains(randomNumber)){
				numbersList.add(randomNumber);
			}else{
				i--;
			}
		}
		//This prints out the number list to check for duplicates.
		//System.out.println(numbersList.toString());
		
		//This adds 2 cards to each player in the game. No two cards will be the same.
		//All player cards are drawn from the first index to 2 * numberOfPlayers index
		//of the arraylist.
		int i = 0;
		for (Player player : players) {
			for (int j = 0; j < cardsPerPlayer; j++) {
				//System.out.println("Player Numbers" + numbersList.get(i));
				player.addCards(this.completeDeck.get(numbersList.get(i)));
				i++;
			}
		}
		
		//This adds the remaining cards to the community cards arraylist. The iterator
		//is set to the last card in the numbersList arraylist. Each community hand is
		//drawn from the last five cards in the arraylist.
		for (int j = cardsToDraw - 1; j >= cardsToDraw - communityCardsTotal; j--) {
			//System.out.println("Community Numbers" + numbersList.get(j));
			this.communityCards.add(this.completeDeck.get(numbersList.get(j)));
		}	
	}


	//This method clears all the cards from the player hand to attempt to save memory.
	private void clearAllCards(List<Player> players) {
		communityCards.clear();
		for (Player player : players) {
			player.clearHand();
		}
	}
	
	//Returns the 5 cards that all players use
	public List<Card> getCommunityCards(){
		return this.communityCards;
	}
	
	public String toString(){
		String allCards = "";
		for (Card card : communityCards) {
			allCards += card.getRankString() + card.getSuitChar() + " ";
		}
		return allCards;
	}
}
