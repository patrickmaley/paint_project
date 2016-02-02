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
	
	PokerHandRank(int value){
		this.value = value;
	}

	public int getValue() {
		return this.value;
	}

}

