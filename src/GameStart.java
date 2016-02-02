import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

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
 *Class Description: The GameStart class implements the other classes in Arizona Hold Em.
 *This class interacts with the user through a Scanner object to determine the amount of
 *players and whether the user wants to continue the game or not. It also interacts with 
 *the other main classes to create the necessary guidelines to create the game.
 *
 */
public class GameStart {
	private int playerCount = 0;
	private boolean continueGame = true;
	
	private Scanner in;
	private static GameStart holdEm; //GameStart object to run the game.
	private List<Player> players = new ArrayList<>();// Holds the amount of Player objects in the game
	private Deck communityDeck = new Deck();//Creates the player cards and community cards
	
	//Main method for the game to start.
	public static void main(String[] args) {
		//Creates the new object for GameStart.
		holdEm = new GameStart();
		int inGamePlayers = holdEm.getPlayerCount();
		//This method runs the game itself.
		holdEm.start(inGamePlayers);
	}
	
	//Constructor which creates the game.
	public GameStart(){
		in = new Scanner(System.in);
		System.out.print("How many players? ");
		playerCount = in.nextInt();
	    createPlayers(playerCount);
		System.out.println();
	}
	
	public int getPlayerCount(){
		return this.playerCount;
	}
	
	//Creates all the player objects and places them into a Player arraylist called players.
	private void createPlayers(int numberOfPlayers) {
		for (int i = 1; i <= numberOfPlayers; i++) {
			Player name = new Player(i, "Player");
			players.add(name);
		}
	}

	//start() begins the game itself. It implements other methods which display and
	//compare player objects and pokerhands objects.
	private void start(int numberOfPlayers) {
		//This will continue until the user changes the game to not run.
		while(continueGame){
			communityDeck.drawCards(numberOfPlayers, this.players);
			printCommunityCards(communityDeck);
			getPokerHands();
			displayPlayers();
			compareHands();
			continueGame();
		}
	}

	//The compareHands method places all players into an arraylist. It then sorts the players 
	//based on the players pokerhand ranking. Then it sends that player arraylist to the settleWinnings
	//method which determines the winner for the game. After that, it uses the player object Comparator
	//to rearrange the players based on their numeric distinguisher.
	private void compareHands() {
		List<Player> comparingPlayers = players;
		Collections.sort(comparingPlayers);
		settleWinnings(comparingPlayers);
//		for (Player player : comparingPlayers) {
//			System.out.print(player.toString() + " ,");
//		}
		Collections.sort(comparingPlayers, new Player(0, null));;
		
	}
	
	//The settle winnings method handles the winner for each hand based on arraylist index.
	private void settleWinnings(List<Player> comparingPlayers) {
		int totalWinnings = (playerCount - 1) * 2; // This variable value is used for only one winner.
		int losers = this.playerCount-2; //Index for where the losers begin in the comparingPlayers arraylist descending.
		int winners = 0;
		int lastIndex = this.playerCount - 1;
		int j = lastIndex - 2; //Set at the player just behind the index of the winning player
		
		Player first = comparingPlayers.get(this.playerCount-1); //Winning player
		Player second = comparingPlayers.get(this.playerCount-2);//Possible tying player
		
		//If the first and second player do not compare equally, then the first player is the winner.
		//If they do equal then multiple winners could be possible.
		if(first.getPokerHand().compareTo(second.getPokerHand()) != 0){
			first.addWinnings(totalWinnings);
			System.out.println("Winner: " + first.getName() + " $" + first.getBalance());
			System.out.println("++++++++++++++++++++++++++++++++++++");
			System.out.println("\t" + first.getPokerHand().getPokerHandRank().getRankName() + " " +  first.getBestHand());
		}else{
			//The method adds 1 to the winners because right now we know at least the first and second
			// players are tied.
			winners++;
			
			//This loops all the players start from the player right before the second.
			//It continues if there are more than two winners. If there are only two winners, the loop
			//will not run.
			while(first.getPokerHand().compareTo(comparingPlayers.get(j).getPokerHand())== 0){
				winners++;
				j--;
				if(j < 0) break;
			}
			
			totalWinnings = (playerCount - (winners + 1)) * 2; //Divides the winnings based on the number of winners
			System.out.println("Winning hands (tie)");
			System.out.println("++++++++++++++++++++++++++++++++++++");
			
			//Iterates the players arraylist starting at the last index based on the amount of winners
			for (int i = winners; i >= 0; i--) {
				Player winningPlayer = comparingPlayers.get(lastIndex);
				winningPlayer.addWinnings(totalWinnings / (winners + 1));
				System.out.println(winningPlayer.getBestHand() + " - " + winningPlayer.getPokerHand().getPokerHandRank().getRankName() +
						" - " + winningPlayer.getName() + " " + winningPlayer.getBalance());
				lastIndex--;
			}
			
			//Sets the amount of losing players in the game
			losers -= winners;
		}
		
		//Subtracts two based on the index of players starting from the losers index
		for (int i = losers; i >= 0; i--) {
			comparingPlayers.get(i).deductWinnings();
		}
	}
	
	//Creates the pokerhand for each player in the game
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
	
	//Prints the community hand at the start of the game
	private void printCommunityCards(Deck communityCards) {
		System.out.println("Community Cards: " + communityCards.toString());
		System.out.println("++++++++++++++++++++++++++++++++++++");
		
	}

	//Displays all players with their name, balance, two cards and also their best hand
	private void displayPlayers() {
		for (Player player : this.players) {
			System.out.println(player.getName() + ": $" + player.getBalance() + " - " + player.getHand());
			System.out.println("\tBest hand: " + player.getBestHand() + " - " + player.getPokerHand().getPokerHandRank().getRankName());
			System.out.println();
		}
	}
	
	//Asks the user whether or not they want to continue playing.
	private void continueGame() {
		String continueGameInput = "";
		System.out.println();
		System.out.println("Play another round? y or n: ");
		continueGameInput = in.next();
		if(continueGameInput.toLowerCase().compareTo("n") == 0 ||continueGameInput.toLowerCase().compareTo("no") == 0){
			this.continueGame = false;
			in.close();
		}
	}
}
