import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * 
 */

/**
 * @author Zeph Nord
 * @version Lab01 
 * @version Date
 * @version Winter 2017
 */
@RunWith(Suite.class)
@SuiteClasses({ BSTAcceptanceTests.class, BSTTests.class, MorseCodeAcceptanceTests.class, MorseToTextTests.class,
		TextToMorseTests.class })
public class AllTests {

}
