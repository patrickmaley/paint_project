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
 *Enum Description: Rank creates enumerations to set the rank property of a Card object.
 *
 */
public enum Rank {
	
	Deuce(2), Three(3), Four(4), Five(5), Six(6), Seven(7), Eight(8), Nine(9), Ten(10), Jack(11), Queen(12), King(13), Ace(14);
	
	private int value;
	
	Rank(int value){
		this.value = value;
	}

	public int getValue() {
		return this.value;
	}
	
	

}
