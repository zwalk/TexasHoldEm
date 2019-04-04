package com.audition.HandRankerTest;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.audition.Hand.Hand;
import com.audition.HandRanker.HandRanker;
import com.audition.Player.Player;
import com.audition.PlayingCard.PlayingCard;

import junit.framework.Assert;

public class HandRankerTest {

	private HandRanker testHandRanker;
	private List<Player> playerList;
	private Player testPlayer;
	
	@Before
	public void setUp() {
		testPlayer = new Player("Player Test");
		playerList = new ArrayList<Player>();
		playerList.add(testPlayer);
		testHandRanker = new HandRanker(playerList);
	}
	
	@Test
	public void assign_hand_name_sets_hand_name_to_high_card_if_hand_is_high_card_hand() {
		
		Hand testHand = playerList.get(0).getHand();
		
		testHand.addCardToHand(new PlayingCard("2", "h", 2));
		testHand.addCardToHand(new PlayingCard("7", "d", 7));
		testHand.addCardToHand(new PlayingCard("T", "s", 10));
		testHand.addCardToHand(new PlayingCard("6", "c", 6));
		testHand.addCardToHand(new PlayingCard("K", "d", 13));
		testHand.addCardToHand(new PlayingCard("A", "s", 14));
		testHand.addCardToHand(new PlayingCard("8", "c", 8));
		
		testHandRanker.assignHandNames();
		
		Assert.assertEquals("High Card", testHand.getName());
	}
	
	@Test
	public void assign_hand_name_sets_hand_name_to_pair_if_hand_is_pair() {
		
		Hand testHand = playerList.get(0).getHand();
		
		testHand.addCardToHand(new PlayingCard("2", "h", 2));
		testHand.addCardToHand(new PlayingCard("2", "c", 2));
		
		testHandRanker.assignHandNames();
		
		Assert.assertEquals("One Pair", testHand.getName());
	}
	
	@Test
	public void assign_hand_name_sets_hand_name_to_two_pair_if_hand_is_two_pair() {
		
		Hand testHand = playerList.get(0).getHand();
		
		testHand.addCardToHand(new PlayingCard("2", "h", 2));
		testHand.addCardToHand(new PlayingCard("2", "c", 2));
		testHand.addCardToHand(new PlayingCard("3", "h", 3));
		testHand.addCardToHand(new PlayingCard("3", "c", 3));
		
		testHandRanker.assignHandNames();
		
		Assert.assertEquals("Two Pair", testHand.getName());
	}
	
	@Test
	public void assign_hand_name_sets_to_three_of_a_kind_if_expected() {
		
		Hand testHand = playerList.get(0).getHand();
		
		testHand.addCardToHand(new PlayingCard("7", "c", 7));
		testHand.addCardToHand(new PlayingCard("7", "h", 7));
		testHand.addCardToHand(new PlayingCard("7", "s", 7));
		
		testHandRanker.assignHandNames();
		
		Assert.assertEquals("Three of a Kind", testHand.getName());
	}
	
	@Test
	public void assign_hand_name_sets_to_straight_if_expected_and_not_all_same_suit() {
		
		Hand testHand = playerList.get(0).getHand();
		
		testHand.addCardToHand(new PlayingCard("2", "c", 2));
		testHand.addCardToHand(new PlayingCard("3", "h", 3));
		testHand.addCardToHand(new PlayingCard("4", "s", 4));
		testHand.addCardToHand(new PlayingCard("T", "h", 10));
		testHand.addCardToHand(new PlayingCard("5", "h", 5));
		testHand.addCardToHand(new PlayingCard("6", "s", 6));
		testHand.addCardToHand(new PlayingCard("K", "s", 13));
		
		testHandRanker.assignHandNames();
		
		Assert.assertEquals("Straight", testHand.getName());
	}
	
	@Test
	public void one_pair_assignment_adds_five_cards_into_cards_used_to_make_hand() {

			
		Hand testHand = playerList.get(0).getHand();
		
		testHand.addCardToHand(new PlayingCard("2", "h", 2));
		testHand.addCardToHand(new PlayingCard("2", "c", 2));
		
		testHandRanker.assignHandNames();
		
		Assert.assertEquals(5, testPlayer.getHand().getCardsUsedToMakeHand().size());
	}
	
	@Test
	public void two_pair_assignment_adds_five_cards_into_cards_used_to_make_hand() {

			
		Hand testHand = playerList.get(0).getHand();
		
		testHand.addCardToHand(new PlayingCard("2", "h", 2));
		testHand.addCardToHand(new PlayingCard("2", "c", 2));
		testHand.addCardToHand(new PlayingCard("3", "h", 3));
		testHand.addCardToHand(new PlayingCard("3", "c", 3));
		
		testHandRanker.assignHandNames();
		
		Assert.assertEquals(5, testPlayer.getHand().getCardsUsedToMakeHand().size());
	}
	
	@Test
	public void three_possible_pairs_only_adds_5_cards_into_cards_used_to_make_hand() {
		
		Hand testHand = playerList.get(0).getHand();
		
		testHand.addCardToHand(new PlayingCard("2", "h", 2));
		testHand.addCardToHand(new PlayingCard("2", "c", 2));
		testHand.addCardToHand(new PlayingCard("3", "h", 3));
		testHand.addCardToHand(new PlayingCard("3", "c", 3));
		testHand.addCardToHand(new PlayingCard("4", "h", 4));
		testHand.addCardToHand(new PlayingCard("4", "c", 4));
		testHand.addCardToHand(new PlayingCard("10", "h", 10));

		
		testHandRanker.assignHandNames();
		
		Assert.assertEquals(5, testPlayer.getHand().getCardsUsedToMakeHand().size());
	}
	
