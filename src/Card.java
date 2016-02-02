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
 *Class Description: Card creates a Card object with a suit, rank, a char representation, and a method
 *for comparing a Card to other Card
 *
 */
public class Card implements Comparable<Card>{
	private Rank cardRank;
	private Suit cardSuit;
	private char highRanks;
	
	public Card(Rank rank, Suit suit){
		this.cardRank = rank;
		this.cardSuit = suit;
	}

	public Suit getSuit() {
		return this.cardSuit;
	}

	public Rank getRank() {
		return this.cardRank;
	}

	//This method is used to compare cards in a list or arraylist;
	@Override
	public int compareTo(Card other) {
		return this.cardRank.getValue() - other.cardRank.getValue();
	}
	
	public char getChar(){
		switch(this.getRank().getValue()){
			case 14: highRanks = 'A'; break;
			case 13: highRanks = 'K'; break;
			case 12: highRanks = 'Q'; break;
			case 11: highRanks = 'J'; break;
			default: break;
		}
		return highRanks;
	}
	
	public String getRankString(){
		if(cardRank.getValue() > 10){
			return this.getChar() + "";
		}
		return cardRank.getValue() + "";
	}

	public char getSuitChar() {
		return cardSuit.getSuitChar();
	}
	
	
	

}
