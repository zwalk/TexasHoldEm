package com.audition.Hand;

import java.util.ArrayList;
import java.util.List;

import com.audition.PlayingCard.PlayingCard;

public class Hand {
	
	private List<PlayingCard> cardsInHand = new ArrayList<PlayingCard>();
	private String name;
	private boolean isWinner = false;
	private boolean isAceLowStraight = false;
	private List<PlayingCard> cardsUsedToMakeHand = new ArrayList<PlayingCard>();
	
	public int getHandValue() {
		int handValue = 1;
		
		if (name == null) {
			return handValue;
		}
		
		if (this.name.equals("Royal Flush")) {
			handValue = 10;
		} else if (this.name.equals("Straight Flush")) {
			handValue = 9;
		} else if (this.name.equals("Four of a Kind")) {
			handValue = 8;
		} else if (this.name.equals("Full House")) {
			handValue = 7;
		} else if (this.name.equals("Flush")) {
			handValue = 6;
		} else if (this.name.equals("Straight")) {
			handValue = 5;
		} else if (this.name.equals("Three of a Kind")) {
			handValue = 4;
		} else if (this.name.equals("Two Pair")) {
			handValue = 3;
		} else if (this.name.equals("One Pair")) {
			handValue = 2;
		} else if (this.name.equals("Folded")) {
			handValue = 0;
		}
		
		return handValue;
	}
	
	public boolean getIsAceLowStraight() {
		return isAceLowStraight;
	}
	
	public void setIsAceLowStraight(boolean isAceLowStraight) {
		this.isAceLowStraight = isAceLowStraight;
	}
	
	public List<PlayingCard> getCardsUsedToMakeHand() {
		return cardsUsedToMakeHand;
	}
	
	public void addCardUsedToMakeHand(PlayingCard card) {
		cardsUsedToMakeHand.add(card);
	}
	
	public void addCardToHand(PlayingCard card) {
		cardsInHand.add(card);
	}
	
	public List<PlayingCard> getCardsInHandList() {
		return cardsInHand;
	}
	
	public boolean getIsWinner() {
		return isWinner;
	}
	
	public void setIsWinner(boolean isWinner) {
		this.isWinner = isWinner;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		String handString = "";
		
		for (PlayingCard card : cardsInHand) {
			handString += card.toString() + " ";
		}
		
		return handString;
	}
	
}
