package com.audition.HandRanker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.audition.Hand.Hand;
import com.audition.Player.Player;
import com.audition.PlayingCard.PlayingCard;

public class HandRanker {
	
	List<Player> playerList;
	
	public HandRanker(List<Player> playerList) {
		this.playerList = playerList;
	}

	public void assignHandNames() {
		
		for(Player player : playerList) {
			Hand hand = player.getHand();
			if (!player.getHasFolded()) {
				
				
				if (checkIfRoyalFlush(player)) {
					hand.setName("Royal Flush");
				} else if (checkIfStraightFlush(player)) {
					hand.setName("Straight Flush");
				} else if (checkIfFourOfAKind(player)) {
					hand.setName("Four of a Kind");
				} else if(checkIfFullHouse(player)) {
					hand.setName("Full House");
				} else if (checkIfFlush(player)) {
					hand.setName("Flush");
				} else if (checkIfStraight(player)) {
					hand.setName("Straight");
				} else if (checkIfThreeOfAKind(player)) {
					hand.setName("Three of a Kind");
				} else if (checkIfTwoPair(player)) {
					hand.setName("Two Pair");
				} else if (checkIfOnePair(player)) {
					hand.setName("One Pair");
				} else {
					hand.setName("High Card");
					addRemainingHighCardsToCardsUsedToMakeHand(player.getHand());
				}
			} else {
				hand.setName("Folded");
			}

			
		}		
		
	}
	
	private boolean checkIfRoyalFlush(Player player) {
		
		boolean isRoyalFlush = false;
		int diamondCount = 0;
		int clubsCount = 0;
		int spadesCount = 0;
		int heartsCount = 0;
		String suit = "";
		
		
		for (PlayingCard card : player.getHand().getCardsInHandList()) {
			if (card.getSuit().equals("d")) {
				diamondCount++;
			} else if (card.getSuit().equals("c")) {
				clubsCount++;
			} else if (card.getSuit().equals("s")) {
				spadesCount++;
			} else if (card.getSuit().equals("h")) {
				heartsCount++;
			}
		}
		
		if (diamondCount >= 5) {
			suit = "d";
		} else if (clubsCount >= 5) {
			suit = "c";
		} else if (spadesCount >= 5) {
			suit = "s";
		} else if (heartsCount >= 5) {
			suit = "h";
		}
		
		PlayingCard aceFlush = new PlayingCard("A", suit, 14);
		PlayingCard kingFlush = new PlayingCard("K", suit, 13);
		PlayingCard queenFlush = new PlayingCard("Q", suit, 12);
		PlayingCard jackFlush = new PlayingCard("J", suit, 11);
		PlayingCard tenFlush = new PlayingCard("T", suit, 10);
		
		if (player.getHand().getCardsInHandList().contains(aceFlush) 
				&& player.getHand().getCardsInHandList().contains(kingFlush)
				&& player.getHand().getCardsInHandList().contains(queenFlush)
				&& player.getHand().getCardsInHandList().contains(jackFlush)
				&& player.getHand().getCardsInHandList().contains(tenFlush)) {
			isRoyalFlush = true;
		}
		
		if (isRoyalFlush) {
			for (PlayingCard card : player.getHand().getCardsInHandList()) {
				if (card.equals(aceFlush) || card.equals(kingFlush) || card.equals(queenFlush) || card.equals(jackFlush) || card.equals(tenFlush)) {
					player.getHand().addCardUsedToMakeHand(card);
				}
			}
		}
		

		
		return isRoyalFlush;
		
	}
	
	private boolean checkIfStraightFlush(Player player) {
		
		boolean isStraightFlush = false;
		int suitCount = 0;
		String suit = "";
		
		if (checkIfStraight(player)) {
			for (PlayingCard card : player.getHand().getCardsUsedToMakeHand()) {
				if (suitCount == 0) {
					suit = card.getSuit();
					suitCount++;
				} else if (card.getSuit().equals(suit)) {
					suitCount++;
				}
			}
		} else {
			while (!player.getHand().getCardsUsedToMakeHand().isEmpty()) {
				player.getHand().getCardsUsedToMakeHand().remove(0);
			}
		}
		
		if (suitCount == 5) {
			isStraightFlush = true;
		}
		
		return isStraightFlush;
	}
	
