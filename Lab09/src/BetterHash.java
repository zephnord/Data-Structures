/**
 * @author Zeph Nord
 * @version Lab09
 * @version Date
 * @version Winter 2017
 */
public class BetterHash implements Hashable<String> {

	// Constructor
	public BetterHash() {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Hashable#hash(java.lang.Object)
	 */
	@Override
	public int hash(String s) {
		int hash = 0;
		int len = s.length();

		for (int i = 0; i < len; i++) {
			// There are 95 displayable ASCII characters, 97 is the next
			// prime...
			hash = hash * 97 + s.charAt(i);
		}

		return hash;
	}

}