	@Test
	public void three_possible_pairs_adds_higher_pairs_into_cards_used_to_make_hand() {
		
		Hand testHand = playerList.get(0).getHand();
		PlayingCard highPairCard1 = new PlayingCard("4", "c", 4);
		PlayingCard highPairCard2 = new PlayingCard("4", "h", 4);
		PlayingCard midPairCard1 = new PlayingCard("3", "c", 3);
		PlayingCard midPairCard2 = new PlayingCard("3", "h", 3);
		
		testHand.addCardToHand(new PlayingCard("2", "h", 2));
		testHand.addCardToHand(new PlayingCard("2", "c", 2));
		testHand.addCardToHand(midPairCard2);
		testHand.addCardToHand(midPairCard1);
		testHand.addCardToHand(highPairCard2);
		testHand.addCardToHand(highPairCard1);
		testHand.addCardToHand(new PlayingCard("10", "h", 10));

		
		testHandRanker.assignHandNames();
		
		Assert.assertTrue(testPlayer.getHand().getCardsUsedToMakeHand().contains(highPairCard1));
		Assert.assertTrue(testPlayer.getHand().getCardsUsedToMakeHand().contains(highPairCard2));
		Assert.assertTrue(testPlayer.getHand().getCardsUsedToMakeHand().contains(midPairCard1));
		Assert.assertTrue(testPlayer.getHand().getCardsUsedToMakeHand().contains(midPairCard2));
	}
	
	@Test
	public void determine_winner_sets_is_winner_when_player_has_straight_and_is_highest_named_hand_amongst_players() {
		Player testPlayer2 = new Player("Player 2");
		testPlayer2.addCardToHand(new PlayingCard("2", "h", 2));
		testPlayer2.addCardToHand(new PlayingCard("2", "c", 2));
		
		Player testPlayer3 = new Player("Player 3");
		testPlayer3.addCardToHand(new PlayingCard("3", "d", 3));
		testPlayer3.addCardToHand(new PlayingCard("6", "s", 6));
		
		Player testPlayer4 = new Player("Player 4");
		testPlayer4.addCardToHand(new PlayingCard("A", "s", 14));
		testPlayer4.addCardToHand(new PlayingCard("6", "c", 6));
		
		Player testPlayer5 = new Player("Player 5");
		testPlayer5.addCardToHand(new PlayingCard("4", "s", 4));
		testPlayer5.addCardToHand(new PlayingCard("9", "d", 9));
		
		Player testPlayer6 = new Player("Player 6");
		testPlayer6.addCardToHand(new PlayingCard("7", "s", 7));
		testPlayer6.addCardToHand(new PlayingCard("8", "c", 8));
		
		playerList.add(testPlayer2);
		playerList.add(testPlayer3);
		playerList.add(testPlayer4);
		playerList.add(testPlayer5);
		playerList.add(testPlayer6);
		
		for (Player player : playerList) {
			player.addCardToHand(new PlayingCard("9", "h", 9));
			player.addCardToHand(new PlayingCard("T", "s", 10));
			player.addCardToHand(new PlayingCard("J", "d", 11));
			player.addCardToHand(new PlayingCard("3", "h", 3));
			player.addCardToHand(new PlayingCard("A", "h", 14));
		}
		
		testHandRanker.assignHandNames();
		testHandRanker.determineWinner();
		
		Assert.assertTrue(testPlayer6.getHand().getIsWinner());
		Assert.assertFalse(testPlayer2.getHand().getIsWinner());
		Assert.assertFalse(testPlayer3.getHand().getIsWinner());
		Assert.assertFalse(testPlayer4.getHand().getIsWinner());
		Assert.assertFalse(testPlayer5.getHand().getIsWinner());
		
	}
	
	@Test
	public void determine_winner_sets_correct_player_hand_to_winner_when_two_players_have_high_hand_but_one_has_highest_card() {
		Player testPlayer2 = new Player("Player 2");
		testPlayer2.addCardToHand(new PlayingCard("2", "h", 2));
		testPlayer2.addCardToHand(new PlayingCard("2", "c", 2));
		
		Player testPlayer3 = new Player("Player 3");
		testPlayer3.addCardToHand(new PlayingCard("3", "d", 3));
		testPlayer3.addCardToHand(new PlayingCard("4", "c", 4));
		
		Player testPlayer4 = new Player("Player 4");
		testPlayer4.addCardToHand(new PlayingCard("A", "s", 14));
		testPlayer4.addCardToHand(new PlayingCard("6", "c", 6));
		
		Player testPlayer5 = new Player("Player 5");
		testPlayer5.addCardToHand(new PlayingCard("4", "s", 4));
		testPlayer5.addCardToHand(new PlayingCard("9", "d", 9));
		
		Player testPlayer6 = new Player("Player 6");
		testPlayer6.addCardToHand(new PlayingCard("J", "s", 11));
		testPlayer6.addCardToHand(new PlayingCard("8", "c", 8));
		
		playerList.add(testPlayer2);
		playerList.add(testPlayer3);
		playerList.add(testPlayer4);
		playerList.add(testPlayer5);
		playerList.add(testPlayer6);
		
		for (Player player : playerList) {
			player.addCardToHand(new PlayingCard("9", "h", 9));
			player.addCardToHand(new PlayingCard("T", "s", 10));
			player.addCardToHand(new PlayingCard("7", "d", 7));
			player.addCardToHand(new PlayingCard("5", "h", 5));
			player.addCardToHand(new PlayingCard("6", "h", 6));
		}
		
		testHandRanker.assignHandNames();
		testHandRanker.determineWinner();
		
		Assert.assertTrue(testPlayer6.getHand().getIsWinner());
		Assert.assertFalse(testPlayer2.getHand().getIsWinner());
		Assert.assertFalse(testPlayer3.getHand().getIsWinner());
		Assert.assertFalse(testPlayer4.getHand().getIsWinner());
		Assert.assertFalse(testPlayer5.getHand().getIsWinner());
	}
	
