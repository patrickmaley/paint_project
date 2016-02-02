import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Deck {
	private List<Card> completeDeck = new ArrayList<>();
	private List<Card> playerCards = new ArrayList<>();
	private List<Card> communityCards = new ArrayList<>();
	
	public Deck(){
		createDeck();
	}

	

	private void createDeck() {
		for (Suit suit : Suit.values()) {
			for (Rank rank : Rank.values()) {
				completeDeck.add(new Card(rank, suit));
			}
		}
	}



	public void drawCards(int numberOfPlayers, List<Player> players) {
		int cardsPerPlayer = 2;
		int communityCardsTotal = 5;
		int cardsToDraw = (numberOfPlayers * 2) + 5;
		int randomNumber;
		
		List<Integer> numbersList = new ArrayList<>();
		Random rng = new Random();
		
		clearAllCards(players);
		for (int i = 0; i < cardsToDraw; i++) {
			randomNumber = rng.nextInt(52);
			if(!numbersList.contains(randomNumber)){
				numbersList.add(randomNumber);
			}else{
				i--;
			}
		}
		
		//System.out.println(numbersList.toString());
		int i = 0;
		for (Player player : players) {
			for (int j = 0; j < cardsPerPlayer; j++) {
				//System.out.println("Player Numbers" + numbersList.get(i));
				player.addCards(completeDeck.get(numbersList.get(i)));
				i++;
			}
		}
		
		for (int j = cardsToDraw - 1; j >= cardsToDraw - communityCardsTotal; j--) {
			//System.out.println("Community Numbers" + numbersList.get(j));
			communityCards.add(completeDeck.get(numbersList.get(j)));
		}
//		for (int j = 0; j < communityCards.size(); j++) {
//			System.out.println(communityCards.get(j).toString());
//		}
		
	}



	private void clearAllCards(List<Player> players) {
		communityCards.clear();
		for (Player player : players) {
			player.clearHand();
		}
	}
	
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