	private boolean checkIfFourOfAKind(Player player) {
		boolean isFourOfAKind = false;
		String nameOfCardThatMakesUpFourOfAKind = "";
		
		Map<String, Integer> matchCount = new LinkedHashMap<String, Integer>();
		
		for (PlayingCard card : player.getHand().getCardsInHandList()) {
			
			if (matchCount.containsKey(card.getName())) {
				matchCount.put(card.getName(), matchCount.get(card.getName()) + 1);
			} else {
				matchCount.put(card.getName(), 1);
			}
			
		}
		
		for (String name : matchCount.keySet()) {
			if (matchCount.get(name) == 4) {
				isFourOfAKind = true;
				nameOfCardThatMakesUpFourOfAKind = name;
				break;
			}
		}
		
		for (PlayingCard card : player.getHand().getCardsInHandList()) {
			if (card.getName().equals(nameOfCardThatMakesUpFourOfAKind)) {
				player.getHand().addCardUsedToMakeHand(card);
			}
		}
		
		if (isFourOfAKind) {
			addRemainingHighCardsToCardsUsedToMakeHand(player.getHand());
		}
		
		
		
		return isFourOfAKind;
	}
	
	private boolean checkIfFullHouse(Player player) {
		boolean isFullHouse = false;
		boolean isThreeOfAKind = false;
		boolean isPair = false;
		
		Map<String, Integer> matchCount = new LinkedHashMap<String, Integer>();
		List<PlayingCard> cardsInHand = player.getHand().getCardsInHandList();
		List<PlayingCard> cardsInHandHighestValueFirst = new ArrayList<PlayingCard>();
		
		while (cardsInHandHighestValueFirst.size() != cardsInHand.size() ) {
			
			PlayingCard highestValueCard = new PlayingCard("placeholder", "placeholder", 0);
			
			for (PlayingCard card : cardsInHand) {
				if (card.getValue() > highestValueCard.getValue() && !cardsInHandHighestValueFirst.contains(card)) {
					highestValueCard = card;
				}
			}
			
			cardsInHandHighestValueFirst.add(highestValueCard);
		}
		
		String nameOfCardThatMakesUpThreeOfAKind = "";
		String nameOfCardThatMakesUpPair = "";
		
		for (PlayingCard card : cardsInHandHighestValueFirst) {
			
			if (matchCount.containsKey(card.getName())) {
				matchCount.put(card.getName(), matchCount.get(card.getName()) + 1);
			} else {
				matchCount.put(card.getName(), 1);
			}
			
			
		}
		
		for (String name : matchCount.keySet()) {
			if (matchCount.get(name) == 3) {
				isThreeOfAKind = true;
				nameOfCardThatMakesUpThreeOfAKind = name;
			}
			
			if (matchCount.get(name) == 2) {
				isPair = true;
				nameOfCardThatMakesUpPair = name;
			}
			
			if (isThreeOfAKind && isPair) {
				isFullHouse = true;
				break;
			}
		}
		
		if (isFullHouse) {
			for (PlayingCard card : cardsInHandHighestValueFirst) {
				if (card.getName() == nameOfCardThatMakesUpThreeOfAKind || card.getName() == nameOfCardThatMakesUpPair) {
					player.getHand().addCardUsedToMakeHand(card);
				}
			}
		}

		
		return isFullHouse;
	}
	