	@Test
	public void determine_winner_sets_correct_player_hand_to_winner_when_player_has_high_hand_of_three_of_a_kind() {
		Player testPlayer2 = new Player("Player 2");
		testPlayer2.addCardToHand(new PlayingCard("2", "h", 2));
		testPlayer2.addCardToHand(new PlayingCard("2", "c", 2));
		
		Player testPlayer3 = new Player("Player 3");
		testPlayer3.addCardToHand(new PlayingCard("3", "d", 3));
		testPlayer3.addCardToHand(new PlayingCard("4", "c", 4));
		
		Player testPlayer4 = new Player("Player 4");
		testPlayer4.addCardToHand(new PlayingCard("A", "s", 14));
		testPlayer4.addCardToHand(new PlayingCard("6", "c", 6));
		
		Player testPlayer5 = new Player("Player 5");
		testPlayer5.addCardToHand(new PlayingCard("4", "s", 4));
		testPlayer5.addCardToHand(new PlayingCard("9", "d", 9));
		
		Player testPlayer6 = new Player("Player 6");
		testPlayer6.addCardToHand(new PlayingCard("J", "s", 11));
		testPlayer6.addCardToHand(new PlayingCard("8", "c", 8));
		
		playerList.add(testPlayer2);
		playerList.add(testPlayer3);
		playerList.add(testPlayer4);
		playerList.add(testPlayer5);
		playerList.add(testPlayer6);
		
		for (Player player : playerList) {
			player.addCardToHand(new PlayingCard("2", "s", 2));
			player.addCardToHand(new PlayingCard("T", "s", 10));
			player.addCardToHand(new PlayingCard("7", "d", 7));
			player.addCardToHand(new PlayingCard("A", "h", 14));
			player.addCardToHand(new PlayingCard("3", "h", 3));
		}
		
		testHandRanker.assignHandNames();
		testHandRanker.determineWinner();
		
		Assert.assertTrue(testPlayer2.getHand().getIsWinner());
		Assert.assertFalse(testPlayer6.getHand().getIsWinner());
		Assert.assertFalse(testPlayer3.getHand().getIsWinner());
		Assert.assertFalse(testPlayer4.getHand().getIsWinner());
		Assert.assertFalse(testPlayer5.getHand().getIsWinner());
	}
	
	@Test
	public void determine_winner_sets_correct_player_hand_to_winner_when_two_players_have_three_of_a_kind() {
		Player testPlayer2 = new Player("Player 2");
		testPlayer2.addCardToHand(new PlayingCard("2", "h", 2));
		testPlayer2.addCardToHand(new PlayingCard("2", "c", 2));
		
		Player testPlayer3 = new Player("Player 3");
		testPlayer3.addCardToHand(new PlayingCard("3", "d", 3));
		testPlayer3.addCardToHand(new PlayingCard("3", "c", 3));
		
		Player testPlayer4 = new Player("Player 4");
		testPlayer4.addCardToHand(new PlayingCard("A", "s", 14));
		testPlayer4.addCardToHand(new PlayingCard("6", "c", 6));
		
		Player testPlayer5 = new Player("Player 5");
		testPlayer5.addCardToHand(new PlayingCard("4", "s", 4));
		testPlayer5.addCardToHand(new PlayingCard("9", "d", 9));
		
		Player testPlayer6 = new Player("Player 6");
		testPlayer6.addCardToHand(new PlayingCard("J", "s", 11));
		testPlayer6.addCardToHand(new PlayingCard("8", "c", 8));
		
		playerList.add(testPlayer2);
		playerList.add(testPlayer3);
		playerList.add(testPlayer4);
		playerList.add(testPlayer5);
		playerList.add(testPlayer6);
		
		for (Player player : playerList) {
			player.addCardToHand(new PlayingCard("2", "s", 2));
			player.addCardToHand(new PlayingCard("T", "s", 10));
			player.addCardToHand(new PlayingCard("7", "d", 7));
			player.addCardToHand(new PlayingCard("A", "h", 14));
			player.addCardToHand(new PlayingCard("3", "h", 3));
		}
		
		testHandRanker.assignHandNames();
		testHandRanker.determineWinner();
		
		Assert.assertTrue(testPlayer3.getHand().getIsWinner());
		Assert.assertFalse(testPlayer6.getHand().getIsWinner());
		Assert.assertFalse(testPlayer2.getHand().getIsWinner());
		Assert.assertFalse(testPlayer4.getHand().getIsWinner());
		Assert.assertFalse(testPlayer5.getHand().getIsWinner());
	}
	
