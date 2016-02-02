import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Player implements Comparable<Player>, Comparator<Player>{
	
	private String playerName;
	private int balance;
	private PokerHand pokerhand;
	private int playerID;
	private List<Card> cards = new ArrayList<>();
	
	
	public Player(int number, String name) {
		this.playerName = name + " " + number;
		this.balance = 10;
		this.playerID = number;
	}

	public String getName() {
		return this.playerName;
	}

	public int getBalance() {
		// TODO Auto-generated method stub
		return this.balance;
	}

	public String getHand() {
		String hand = "";
		for (Card card : cards) {
			hand += card.getRankString() + card.getSuitChar() + " ";
		}
		return hand;
	}
	
	public void addCards(Card playerCard){
		this.cards.add(playerCard);
	}
	
	public void clearHand(){
		this.cards.clear();
	}
	public List<Card> getPlayerHand(){
		return this.cards;
	}
	
	public void setPokerHand(Card cardOne, Card cardTwo, Card cardThree, Card cardFour, Card cardFive, Card cardSix, Card cardSeven){
		List<Card> sCH = new ArrayList<>();
		List<PokerHand> allCombinations = new ArrayList<>();
		sCH.add(cardOne);
		sCH.add(cardTwo);
		sCH.add(cardThree);
		sCH.add(cardFour);
		sCH.add(cardFive);
		sCH.add(cardSix);
		sCH.add(cardSeven);
		
		allCombinations.add(new PokerHand(sCH.get(0 ), sCH.get(1), sCH.get(2), sCH.get(3), sCH.get(4)));
		allCombinations.add(new PokerHand(sCH.get(0 ), sCH.get(1), sCH.get(2), sCH.get(3), sCH.get(5)));
		allCombinations.add(new PokerHand(sCH.get(0 ), sCH.get(1), sCH.get(2), sCH.get(3), sCH.get(6)));
		allCombinations.add(new PokerHand(sCH.get(0 ), sCH.get(1), sCH.get(2), sCH.get(4), sCH.get(5)));
		allCombinations.add(new PokerHand(sCH.get(0 ), sCH.get(1), sCH.get(2), sCH.get(4), sCH.get(6)));
		allCombinations.add(new PokerHand(sCH.get(0 ), sCH.get(1), sCH.get(2), sCH.get(5), sCH.get(6)));
		allCombinations.add(new PokerHand(sCH.get(0 ), sCH.get(1), sCH.get(3), sCH.get(4), sCH.get(5)));
		allCombinations.add(new PokerHand(sCH.get(0 ), sCH.get(1), sCH.get(3), sCH.get(4), sCH.get(6)));
		allCombinations.add(new PokerHand(sCH.get(0 ), sCH.get(1), sCH.get(3), sCH.get(5), sCH.get(6)));
		allCombinations.add(new PokerHand(sCH.get(0 ), sCH.get(1), sCH.get(4), sCH.get(5), sCH.get(6)));
		allCombinations.add(new PokerHand(sCH.get(0 ), sCH.get(2), sCH.get(3), sCH.get(4), sCH.get(5)));
		allCombinations.add(new PokerHand(sCH.get(0 ), sCH.get(2), sCH.get(3), sCH.get(4), sCH.get(6)));
		allCombinations.add(new PokerHand(sCH.get(0 ), sCH.get(2), sCH.get(3), sCH.get(5), sCH.get(6)));
		allCombinations.add(new PokerHand(sCH.get(0 ), sCH.get(2), sCH.get(4), sCH.get(5), sCH.get(6)));
		allCombinations.add(new PokerHand(sCH.get(0 ), sCH.get(3), sCH.get(4), sCH.get(5), sCH.get(6)));
		allCombinations.add(new PokerHand(sCH.get(1 ), sCH.get(2), sCH.get(3), sCH.get(4), sCH.get(5)));
		allCombinations.add(new PokerHand(sCH.get(1 ), sCH.get(2), sCH.get(3), sCH.get(4), sCH.get(6)));
		allCombinations.add(new PokerHand(sCH.get(1 ), sCH.get(2), sCH.get(3), sCH.get(5), sCH.get(6)));
		allCombinations.add(new PokerHand(sCH.get(1 ), sCH.get(2), sCH.get(4), sCH.get(5), sCH.get(6)));
		allCombinations.add(new PokerHand(sCH.get(1 ), sCH.get(3), sCH.get(4), sCH.get(5), sCH.get(6)));
		allCombinations.add(new PokerHand(sCH.get(2 ), sCH.get(3), sCH.get(4), sCH.get(5), sCH.get(6)));
		
		Collections.sort(allCombinations);
		this.pokerhand = allCombinations.get(20);
		//System.out.print(allCombinations.size());
		allCombinations = null;
		sCH = null;
		
	}
	
	public PokerHand getPokerHand(){
		return this.pokerhand;
	}
	
	@Override
	public int compareTo(Player o) {
		return this.pokerhand.compareTo(o.pokerhand);
	}
	
	
	public String getBestHand(){
		String bestHand = "";
		for (Card card : this.pokerhand.getHand()) {
			bestHand += card.getRankString() + card.getSuitChar() + " ";
		}
		return bestHand;
	}
	
	public String toString(){
		return this.playerName + ": " + this.getBestHand();
	}

	@Override
	public int compare(Player thisPlayer, Player otherPlayer) {
		return  thisPlayer.playerID - otherPlayer.playerID;
		
	}
	public void deductWinnings(){
		this.balance -= 2;
	}
	
	public void addWinnings(int winning){
		this.balance += winning;
	}

	public void setPokerHandNull() {
		this.pokerhand = null;
	}
}
