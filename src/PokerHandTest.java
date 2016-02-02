import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

/**
 * Tests the PokerHand class and the enums 
 * 
 * Rick includes all 52 cards to save you time (see end of file, after the @Test methods)
 * 
* There are also some additional test cases here.  But this is in no way complete.  Many more are needed
 */
/*Author: Patrick Maley && Rick Mercer
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
 *Class Description: PokerHandTest uses jUnit to test the PokerHand object and compare pokerhands
 *with Comparable.
 *
 */
public class PokerHandTest {

  @Test
  public void testSuitEnum() {
    String result = "";
    for (Suit suit : Suit.values())
      result += suit + " ";
    assertEquals("Clubs Diamonds Hearts Spades", result.trim());
  }

  @Test
  public void testRankEnum() {
    String result = "";
    for (Rank rank : Rank.values())
      result += rank + " ";
    assertEquals(
        "Deuce Three Four Five Six Seven Eight Nine Ten Jack Queen King Ace",
        result.trim());
  }

  // Do not allow duplicate cards, throw an exception
  @Test(expected = DuplicateCardException.class)
  public void tryToAddTheSameCardTwiceA() {
    new PokerHand(C2, C3, C4, C5, C5);
  }

//  @Test(expected = DuplicateCardException.class)
//  public void testPair2() {
//    PokerHand a = new PokerHand(H3, CA, D4, H6, DA);
//    a.toString();
//    PokerHand b = new PokerHand(H3, C5, HA, SA, C6);
//    assertTrue(a.compareTo(b) < 0);
//  }

  @Test
  public void testTwoPairWhenOnePairIsEqual() {
    PokerHand a = new PokerHand(C4, HK, D4, H3, DK);
    PokerHand b = new PokerHand(H4, C10, CA, DA, S4);
    assertTrue(a.compareTo(b) < 0);
    assertTrue(b.compareTo(a) > 0);
  }

  @Test
  public void testAceLowStraight() {
    PokerHand a = new PokerHand(CA, C2, C3, C4, H5);
    PokerHand b = new PokerHand(D2, D3, D4, D5, H6);
    boolean answer = a.compareTo(b) < 0;
    assertTrue(answer);
  }

  // Set up 52 cards so we can use C2 instead of new Card(Rank.Deuce, Suit.Clubs)
  private final static Card C2 = new Card(Rank.Deuce, Suit.Clubs);
  private final static Card C3 = new Card(Rank.Three, Suit.Clubs);
  private final static Card C4 = new Card(Rank.Four, Suit.Clubs);
  private final static Card C5 = new Card(Rank.Five, Suit.Clubs);
  private final static Card C6 = new Card(Rank.Six, Suit.Clubs);
  private final static Card C7 = new Card(Rank.Seven, Suit.Clubs);
  private final static Card C8 = new Card(Rank.Eight, Suit.Clubs);
  private final static Card C9 = new Card(Rank.Nine, Suit.Clubs);
  private final static Card C10 = new Card(Rank.Ten, Suit.Clubs);
  private final static Card CJ = new Card(Rank.Jack, Suit.Clubs);
  private final static Card CQ = new Card(Rank.Queen, Suit.Clubs);
  private final static Card CK = new Card(Rank.King, Suit.Clubs);
  private final static Card CA = new Card(Rank.Ace, Suit.Clubs);

  private final static Card D2 = new Card(Rank.Deuce, Suit.Diamonds);
  private final static Card D3 = new Card(Rank.Three, Suit.Diamonds);
  private final static Card D4 = new Card(Rank.Four, Suit.Diamonds);
  private final static Card D5 = new Card(Rank.Five, Suit.Diamonds);
  private final static Card D6 = new Card(Rank.Six, Suit.Diamonds);
  private final static Card D7 = new Card(Rank.Seven, Suit.Diamonds);
  private final static Card D8 = new Card(Rank.Eight, Suit.Diamonds);
  private final static Card D9 = new Card(Rank.Nine, Suit.Diamonds);
  private final static Card D10 = new Card(Rank.Ten, Suit.Diamonds);
  private final static Card DJ = new Card(Rank.Jack, Suit.Diamonds);
  private final static Card DQ = new Card(Rank.Queen, Suit.Diamonds);
  private final static Card DK = new Card(Rank.King, Suit.Diamonds);
  private final static Card DA = new Card(Rank.Ace, Suit.Diamonds);

