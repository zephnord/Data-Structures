import java.util.Arrays;
import java.util.Scanner;

/**
 * @author Zeph Nord
 * @version Project04
 * @version Date
 * @version Winter 2017
 */
public class MorseToText implements BSTTranslator<MorseOrder> {
	private BST<MorseOrder> morseOrderBST;
	private MorseOrder[] morseOrderArray;
	//Class constructors (may use multiple)
	
	public MorseToText() {
		super();
		morseOrderBST = new BST<MorseOrder>();
		morseOrderArray = new MorseOrder[MorseCode.size()];
		for(int i = 0; i < morseOrderArray.length; i++) {
			MorseCode tmp = MorseCode.get(i);
			morseOrderArray[i] = new MorseOrder(tmp.getCharacter(), tmp.getCode());
		}
		//now sort the array
		Arrays.sort(morseOrderArray);
		
		//now build the bst from the sorted array
		//createMorseOrderBST(morseOrderArray, 0, morseOrderArray.length);
		createMorseOrderBST(morseOrderArray, 0, morseOrderArray.length - 1);
	}
	
	//Recursively insert from middle of sorted list
	private void createMorseOrderBST(MorseOrder[] morseOrderArray, int low, int high) {
		if(low > high) {
			return;
		}
		int middle = (high + low) /2;
		
		morseOrderBST.insert(morseOrderArray[middle]);
		createMorseOrderBST(morseOrderArray, low, middle - 1);
		createMorseOrderBST(morseOrderArray, middle + 1, high);
	}
	
	/* (non-Javadoc)
	 * @see BSTTranslator#getBST()
	 */
	@Override
	public BST<MorseOrder> getBST() {
		return morseOrderBST;
	}

	/* (non-Javadoc)
	 * @see BSTTranslator#translate(java.lang.String)
	 */
	@Override
	public String translate(String s) {
		Scanner in = new Scanner(s);
		String next;
		StringBuilder text = new StringBuilder();
		
		while(in.hasNext()) {
			next = in.next();
			try {
				MorseOrder tmpCharacterOrder = morseOrderBST.get(new MorseOrder(null, next));
				text.append(tmpCharacterOrder.getCharacter());
			}
			catch(Exception ex) {
				text.append("");
			}			
		}
		in.close();
		return text.toString().trim();
	}

}
