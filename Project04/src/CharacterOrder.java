/**
 * @author Zeph Nord
 * @version Project04
 * @version Date
 * @version Winter 2017
 */
public class CharacterOrder extends MorseCode implements Comparable<CharacterOrder> {

	/**
	 * @param code
	 * @param character
	 */
	public CharacterOrder(String code, Character character) {
		super(character, code);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @param character
	 * @param code
	 */
	public CharacterOrder (MorseCode other) {
		super(other);
		// TODO Auto-generated constructor stub
	}
	

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(CharacterOrder o) {
		return this.getCharacter().compareTo(o.getCharacter());
	}

}