  private final static Card H2 = new Card(Rank.Deuce, Suit.Hearts);
  private final static Card H3 = new Card(Rank.Three, Suit.Hearts);
  private final static Card H4 = new Card(Rank.Four, Suit.Hearts);
  private final static Card H5 = new Card(Rank.Five, Suit.Hearts);
  private final static Card H6 = new Card(Rank.Six, Suit.Hearts);
  private final static Card H7 = new Card(Rank.Seven, Suit.Hearts);
  private final static Card H8 = new Card(Rank.Eight, Suit.Hearts);
  private final static Card H9 = new Card(Rank.Nine, Suit.Hearts);
  private final static Card H10 = new Card(Rank.Ten, Suit.Hearts);
  private final static Card HJ = new Card(Rank.Jack, Suit.Hearts);
  private final static Card HQ = new Card(Rank.Queen, Suit.Hearts);
  private final static Card HK = new Card(Rank.King, Suit.Hearts);
  private final static Card HA = new Card(Rank.Ace, Suit.Hearts);

  private final static Card S2 = new Card(Rank.Deuce, Suit.Spades);
  private final static Card S3 = new Card(Rank.Three, Suit.Spades);
  private final static Card S4 = new Card(Rank.Four, Suit.Spades);
  private final static Card S5 = new Card(Rank.Five, Suit.Spades);
  private final static Card S6 = new Card(Rank.Six, Suit.Spades);
  private final static Card S7 = new Card(Rank.Seven, Suit.Spades);
  private final static Card S8 = new Card(Rank.Eight, Suit.Spades);
  private final static Card S9 = new Card(Rank.Nine, Suit.Spades);
  private final static Card S10 = new Card(Rank.Ten, Suit.Spades);
  private final static Card SJ = new Card(Rank.Jack, Suit.Spades);
  private final static Card SQ = new Card(Rank.Queen, Suit.Spades);
  private final static Card SK = new Card(Rank.King, Suit.Spades);
  private final static Card SA = new Card(Rank.Ace, Suit.Spades);

  // TEST CARD HGH HANDS

  private static PokerHand nothing72 = new PokerHand(C2, C3, C4, C5, D7);
  private static PokerHand nothing73 = new PokerHand(D2, D4, D5, D6, C7);
  private static PokerHand nothingJ = new PokerHand(C8, C9, C10, SJ, D3);
  private static PokerHand nothingK9 = new PokerHand(CK, CQ, CJ, D10, H9);
  private static PokerHand nothingK8 = new PokerHand(HK, HQ, HJ, H10, S8);
  private static PokerHand nothingA = new PokerHand(S9, SJ, SQ, SK, CA);

  
  @Test
  public void testNothing0() {
    assertTrue(nothing73.compareTo(nothing72) > 0);
  }

  @Test
  public void testNothing1() {
    assertTrue(nothingJ.compareTo(nothing73) > 0);
  }

  @Test
  public void testNothing2() {
    assertTrue(nothingK8.compareTo(nothingJ) > 0);
  }

  @Test
  public void testNothing3() {
    assertTrue(nothingK9.compareTo(nothingK8) > 0);
  }

  @Test
  public void testNothing4() {
    assertTrue(nothingA.compareTo(nothingK8) > 0);
  }
  
  // Many more tests needed


  @Test
  public void toStringTest() {
	  //System.out.println(nothingA);
      assertEquals("HighCard: [ Nine Jack Queen King Ace]", nothingA.toString().trim());
  }
  
  @Test
  public void testIsFlush() {
    PokerHand a = new PokerHand(C4, CK, C5, C3, C2);
    PokerHand b = new PokerHand(S9, SJ, SQ, SK, CA);
    assertTrue(a.compareTo(b) > 0);
    assertTrue(b.compareTo(a) < 0);
  }
  
  @Test
  public void testIsStraight() {
    PokerHand a = new PokerHand(C4, C6, H2, C3, C5);
    assertEquals(PokerHandRank.Straight, a.getPokerHandRank());
  }
  
  
  @Test
  public void testIsStraightFlush() {
    PokerHand a = new PokerHand(C4, C6, C2, C3, C5);
    assertEquals(PokerHandRank.StraightFlush, a.getPokerHandRank());
  }
  
  @Test
  public void testIsFullHouse() {
    PokerHand a = new PokerHand(C4, H4, D4, S5, C5);
    assertEquals(PokerHandRank.FullHouse, a.getPokerHandRank());
  }
  
