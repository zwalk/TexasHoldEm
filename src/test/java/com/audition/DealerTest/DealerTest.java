package com.audition.DealerTest;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.audition.Dealer.Dealer;
import com.audition.Player.Player;

import junit.framework.Assert;

public class DealerTest {
	
	private Dealer testDealer;
	
	@Before
	public void setUp() {
		testDealer = new Dealer();
	}
	
	@Test
	public void get_players_returns_list_of_6_players() {
		Assert.assertEquals(6, testDealer.getPlayers().size());
	}
	
	@Test
	public void dealer_build_player_list_with_expected_names() {
		Assert.assertEquals("Player 1", testDealer.getPlayers().get(0).getName());
		Assert.assertEquals("Player 2", testDealer.getPlayers().get(1).getName());
		Assert.assertEquals("Player 3", testDealer.getPlayers().get(2).getName());
		Assert.assertEquals("Player 4", testDealer.getPlayers().get(3).getName());
		Assert.assertEquals("Player 5", testDealer.getPlayers().get(4).getName());
		Assert.assertEquals("Player 6", testDealer.getPlayers().get(5).getName());
	}
	
	@Test
	public void deal_starting_hand_burns_one_card_and_gives_each_player_two_cards_in_their_hand() {
		testDealer.dealStartingHand();
		
		Assert.assertTrue(testDealer.getBurnPile().size() == 1);
		
		List<Player> testPlayers = testDealer.getPlayers();
		
		for (Player player : testPlayers) {
			Assert.assertTrue(player.getHand().getCardsInHandList().size() == 2);
		}
		
	}
	
	@Test
	public void deal_flop_adds_three_cards_to_each_player_hand_if_not_folded() {
		testDealer.dealFlop();
		
		List<Player> testPlayers = testDealer.getPlayers();
		
		for (Player player : testPlayers) {
			if (player.getHasFolded()) {
				Assert.assertTrue(player.getHand().getCardsInHandList().size() == 0);
			} else {
				Assert.assertTrue(player.getHand().getCardsInHandList().size() == 3);
			}
			
		}
		
	}
	
	@Test
	public void deal_flop_adds_one_to_burn_pile() {
		testDealer.dealFlop();
		
		Assert.assertTrue(testDealer.getBurnPile().size() == 1);
		
	}
	
	@Test
	public void deal_turn_adds_one_card_to_each_player_hand_if_not_folded() {
		testDealer.dealTurn();
		
		List<Player> testPlayers = testDealer.getPlayers();
		
		for (Player player : testPlayers) {
			if (player.getHasFolded()) {
				Assert.assertTrue(player.getHand().getCardsInHandList().size() == 0);
			} else {
				Assert.assertTrue(player.getHand().getCardsInHandList().size() == 1);
			}
			
		}
		
	}
	
	@Test
	public void deal_turn_adds_one_to_burn_pile() {
		testDealer.dealTurn();
		
		Assert.assertTrue(testDealer.getBurnPile().size() == 1);
		
	}
	
	@Test
	public void deal_river_adds_one_card_to_each_player_hand_if_not_folded() {
		testDealer.dealRiver();
		
		List<Player> testPlayers = testDealer.getPlayers();
		
		for (Player player : testPlayers) {
			if (player.getHasFolded()) {
				Assert.assertTrue(player.getHand().getCardsInHandList().size() == 0);
			} else {
				Assert.assertTrue(player.getHand().getCardsInHandList().size() == 1);
			}
			
		}
		
	}
	
	@Test
	public void deal_river_adds_one_to_burn_pile() {
		testDealer.dealRiver();
		
		Assert.assertTrue(testDealer.getBurnPile().size() == 1);
		
	}

}