	private boolean checkIfFlush(Player player) {
		boolean isFlush = false;
		int diamondCount = 0;
		int clubsCount = 0;
		int spadesCount = 0;
		int heartsCount = 0;
		String flushSuit = "";
		
		for (PlayingCard card : player.getHand().getCardsInHandList()) {
			if (card.getSuit().equals("d")) {
				diamondCount++;
			} else if (card.getSuit().equals("c")) {
				clubsCount++;
			} else if (card.getSuit().equals("s")) {
				spadesCount++;
			} else if (card.getSuit().equals("h")) {
				heartsCount++;
			}
		}
		
		if (diamondCount >= 5) {
			flushSuit = "d";
			isFlush = true;
		} else if (clubsCount >= 5) {
			flushSuit = "c";
			isFlush = true;
		} else if (spadesCount >= 5) {
			flushSuit = "s";
			isFlush = true;
		} else if (heartsCount >= 5) {
			flushSuit = "h";
			isFlush = true;
		}
		
		if (isFlush) {
			addFlushCardsToPlayerHand(player, flushSuit);
		}
		
		return isFlush;
	}
	
	private void addFlushCardsToPlayerHand(Player player, String flushSuit) {
		
		while (!(player.getHand().getCardsUsedToMakeHand().size() == 5)) {
			
			PlayingCard highCard = new PlayingCard("placeholder", "placeholder", 0);
			
			for (PlayingCard card : player.getHand().getCardsInHandList()) {
				if (card.getSuit().equals(flushSuit) && card.getValue() > highCard.getValue()) {
					highCard = card;
				}
			}
			
			player.getHand().addCardUsedToMakeHand(highCard);
			
		}

	}
	

	
	private boolean checkIfStraight(Player player) {
		boolean isStraight = false;

		if (lookForSequence(player)) {
			isStraight = true;
		}
		
		return isStraight;
	}
	
	private boolean lookForSequence(Player player) {
		boolean isThereASequence = false;
		List<Integer> valueList = new ArrayList<Integer>();
		List<PlayingCard> cardsInHand = player.getHand().getCardsInHandList();
		Map<Integer, PlayingCard> valueToCardMap = new HashMap<Integer, PlayingCard>();
		
		for (PlayingCard card : cardsInHand) {
			valueList.add(card.getValue());
			valueToCardMap.put(card.getValue(), card);
		}
		
		Collections.sort(valueList);
		
		int numberOfCardsInHand = cardsInHand.size() - 1;
		int countOfNumbersInSequence = 1;
		List<PlayingCard> straightList = new ArrayList<PlayingCard>();
		boolean isAnAceLowStraight = false;
		
		for (int i = numberOfCardsInHand; i != 0; i--) {
			int currentNumber = valueList.get(i);
			
			if (straightList.size() == 0) {
				straightList.add(valueToCardMap.get(currentNumber));
			}
			
			if (valueList.contains(14) && valueList.contains(2) && valueList.contains(3) && valueList.contains(4) && valueList.contains(5)) {
				countOfNumbersInSequence = 5;
				straightList.remove(0);
				straightList.add(valueToCardMap.get(14));
				straightList.add(valueToCardMap.get(2));
				straightList.add(valueToCardMap.get(3));
				straightList.add(valueToCardMap.get(4));
				straightList.add(valueToCardMap.get(5));
				isAnAceLowStraight = true;
				player.getHand().setIsAceLowStraight(true);
			} else if (valueList.get(i - 1) == currentNumber - 1 && !isAnAceLowStraight) {
				countOfNumbersInSequence++;
				straightList.add(valueToCardMap.get(currentNumber));
			} else if (!(valueList.get(i - 1) == currentNumber - 1) && i < 5 && !isAnAceLowStraight) {
				break;
			} else if (!isAnAceLowStraight) {
				countOfNumbersInSequence = 1;
				straightList = new ArrayList<PlayingCard>();
			}
		
			if (countOfNumbersInSequence == 5) {
				isThereASequence = true;
				for (PlayingCard card : straightList) {
					player.getHand().addCardUsedToMakeHand(card);
				}
				break;
			}
			
		}
		
		return isThereASequence;
	}
	
