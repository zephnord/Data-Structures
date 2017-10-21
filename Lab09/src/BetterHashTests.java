import static org.junit.Assert.*;

import org.junit.Test;
/**
 * @author Zeph Nord
 * @version Lab09
 * @version Date
 * @version Winter 2017
 */
public class BetterHashTests {

	@Test
	public void test01_BetterHash() {
		Hashable<String> hashable = new BetterHash();
		int stringHash = hashable.hash("Greetings From Mars");
		assertEquals(-945586001, stringHash);
	}
	
	@Test
	public void test02_BetterHash() {
		Hashable<String> hashable = new BetterHash();
		int stringHash = hashable.hash("Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
		assertEquals(877844886, stringHash);
	}

	@Test
	public void test03_BetterHash() {
		Hashable<String> hashable = new BetterHash();
		int stringHash = hashable.hash("Greetings");
		assertEquals(-775048760, stringHash);
	}
}