  @Test
  public void testIsFourOfAKind() {
    PokerHand a = new PokerHand(C4, H4, D4, S4, C5);
    assertEquals(PokerHandRank.FourOfAKind, a.getPokerHandRank());
  }
  
  @Test
  public void testIsThreeOfAKind() {
    PokerHand a = new PokerHand(C4, H4, D4, S6, C5);
    assertEquals(PokerHandRank.ThreeOfAKind, a.getPokerHandRank());
  }
  
  @Test
  public void testIsTwoPair() {
    PokerHand a = new PokerHand(C4, H4, D5, S6, C5);
    assertEquals(PokerHandRank.TwoPair, a.getPokerHandRank());
  }
  
  @Test
  public void testIsOnePair() {
    PokerHand a = new PokerHand(C4, H4, D7, S6, C5);
    assertEquals(PokerHandRank.OnePair, a.getPokerHandRank());
  }
  
  @Test
  public void testStraightFlushVsStraight() {
    PokerHand a = new PokerHand(CA, CK, CJ, CQ, C10);
    PokerHand b = new PokerHand(S10, S8, S7, SJ, C9);
    assertTrue(a.compareTo(b) > 0);
    assertTrue(b.compareTo(a) < 0);
  }
  @Test
  public void testStraightFlushVsStraightFlushEquals() {
    PokerHand a = new PokerHand(CA, CK, CJ, CQ, C10);
    PokerHand b = new PokerHand(HA, HK, HJ, HQ, H10);
    assertTrue(a.compareTo(b) == 0);
    assertTrue(b.compareTo(a) == 0);
  }
  
  @Test
  public void testStraightFlushVsStraightFlush() {
    PokerHand a = new PokerHand(CA, CK, CJ, CQ, C10);
    PokerHand b = new PokerHand(H9, HK, HJ, HQ, H10);
    assertTrue(a.compareTo(b) > 0);
    assertTrue(b.compareTo(a) < 0);
  }
  
  @Test
  public void testStraightFlushVsStraightFlush2() {
    PokerHand a = new PokerHand(CA, CK, CJ, CQ, C10);
    PokerHand b = new PokerHand(HA, H2, H3, H4, H5);
    assertTrue(a.compareTo(b) > 0);
    assertTrue(b.compareTo(a) < 0);
  }
  
  @Test
  public void testFourOfAKindVsFourOfAKind() {
    PokerHand a = new PokerHand(H4, D4, C4, S4, CJ);
    PokerHand b = new PokerHand(H5, D5, C5, S5, C10);
    assertTrue(a.compareTo(b) < 0);
    assertTrue(b.compareTo(a) > 0);
  }
  
  @Test
  public void testFourOfAKindVsFourOfAKind2() {
    PokerHand a = new PokerHand(HA, DA, CA, SA, CJ);
    PokerHand b = new PokerHand(HK, DK, CK, SK, C10);
    assertTrue(a.compareTo(b) > 0);
    assertTrue(b.compareTo(a) < 0);
  }
  
  @Test
  public void testFullHouseVsFullHouse() {
    PokerHand a = new PokerHand(HA, DA, CA, S10, C10);
    PokerHand b = new PokerHand(HK, DK, C2, SK, S2);
    assertTrue(a.compareTo(b) > 0);
    assertTrue(b.compareTo(a) < 0);
  }
  
  @Test
  public void testFullHouseVsFullHouse2() {
    PokerHand a = new PokerHand(H2, D2, C2, S3, C3);
    PokerHand b = new PokerHand(HJ, DJ, C4, SJ, S4);
    assertTrue(a.compareTo(b) < 0);
    assertTrue(b.compareTo(a) > 0);
  }
  
  @Test
  public void testFlushVsFlushEquals() {
    PokerHand a = new PokerHand(H2, H4, H6, H8, H10);
    PokerHand b = new PokerHand(S2, S6, S4, S8, S10);
    assertTrue(a.compareTo(b) == 0);
    assertTrue(b.compareTo(a) == 0);
  }
  
  @Test
  public void testFlushVsFlush() {
    PokerHand a = new PokerHand(HA, H7, H6, H5, H10);
    PokerHand b = new PokerHand(S2, S6, SQ, S8, SA);
    assertTrue(a.compareTo(b) < 0);
    assertTrue(b.compareTo(a) > 0);
  }
  
