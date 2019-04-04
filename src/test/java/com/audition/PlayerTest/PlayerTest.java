package com.audition.PlayerTest;

import org.junit.Before;
import org.junit.Test;

import com.audition.Player.Player;
import com.audition.PlayingCard.PlayingCard;

import junit.framework.Assert;

public class PlayerTest {
	
	private Player testPlayer;
	
	@Before
	public void setUp() {
		testPlayer = new Player("Player 1");
	}
	
	@Test
	public void get_name_returns_expected_name() {
		Assert.assertEquals("Player 1", testPlayer.getName());
	}
	
	@Test
	public void get_hand_returns_empty_hand_before_card_has_been_added() {
		Assert.assertEquals(0, testPlayer.getHand().getCardsInHandList().size());
	}
	
	@Test
	public void add_card_to_hand_adds_into_correct_hand() {
		
		PlayingCard testCard = new PlayingCard("8", "D", 8);
		
		testPlayer.addCardToHand(testCard);
		
		Assert.assertEquals(1, testPlayer.getHand().getCardsInHandList().size());
		Assert.assertTrue(testPlayer.getHand().getCardsInHandList().contains(testCard));
	
	}
	
	@Test
	public void get_fold_decision_returns_boolean() {
		Assert.assertTrue(testPlayer.getHasFolded() == true || testPlayer.getHasFolded() == false);
	}

}
