import java.util.Arrays;
import java.util.Scanner;

/**
 * @author Zeph Nord
 * @version Project04
 * @version Date
 * @version Winter 2017
 */
public class TextToMorse implements BSTTranslator<CharacterOrder> {
	private BST<CharacterOrder> characterOrderBST;
	private CharacterOrder[] characterOrderArray;

	public TextToMorse() {
		super();
		characterOrderBST = new BST<CharacterOrder>();
		characterOrderArray = new CharacterOrder[MorseCode.size()];

		for (int i = 0; i < characterOrderArray.length; i++) {
			MorseCode tmp = MorseCode.get(i);
			characterOrderArray[i] = new CharacterOrder(tmp);
		}
		// now sort the array
		Arrays.sort(characterOrderArray);

		// now build the bst from the sorted array
		// createMorseOrderBST(morseOrderArray, 0, morseOrderArray.length);
		createCharacterOrderBST(characterOrderArray, 0, characterOrderArray.length - 1);
	}

	// Recursively insert from middle of sorted list
	private void createCharacterOrderBST(CharacterOrder[] characterOrderArray, int low, int high) {
		if (low > high) {
			return;
		}
		int middle = (high + low) / 2;

		characterOrderBST.insert(characterOrderArray[middle]);
		createCharacterOrderBST(characterOrderArray, low, middle - 1);
		createCharacterOrderBST(characterOrderArray, middle + 1, high);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see BSTTranslator#getBST()
	 */
	@Override
	public BST<CharacterOrder> getBST() {
		return characterOrderBST;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see BSTTranslator#translate(java.lang.String)
	 */
	@Override
	public String translate(String s) {
		char[] character = s.toCharArray();
		StringBuilder text = new StringBuilder();
		// CharacterOrder tmp = new CharacterOrder(null, character[0]);

		for (int i = 0; i < character.length; i++) {
			try {
				CharacterOrder tmp = characterOrderBST.get(new CharacterOrder(null, character[i]));
				text.append((tmp).getCode() + " ");
			} catch (Exception ex) {
				text.append("");
			}
		}
		return text.toString().trim();
	}

}
