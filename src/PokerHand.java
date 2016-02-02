import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
 *Class Description: PokerHand creates a pokerhand object using five card objects. 
 *The card objects are sorted based on their ranking value. The constructor then 
 *determines what type of PokerHandRank enum it is. A pokerhand can be compared 
 *with other pokerhands to determine the higher hand based on 5-card Poker.
 *
 */
public class PokerHand implements Comparable<PokerHand>{
	//private Card firstCard;
	//private Card secondCard;
	//private Card thirdCard;
	//private Card fourthCard;
	//private Card fifthCard;
	//private int fullHousePairValue;
	
	private PokerHandRank pokerHandRank;
	
	private boolean flush = false;
	private boolean lowStraight = false;
	private int fourOfAKindValue;
	private int threeOfAKindValue;
	private int twoPairValueHigher;
	private int twoPairValueLower;
	private int onePairValue;
	
	private int[] valuesInPair = new int[3];
	private List<Card> hand = new ArrayList<Card>();
	
	//Set the values for the PokerHand object and sorts them from smallest(0 index) to Largest(4 index)
	public PokerHand(Card cardOne, Card cardTwo, Card cardThree, Card cardFour, Card cardFive) {
		//firstCard = cardOne;
		//secondCard = cardTwo;
		//thirdCard = cardThree;
		//fourthCard = cardFour;
		//fifthCard = cardFive;
		//createHand();
		
		hand.add(cardOne);
		hand.add(cardTwo);
		hand.add(cardThree);
		hand.add(cardFour);
		hand.add(cardFive);
		
		findDuplicates(hand);
		Collections.sort(hand);
		
		pokerHandRank = getPokerHandRank(hand);
	}
	
	//Determines if a duplicate exists in any hand based on rank and suit
	private void findDuplicates(List<Card> handDuplicate) {
		Card duplicateChecker = handDuplicate.get(0);
		
		for (int j = 1; j < handDuplicate.size(); j++) {
			for (int p = j ; p < handDuplicate.size(); p++) {
				if(duplicateChecker.getRank() == handDuplicate.get(p).getRank() && duplicateChecker.getSuit() == handDuplicate.get(p).getSuit()){
					throw new DuplicateCardException();
				}
			}
			duplicateChecker = handDuplicate.get(j);
		}
		
	}
	
	//	private void createHand() {
	//		hand.add(firstCard);
	//		hand.add(secondCard);
	//		hand.add(thirdCard);
	//		hand.add(fourthCard);
	//		hand.add(fifthCard);
	//	}
	
	//Sets the PokerHandRank enum for each pokerhand
	private PokerHandRank getPokerHandRank(List<Card> cards) {
		//Test isFlush first then do straight
		isFlush(cards);
		if(isStraight(cards) && flush){
			return PokerHandRank.StraightFlush;
		}else if(isFourOfAKind(cards)){
			return PokerHandRank.FourOfAKind;
		}else if(isFullHouse(cards)){
			return  PokerHandRank.FullHouse;
		}else if(flush){
			return PokerHandRank.Flush;
		}else if(isStraight(cards)){
			return PokerHandRank.Straight;
		}else if(isThreeOfAKind(cards)){
			return PokerHandRank.ThreeOfAKind;
		}else if(isTwoPair(cards)){
			return PokerHandRank.TwoPair;
		}else if(isOnePair(cards)){
			return PokerHandRank.OnePair;
		}else{
			return PokerHandRank.HighCard;
		}
	}
	
	//Determines if the hand is a flush
	private void isFlush(List<Card> cards) {
		int i = 1;
		Suit firstCard = cards.get(0).getSuit();
		for (int j = 1; j < cards.size(); j++) {
			if(firstCard == cards.get(i).getSuit()){
				i++;
			}
		}
		
		if(i == 5){
			this.flush = true;
			
		}
	}
	