	@Test
	public void determine_winner_sets_correct_player_hand_to_winner_when_player_has_high_hand_of_two_pair() {
		Player testPlayer2 = new Player("Player 2");
		testPlayer2.addCardToHand(new PlayingCard("2", "h", 2));
		testPlayer2.addCardToHand(new PlayingCard("2", "c", 2));
		
		Player testPlayer3 = new Player("Player 3");
		testPlayer3.addCardToHand(new PlayingCard("3", "d", 3));
		testPlayer3.addCardToHand(new PlayingCard("4", "c", 4));
		
		Player testPlayer4 = new Player("Player 4");
		testPlayer4.addCardToHand(new PlayingCard("A", "s", 14));
		testPlayer4.addCardToHand(new PlayingCard("6", "c", 6));
		
		Player testPlayer5 = new Player("Player 5");
		testPlayer5.addCardToHand(new PlayingCard("4", "s", 4));
		testPlayer5.addCardToHand(new PlayingCard("9", "d", 9));
		
		Player testPlayer6 = new Player("Player 6");
		testPlayer6.addCardToHand(new PlayingCard("J", "s", 11));
		testPlayer6.addCardToHand(new PlayingCard("8", "c", 8));
		
		playerList.add(testPlayer2);
		playerList.add(testPlayer3);
		playerList.add(testPlayer4);
		playerList.add(testPlayer5);
		playerList.add(testPlayer6);
		
		for (Player player : playerList) {
			player.addCardToHand(new PlayingCard("5", "s", 5));
			player.addCardToHand(new PlayingCard("K", "s", 13));
			player.addCardToHand(new PlayingCard("7", "d", 7));
			player.addCardToHand(new PlayingCard("T", "h", 10));
			player.addCardToHand(new PlayingCard("5", "d", 5));
		}
		
		testHandRanker.assignHandNames();
		testHandRanker.determineWinner();
		
		Assert.assertTrue(testPlayer2.getHand().getIsWinner());
		Assert.assertFalse(testPlayer6.getHand().getIsWinner());
		Assert.assertFalse(testPlayer3.getHand().getIsWinner());
		Assert.assertFalse(testPlayer4.getHand().getIsWinner());
		Assert.assertFalse(testPlayer5.getHand().getIsWinner());
	}
	
	@Test
	public void determine_winner_sets_correct_player_hand_to_winner_when_two_players_have_high_hand_of_two_pair() {
		Player testPlayer2 = new Player("Player 2");
		testPlayer2.addCardToHand(new PlayingCard("2", "h", 2));
		testPlayer2.addCardToHand(new PlayingCard("2", "c", 2));
		
		Player testPlayer3 = new Player("Player 3");
		testPlayer3.addCardToHand(new PlayingCard("3", "d", 3));
		testPlayer3.addCardToHand(new PlayingCard("4", "c", 4));
		
		Player testPlayer4 = new Player("Player 4");
		testPlayer4.addCardToHand(new PlayingCard("A", "s", 14));
		testPlayer4.addCardToHand(new PlayingCard("6", "c", 6));
		
		Player testPlayer5 = new Player("Player 5");
		testPlayer5.addCardToHand(new PlayingCard("4", "s", 4));
		testPlayer5.addCardToHand(new PlayingCard("9", "d", 9));
		
		Player testPlayer6 = new Player("Player 6");
		testPlayer6.addCardToHand(new PlayingCard("J", "s", 11));
		testPlayer6.addCardToHand(new PlayingCard("8", "c", 8));
		
		playerList.add(testPlayer2);
		playerList.add(testPlayer3);
		playerList.add(testPlayer4);
		playerList.add(testPlayer5);
		playerList.add(testPlayer6);
		
		for (Player player : playerList) {
			player.addCardToHand(new PlayingCard("5", "s", 5));
			player.addCardToHand(new PlayingCard("T", "s", 10));
			player.addCardToHand(new PlayingCard("7", "d", 7));
			player.addCardToHand(new PlayingCard("T", "h", 10));
			player.addCardToHand(new PlayingCard("A", "d", 14));
		}
		
		testHandRanker.assignHandNames();
		testHandRanker.determineWinner();
		
		Assert.assertTrue(testPlayer4.getHand().getIsWinner());
		Assert.assertFalse(testPlayer6.getHand().getIsWinner());
		Assert.assertFalse(testPlayer3.getHand().getIsWinner());
		Assert.assertFalse(testPlayer2.getHand().getIsWinner());
		Assert.assertFalse(testPlayer5.getHand().getIsWinner());
	}
	
