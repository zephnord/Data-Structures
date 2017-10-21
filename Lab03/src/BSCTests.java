/**
 * JUnit tests for BSC class
 * @author Zeph Nord
 * @version CPE 103-03
 * @version Winter 2017
 * @version Lab03
 */
import static org.junit.Assert.*;
import org.junit.*;
import org.junit.runners.MethodSorters;
import org.junit.rules.*;
import org.junit.runner.Description;
import java.util.concurrent.TimeUnit;
import java.io.*;

public class BSCTests
{
   @Test
   public void test01_simpleString() 
   {
      assertTrue(BSC.isBalanced("[hello]"));
   }
   
   @Test
   public void test02() {
	   assertTrue(BSC.isBalanced("<<>>"));
	   assertFalse(BSC.isBalanced("<<>"));
   }
   
   @Test
   public void testEmptyString() {
	   assertTrue(BSC.isBalanced(""));
   }
   
   @Test
   public void testNoBrackets() {
	   assertTrue(BSC.isBalanced("No brackets in this string"));
   }
   
   @Test
   public void testUnbalancedStrings() {
	   assertFalse(BSC.isBalanced("(((("));
	   assertFalse(BSC.isBalanced("<><><)]"));
	   assertFalse(BSC.isBalanced("[{{][][]}}}}"));
	   assertFalse(BSC.isBalanced("advdf<><"));
	   assertFalse(BSC.isBalanced("]"));
   }
   
   @Test
   public void testBalancedStrings() {
	   assertTrue(BSC.isBalanced(""));
	   assertTrue(BSC.isBalanced("asdfa()sdf"));
	   assertTrue(BSC.isBalanced("<<>>asdf()adf"));
	   assertTrue(BSC.isBalanced("( [ { < ( ) <( ){}>> } ] )"));
	   assertTrue(BSC.isBalanced("[[[<<(())>>]]]"));
	   
   }
}