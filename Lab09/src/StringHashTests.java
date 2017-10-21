import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author Zeph Nord
 * @version Lab09
 * @version Date
 * @version Winter 2017
 */
public class StringHashTests {

	@Test
	public void test01_StringHash() {
		Hashable<String> hashable = new StringHash();
		int stringHash = hashable.hash("Greetings From Mars");
		assertEquals(966080549, stringHash);
	}
	
	@Test
	public void test02_StringHash() {
		Hashable<String> hashable = new StringHash();
		int stringHash = hashable.hash("Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
		assertEquals(1555402512, stringHash);
	}

	@Test
	public void test03_StringHash() {
		Hashable<String> hashable = new StringHash();
		int stringHash = hashable.hash("Greetings");
		assertEquals(-217864390, stringHash);
	}
}
