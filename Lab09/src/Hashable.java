/**
 * @author Zeph Nord
 * @version Lab09
 * @version Date
 * @version Winter 2017
 */
public interface Hashable<T> {
	/**
	 * Returns a hash code value for the specified object.
	 * 
	 * @param input
	 *            - the object for which you will calculate a hash code
	 * @return - a hash code value for the specified object (may be negative)
	 */
	int hash(T input);
}
