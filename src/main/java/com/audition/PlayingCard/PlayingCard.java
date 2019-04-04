package com.audition.PlayingCard;

public class PlayingCard {
	
	private String name;
	private String suit;
	private int value;
	
	public PlayingCard(String name, String suit, int value) {
		this.name = name;
		this.suit = suit;
		this.value = value;
	}
	
	public String getName() {
		return name;
	}
	
	public String getSuit() {
		return suit;
	}
	
	public int getValue() {
		return value;
	}

	@Override
	public String toString() {
		return (name + suit);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((suit == null) ? 0 : suit.hashCode());
		result = prime * result + value;
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
		PlayingCard other = (PlayingCard) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (suit == null) {
			if (other.suit != null)
				return false;
		} else if (!suit.equals(other.suit))
			return false;
		if (value != other.value)
			return false;
		return true;
	}
	
	
}
