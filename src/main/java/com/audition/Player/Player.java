package com.audition.Player;

import com.audition.Hand.Hand;
import com.audition.PlayingCard.PlayingCard;

public class Player {
	
	private String name;
	private Hand hand;
	private boolean hasFolded = false;
	
	public Player(String name) {
		this.name = name;
		this.hand = new Hand();
	}
	
	public String getName() {
		return name;
	}
	
	public Hand getHand() {
		return hand;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((hand == null) ? 0 : hand.hashCode());
		result = prime * result + (hasFolded ? 1231 : 1237);
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Player other = (Player) obj;
		if (hand == null) {
			if (other.hand != null)
				return false;
		} else if (!hand.equals(other.hand))
			return false;
		if (hasFolded != other.hasFolded)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	public void addCardToHand(PlayingCard card) {
		hand.addCardToHand(card);
	}
	
	public boolean getHasFolded() {
		return hasFolded;
	}
	
	public void getFoldDecision() {

		
		int max = 100;
		int min = 1;
		
		int randomNumber = (int)(Math.random() * ((max - min) + 1) + min);
		
		if (randomNumber < 30) {
			hasFolded = true;
		}

	}

}