  @Test
  public void testFlushVsFlush2() {
    PokerHand a = new PokerHand(HQ, H9, H4, H2, HJ);
    PokerHand b = new PokerHand(S2, S6, SQ, S8, S5);
    assertTrue(a.compareTo(b) > 0);
    assertTrue(b.compareTo(a) < 0);
  }
  
  @Test
  public void testStraightVsStraight() {
    PokerHand a = new PokerHand(DQ, DJ, D10, H8, H9);
    PokerHand b = new PokerHand(S3, S4, D7, D5, D6);
    assertTrue(a.compareTo(b) > 0);
    assertTrue(b.compareTo(a) < 0);
  }
  
  @Test
  public void testStraightVsStraight2() {
    PokerHand a = new PokerHand(D2, D3, D5, H4, H6);
    PokerHand b = new PokerHand(S5, S3, D4, D7, D6);
    assertTrue(a.compareTo(b) < 0);
    assertTrue(b.compareTo(a) > 0);
  }
  
  @Test
  public void testLowStraightVsLowStraight() {
    PokerHand a = new PokerHand(DA, D3, D5, H4, H2);
    PokerHand b = new PokerHand(S5, S3, D4, D2, SA);
    assertTrue(a.compareTo(b) == 0);
    assertTrue(b.compareTo(a) == 0);
  }
  
  @Test
  public void testLowStraightVsStraight() {
    PokerHand a = new PokerHand(DA, DK, DQ, HJ, H10);
    PokerHand b = new PokerHand(S5, S3, D4, D2, SA);
    assertTrue(a.compareTo(b) > 0);
    assertTrue(b.compareTo(a) < 0);
  }
  
  @Test
  public void testThreeOfAKindVsThreeOfAKind() {
    PokerHand a = new PokerHand(DA, SA, HA, HJ, H10);
    PokerHand b = new PokerHand(SK, S3, D4, DK, HK);
    assertTrue(a.compareTo(b) > 0);
    assertTrue(b.compareTo(a) < 0);
  }
  
  @Test
  public void testTwoPairVsTwoPairEquals() {
    PokerHand a = new PokerHand(DA, SA, HK, CK, H10);
    PokerHand b = new PokerHand(CA, HA, D10, DK, SK);
    assertTrue(a.compareTo(b) == 0);
    assertTrue(b.compareTo(a) == 0);
  }
  
  @Test
  public void testOnePairVsOnePair() {
    PokerHand a = new PokerHand(DA, SA, H5, CK, H10);
    PokerHand b = new PokerHand(CA, HA, D4, DK, DJ);
    assertTrue(a.compareTo(b) < 0);
    assertTrue(b.compareTo(a) > 0);
  }
  
  @Test
  public void testOnePairVsOnePair2() {
    PokerHand a = new PokerHand(DA, SA, H5, CK, H9);
    PokerHand b = new PokerHand(C10, H10, D4, DK, DJ);
    assertTrue(a.compareTo(b) > 0);
    assertTrue(b.compareTo(a) < 0);
  }
  
  @Test
  public void testOnePairVsOnePairEquals() {
    PokerHand a = new PokerHand(DA, SA, H5, CK, HJ);
    PokerHand b = new PokerHand(CA, HA, D5, DK, DJ);
    assertTrue(a.compareTo(b) == 0);
    assertTrue(b.compareTo(a) == 0);
  }
  
  @Test
  public void testTwoPair() {
    PokerHand a = new PokerHand(C5, H5, D6, H2, D2);
    PokerHand b = new PokerHand(S9, SJ, SQ, SK, CA);
    assertTrue(a.compareTo(b) > 0);
    assertTrue(b.compareTo(a) < 0);
  }
  
  @Test
  public void testTwoPair2() {
    PokerHand a = new PokerHand(C6, H6, D5, H5, D2);
    PokerHand b = new PokerHand(D6, S6, SQ, SK, CA);
    assertTrue(a.compareTo(b) > 0);
    assertTrue(b.compareTo(a) < 0);
  }

  @Test
  public void testTwoPair3() {
    PokerHand a = new PokerHand(C6, H6, D4, H4, D2);
    PokerHand b = new PokerHand(D6, S6, D5 , S5, CA);
    assertTrue(a.compareTo(b) < 0);
    assertTrue(b.compareTo(a) > 0);
  }

  @Test
  public void testStraightVsTwoPair() {
    PokerHand a = new PokerHand(D8, D9, S7, S6, H5);
    PokerHand b = new PokerHand(D6, C6, D5 , S5, CA);
    assertTrue(a.compareTo(b) > 0);
    assertTrue(b.compareTo(a) < 0);
  }
  
