/**
 * @author Zeph Nord
 * @version Lab09
 * @version Date
 * @version Winter 2017
 */
public class StringHash implements Hashable<String> {
	// Constructor
	public StringHash() {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Hashable#hash(java.lang.Object)
	 */
	@Override
	public int hash(String s) {
		return s.hashCode();
	}
}