	//Determines if the hand is a straight
	private boolean isStraight(List<Card> cards) {
		int previousRank = cards.get(4).getRank().getValue();
		int straight = 1;
		
		//LowStraight Edge Case
		if(previousRank == Rank.Ace.getValue() && cards.get(3).getRank().getValue() == Rank.Five.getValue()){
			previousRank = cards.get(3).getRank().getValue();
			straight++;
			for (int i = 2; i >= 0; i--) {
				if(previousRank - 1 == cards.get(i).getRank().getValue()){
					straight++;
					previousRank = cards.get(i).getRank().getValue();
				};
			}
			if(straight == 5){
				lowStraight = true;
				return true;
			}
			
		}else{
			for (int i = 3; i >= 0; i--) {
				if(previousRank - 1 == cards.get(i).getRank().getValue()){
					straight++;
					previousRank = cards.get(i).getRank().getValue();
				};
			}
			if(straight == 5){
				return true;
			}
			
		}
		
		
		
		return false;
	}

	//Determines if the hand is four-of-a-kind
	private boolean isFourOfAKind(List<Card> cards) {
		int fourOfAKind = 0;
		for (int j = 0; j < cards.size(); j++) {
			for (int i = 0 ; i < cards.size(); i++) {
				if(cards.get(j).getRank() == cards.get(i).getRank()){
					fourOfAKind++;
				}
			}
			if(fourOfAKind == 4){
				fourOfAKindValue = cards.get(j).getRank().getValue();
				return true;
			}
			fourOfAKind = 0;
		}
		return false;
	}
	
	//Determines if the hand is a Full House
	private boolean isFullHouse(List<Card> cards) {
		Card pairFinder = null;
		if(isThreeOfAKind(cards)){
			for (Card card : cards) {
				if(card.getRank().getValue() != this.threeOfAKindValue){
					pairFinder = card;
					break;
				}
			}
			for (Card card : cards) {
				if(pairFinder.getRank().getValue() == card.getRank().getValue() 
						&& card.getRank().getValue() != this.threeOfAKindValue
						&& pairFinder.getSuit() != card.getSuit()){
					//this.fullHousePairValue = pairFinder.getRank().getValue();
					return true;
				}
			}
		}
		return false;
	}

	//Determines if the hand is three of a kind
	private boolean isThreeOfAKind(List<Card> cards) {
		int threeOfAKind = 0;
		for (int j = 0; j < cards.size(); j++) {
			for (int i = 0 ; i < cards.size(); i++) {
				if(cards.get(j).getRank() == cards.get(i).getRank()){
					threeOfAKind++;
				}
			}
			if(threeOfAKind == 3){
				threeOfAKindValue = cards.get(j).getRank().getValue();
				return true;
			}
			threeOfAKind = 0;
		}
		return false;
		
	}

	//Determines if the hand is a two pair
	private boolean isTwoPair(List<Card> cards) {
		int twoOfAKindHigher = 0;
		int twoOfAKindLower = 0;
		int switchRanks = 0;
		for (int j = 0; j < cards.size(); j++) {
			for (int i = 0 ; i < cards.size(); i++) {
				if(cards.get(j).getRank() == cards.get(i).getRank()){
					twoPairValueHigher = cards.get(i).getRank().getValue();
					twoOfAKindHigher++;
				}
			}
			if(twoOfAKindHigher == 2){
				break;
			}
			twoOfAKindHigher = 0;
		}
		
		if(twoOfAKindHigher == 2){
			for (int j = 0; j < cards.size(); j++) {
				for (int i = 0 ; i < cards.size(); i++) {
					if(cards.get(j).getRank() == cards.get(i).getRank() && cards.get(i).getRank().getValue() != twoPairValueHigher){
						twoPairValueLower = cards.get(i).getRank().getValue();
						twoOfAKindLower++;
					}
				}
				if(twoOfAKindLower ==2){
					break;
				}
				twoOfAKindLower = 0;
			}
			if(twoOfAKindLower == 2){
				if(twoPairValueHigher < twoPairValueLower){
					switchRanks = twoPairValueLower;
					twoPairValueLower = twoPairValueHigher;
					twoPairValueHigher = switchRanks;
				}
				for (int i = 0; i < cards.size(); i++) {
					if(cards.get(i).getRank().getValue() != this.twoPairValueHigher &&
							cards.get(i).getRank().getValue() != this.twoPairValueLower){
						this.valuesInPair[0] = cards.get(i).getRank().getValue();
						break;
					}
				}
				return true;
			}
		}
		return false;
	}
	
