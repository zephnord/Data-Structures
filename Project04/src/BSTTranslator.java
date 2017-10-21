/**
 * @author Zeph Nord
 * @version Project04
 * @version Date
 * @version Winter 2017
 */
public interface BSTTranslator<T extends Comparable<? super T>> {
	public BST<T> getBST();
	
	public String translate(String s);
}
