import java.util.List;

/**
 * @author Zeph Nord
 * @version Lab09
 * @version Date
 * @version Winter 2017
 */
public class HashTools {
	/**
	 * Determines the number of hash code collisions in the specified case
	 * @param list
	 * @param tableSize
	 * @param hashable
	 * @return
	 */
	public static <T> int collisions(List<T> list, int tableSize, Hashable<T> hashable) {
		if(!PrimeTools.isPrime(tableSize)) {
			tableSize = PrimeTools.nextPrime(tableSize);
		}
		boolean[] boolArr = new boolean[tableSize];
		int hash = 0;
		int collisions = 0;
		for(int i = 0; i < list.size(); i++) {
			hash = hashable.hash(list.get(i));
			if(boolArr[Math.abs(hash % tableSize)]) {
				collisions++;
			}
			boolArr[Math.abs(hash % tableSize)] = true;
		}
		return collisions;
	}
	
	/**
	 * 
	 * @param list
	 * @param tableSize
	 * @param hashable
	 * @return
	 */
	public static <T> int maxCollisions(List<T> list, int tableSize, Hashable<T> hashable) {
		if(!PrimeTools.isPrime(tableSize)) {
			tableSize = PrimeTools.nextPrime(tableSize);
		}
		int[] collisionArray = new int[tableSize];
		int hash = 0;
		for(int i = 0; i < list.size(); i++) {
			hash = hashable.hash(list.get(i));
			collisionArray[Math.abs(hash % tableSize)]++;			
		}
		int maxCollisions = Integer.MIN_VALUE;
		for(int i = 0; i < collisionArray.length; i++) {
			if(collisionArray[i] > maxCollisions) {
				maxCollisions = collisionArray[i];
			}
		}
		return maxCollisions;
	}
	
	public static <T> double avgChainLength(List<T> list, int tableSize, Hashable<T> hashable) {
		if(!PrimeTools.isPrime(tableSize)) {
			tableSize = PrimeTools.nextPrime(tableSize);
		}
		double avgChain =  (float) list.size() / (tableSize - unused(list, tableSize, hashable));
		return avgChain;
	}
	
	public static <T> int unused(List<T> list, int tableSize, Hashable<T> hashable) {
		if(!PrimeTools.isPrime(tableSize)) {
			tableSize = PrimeTools.nextPrime(tableSize);
		}
		boolean[] boolArr = new boolean[tableSize];
		int hash = 0;
		for(int i = 0; i < list.size(); i++) {
			hash = hashable.hash(list.get(i));
			boolArr[Math.abs(hash % tableSize)] = true;
		}
		int unused = 0;
		for(int i = 0; i < tableSize; i++) {
			if(!boolArr[i]) {
				unused++;
			}
		}
		return unused;
	}
}