	@Test
	public void determine_winner_sets_two_players_to_winner_if_tied_and_hand_is_two_pair() {
		Player testPlayer2 = new Player("Player 2");
		testPlayer2.addCardToHand(new PlayingCard("2", "h", 2));
		testPlayer2.addCardToHand(new PlayingCard("2", "c", 2));
		
		Player testPlayer3 = new Player("Player 3");
		testPlayer3.addCardToHand(new PlayingCard("2", "d", 2));
		testPlayer3.addCardToHand(new PlayingCard("2", "s", 2));
		
		Player testPlayer4 = new Player("Player 4");
		testPlayer4.addCardToHand(new PlayingCard("A", "s", 14));
		testPlayer4.addCardToHand(new PlayingCard("6", "c", 6));
		
		Player testPlayer5 = new Player("Player 5");
		testPlayer5.addCardToHand(new PlayingCard("4", "s", 4));
		testPlayer5.addCardToHand(new PlayingCard("9", "d", 9));
		
		Player testPlayer6 = new Player("Player 6");
		testPlayer6.addCardToHand(new PlayingCard("J", "s", 11));
		testPlayer6.addCardToHand(new PlayingCard("8", "c", 8));
		
		playerList.add(testPlayer2);
		playerList.add(testPlayer3);
		playerList.add(testPlayer4);
		playerList.add(testPlayer5);
		playerList.add(testPlayer6);
		
		for (Player player : playerList) {
			player.addCardToHand(new PlayingCard("5", "s", 5));
			player.addCardToHand(new PlayingCard("T", "s", 10));
			player.addCardToHand(new PlayingCard("7", "d", 7));
			player.addCardToHand(new PlayingCard("T", "h", 10));
			player.addCardToHand(new PlayingCard("Q", "d", 12));
		}
		
		testHandRanker.assignHandNames();
		testHandRanker.determineWinner();
		
		Assert.assertTrue(testPlayer2.getHand().getIsWinner());
		Assert.assertFalse(testPlayer6.getHand().getIsWinner());
		Assert.assertTrue(testPlayer3.getHand().getIsWinner());
		Assert.assertFalse(testPlayer4.getHand().getIsWinner());
		Assert.assertFalse(testPlayer5.getHand().getIsWinner());
	}
	
	@Test
	public void determine_winner_sets_two_players_to_winner_if_tied_and_hand_is_pair() {
		Player testPlayer2 = new Player("Player 2");
		testPlayer2.addCardToHand(new PlayingCard("2", "h", 2));
		testPlayer2.addCardToHand(new PlayingCard("2", "c", 2));
		
		testPlayer.addCardToHand(new PlayingCard("2", "d", 2));
		testPlayer.addCardToHand(new PlayingCard("2", "s", 2));
		
		
		playerList.add(testPlayer2);

		
		for (Player player : playerList) {
			player.addCardToHand(new PlayingCard("5", "s", 5));
			player.addCardToHand(new PlayingCard("A", "s", 14));
			player.addCardToHand(new PlayingCard("7", "d", 7));
			player.addCardToHand(new PlayingCard("T", "h", 10));
			player.addCardToHand(new PlayingCard("Q", "d", 12));
		}
		
		testHandRanker.assignHandNames();
		testHandRanker.determineWinner();
		
		Assert.assertTrue(testPlayer2.getHand().getIsWinner());
		Assert.assertTrue(testPlayer.getHand().getIsWinner());

	}
	
	@Test
	public void determine_winner_sets_two_players_to_winner_if_tied_and_hand_is_high_card() {
		Player testPlayer2 = new Player("Player 2");
		testPlayer2.addCardToHand(new PlayingCard("A", "h", 14));
		testPlayer2.addCardToHand(new PlayingCard("2", "c", 2));
		
		testPlayer.addCardToHand(new PlayingCard("A", "d", 14));
		testPlayer.addCardToHand(new PlayingCard("2", "s", 2));
		
		
		playerList.add(testPlayer2);

		
		for (Player player : playerList) {
			player.addCardToHand(new PlayingCard("5", "s", 5));
			player.addCardToHand(new PlayingCard("3", "s", 3));
			player.addCardToHand(new PlayingCard("7", "d", 7));
			player.addCardToHand(new PlayingCard("T", "h", 10));
			player.addCardToHand(new PlayingCard("Q", "d", 12));
		}
		
		testHandRanker.assignHandNames();
		testHandRanker.determineWinner();
		
		Assert.assertTrue(testPlayer2.getHand().getIsWinner());
		Assert.assertTrue(testPlayer.getHand().getIsWinner());
		Assert.assertTrue(testPlayer.getHand().getName().equals("High Card"));

	}
	
	@Test
	public void determine_winner_sets_two_players_to_winner_if_tied_and_hand_is_straight() {
		Player testPlayer2 = new Player("Player 2");
		testPlayer2.addCardToHand(new PlayingCard("A", "h", 14));
		testPlayer2.addCardToHand(new PlayingCard("2", "c", 2));
		
		testPlayer.addCardToHand(new PlayingCard("A", "d", 14));
		testPlayer.addCardToHand(new PlayingCard("2", "s", 2));
		
		
		playerList.add(testPlayer2);

		
		for (Player player : playerList) {
			player.addCardToHand(new PlayingCard("5", "s", 5));
			player.addCardToHand(new PlayingCard("3", "s", 3));
			player.addCardToHand(new PlayingCard("4", "d", 4));
			player.addCardToHand(new PlayingCard("T", "h", 10));
			player.addCardToHand(new PlayingCard("Q", "d", 12));
		}
		
		testHandRanker.assignHandNames();
		testHandRanker.determineWinner();
		
		Assert.assertTrue(testPlayer2.getHand().getIsWinner());
		Assert.assertTrue(testPlayer.getHand().getIsWinner());
		Assert.assertTrue(testPlayer.getHand().getName().equals("Straight"));

	}
	
