/**
 * A representation of a card in a standard 52 card deck. The ranks are "Ace",
 * "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", and "King".
 * The suits are "Spades", "Hearts", "Diamonds", and "Clubs".
 * 
 * @author Zeph Nord
 * @version Program1
 * @version CPE 103-03
 * @version Winter 2017
 */
public class Card {
	//Class fields
	private int rank;
	private int suit;
	
	// Class constructors

	/**
	 * Constructs a card with the specified rank and suit. The rank will be
	 * given as a number between 1 and 13 representing "Ace through "King". The
	 * quit will be given as a number between 1 and 4 representing "Spades",
	 * "Hearts", "Diamonds", "Clubs" respectively
	 * 
	 * @param rank
	 *            - the rank of the card
	 * @param suit
	 *            - the suit of the card
	 * @throws IllegalArgumentException
	 *             - if the specified rank or the specified suit are invalid
	 */
	public Card(int rank, int suit) throws RuntimeException {
		if (rank < 1 || rank > 13)
			throw new IllegalArgumentException();
		if (suit < 1 || suit > 4)
			throw new IllegalArgumentException();
		this.rank = rank;
		this.suit = suit;
	}

	/**
	 * Returns the rank of this card as a String. This will be one of "Ace",
	 * "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", or "King".
	 * 
	 * @return the rank of this card
	 */
	public String getRank() {
		if(rank > 1 && rank <= 10)
			return Integer.toString(rank);
		else if(rank == 1) {
			return "Ace";
		}
		else if(rank == 11) {
			return "Jack";
		}
		else if(rank == 12) {
			return "Queen";
		}
		else
			return "King";
	}

	/**
	 * Returns the suit of this card as a String. This will be one of "Spades",
	 * "Hearts", "Diamonds", or "Clubs".
	 * 
	 * @return the suit of this card
	 */
	public String getSuit() {
		if(suit == 1) {
			return "Spades";
		}
		else if(suit == 2) {
			return "Hearts";
		}
		else if(suit == 3) {
			return "Diamonds";
		}
		else 
			return "Clubs";
	}
	
	/**
	 * Returns the name of this card. This will be of the form "<RANK> of <SUIT>", 
	 * @overrides - toString in class Object
	 * @return the name of this card
	 */
	public String toString() {
		return getRank() + " of " + getSuit();
	}
}