	private boolean checkIfThreeOfAKind(Player player) {
		boolean isThreeOfAKind = false;
		Map<String, Integer> matchCount = new HashMap<String, Integer>();
		List<PlayingCard> cardsInHand = player.getHand().getCardsInHandList();
		List<PlayingCard> cardsThatMakeUpThreeOfAKind = new ArrayList<PlayingCard>();
		
		List<PlayingCard> cardsInHandHighestValueFirst = new ArrayList<PlayingCard>();
		
		while (cardsInHandHighestValueFirst.size() != cardsInHand.size() ) {
			
			PlayingCard highestValueCard = new PlayingCard("placeholder", "placeholder", 0);
			
			for (PlayingCard card : cardsInHand) {
				if (card.getValue() > highestValueCard.getValue() && !cardsInHandHighestValueFirst.contains(card)) {
					highestValueCard = card;
				}
			}
			
			cardsInHandHighestValueFirst.add(highestValueCard);
		}
		
		String nameOfCardThatMakesUpThreeOfAKind = "";
		
		for (PlayingCard card : cardsInHandHighestValueFirst) {
			
			if (matchCount.containsKey(card.getName())) {
				matchCount.put(card.getName(), matchCount.get(card.getName()) + 1);
			} else {
				matchCount.put(card.getName(), 1);
			}
			
			if (matchCount.get(card.getName()) == 3) {
				isThreeOfAKind = true;
				nameOfCardThatMakesUpThreeOfAKind = card.getName();
				break;
			}
			
		}
		
		if (isThreeOfAKind) {
			for (PlayingCard card : cardsInHandHighestValueFirst) {
				if (card.getName().equals(nameOfCardThatMakesUpThreeOfAKind)) {
					cardsThatMakeUpThreeOfAKind.add(card);
				}
			}
			
			for (PlayingCard card : cardsThatMakeUpThreeOfAKind) {
				player.getHand().addCardUsedToMakeHand(card);
			}
			addRemainingHighCardsToCardsUsedToMakeHand(player.getHand());
		}
		
		
		
		return isThreeOfAKind;
		
	}
	
	private boolean checkIfTwoPair(Player player) {
		boolean isTwoPair = false;
		
		if (lookForPairs(player) >= 2) {
			isTwoPair = true;
		}
		
		addRemainingHighCardsToCardsUsedToMakeHand(player.getHand());
		
		return isTwoPair;
	}
	
	private boolean checkIfOnePair(Player player) {
		
		boolean isPair = false;
		
		if (lookForPairs(player) == 1) {
			isPair = true;
		}
		
		addRemainingHighCardsToCardsUsedToMakeHand(player.getHand());
		
		return isPair;
	}
	
	private int lookForPairs(Player player) {
		List<PlayingCard> cardsInHand = player.getHand().getCardsInHandList();
		List<PlayingCard> cardsInHandSorted = sortCardListLargestToSmallestValue(cardsInHand);
		int pairCount = 0;
		Map<Integer, PlayingCard> nameOfCardMap = new HashMap<Integer, PlayingCard>();
		
		for (PlayingCard card : cardsInHandSorted) {
			
			if (! nameOfCardMap.containsKey(card.getValue())) {
				nameOfCardMap.put(card.getValue(), card);
			} else {
				pairCount++;
				
				PlayingCard firstOfPair = nameOfCardMap.remove(card.getValue());
				if (! (player.getHand().getCardsUsedToMakeHand().size() >= 4)) {
					
					if (! player.getHand().getCardsUsedToMakeHand().contains(card)) {
						player.getHand().addCardUsedToMakeHand(card);
					}
					
					if (! player.getHand().getCardsUsedToMakeHand().contains(firstOfPair)) {
						player.getHand().addCardUsedToMakeHand(firstOfPair);
					}
				}
			}		
			
		}
		
		
		
		return pairCount;
		
	}
	