	@Test
	public void determine_winner_sets_correct_player_to_winner_when_ace_straight_makes_ace_low_card() {
		Player testPlayer2 = new Player("Player 2");
		testPlayer2.addCardToHand(new PlayingCard("A", "h", 14));
		testPlayer2.addCardToHand(new PlayingCard("2", "c", 2));
		
		testPlayer.addCardToHand(new PlayingCard("6", "d", 6));
		testPlayer.addCardToHand(new PlayingCard("2", "s", 2));
		
		
		playerList.add(testPlayer2);

		
		for (Player player : playerList) {
			player.addCardToHand(new PlayingCard("5", "s", 5));
			player.addCardToHand(new PlayingCard("3", "s", 3));
			player.addCardToHand(new PlayingCard("4", "d", 4));
			player.addCardToHand(new PlayingCard("T", "h", 10));
			player.addCardToHand(new PlayingCard("Q", "d", 12));
		}
		
		testHandRanker.assignHandNames();
		testHandRanker.determineWinner();
		
		Assert.assertFalse(testPlayer2.getHand().getIsWinner());
		Assert.assertTrue(testPlayer.getHand().getIsWinner());
		Assert.assertTrue(testPlayer.getHand().getName().equals("Straight"));

	}
	
	@Test
	public void assign_hand_name_sets_hand_name_to_flush_if_hand_is_flush_hand() {
		
		Hand testHand = playerList.get(0).getHand();
		
		testHand.addCardToHand(new PlayingCard("2", "s", 2));
		testHand.addCardToHand(new PlayingCard("7", "s", 7));
		testHand.addCardToHand(new PlayingCard("T", "s", 10));
		testHand.addCardToHand(new PlayingCard("6", "s", 6));
		testHand.addCardToHand(new PlayingCard("K", "d", 13));
		testHand.addCardToHand(new PlayingCard("A", "s", 14));
		testHand.addCardToHand(new PlayingCard("8", "c", 8));
		
		testHandRanker.assignHandNames();
		
		Assert.assertEquals("Flush", testHand.getName());
	}

	@Test
	public void determine_winner_sets_correct_player_to_winner_when_flush_wins_outright() {
		Player testPlayer2 = new Player("Player 2");
		testPlayer2.addCardToHand(new PlayingCard("A", "h", 14));
		testPlayer2.addCardToHand(new PlayingCard("2", "c", 2));
		
		testPlayer.addCardToHand(new PlayingCard("6", "s", 6));
		testPlayer.addCardToHand(new PlayingCard("2", "s", 2));
		
		
		playerList.add(testPlayer2);

		
		for (Player player : playerList) {
			player.addCardToHand(new PlayingCard("7", "s", 7));
			player.addCardToHand(new PlayingCard("3", "s", 3));
			player.addCardToHand(new PlayingCard("4", "s", 4));
			player.addCardToHand(new PlayingCard("T", "h", 10));
			player.addCardToHand(new PlayingCard("Q", "d", 12));
		}
		
		testHandRanker.assignHandNames();
		testHandRanker.determineWinner();
		
		Assert.assertFalse(testPlayer2.getHand().getIsWinner());
		Assert.assertTrue(testPlayer.getHand().getIsWinner());
		Assert.assertTrue(testPlayer.getHand().getName().equals("Flush"));

	}
	
	@Test
	public void determine_winner_sets_correct_player_to_winner_when_two_players_have_flush() {
		Player testPlayer2 = new Player("Player 2");
		testPlayer2.addCardToHand(new PlayingCard("A", "s", 14));
		testPlayer2.addCardToHand(new PlayingCard("7", "s", 7));
		
		testPlayer.addCardToHand(new PlayingCard("9", "s", 9));
		testPlayer.addCardToHand(new PlayingCard("2", "s", 2));
		
		
		playerList.add(testPlayer2);

		
		for (Player player : playerList) {
			player.addCardToHand(new PlayingCard("5", "s", 5));
			player.addCardToHand(new PlayingCard("3", "s", 3));
			player.addCardToHand(new PlayingCard("4", "s", 4));
			player.addCardToHand(new PlayingCard("T", "h", 10));
			player.addCardToHand(new PlayingCard("Q", "d", 12));
		}
		
		testHandRanker.assignHandNames();
		testHandRanker.determineWinner();
		
		Assert.assertTrue(testPlayer2.getHand().getIsWinner());
		Assert.assertFalse(testPlayer.getHand().getIsWinner());
		Assert.assertTrue(testPlayer.getHand().getName().equals("Flush"));

	}
	
	@Test
	public void assign_hand_name_sets_hand_name_to_full_house_if_hand_is_full_house_hand() {
		
		Hand testHand = playerList.get(0).getHand();
		
		testHand.addCardToHand(new PlayingCard("2", "s", 2));
		testHand.addCardToHand(new PlayingCard("2", "d", 2));
		testHand.addCardToHand(new PlayingCard("2", "h", 2));
		testHand.addCardToHand(new PlayingCard("K", "s", 13));
		testHand.addCardToHand(new PlayingCard("K", "d", 13));
		testHand.addCardToHand(new PlayingCard("A", "s", 14));
		testHand.addCardToHand(new PlayingCard("8", "c", 8));
		
		testHandRanker.assignHandNames();
		
		Assert.assertEquals("Full House", testHand.getName());
	}
	
