package com.audition.PlayingCardTest;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.audition.PlayingCard.PlayingCard;

import junit.framework.Assert;

public class PlayingCardTest {

	private final static String TEST_CARD_NAME = "7";
	private final static String TEST_CARD_SUIT = "H";
	private final static int TEST_CARD_VALUE = 7;
	private PlayingCard testCard;
	
	@Before
	public void setUp() {
		testCard = new PlayingCard(TEST_CARD_NAME, TEST_CARD_SUIT, TEST_CARD_VALUE);
	}
	
	@Test
	public void get_name_returns_expected_name() {
		Assert.assertEquals(TEST_CARD_NAME, testCard.getName());
	}
	
	@Test
	public void get_suit_returns_expected_suit() {
		Assert.assertEquals(TEST_CARD_SUIT, testCard.getSuit());
	}
	
	@Test
	public void get_value_returns_expected_value() {
		Assert.assertEquals(TEST_CARD_VALUE, testCard.getValue());
		
	}
	
	@Test
	public void to_string_override_returns_name_and_suit__as_one_string() {
		Assert.assertEquals(TEST_CARD_NAME + TEST_CARD_SUIT, testCard.toString());
	}
	
	@Test
	public void equals_is_true_when_two_cards_have_the_same_name_suit_and_value() {
		PlayingCard cardToCompare = new PlayingCard(TEST_CARD_NAME, TEST_CARD_SUIT, TEST_CARD_VALUE);
		
		Assert.assertTrue(cardToCompare.equals(testCard));
	}
	
	
}
