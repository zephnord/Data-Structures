import static org.junit.Assert.*;
import java.lang.IllegalArgumentException;
import java.util.NoSuchElementException;

import org.junit.Test;

/**
 * UnitTests for the Card, Deck, and Hand classes
 * @author Zeph Nord
 * @version Program1
 * @version CPE 103-03
 * @version Winter 2017
 */
public class CardTests {

	@Test
	public void testToString() {
		Card card = new Card(1, 2);
		assertEquals(card.toString(), "Ace of Hearts");
		card = new Card(4, 2);
		assertEquals(card.toString(), "4 of Hearts");
		card = new Card(10, 4);
		assertEquals(card.toString(), "10 of Clubs");		
	}

	@SuppressWarnings("unused")
	@Test(expected=IllegalArgumentException.class)
	public void testAddIllegalRankNonPositive() {
		Card card = new Card(0, 2);
	}
	
	@SuppressWarnings("unused")
	@Test(expected=IllegalArgumentException.class)
	public void testAddIllegalRankPositive() {
		Card card = new Card(18, 2);
	}
	
	@SuppressWarnings("unused")
	@Test(expected=IllegalArgumentException.class)
	public void testAddIllegalSuitNonPositive() {
		Card card = new Card(5, 0);
	}
	
	@SuppressWarnings("unused")
	@Test(expected=IllegalArgumentException.class)
	public void testAddIllegalSuitPositive() {
		Card card = new Card(5, 6);
	}
	
	//Deck Tests
	
	@Test
	public void testDeckUnshuffled() {
		Deck deck = new Deck(false);
		for(int i = 1; i <= 4; i++) {
			for(int j = 1; j <= 13; j++) {
				assertEquals(deck.dequeue().toString(), new Card(j, i).toString());
			}
		}
	}	
	
	@Test
	public void testDrawFromOrderedDeck() {
		Deck deck = new Deck(false);
		int size = deck.size();
		for(int i = 1; i <= 4; i++) {
			for(int j = 1; j <= 13; j++) {
				assertEquals(deck.draw().toString(), new Card(j, i).toString());
				assertEquals(--size, deck.size());
			}
		}
	}
	
	@Test(expected=NoSuchElementException.class)
	public void testIllegalDraw() {
		Deck deck = new Deck(false);
		int size = deck.size();
		for(int i = 0; i < size + 1; i++) {
			deck.draw();
		}
	}
	
	@Test
	public void testNewDeckSize() {
		Deck deck = new Deck(false);
		assertEquals(52, deck.size());
	}
	
	@Test
	public void testShuffledDeck() {
		Deck deck = new Deck(true);
		int size = deck.size();
		for(int i = 0; i < size; i++) {
			//Uncomment to print deck to stdout in order to see randomness
			//System.out.println(deck.draw()); 
		}
	}
	
	@Test
	public void testUnshuffledThenShuffledDeck() {
		Deck deck = new Deck(false);
		deck.shuffle();
		int size = deck.size();
		for(int i = 0; i < size; i++) {
			//Uncomment to print deck to stdout in order to see randomness
			//System.out.println(deck.draw()); 
		}
	}
	
	
	//Hand tests
	
	@Test
	public void test5CardHand() {
		//use a shuffled deck to check against first five cards
		int handSize = 5;
		Deck deck = new Deck(false);
		Hand hand = new Hand(deck, handSize);
		
		assertEquals(deck.size(), 52 - hand.size());
		for(int i = 0; i < handSize; i++) {
			assertEquals(new Card(i + 1, 1).toString(), hand.getCard(i).toString());
		}		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testInvalidHandNonPositive() {
		int handSize = 0;
		Deck deck = new Deck(true);
		@SuppressWarnings("unused")
		Hand hand = new Hand(deck, handSize);
	}
	
	//Attempts to make 6 hands of 10 cards from 52 card deck
	@SuppressWarnings("unused")
	@Test(expected=NoSuchElementException.class)
	public void testInvalidHandPositive() {
		int handSize = 10;
		Deck deck = new Deck(true);
		Hand handOne = new Hand(deck, handSize);
		Hand handTwo = new Hand(deck, handSize);
		Hand handThree = new Hand(deck, handSize);
		Hand handFour = new Hand(deck, handSize);
		Hand handFive = new Hand(deck, handSize);
		Hand handSix = new Hand(deck, handSize);
	}
	
	@Test
	public void playAllCardsInHand() {
		int handSize = 5;
		Deck deck = new Deck(false);
		Hand hand = new Hand(deck, handSize);
		for(int i = 0; i < handSize; i++) {
			//System.out.println(hand.getCard(i));
		}
		for(int i = 0; i < handSize; i++) {
			assertEquals(new Card(i + 1, 1).toString(), hand.play(0).toString());
			//System.out.println(hand.play(0));
		}		
	}
	
	@Test
	public void oneCardHand() {
		int handSize = 1;
		Deck deck = new Deck(false);
		Hand hand = new Hand(deck, handSize);
		for(int i = 0; i < handSize; i++) {
		//	System.out.println(hand.getCard(0));
		}
	}
	
	@Test
	public void drawThenPlayWholeDeck() {
		int handSize = 52;
		Deck deck = new Deck(false);
		Hand hand = new Hand(deck, handSize);
		for(int i = 0; i < handSize; i++) {
		//	System.out.println(hand.play(0));
		}
	}
	
}
