/**
 * @author Zeph Nord
 * @version Lab09
 * @version Date
 * @version Winter 2017
 */
public class MyHash implements Hashable<String> {

	/* (non-Javadoc)
	 * @see Hashable#hash(java.lang.Object)
	 */
	@Override
	public int hash(String s) {
		int N = 178689;
		N = PrimeTools.nextPrime(N);
		int sum = 0;
		
		for(int i = 0; i < s.length(); i++) {
			sum += s.charAt(i);
		}
		return sum % N;
	}

}
