import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.Test;

/**
 * @author Zeph Nord
 * @version Lab09
 * @version Date
 * @version Winter 2017
 */
public class PrimeToolsTests {
	
	private static int[] primes = new int[] {127, 131, 137, 139, 149};

	@Test
	public void test01_isPrime() {
		for(int i = 0; i < primes.length; i++) {
			assertTrue(PrimeTools.isPrime(primes[i]));
		}
		assertFalse(PrimeTools.isPrime(-6));
	}
	
	@Test
	public void test02_nextPrime() {
		int nextPrime = 151;
		assertEquals(nextPrime, PrimeTools.nextPrime(primes[4]));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test03_PrimeToolsNegative() {
		PrimeTools.nextPrime(-1);
	}
	
	@Test
	public void test04_PrimeTools1() {
		assertFalse(PrimeTools.isPrime(1));
		assertFalse(PrimeTools.isPrime(0));
	}
	
	@Test
	public void test05_PrimeToolsMax() {
		int prime = Integer.MAX_VALUE;
		assertEquals(2147483647, PrimeTools.nextPrime(prime - 1));
	}
	/*
	@Test(expected = ArithmeticException.class) 
	public void test04_PrimeToolsMaximum() {
		PrimeTools.nextPrime(Integer.MAX_VALUE + 1);
	}*/
}
