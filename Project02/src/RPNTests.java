import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Junit Tests for RPN class
 * @author Zeph Nord
 * @version Project02 
 * @version CPE 103-03
 * @version Winter 2017
 */
public class RPNTests {
	private static final double DELTA = 0.000001;
	
	@Test
	public void testRPNEvaluations() {
		double sum = 3.0;
		double difference = 5.0;
		double product = 100.0;
		double quotient = 5.0;
		double exponent = 1.0E10;
		
		assertEquals(sum, RPN.evaluateRPN("2 1 +"), DELTA);
		assertEquals(difference, RPN.evaluateRPN("10 5 -"), DELTA);
		assertEquals(product, RPN.evaluateRPN("10 10 *"), DELTA);
		assertEquals(quotient, RPN.evaluateRPN("50 10 /"), DELTA);
		assertEquals(exponent, RPN.evaluateRPN("10 10 ^"), DELTA);
		assertEquals((Double) 0.0, (Double) RPN.evaluateRPN("5 5 * 5 + 5 5 * 5 + - 1 ^"), DELTA);
	}
	
	@Test
	public void testFloatingPointNumbers() {
		double sum = 3.0;
		assertEquals(sum, RPN.evaluateRPN("2.0 1.0 +"), DELTA);
	}
	
	@Test
	public void testRPNEvaluationsNegative() {
		double sum = -3.0;
		double difference = -5.0;
		double product = -100.0;
		double quotient = -5.0;
		double exponent = 1.0E10;
		
		assertEquals(sum, RPN.evaluateRPN("-2 -1 +"), DELTA);
		assertEquals(difference, RPN.evaluateRPN("-10 -5 -"), DELTA);
		assertEquals(product, RPN.evaluateRPN("-10 10 *"), DELTA);
		assertEquals(quotient, RPN.evaluateRPN("-50 10 /"), DELTA);
		assertEquals(exponent, RPN.evaluateRPN("-10 10 ^"), DELTA); 
	}
	
	@Test
	public void testTrueExpression() {
		assertEquals(83.0, RPN.evaluateRPN("5 1 2 + 4 ^ + 3 -"), DELTA);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testRPNLoneOperator () {
		RPN.evaluateRPN("+");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testRPNNonNumericCharacter () {
		RPN.evaluateRPN("10 10 + a");
	}
	
	@Test
	public void testSingleDigit() {
		assertEquals(25.6, RPN.evaluateRPN("25.6"), DELTA);
	}
	
	@Test
	public void testLargeRandomInfix() {
		String infix = "( 4.6 + 7.99 ) ^ 2 / 3.59 + 22 * 3 * 0.5";
		double answer = 3.00012207;
		assertEquals("4.6 7.99 + 2 ^ 3.59 / 22 3 * 0.5 * +", RPN.toRPN(infix));
		assertEquals(answer, RPN.evaluateRPN("3 4 2 * 1 5 - 2 3 ^ ^ / +"), DELTA);
		assertEquals(77.15267409, RPN.evaluateInfix(infix), DELTA);
		assertEquals("3 4 2 * 1 5 - 2 3 ^ ^ / +", RPN.toRPN("3 + 4 * 2 / ( 1 - 5 ) ^ 2 ^ 3"));
	}
	
	@Test
	public void testInfixToRPN() {
		String infixExpression = "( 8 + 2 ) - 5";
		String RPNExpression = "8 2 + 5 -";
		assertEquals(RPNExpression, RPN.toRPN(infixExpression));
		assertEquals("", RPN.toRPN("( )"));
		assertEquals("10 2 ^ 5 2 - +", RPN.toRPN("10 ^ 2  + ( 5 - 2 )"));
		assertEquals("10 2 * 5 2 - +", RPN.toRPN("10 * 2  + ( 5 - 2 )"));
		assertEquals("10 2 * 5 2 - +", RPN.toRPN("( 10 * 2 ) + ( 5 - 2 )"));
		assertEquals("10 2 5 2 - + *", RPN.toRPN("10 * ( 2  + ( 5 - 2 ) )"));
		assertEquals("5 5 * 5 + 5 5 * 5 + - 1 ^", RPN.toRPN("( 5 * 5 + 5 - ( 5 * 5 + 5 ) ) ^ 1"));
	}
	
	@Test
	public void testEvaluateInfix() {
		assertEquals(7.0, RPN.evaluateInfix("5 + 2"), DELTA);
		assertEquals(1.0E10, RPN.evaluateInfix("10 ^ 10"), DELTA);
		assertEquals(625.0, RPN.evaluateInfix("5 ^ 2 ^ 2"), DELTA);
		assertEquals(79.792266297612E15, RPN.evaluateInfix("( 7 * 7 ) ^ 10"), DELTA);
		assertEquals(0.0, (Double) RPN.evaluateInfix("( 5 * 5 + 5 - ( 5 * 5 + 5 ) ) ^ 1"), DELTA);
	}
}