	@Test
	public void determine_winner_sets_correct_player_to_winner_when_two_players_have_full_house_one_higher_card() {
		Player testPlayer2 = new Player("Player 2");
		testPlayer2.addCardToHand(new PlayingCard("A", "s", 14));
		testPlayer2.addCardToHand(new PlayingCard("A", "d", 14));
		
		testPlayer.addCardToHand(new PlayingCard("2", "s", 2));
		testPlayer.addCardToHand(new PlayingCard("2", "c", 2));
		
		
		playerList.add(testPlayer2);

		
		for (Player player : playerList) {
			player.addCardToHand(new PlayingCard("5", "s", 5));
			player.addCardToHand(new PlayingCard("5", "c", 5));
			player.addCardToHand(new PlayingCard("5", "h", 5));
			player.addCardToHand(new PlayingCard("T", "h", 10));
			player.addCardToHand(new PlayingCard("Q", "d", 12));
		}
		
		testHandRanker.assignHandNames();
		testHandRanker.determineWinner();
		
		Assert.assertTrue(testPlayer2.getHand().getIsWinner());
		Assert.assertFalse(testPlayer.getHand().getIsWinner());
		Assert.assertTrue(testPlayer.getHand().getName().equals("Full House"));

	}
	
	@Test
	public void determine_winner_sets_two_players_to_winner_when_two_players_have_full_house_tie() {
		Player testPlayer2 = new Player("Player 2");
		testPlayer2.addCardToHand(new PlayingCard("2", "h", 2));
		testPlayer2.addCardToHand(new PlayingCard("2", "d", 2));
		
		testPlayer.addCardToHand(new PlayingCard("2", "s", 2));
		testPlayer.addCardToHand(new PlayingCard("2", "c", 2));
		
		
		playerList.add(testPlayer2);

		
		for (Player player : playerList) {
			player.addCardToHand(new PlayingCard("5", "s", 5));
			player.addCardToHand(new PlayingCard("5", "c", 5));
			player.addCardToHand(new PlayingCard("5", "h", 5));
			player.addCardToHand(new PlayingCard("T", "h", 10));
			player.addCardToHand(new PlayingCard("Q", "d", 12));
		}
		
		testHandRanker.assignHandNames();
		testHandRanker.determineWinner();
		
		Assert.assertTrue(testPlayer2.getHand().getIsWinner());
		Assert.assertTrue(testPlayer.getHand().getIsWinner());
		Assert.assertTrue(testPlayer.getHand().getName().equals("Full House"));

	}
	
	@Test
	public void assign_hand_name_sets_hand_name_to_four_of_a_kind_if_hand_is_four_of_a_kind_hand() {
		
		Hand testHand = playerList.get(0).getHand();
		
		testHand.addCardToHand(new PlayingCard("2", "s", 2));
		testHand.addCardToHand(new PlayingCard("2", "d", 2));
		testHand.addCardToHand(new PlayingCard("2", "h", 2));
		testHand.addCardToHand(new PlayingCard("2", "c", 2));
		testHand.addCardToHand(new PlayingCard("K", "d", 13));
		testHand.addCardToHand(new PlayingCard("A", "s", 14));
		testHand.addCardToHand(new PlayingCard("8", "c", 8));
		
		testHandRanker.assignHandNames();
		
		Assert.assertEquals("Four of a Kind", testHand.getName());
	}
	
	@Test
	public void determine_winner_sets_correct_player_to_winner_when_two_players_have_four_of_a_kind_one_has_higher_card() {
		Player testPlayer2 = new Player("Player 2");
		testPlayer2.addCardToHand(new PlayingCard("A", "s", 14));
		testPlayer2.addCardToHand(new PlayingCard("A", "d", 14));
		
		testPlayer.addCardToHand(new PlayingCard("2", "s", 2));
		testPlayer.addCardToHand(new PlayingCard("2", "c", 2));
		
		
		playerList.add(testPlayer2);

		
		for (Player player : playerList) {
			player.addCardToHand(new PlayingCard("A", "c", 14));
			player.addCardToHand(new PlayingCard("A", "h", 14));
			player.addCardToHand(new PlayingCard("2", "h", 2));
			player.addCardToHand(new PlayingCard("2", "d", 2));
			player.addCardToHand(new PlayingCard("Q", "d", 12));
		}
		
		testHandRanker.assignHandNames();
		testHandRanker.determineWinner();
		
		Assert.assertTrue(testPlayer2.getHand().getIsWinner());
		Assert.assertFalse(testPlayer.getHand().getIsWinner());
		Assert.assertTrue(testPlayer.getHand().getName().equals("Four of a Kind"));

	}
	
	@Test
	public void assign_hand_name_sets_hand_name_to_straight_flush_if_hand_is_straight_flush_hand() {
		
		Hand testHand = playerList.get(0).getHand();
		
		testHand.addCardToHand(new PlayingCard("2", "s", 2));
		testHand.addCardToHand(new PlayingCard("3", "s", 3));
		testHand.addCardToHand(new PlayingCard("4", "s", 4));
		testHand.addCardToHand(new PlayingCard("5", "s", 5));
		testHand.addCardToHand(new PlayingCard("K", "d", 13));
		testHand.addCardToHand(new PlayingCard("A", "s", 14));
		testHand.addCardToHand(new PlayingCard("8", "c", 8));
		
		testHandRanker.assignHandNames();
		
		Assert.assertEquals("Straight Flush", testHand.getName());
	}
	
