import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Stack;

/**
 * A representation of a standard 52 card deck.
 * 
 * @author Zeph Nord
 * @version Project1
 * @version CPE 103-03
 * @version Winter 2017
 */
public class Deck extends CircularQueue<Card> {
	// Class fields
	// Inherits INITIAL_LENGTH from CircularQueue
	public static final int NUM_RANKS = 13;
	public static final int NUM_SUITS = 4;
	public static final int NUM_CARDS = NUM_RANKS * NUM_SUITS;

	// Class constructors

	/**
	 * Constructs a standard deck of 52 playing cards. If the deck is not to be
	 * shuffled, the cards should be in the order Ace -> King of Spades, Ace ->
	 * King of Hearts, Ace -> King of Diamonds, Ace -> King of Clubs.
	 * 
	 * @param shuffle
	 *            - whether the deck is to be shuffled
	 */
	public Deck(boolean shuffle) {
		super(NUM_CARDS);
		for (int i = 1; i <= NUM_SUITS; i++) {
			for (int j = 1; j <= NUM_RANKS; j++) {
				super.enqueue(new Card(j, i));
			}
		}
		if (shuffle)
			this.shuffle();
	}

	// Class methods

	/**
	 * Shuffles this deck using the inverse permutation of the
	 * Gilbert-Shannon-Reeds Model
	 */
	public void shuffle() {
		Stack<Card> stackOne = new Stack<Card>();
		Stack<Card> stackTwo = new Stack<Card>();
		Random rand = new Random();
		for (int i = 0; i < 7; i++) {
			while(this.size() > 0) {
				int chooseStack = rand.nextInt(2);
				if (chooseStack == 0)
					stackOne.push(this.draw());
				else
					stackTwo.push(this.draw());
			}
			while (!stackOne.isEmpty()) {
				this.enqueue(stackOne.pop());
			}
			while (!stackTwo.isEmpty()) {
				this.enqueue(stackTwo.pop());
			}
		}
	}

	/**
	 * Removes and returns the top card of this deck.
	 * 
	 * @return the top card of this deck
	 * @throws NoSuchElementException
	 *             - if the deck is empty
	 */
	public Card draw() throws RuntimeException {
		if (size() == 0)
			throw new NoSuchElementException();
		return this.dequeue();
	}
}
