package com.audition.TexasHoldEmCLI;

import com.audition.Dealer.Dealer;
import com.audition.HandRanker.HandRanker;
import com.audition.Menu.Menu;

public class TexasHoldEmCLI {
	
	private static Menu theMenu = new Menu();
	private static Dealer theDealer = new Dealer();
	private static HandRanker theHandRanker;
	
	
	public static void main(String[] args) {
		
		theHandRanker = new HandRanker(theDealer.getPlayers());
		
		theMenu.displayWelcomeMessage();
		
		theDealer.dealStartingHand();
		theMenu.displayStartingHands(theDealer.getPlayers());
		theDealer.askPlayersForFoldDecision();
		
		if (theDealer.getHaveAllPlayersFolded()) {
			theMenu.displayAllPlayersFoldedMessage();
			theMenu.displayEndGameMessage();
			System.exit(0);
		}
		
		theDealer.dealFlop();
		theMenu.displayHandsAfterFlop(theDealer.getPlayers());
		theDealer.askPlayersForFoldDecision();
		
		if (theDealer.getHaveAllPlayersFolded()) {
			theMenu.displayAllPlayersFoldedMessage();
			theMenu.displayEndGameMessage();
			System.exit(0);
		}
		
		theDealer.dealTurn();
		theMenu.displayHandsAfterTurn(theDealer.getPlayers());
		theDealer.askPlayersForFoldDecision();
		
		if (theDealer.getHaveAllPlayersFolded()) {
			theMenu.displayAllPlayersFoldedMessage();
			theMenu.displayEndGameMessage();
			System.exit(0);
		}
		
		theDealer.dealRiver();
		
		theHandRanker.assignHandNames();
		theHandRanker.determineWinner();
		
		theMenu.displayWinningHands(theDealer.getPlayers());
		
		theMenu.displayEndGameMessage();
		
	}

}