  @Test
  public void testStraightVsThreeOfAKind() {
    PokerHand a = new PokerHand(D8, D9, S7, S6, H5);
    PokerHand b = new PokerHand(D6, C6, H6 , S5, CA);
    assertTrue(a.compareTo(b) > 0);
    assertTrue(b.compareTo(a) < 0);
  }
  
  @Test
  public void testStraightVsOnePair() {
    PokerHand a = new PokerHand(D8, D9, S7, S6, H5);
    PokerHand b = new PokerHand(D6, C6, H9 , S5, CA);
    assertTrue(a.compareTo(b) > 0);
    assertTrue(b.compareTo(a) < 0);
  }
  
  @Test
  public void testStraightVsHighCard() {
    PokerHand a = new PokerHand(D8, D9, S7, S6, H5);
    PokerHand b = new PokerHand(D6, CA, H9 , S5, C10);
    assertTrue(a.compareTo(b) > 0);
    assertTrue(b.compareTo(a) < 0);
  }
  
  @Test
  public void testFlushVsStraight() {
    PokerHand a = new PokerHand(D8, D9, S7, S6, H5);
    PokerHand b = new PokerHand(H6, HA, H9 , H8, H10);
    assertTrue(a.compareTo(b) < 0);
    assertTrue(b.compareTo(a) > 0);
  }
  
  private static PokerHand straightFlushAceHigh = new PokerHand(CA, CQ, CJ, CK, C10);
  private static PokerHand straightFlushAceLow = new PokerHand(SA, S5, S4, S3, S2);
  private static PokerHand straightFlush = new PokerHand(HJ, H9, H8, H10, H7);
  private static PokerHand FourOfAKindSixs = new PokerHand(C6, H6, S6, D6, D8);
  private static PokerHand diamondFlush = new PokerHand(DK,D10, DJ, D7, DA);
  private static PokerHand highCard7 = new PokerHand(D2, D4, D3, D5, C7);
  private static PokerHand threeOfAKindQueen = new PokerHand(DQ, HQ, SQ, H2, H4);
  private static PokerHand twoPair = new PokerHand(D9, S9, S8, C8, H3);
  
	@Test
	public void testSort() {
	  List<PokerHand> pokerhandList = new ArrayList<>();
	  pokerhandList.add(straightFlushAceLow);
	  pokerhandList.add(straightFlushAceHigh);
	  pokerhandList.add(straightFlush);
	  pokerhandList.add(FourOfAKindSixs);
	  pokerhandList.add(diamondFlush);
	  pokerhandList.add(highCard7);
	  pokerhandList.add(threeOfAKindQueen);
	  pokerhandList.add(twoPair);
	  System.out.println(pokerhandList.toString());
	  Collections.sort(pokerhandList);
	  System.out.println(pokerhandList.toString());
	  assertEquals(straightFlushAceHigh, pokerhandList.get(7));
	  assertEquals(straightFlush, pokerhandList.get(6));
	  assertEquals(straightFlushAceLow, pokerhandList.get(5));
	  assertEquals(FourOfAKindSixs, pokerhandList.get(4));
	  assertEquals(diamondFlush, pokerhandList.get(3));
	  assertEquals(threeOfAKindQueen, pokerhandList.get(2));
	  assertEquals(twoPair, pokerhandList.get(1));
	  assertEquals(highCard7, pokerhandList.get(0));
	}
	
  private static PokerHand straightHigh = new PokerHand(CA, HQ, CJ, CK, C10);
  private static PokerHand straightLow = new PokerHand(SA, D5, S4, S3, S2);
  private static PokerHand straightSecond = new PokerHand(HJ, D10, S9, C8, H7);
  private static PokerHand StraightThird = new PokerHand(D9, H8, S7, C6, C5);
  private static PokerHand StraightFour = new PokerHand(D8,D7, S6, S5, D4);
  private static PokerHand StraightFive = new PokerHand(D2, C4, D3, H5, H6);
  	
