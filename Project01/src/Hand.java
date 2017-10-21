import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * A representation of a hand of playing cards to be used in a game. Cards will
 * remain in the order they were added to the hand.
 * 
 * @author Zeph Nord
 * @version Program1
 * @version CPE 103-03
 * @version Winter 2017
 */
public class Hand {
	// Class fields
	private ArrayList<Card> hand;
	private Deck deck; // The deck to draw from for this hand
	private int handSize; // Size of this hand

	// Class constructors

	/**
	 * Constructs a hand of cards of the specified size by repeatedly drawing
	 * cards from the specified deck.
	 * 
	 * @param deck
	 *            - the deck from which to draw cards
	 * @param handSize
	 *            - the initial size of the player's hand
	 * @throws IllegalArgumentException
	 *             - if the initial hand size is non-positive
	 * @throws NoSuchElementException
	 *             - if the specified deck has fewer than the number of cards
	 *             requested
	 * @throws NullPointerException
	 *             - if the specified deck is null
	 */
	public Hand(Deck deck, int handSize) throws RuntimeException {
		if (deck == null)
			throw new NullPointerException();
		if (deck.size() < handSize)
			throw new NoSuchElementException();
		if (handSize <= 0)
			throw new IllegalArgumentException();

		this.deck = deck;
		this.handSize = handSize;
		hand = new ArrayList<Card>();

		for (int i = 0; i < handSize; i++) {
			hand.add(deck.draw());
		}
	}

	// Class methods

	/**
	 * Draws a card from the deck specified in the constructor and adds it to
	 * this hand.
	 * 
	 * @throws NoSuchElementException
	 *             - if the deck is empty
	 */
	// Ask if this should return void like in the documentation
	public void draw() {
		if (deck.size() <= 0)
			throw new NoSuchElementException();
		if (handSize == 0) {
			hand.add(0, deck.draw());
		} else
			hand.add(deck.draw());
		handSize++;
	}

	/**
	 * Returns the card at the specified position in this hand
	 * 
	 * @param cardNumber
	 *            - the index of the card to return
	 * @return the card at the specified location in this hand
	 * @throws IndexOutOfBoundsException
	 *             - if the index is less than zero or greater than or equal to
	 *             the size() of this hand
	 */
	public Card getCard(int cardNumber) throws RuntimeException {
		if (cardNumber < 0 || cardNumber >= size())
			throw new IndexOutOfBoundsException();
		return hand.get(cardNumber);
	}

	/**
	 * Removes and returns the card at the specified position in this hand.
	 * Shifts any subsequent cards to the left.
	 * 
	 * @param cardNumber
	 *            - the index of the card to play
	 * @return the card that was played from this hand
	 * @throws IndexOutOfBoundsException
	 *             - if the index is less than zero or greater than or equal to
	 *             the size() of this hand
	 */
	public Card play(int cardNumber) {
		if (cardNumber < 0 || cardNumber >= size())
			throw new IndexOutOfBoundsException();
		Card tmpCard = hand.get(cardNumber);

		for (int i = cardNumber + 1; i < size(); i++) {
			hand.set(i - 1, hand.get(i));
		}
		handSize--;
		return tmpCard;
	}

	/**
	 * returns the number of cards in this hand
	 */
	public int size() {
		return handSize;
	}
}
