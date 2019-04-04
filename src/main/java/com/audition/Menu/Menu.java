package com.audition.Menu;

import java.util.List;

import com.audition.Player.Player;

public class Menu {
	
	public void displayWelcomeMessage() {
		System.out.println("\n" + 
				"╦ ╦┌─┐┬  ┌─┐┌─┐┌┬┐┌─┐  ┌┬┐┌─┐  ╔═╗┌─┐┌─┐┬ ┬┌─┐  ╔╦╗┌─┐─┐ ┬┌─┐┌─┐  ╦ ╦┌─┐┬  ┌┬┐╔═╗┌┬┐\n" + 
				"║║║├┤ │  │  │ ││││├┤    │ │ │  ╔═╝├─┤│  ├─┤└─┐   ║ ├┤ ┌┴┬┘├─┤└─┐  ╠═╣│ ││   ││║╣ │││\n" + 
				"╚╩╝└─┘┴─┘└─┘└─┘┴ ┴└─┘   ┴ └─┘  ╚═╝┴ ┴└─┘┴ ┴└─┘   ╩ └─┘┴ └─┴ ┴└─┘  ╩ ╩└─┘┴─┘─┴┘╚═╝┴ ┴\n");
	}
	
	public void displayStartingHands(List<Player> playerList) {
		System.out.println();
		System.out.println("STARTING HANDS");
		System.out.println();
		displayHands(playerList);
	}
	
	public void displayHandsAfterFlop(List<Player> playerList) {
		System.out.println();
		System.out.println("THE FLOP");
		System.out.println();
		displayHands(playerList);
	}
	
	public void displayHandsAfterTurn(List<Player> playerList) {
		System.out.println();
		System.out.println("THE TURN");
		System.out.println();
		displayHands(playerList);
	}
	
	
	private void displayHands(List<Player> playerList) {
		
		int lastPlayerCheck = 1;
		
		for (Player player : playerList) {
			
			if (lastPlayerCheck == playerList.size()) {
				System.out.println(player.getHand().toString());
				System.out.println();
			} else {
				System.out.println(player.getHand().toString());
				lastPlayerCheck++;
			}

		}
	}
	
	public void displayWinningHands(List<Player> playerList) {
		
		System.out.println();
		System.out.println("THE RIVER");
		System.out.println();
		
		for (Player player : playerList) {
			if(! player.getHasFolded()) {
				System.out.print(player.getHand().toString());
				System.out.print(player.getHand().getName());
				if (player.getHand().getIsWinner()) {
					System.out.println("  (Winner)");
				} else {
					System.out.println();
				}
			} else {
				System.out.println(player.getHand().toString());
			}

		}
	}
	
	public void displayAllPlayersFoldedMessage() {
		System.out.println();
		System.out.println("All players folded, POT GOES TO CHARITY");
		System.out.println();
	}
	
	public void displayEndGameMessage() {
		System.out.println();
		System.out.println("\n" + 
				"████████╗██╗  ██╗ █████╗ ███╗   ██╗██╗  ██╗    ██╗   ██╗ ██████╗ ██╗   ██╗    ███████╗ ██████╗ ██████╗     ██████╗ ██╗      █████╗ ██╗   ██╗██╗███╗   ██╗ ██████╗ ██╗\n" + 
				"╚══██╔══╝██║  ██║██╔══██╗████╗  ██║██║ ██╔╝    ╚██╗ ██╔╝██╔═══██╗██║   ██║    ██╔════╝██╔═══██╗██╔══██╗    ██╔══██╗██║     ██╔══██╗╚██╗ ██╔╝██║████╗  ██║██╔════╝ ██║\n" + 
				"   ██║   ███████║███████║██╔██╗ ██║█████╔╝      ╚████╔╝ ██║   ██║██║   ██║    █████╗  ██║   ██║██████╔╝    ██████╔╝██║     ███████║ ╚████╔╝ ██║██╔██╗ ██║██║  ███╗██║\n" + 
				"   ██║   ██╔══██║██╔══██║██║╚██╗██║██╔═██╗       ╚██╔╝  ██║   ██║██║   ██║    ██╔══╝  ██║   ██║██╔══██╗    ██╔═══╝ ██║     ██╔══██║  ╚██╔╝  ██║██║╚██╗██║██║   ██║╚═╝\n" + 
				"   ██║   ██║  ██║██║  ██║██║ ╚████║██║  ██╗       ██║   ╚██████╔╝╚██████╔╝    ██║     ╚██████╔╝██║  ██║    ██║     ███████╗██║  ██║   ██║   ██║██║ ╚████║╚██████╔╝██╗\n" + 
				"   ╚═╝   ╚═╝  ╚═╝╚═╝  ╚═╝╚═╝  ╚═══╝╚═╝  ╚═╝       ╚═╝    ╚═════╝  ╚═════╝     ╚═╝      ╚═════╝ ╚═╝  ╚═╝    ╚═╝     ╚══════╝╚═╝  ╚═╝   ╚═╝   ╚═╝╚═╝  ╚═══╝ ╚═════╝ ╚═╝\n" + 
				"                                                                                                                                                                     \n"
				);
	}

}
