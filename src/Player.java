import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
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
 *Class Description: The player class holds all necessary information for each player.
 *It allows comparison between player objects based on their pokerhand objects. It holds
 *the players two cards that are drawn, the balance, and their playerID and name.
 *
 */
public class Player implements Comparable<Player>, Comparator<Player>{
	
	private String playerName;
	private double balance;
	private PokerHand pokerhand;
	private int playerID;
	private List<Card> cards = new ArrayList<>();
	
	
	public Player(int number, String name) {
		this.playerName = name + " " + number;
		this.balance = 100.0;
		this.playerID = number;
	}

	public String getName() {
		return this.playerName;
	}

	public Double getBalance() {
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
	
	//This creates all 21 possible pokerhands for the player. Once all 21 are created, it gets the pokerhand
	//at the very last index of the allCombinations arraylist and sets that equal to the players pokerhand variable.
	public void setPokerHand(Card cardOne, Card cardTwo, Card cardThree, Card cardFour, Card cardFive, Card cardSix, Card cardSeven){
		List<Card> sCH = new ArrayList<>(); //sevenCardHand
		List<PokerHand> allCombinations = new ArrayList<>();
		
		//Adds all the cards to a the card Arraylist
		sCH.add(cardOne);
		sCH.add(cardTwo);
		sCH.add(cardThree);
		sCH.add(cardFour);
		sCH.add(cardFive);
		sCH.add(cardSix);
		sCH.add(cardSeven);
		
		//Sets all the pokerhand combinations- 21 in total- 7 choose 5
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
		//Sets the arraylist and 
		allCombinations = null;
		sCH = null;
		
	}
	
	public PokerHand getPokerHand(){
		return this.pokerhand;
	}
	
	@Override
	//Uses comparable to sort by pokerhands
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
	//Uses the comparator to sort by the playerID
	public int compare(Player thisPlayer, Player otherPlayer) {
		return  thisPlayer.playerID - otherPlayer.playerID;
		
	}
	
	public void deductWinnings(){
		this.balance -= 2;
	}
	
	public void addWinnings(double totalWinnings){
		this.balance += totalWinnings;
	}

	public void setPokerHandNull() {
		this.pokerhand = null;
	}
}
