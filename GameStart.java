import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
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
public class GameStart {
	private static int playerCount = 0;
	private boolean continueGame = true;
	private Scanner in;
	private static GameStart holdEm;
	private List<Player> players = new ArrayList<>();
	private Deck communityDeck = new Deck();
	//construct a game object
	
	public static void main(String[] args) {
		holdEm = new GameStart();
		holdEm.start(playerCount);
	}

	public GameStart(){
		in = new Scanner(System.in);
		System.out.print("How many players? ");
		playerCount = in.nextInt();
	    createPlayers(playerCount);
		System.out.println();
	}
	
	private void createPlayers(int numberOfPlayers) {
		for (int i = 1; i <= numberOfPlayers; i++) {
			Player name = new Player(i, "Player");
			players.add(name);
		}
	}

	
	private void start(int numberOfPlayers) {
		if(continueGame){
			communityDeck.drawCards(numberOfPlayers, this.players);
			printCommunityCards(communityDeck);
			getPokerHands();
			displayPlayers();
			compareHands();
			continueGame();
		}
		
	}


	private void compareHands() {
		List<Player> comparingPlayers = players;
		Collections.sort(comparingPlayers);
		settleWinnings(comparingPlayers);
//		for (Player player : comparingPlayers) {
//			System.out.print(player.toString() + " ,");
//		}
		Collections.sort(comparingPlayers, new Player(0, null));;
		
	}

	private void settleWinnings(List<Player> comparingPlayers) {
		
		int totalWinnings = (playerCount - 1) * 2;
		int losers = this.playerCount-2;
		int winners = 0;
		int lastIndex = this.playerCount - 1;
		int j = lastIndex - 2;
		Player first = comparingPlayers.get(this.playerCount-1);
		Player second = comparingPlayers.get(this.playerCount-2);
		
		if(first.getPokerHand().compareTo(second.getPokerHand()) != 0){
			first.addWinnings(totalWinnings);
			System.out.println("Winner: " + first.getName() + " $" + first.getBalance());
			System.out.println("++++++++++++++++++++++++++++++++++++");
			System.out.println("\t" + first.getPokerHand().getPokerHandRank() + " " +  first.getBestHand());
		}else{
			winners++;
			while(first.getPokerHand().compareTo(comparingPlayers.get(j).getPokerHand())== 0){
				winners++;
				j--;
				if(j < 0) break;
			}
			totalWinnings = (playerCount - (winners + 1)) * 2;
			System.out.println("Winning hands (tie)");
			System.out.println("++++++++++++++++++++++++++++++++++++");
			for (int i = winners; i >= 0; i--) {
				Player winningPlayer = comparingPlayers.get(lastIndex);
				winningPlayer.addWinnings(totalWinnings / (winners + 1));
				System.out.println(winningPlayer.getBestHand() + " - " + winningPlayer.getPokerHand().getPokerHandRank() +
						" - " + winningPlayer.getName() + " " + winningPlayer.getBalance());
				lastIndex--;
			}
			losers -= winners;
		}
		
	
		for (int i = losers; i >= 0; i--) {
			comparingPlayers.get(i).deductWinnings();
		}
	}

	private void getPokerHands() {
		for (Player player : players) {
			player.setPokerHand(player.getPlayerHand().get(0),
					player.getPlayerHand().get(1), 
					communityDeck.getCommunityCards().get(0),
					communityDeck.getCommunityCards().get(1),
					communityDeck.getCommunityCards().get(2),
					communityDeck.getCommunityCards().get(3), 
					communityDeck.getCommunityCards().get(4));
			//System.out.println(player.getPokerHand().toString());
		}
		
	}

	private void printCommunityCards(Deck communityCards) {
		System.out.println("Community Cards: " + communityCards.toString());
		System.out.println("++++++++++++++++++++++++++++++++++++");
		
	}

	private void displayPlayers() {
		for (Player player : this.players) {
			System.out.println(player.getName() + ": $" + player.getBalance() + " - " + player.getHand());
			System.out.println("\tBest hand: " + player.getBestHand() + " - " + player.getPokerHand().getPokerHandRank());
			System.out.println();
		}
	}

	private void continueGame() {
		String continueGameInput = "";
		System.out.println();
		System.out.println("Play another round? y or n: ");
		continueGameInput = in.next();
		if(continueGameInput.toLowerCase().compareTo("n") == 0 ||continueGameInput.toLowerCase().compareTo("no") == 0){
			this.continueGame = false;
			in.close();
		}
		setPlayerHandsNull(this.players);
		holdEm.start(playerCount);
		
	}

	private void setPlayerHandsNull(List<Player> players2) {
		for (Player player : players2) {
			player.setPokerHandNull();
		}
		
	}


	

}