	@Test
	public void determine_winner_sets_correct_player_to_winner_when_two_players_have_straight_flush_one_has_higher_card() {
		Player testPlayer2 = new Player("Player 2");
		testPlayer2.addCardToHand(new PlayingCard("A", "s", 14));
		testPlayer2.addCardToHand(new PlayingCard("3", "h", 3));
		
		testPlayer.addCardToHand(new PlayingCard("6", "s", 6));
		testPlayer.addCardToHand(new PlayingCard("2", "c", 2));
		
		
		playerList.add(testPlayer2);

		
		for (Player player : playerList) {
			player.addCardToHand(new PlayingCard("2", "s", 2));
			player.addCardToHand(new PlayingCard("3", "s", 3));
			player.addCardToHand(new PlayingCard("4", "s", 4));
			player.addCardToHand(new PlayingCard("5", "s", 5));
			player.addCardToHand(new PlayingCard("Q", "d", 12));
		}
		
		testHandRanker.assignHandNames();
		testHandRanker.determineWinner();
		
		Assert.assertFalse(testPlayer2.getHand().getIsWinner());
		Assert.assertTrue(testPlayer.getHand().getIsWinner());
		Assert.assertTrue(testPlayer.getHand().getName().equals("Straight Flush"));

	}
	
	@Test
	public void determine_winner_sets_correct_player_to_winner_when_player_has_straight_flush_as_highest_hand() {
		Player testPlayer2 = new Player("Player 2");
		testPlayer2.addCardToHand(new PlayingCard("9", "c", 9));
		testPlayer2.addCardToHand(new PlayingCard("3", "h", 3));
		
		testPlayer.addCardToHand(new PlayingCard("6", "s", 6));
		testPlayer.addCardToHand(new PlayingCard("2", "c", 2));
		
		
		playerList.add(testPlayer2);

		
		for (Player player : playerList) {
			player.addCardToHand(new PlayingCard("2", "s", 2));
			player.addCardToHand(new PlayingCard("3", "s", 3));
			player.addCardToHand(new PlayingCard("4", "s", 4));
			player.addCardToHand(new PlayingCard("5", "s", 5));
			player.addCardToHand(new PlayingCard("Q", "d", 12));
		}
		
		testHandRanker.assignHandNames();
		testHandRanker.determineWinner();
		
		Assert.assertFalse(testPlayer2.getHand().getIsWinner());
		Assert.assertTrue(testPlayer.getHand().getIsWinner());
		Assert.assertTrue(testPlayer.getHand().getName().equals("Straight Flush"));

	}
	
	@Test
	public void assign_hand_name_sets_hand_name_to_royal_flush_if_hand_is_royal_flush_hand() {
		
		Hand testHand = playerList.get(0).getHand();
		
		testHand.addCardToHand(new PlayingCard("2", "s", 2));
		testHand.addCardToHand(new PlayingCard("3", "s", 3));
		testHand.addCardToHand(new PlayingCard("T", "s", 10));
		testHand.addCardToHand(new PlayingCard("J", "s", 11));
		testHand.addCardToHand(new PlayingCard("K", "s", 13));
		testHand.addCardToHand(new PlayingCard("A", "s", 14));
		testHand.addCardToHand(new PlayingCard("Q", "s", 12));
		
		testHandRanker.assignHandNames();
		
		Assert.assertEquals("Royal Flush", testHand.getName());
	}
	
	@Test
	public void determine_winner_sets_correct_player_to_winner_when_player_has_royal_flush() {
		Player testPlayer2 = new Player("Player 2");
		testPlayer2.addCardToHand(new PlayingCard("A", "s", 14));
		testPlayer2.addCardToHand(new PlayingCard("K", "s", 13));
		
		testPlayer.addCardToHand(new PlayingCard("6", "s", 6));
		testPlayer.addCardToHand(new PlayingCard("2", "c", 2));
		
		
		playerList.add(testPlayer2);

		
		for (Player player : playerList) {
			player.addCardToHand(new PlayingCard("2", "s", 2));
			player.addCardToHand(new PlayingCard("3", "s", 3));
			player.addCardToHand(new PlayingCard("T", "s", 10));
			player.addCardToHand(new PlayingCard("J", "s", 11));
			player.addCardToHand(new PlayingCard("Q", "s", 12));
		}
		
		testHandRanker.assignHandNames();
		testHandRanker.determineWinner();
		
		Assert.assertTrue(testPlayer2.getHand().getIsWinner());
		Assert.assertFalse(testPlayer.getHand().getIsWinner());
		Assert.assertTrue(testPlayer2.getHand().getName().equals("Royal Flush"));

	}
	
	@Test
	public void determine_winner_sets_correct_player_to_winner_when_winning_hand_is_two_pair_from_community() {
		Player testPlayer2 = new Player("Player 2");
		testPlayer2.addCardToHand(new PlayingCard("A", "s", 14));
		testPlayer2.addCardToHand(new PlayingCard("K", "d", 13));
		
		testPlayer.addCardToHand(new PlayingCard("6", "s", 6));
		testPlayer.addCardToHand(new PlayingCard("2", "c", 2));
		
		
		playerList.add(testPlayer2);

		
		for (Player player : playerList) {
			player.addCardToHand(new PlayingCard("4", "d", 4));
			player.addCardToHand(new PlayingCard("4", "c", 4));
			player.addCardToHand(new PlayingCard("5", "h", 5));
			player.addCardToHand(new PlayingCard("5", "d", 5));
			player.addCardToHand(new PlayingCard("Q", "s", 12));
		}
		
		testHandRanker.assignHandNames();
		testHandRanker.determineWinner();
		
		Assert.assertTrue(testPlayer2.getHand().getIsWinner());
		Assert.assertFalse(testPlayer.getHand().getIsWinner());
		Assert.assertTrue(testPlayer2.getHand().getName().equals("Two Pair"));
	}
	
	
}
