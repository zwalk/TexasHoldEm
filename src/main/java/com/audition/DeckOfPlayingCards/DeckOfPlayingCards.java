package com.audition.DeckOfPlayingCards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.audition.PlayingCard.PlayingCard;

public class DeckOfPlayingCards {
	
	private final String[] suits = {"h", "d", "c", "s"};
	private final String[] names = {"2", "3", "4", "5", "6", "7", "8", "9", "T", "J", "Q", "K", "A"};
	private List<PlayingCard> cardList = new ArrayList<PlayingCard>();
	
	public DeckOfPlayingCards() {
		buildDeck();
	}
	
	private void buildDeck() {
		for (String suit : suits) {
			for (String name : names) {
				int value = assignValue(name);
				
				cardList.add(new PlayingCard(name, suit, value));
			}
		}
	}
	
	private int assignValue(String name) {
		int value = 0;
		
		if (name.equals("T")) {
			value = 10;
		} else if (name.equals("J")) {
			value = 11;
		} else if (name.equals("Q")) {
			value = 12;
		} else if (name.equals("K")) {
			value = 13;
		} else if (name.equals("A")) {
			value = 14;
		} else {
			value = Integer.parseInt(name);
		}
		
		return value;
		
		
	}
	
	public void shuffleDeck() {
		Collections.shuffle(cardList);
	}
	
	public PlayingCard getNextCard() {
		return cardList.remove(0);
	}
	

}
