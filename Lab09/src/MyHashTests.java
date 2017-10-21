import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author Zeph Nord
 * @version Lab09
 * @version Date
 * @version Winter 2017
 */
public class MyHashTests {

	@Test
	public void test01_MyHash() {
		Hashable<String> hashable = new MyHash();
		int stringHash = hashable.hash("Greetings From Mars");
		assertEquals(1807, stringHash);
	}
	
	@Test
	public void test02_MyHash() {
		Hashable<String> hashable = new MyHash();
		int stringHash = hashable.hash("Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
		assertEquals(5366, stringHash);
	}

	@Test
	public void test03_MyHash() {
		Hashable<String> hashable = new MyHash();
		int stringHash = hashable.hash("Greetings");
		assertEquals(936, stringHash);
	}
}
