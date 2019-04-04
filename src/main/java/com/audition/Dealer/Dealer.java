package com.audition.Dealer;

import java.util.ArrayList;
import java.util.List;
import com.audition.DeckOfPlayingCards.DeckOfPlayingCards;
import com.audition.Player.Player;
import com.audition.PlayingCard.PlayingCard;

public class Dealer {
	
	private DeckOfPlayingCards gameDeck = new DeckOfPlayingCards();
	private List<PlayingCard> burnPile = new ArrayList<PlayingCard>();
	private List<Player> playersAtTable = new ArrayList<Player>();
	
	public Dealer() {
		buildPlayers();
		shuffleDeckFourTimes();
	}
	
	private void buildPlayers() {
		for (int i = 1; i <= 6; i++) {
			playersAtTable.add(new Player("Player " + i));
		}
	}
	
	public boolean getHaveAllPlayersFolded() {
		boolean haveAllPlayersFolded = true;
		
		for (Player player : playersAtTable) {
			if (! player.getHasFolded()) {
				haveAllPlayersFolded = false;
			}
		}
		
		return haveAllPlayersFolded;
	}
	
	public List<Player> getPlayers() {
		return playersAtTable;
	}
	
	public List<PlayingCard> getBurnPile() {
		return burnPile;
	}
	
	public void dealStartingHand() {
		burnOneCard();
		
		for (int i = 0; i < 2; i++) {
			for(Player player : playersAtTable) {
				player.addCardToHand(gameDeck.getNextCard());
			}
		}
	}
	
	private void burnOneCard() {
		burnPile.add(gameDeck.getNextCard());
	}
	
	private void shuffleDeckFourTimes() {
		for (int i = 0; i < 4; i++) {
			gameDeck.shuffleDeck();
		}
	}
	
	public void askPlayersForFoldDecision() {
		for (Player player : playersAtTable) {
			player.getFoldDecision();
		}
	}
	
	public void dealFlop() {
		burnOneCard();
		
		PlayingCard firstFlopCard = gameDeck.getNextCard();
		PlayingCard secondFlopCard = gameDeck.getNextCard();
		PlayingCard thirdFlopCard = gameDeck.getNextCard();
		
		for (Player player : playersAtTable) {
			if (! player.getHasFolded()) {
				player.addCardToHand(firstFlopCard);
				player.addCardToHand(secondFlopCard);
				player.addCardToHand(thirdFlopCard);
			}
		}
	}
	
	public void dealTurn() {
		burnOneCard();
		addOneCardToCommunity();
	}
	
	public void dealRiver() {
		burnOneCard();
		addOneCardToCommunity();
	}
	
	private void addOneCardToCommunity() {
		PlayingCard turnCard = gameDeck.getNextCard();
		
		for (Player player : playersAtTable) {
			if (! player.getHasFolded()) {
				player.addCardToHand(turnCard);
			}
		}
	}

}