	//Determines if the hand is a one pair
	private boolean isOnePair(List<Card> cards) {
		int pair = 0;
		int otherValues = 0;
		for (int j = 0; j < cards.size(); j++) {
			for (int i = 0 ; i < cards.size(); i++) {
				if(cards.get(j).getRank() == cards.get(i).getRank()){
					
					pair++;
				}
			}
			if(pair == 2){
				onePairValue = cards.get(j).getRank().getValue();
				for (int i = 0; i < cards.size(); i++) {
					if(cards.get(i).getRank().getValue() != this.onePairValue){
						this.valuesInPair[otherValues] = cards.get(i).getRank().getValue();
						otherValues++;
					}
				}
				return true;
			}
			pair = 0;
		}
		
		return false;
	}
	
	//Compares pokerhands based on their PokerHandRank enum value. If there is a tie
	//it compares the highest cards. If the tie remains, it returns 0;
	public int compareTo(PokerHand other) {
		//List<Card> duplicateList = createDuplicateList(other);
		//findDuplicates(duplicateList);
		
		if(this.pokerHandRank.getValue() == other.pokerHandRank.getValue()){
		
			
			 if(this.pokerHandRank == PokerHandRank.FourOfAKind){
				 if(this.fourOfAKindValue == other.fourOfAKindValue){
						return isHighCard(other);
					}
				return this.fourOfAKindValue - other.fourOfAKindValue;
			}else if(this.pokerHandRank == PokerHandRank.FullHouse || this.pokerHandRank == PokerHandRank.ThreeOfAKind){
				if(this.threeOfAKindValue == other.threeOfAKindValue){
					return isHighCard(other);
				}
				return this.threeOfAKindValue - other.threeOfAKindValue;
			}else if(this.pokerHandRank == PokerHandRank.Straight || this.pokerHandRank == PokerHandRank.StraightFlush){
				if(this.lowStraight && !other.lowStraight){
					return -1;
				}
				if(other.lowStraight && !this.lowStraight){
					return 1;
				}
			}else if(this.pokerHandRank == PokerHandRank.TwoPair){
				if(this.twoPairValueHigher != other.twoPairValueHigher){
					return this.twoPairValueHigher - other.twoPairValueHigher;
				}else if(this.twoPairValueLower != other.twoPairValueLower){
					return this.twoPairValueLower - other.twoPairValueLower;
				}else{
					return this.valuesInPair[0] - other.valuesInPair[0];
				}
			}else if(this.pokerHandRank == PokerHandRank.OnePair){
				if(this.onePairValue != other.onePairValue){
					return this.onePairValue - other.onePairValue;
				}else{
					for (int i = 2; i >= 0; i--) {
						if(this.valuesInPair[i] != other.valuesInPair[i]){
							return this.valuesInPair[i] - other.valuesInPair[i];
						}
					}
					return 0;
				}
			}
			 return isHighCard(other);
			
		}else{
			return this.pokerHandRank.getValue() - other.pokerHandRank.getValue();
		}
	}
	
	private int isHighCard(PokerHand other) {
		int playerHand = 4; //Fifth Card in hand Highest Value
		int otherHand = 4; //Fifth Card in hand Highest Value
		while(this.hand.get(playerHand).getRank().getValue() == other.hand.get(otherHand).getRank().getValue()){
			playerHand--;
			otherHand--;
			if(playerHand < 0 || otherHand < 0){
				return 0;
			}
		}
		return this.hand.get(playerHand).getRank().getValue() - other.hand.get(otherHand).getRank().getValue();
	}

	//Creates a single pokerhand with 10 cards to return for duplicate testing
	private List<Card> createDuplicateList(PokerHand other) {
		List<Card> playerList = new ArrayList<>();
		for (Card card : this.hand) {
			playerList.add(card);
		}
		for (Card card : other.hand) {
			playerList.add(card);
		}
		return playerList;
	}

	//Getter for each hands pokerHandRank
	public PokerHandRank getPokerHandRank(){
		return this.pokerHandRank;
	}
	
	//Implements string representation of a Pokerhand using Ranks
	public String toString(){
		String tester = "";
		tester += this.getPokerHandRank() + ": [ ";
		for (Card str : hand)
			tester += str.getRank() + " ";
		tester = tester.trim();
		tester += "] ";
		return tester;
	}

	public List<Card> getHand() {
		// TODO Auto-generated method stub
		return this.hand;
	}
	
}
