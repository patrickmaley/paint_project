/*Author: Patrick Maley
 * 
 *Class: CSC 335
 * 
 *Project: Rank, Suit, Card, Pokerhand
 * 
 *Date: January 24, 2016
 *
 *Professor: Dr. Mercer
 *
 *Section Lead: Cindy Trieu
 *
 *Enum Description: PokerHandRank sets the value of all possible poker hands.
 *
 */
public enum PokerHandRank {
	
	HighCard(1),OnePair(2), TwoPair(3), ThreeOfAKind(4), Straight(5), Flush(6), FullHouse(7), FourOfAKind(8), StraightFlush(9);
	
	private int value;
	private String rankName;
	
	PokerHandRank(int value){
		this.value = value;
		this.rankName = setRankName(value);
	}

	private String setRankName(int value) {
		switch(value){
		case 1: return "High Card"; 
		case 2: return "Pair";
		case 3: return "Two Pair"; 
		case 4: return "Three Of A Kind"; 
		case 5: return "Straight"; 
		case 6: return "Flush"; 
		case 7: return "FullHouse"; 
		case 8: return "Four Of A Kind"; 
		case 9: return "Straight Flush"; 
		default: return "";
		}
		
	}

	public int getValue() {
		return this.value;
	}
	
	public String getRankName(){
		return this.rankName;
	}

}

