package com.audition.HandTest;

import org.junit.Before;
import org.junit.Test;

import com.audition.Hand.Hand;
import com.audition.PlayingCard.PlayingCard;

import junit.framework.Assert;

public class HandTest {
	
	private Hand testHand;
	private final static String TEST_CARD_NAME = "7";
	private final static String TEST_CARD_SUIT = "H";
	private final static int TEST_CARD_VALUE = 7;
	private PlayingCard testCard;
	
	@Before
	public void setUp() {
		testHand = new Hand();
		testCard = new PlayingCard(TEST_CARD_NAME, TEST_CARD_SUIT, TEST_CARD_VALUE);
	}
	
	@Test
	public void add_card_inputs_card_to_list_of_cards_in_hand() {
		testHand.addCardToHand(testCard);
		Assert.assertTrue(testHand.getCardsInHandList().size() == 1);
		
		Assert.assertEquals(testCard, testHand.getCardsInHandList().get(0));
	}
	
	@Test
	public void get_name_returns_null_when_hand_has_not_been_assigned_rank() {
		Assert.assertTrue(testHand.getName() == null);
	}
	
	@Test
	public void get_winner_returns_false_by_default() {
		Assert.assertEquals(false, testHand.getIsWinner());
	}
	
	@Test
	public void to_string_returns_expected_string_results() {
		testHand.addCardToHand(testCard);
		Assert.assertEquals("7H ", testHand.toString());
	}

}
