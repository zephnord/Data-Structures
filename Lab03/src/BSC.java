/**
 * Balanced Symbol Check class that utilizes a simple stack
 * @author Zeph Nord
 * @version CPE 103-03
 * @version Winter 2017
 * @version Lab03
 */
public class BSC<T> extends SimpleArrayStack<T>{
	
	static public boolean isBalanced(String string) {
		SimpleArrayStack<Character> stack = new SimpleArrayStack<Character>();
		if(string.length() == 0) {
			return true;
		}
		
		for(int i = 0; i < string.length(); i++) {
			if(string.charAt(i) == '{' || string.charAt(i) == '[' || string.charAt(i) == '(' || string.charAt(i) == '<') {
				stack.push(string.charAt(i));
			}
			else if(string.charAt(i) == '}') {
				if(stack.size == 0 || stack.pop() != '{') {
					return false;
				}
			}
			else if(string.charAt(i) == ']') {
				if(stack.size == 0 || stack.pop() != '[') {
					return false;
				}
			}
			else if(string.charAt(i) == ')') {
				if(stack.size == 0 || stack.pop() != '(') {
					return false;
				}
			}
			else if(string.charAt(i) == '>') {
				if(stack.size == 0 || stack.pop() != '<') {
					return false;
				}
			}
		}
		
		if(stack.size == 0) {
			return true;
		}
		else {
			return false;
		}
	}
}