  @Test
	public void testSortStraight() {
	  List<PokerHand> pokerhandList = new ArrayList<>();
	  pokerhandList.add(straightHigh);
	  pokerhandList.add(straightLow);
	  pokerhandList.add(straightSecond);
	  pokerhandList.add(StraightFour);
	  pokerhandList.add(StraightFive);
	  pokerhandList.add(StraightThird);
	  System.out.println(pokerhandList.toString());
	  Collections.sort(pokerhandList);
	  System.out.println(pokerhandList.toString());
	  assertEquals(straightHigh, pokerhandList.get(5));
	  assertEquals(straightSecond, pokerhandList.get(4));
	  assertEquals(StraightThird, pokerhandList.get(3));
	  assertEquals(StraightFour, pokerhandList.get(2));
	  assertEquals(StraightFive, pokerhandList.get(1));
	  assertEquals(straightLow, pokerhandList.get(0));
	}
	
  private static PokerHand straightAceLow = new PokerHand(CA, H2, C3, C4, C5);
  private static PokerHand twoPairOne = new PokerHand(SA, HA, S4, H4, S2);
  private static PokerHand pair = new PokerHand(H2, D2, S9, C8, H7);
  private static PokerHand pairTwo = new PokerHand(D3, H3, SK, C10, CJ);
  private static PokerHand twoPairTwo = new PokerHand(D6,C6, S7, C7, DA);
  private static PokerHand pairThree = new PokerHand(DK, HK, DJ, H5, H6);
  private static PokerHand pairFour = new PokerHand(DQ, CQ, C2, H10, S3);
  
  @Test
 	public void testPairStraight() {
 	  List<PokerHand> pokerhandList = new ArrayList<>();
 	  pokerhandList.add(straightAceLow);
 	  pokerhandList.add(twoPairOne);
 	  pokerhandList.add(pair);
 	  pokerhandList.add(twoPairTwo);
 	  pokerhandList.add(pairTwo);
 	  pokerhandList.add(pairThree);
 	 pokerhandList.add(pairFour);
 	  System.out.println(pokerhandList.toString());
 	  Collections.sort(pokerhandList);
 	  System.out.println(pokerhandList.toString());
 	  assertEquals(straightAceLow, pokerhandList.get(6));
 	  assertEquals(twoPairOne, pokerhandList.get(5));
 	  assertEquals(twoPairTwo, pokerhandList.get(4));
 	  assertEquals(pairThree, pokerhandList.get(3));
 	  assertEquals(pairFour, pokerhandList.get(2));
 	  assertEquals(pairTwo, pokerhandList.get(1));
 	  assertEquals(pair, pokerhandList.get(0));
 	}
  
  private static PokerHand fullHouseFours = new PokerHand(C4, H4, D4, C7, D7);
  private static PokerHand fullHouseDeuces = new PokerHand(S2, H2, D2, HK, SK);
  private static PokerHand twoPairsKings = new PokerHand(H2, D2, SK, CK, HJ);
  private static PokerHand twoPairsSevensDeuces = new PokerHand(D7, S7, S2, C2, C10);
  private static PokerHand twoPairsSevensThrees = new PokerHand(D3,C3, H7, C7, DA);
  private static PokerHand highCardAces = new PokerHand(DK, HA, D8, H3, H2);
  private static PokerHand highCardAces2 = new PokerHand(DA, CK, C7, H6, S5);
  private static PokerHand straight = new PokerHand(D6, C5, C4, H3, S2);
  private static PokerHand lowStraight = new PokerHand(DA, C2, C3, H4, S5);
  
  @Test
  public void fullHouseVsFullHouse() {
    assertTrue(fullHouseFours.compareTo(fullHouseDeuces) > 0);
    assertTrue(fullHouseDeuces.compareTo(fullHouseFours) < 0);
  }
  
  @Test
  public void twoPairsVsTwoPairs() {
    assertTrue(twoPairsKings.compareTo(twoPairsSevensThrees) > 0);
    assertTrue(twoPairsSevensThrees.compareTo(twoPairsKings) < 0);
  }
  
  @Test
  public void twoPairsVsTwoPairs2() {
    assertTrue(twoPairsSevensDeuces.compareTo(twoPairsSevensThrees) < 0);
    assertTrue(twoPairsSevensThrees.compareTo(twoPairsSevensDeuces) > 0);
  }
  
  @Test
  public void highCardVsHighCard() {
    assertTrue(highCardAces.compareTo(highCardAces2) > 0);
    assertTrue(highCardAces2.compareTo(highCardAces) < 0);
  }
  
  @Test
  public void straightVsLowStraight() {
    assertTrue(straight.compareTo(lowStraight) > 0);
    assertTrue(lowStraight.compareTo(straight) < 0);
  }
}