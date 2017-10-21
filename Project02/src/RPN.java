import java.util.ArrayList;
import java.util.Scanner;

/**
 * A class containing various static methods for reverse polish notation
 * 
 * @author Zeph Nord
 * @version Project02
 * @version CPE 103-03
 * @version Winter 2017
 */
public class RPN {
	/**
	 * Evaluates the given RPN expression. The given expression may or may not
	 * be a well-formed RPN expression. An exception will be thrown(see below)
	 * if the expression is not well-formed. This method supports all numeric
	 * values (integers and floating points) and operators +, -, *, /, and ^
	 * (power operator). Every value and operator in the expression must have at
	 * least one whitespace character found around it. Not that there may be
	 * more whitespace around values and operators. This assumption is designed
	 * to encourage you to use the Scanner class methods to parse the
	 * expressions.
	 * 
	 * @param expression-
	 *            the RPN expression to evaluate
	 * @return the result of the RPN expression
	 * @throws IllegalArgumentException
	 *             - if the RPN expression is not well formed. The exception
	 *             message must be the mal-formed RPN expression.
	 */
	public static double evaluateRPN(String expression) throws IllegalArgumentException {
		@SuppressWarnings("resource")
		Scanner string = new Scanner(expression);

		SimpleLinkedStack<Double> stack = new SimpleLinkedStack<Double>();
		double RPN = 0;
		int numOperands = 0; // counter to ensure equation has balanced number
								// of operands and operators
		int numOperators = 0;

		while (string.hasNext()) {
			// check if character is an operator
			String next = string.next();
			if (next.equals("+") || next.equals("-") || next.equals("*") || next.equals("/") || next.equals("^")) {
				// If an operator is encountered and there are no operands to
				// operate on, malformed expression
				if (stack.size() < 2) {
					throw new IllegalArgumentException(expression);
				}
				numOperators++;
				double firstPop = stack.pop();
				double secondPop = stack.pop();
				switch (next) {
				case "+":
					RPN = secondPop + firstPop;
					stack.push(RPN);
					break;
				case "-":
					RPN = secondPop - firstPop;
					stack.push(RPN);
					break;
				case "*":
					RPN = secondPop * firstPop;
					stack.push(RPN);
					break;
				case "/":
					RPN = secondPop / firstPop;
					stack.push(RPN);
					break;
				case "^":
					RPN = Math.pow(secondPop, firstPop);
					stack.push(RPN);
					break;
				}
			} else {
				// if not an operator, must be an operand so push on stack else
				// Check to ensure operand is a numeric character, must allow //
				// for negative sign
				numOperands++;
				for (int i = 0; i < next.length(); i++) {
					if (Character.valueOf(next.charAt(i)) < 48 || Character.valueOf(next.charAt(i)) > 57) {
						if (Character.valueOf(next.charAt(i)) != 45 && Character.valueOf(next.charAt(i)) != 46
								&& Character.valueOf(next.charAt(i)) != 69) {
							throw new IllegalArgumentException(expression);
						}
					}
				}
				double toPush = Double.parseDouble(next);
				stack.push(toPush);
			}

		}
		RPN = stack.pop();
		// if there is still an item on the stack, the input must have been a
		// single digit
		if (stack.size() == 1) {
			if (numOperators > 0 || numOperands > 0) {
				throw new IllegalArgumentException(expression);
			} else {
				RPN = stack.pop();
			}
		}

		string.close();
		return RPN;
	}

	/**
	 * Converts the given infix expression to reverse polish notation. The given
	 * expression must be a well-formed infix expression. This method supports
	 * all numerical values(integers and floating points), the operators +, -,
	 * *, /, and ^ (power operator) and any amount of parenthesis, In the
	 * resulting RPN Expression: -There will not be any whitespace before the
	 * first character or after the last character. -There will be exactly one
	 * space between each value or operator. -The format of the values will be
	 * preserved: i.e. the integers will stay integers and floating points will
	 * stay floating points.
	 * 
	 * @param infix
	 *            - the expression to convert
	 * @return the resulting RPN notation
	 */
	public static String toRPN(String infix) {
		Scanner string = new Scanner(infix);
		SimpleLinkedStack<String> stack = new SimpleLinkedStack<String>();
		ArrayList<String> list = new ArrayList<String>();
		String RPNExpression = "";
		while (string.hasNext()) {
			String next = string.next();

			if (next.equals("(")) {
				stack.push(next);
			} else if (next.equals(")")) {
				while (!stack.peek().equals("(")) {
					list.add(stack.pop());
				}
				stack.pop();
			} else if (next.equals("^")) {
				stack.push(next);

			} else if (next.equals("*") || next.equals("/")) {
				while (stack.size() > 0 && !stack.peek().equals("(")
						&& (stack.peek().equals("/") || stack.peek().equals("*") || stack.peek().equals("^"))) {
					list.add(stack.pop());
				}
				stack.push(next);
			} else if (next.equals("-") || next.equals("+")) {
				while (stack.size() > 0 && !stack.peek().equals("(")) {
					list.add(stack.pop());
				}
				stack.push(next);
			} else { // must be a value, add to RPN expression
				list.add(next);
			}
		}

		// Once you reach the end of the infix expression, pop all remaining
		// operators and add to the RPN expression
		while (stack.size() > 0) {
			list.add(stack.pop());
		}

		for (int i = 0; i < list.size(); i++) {
			RPNExpression += list.get(i) + " ";
		}
		string.close();

		return RPNExpression.trim();
	}

	/**
	 * Evaluates the given infix expression. The give expression must be a
	 * well-formed infix expression. Uses the previous two methods toRPN and
	 * evaluateRPN.
	 * 
	 * @param infix
	 *            - the infix expression to evaluate.
	 * @return the result of the infix expression
	 */
	public static double evaluateInfix(String infix) {
		return evaluateRPN(toRPN(infix));
	}
}
