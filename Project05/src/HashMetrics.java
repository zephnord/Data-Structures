/**
 * Contains the interface for HashMetrics which calculates the number of collisions and max collisions for a hash table.
 * @author Zeph Nord
 * @version Project05 
 * @version CPE103-03
 * @version Winter 2017
 */
public interface HashMetrics {
	/**
	 * Returns the total number of collisions that have occurred during all calls to add(T element) in this table. This method is O(1).
	 * *Collisions during a rehash are not counted in the number of collisions.
	 * @return the current total number of collisions that have occurred during all calls to add(T element)
	 */
	long collisions();
	
	/**
	 * Returns the maximum number of collisions that have occurred during a single call to add(T element) in this table. this method is O(1).
	 * *Collisions during a rehash are not counted in the number of collisions.
	 * @return the current maximum number of collisions that have occured during a single call to add(T element).
	 */
	int maxCollisions();
}
