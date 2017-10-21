/**
 * @author Zeph Nord
 * @version Project04
 * @version Date
 * @version Winter 2017
 */
public class MorseOrder extends MorseCode implements Comparable<MorseOrder> {	
	/**
	 * @param character
	 * @param code
	 */
	public MorseOrder(Character character, String code) {
		super(character, code);
		// TODO Auto-generated constructor stub
	}	
	
	/**
	 * @param character
	 * @param code
	 */
	public MorseOrder(MorseCode other) {
		super(other);
		// TODO Auto-generated constructor stub
	}
	
	

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(MorseOrder o) {
		//int compare = this.compareTo(o);
		return this.getCode().compareTo(o.getCode());
	}

}