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
 *Enum Description: Suit creates enumerations to set the suit property of a Card object.
 *
 */
public enum Suit{

	Clubs("Clubs"), Diamonds("Diamonds"), Hearts("Hearts"), Spades("Spades");
	

	private String suit;
	private char suitChar;
	
	Suit(String suit){
		this.suit = suit;
		setSuitChar();
	}

	private void setSuitChar() {
		switch (this.getValue()) {
			case "Clubs":
				this.suitChar = '\u2663';
				break;
			case "Diamonds":
				this.suitChar = '\u2666';
				break;
			case "Spades":
				this.suitChar = '\u2660';
				break;
			case "Hearts":
				this.suitChar = '\u2665';
				break;
			default:
				break;
		}		
	}
	
	public String getValue(){
		return this.suit;
	}

	public char getSuitChar() {
		
		return suitChar;
	}
}