	private List<PlayingCard> sortCardListLargestToSmallestValue(List<PlayingCard> cardList) {
		
		
		List<PlayingCard> sortedCardListDescValue = new ArrayList<PlayingCard>();
		
		while (sortedCardListDescValue.size() != cardList.size()) {
			
			PlayingCard highCard = new PlayingCard("0", "s", 0);
			
			for (PlayingCard card : cardList) {
				if (!(sortedCardListDescValue.contains(card)) && card.getValue() > highCard.getValue()) {
					highCard = card;
				}
			}
			
			sortedCardListDescValue.add(highCard);
			
		}
			
		
		return sortedCardListDescValue;
		
	}
	
	private void addRemainingHighCardsToCardsUsedToMakeHand(Hand hand) {
		
		
		while (! (hand.getCardsUsedToMakeHand().size() == 5)) {
			
			PlayingCard highCard = new PlayingCard("0", "s", 0);
			
			for (PlayingCard card : hand.getCardsInHandList()) {
				if (!(hand.getCardsUsedToMakeHand().contains(card)) && card.getValue() > highCard.getValue()) {
					highCard = card;
				}
			}
			
			hand.addCardUsedToMakeHand(highCard);
			
		}
		
	}
	
	public void determineWinner() {
		List<Player> highestHandFirstSorted = new ArrayList<Player>();
		
		while (! (highestHandFirstSorted.size() == playerList.size())) {
			
			Player playerWithHighestValuedHand = new Player("placeholder player");
			
			for (Player player : playerList) {
				if (!(highestHandFirstSorted.contains(player)) 
						&& player.getHand().getHandValue() >= playerWithHighestValuedHand.getHand().getHandValue()) {
					playerWithHighestValuedHand = player;
				}
			}
			
			highestHandFirstSorted.add(playerWithHighestValuedHand);
			
		}
		
		int highestHandValue = highestHandFirstSorted.get(0).getHand().getHandValue();
		
		for (Player sortedPlayer : highestHandFirstSorted) {
			
			if (sortedPlayer.getHand().getHandValue() == highestHandValue) {
				sortedPlayer.getHand().setIsWinner(true);
			}
		}
		
		breakTie(highestHandFirstSorted);

	}
	
	private void breakTie(List<Player> highestHandFirstSorted) {
		List<Player> winnersOnly = new ArrayList<Player>();
		Player winner = null;
		
		for (Player player : highestHandFirstSorted) {
			if (player.getHand().getIsWinner()) {
				winnersOnly.add(player);
			}
		}

		
		for (int i = 0; i < 5; i++) {
			
			Map<Integer, Player> cardValuesToCompareMappedToPlayer = new HashMap<Integer, Player>();
			List<Integer> cardValuesToCompare = new ArrayList<Integer>();
			
			for (Player player : winnersOnly) {
				
				if (player.getHand().getIsAceLowStraight() && player.getHand().getCardsUsedToMakeHand().get(i).getValue() == 14) {
					cardValuesToCompareMappedToPlayer.put(1, player);
					cardValuesToCompare.add(1);
				} else {
					cardValuesToCompareMappedToPlayer.put(player.getHand().getCardsUsedToMakeHand().get(i).getValue(), player);
					cardValuesToCompare.add(player.getHand().getCardsUsedToMakeHand().get(i).getValue());
				}
				

			}
			
			int highestCardValue = 0;
			
			for (int value : cardValuesToCompare) {
				
				if (value > highestCardValue) {
					highestCardValue = value;
				}
			}
			
			int highestCardMatchCount = 0;
			
			for (int cardValue : cardValuesToCompare) {
				
				
				
				if (cardValue == highestCardValue) {
					highestCardMatchCount++;
				}
				
				if (highestCardMatchCount == 2) {
					break;
				}
			}
			
			if (highestCardMatchCount == 1) {
				winner = cardValuesToCompareMappedToPlayer.get(highestCardValue);
					
					for (Player player : winnersOnly) {
						if (! player.equals(winner)) {
							player.getHand().setIsWinner(false);
						}
					}
					break;
			}
			
		}
		



	}
	
}
