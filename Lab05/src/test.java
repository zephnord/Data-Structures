/**
 * 
 */

/**
 * @author Zeph Nord
 * @version Lab01
 * @version Date
 * @version Winter 2017
 */
public class test {
	public static void main(String[] args) {
		int n = 100;
		int[] bitRepresentation = new int[10];
		int count = 0;
		while (n / 2 > 0) {
			if (count > bitRepresentation.length - 1) {
				int[] tmpArr = new int[2 * count];
				for (int i = 0; i < count; i++) {
					tmpArr[i] = bitRepresentation[i];
				}
				bitRepresentation = tmpArr;
			}

			bitRepresentation[count] = n % 2;
			n = n / 2;
			count++;
		}
		// count++;
		// bitRepresentation[count] = n % 2;
		// reverse the string

		System.out.println(count);
		int[] bitReverse = new int[count];
		for (int i = 0; i < count; i++) {
			bitReverse[i] = bitRepresentation[count - i];
		}

		// complement the string
		
		for (int i = 0; i < count; i++) {
			if (bitRepresentation[i] == 1) {
				bitRepresentation[i] = 0;
			} else {
				bitRepresentation[i] = 1;
			}
		}
		
		for (int i = 0; i < count; i++) {
			System.out.println(bitReverse[i]);;
		}
		int decimalVal = 0;
		// convert back to decimal
		for (int i = 0; i < count; i++) {
			decimalVal += bitRepresentation[i] * Math.pow(2, i);
		}
		
		String string = " ";
		char[] arr = string.toCharArray();
		int tmp = arr[1];

		System.out.println(decimalVal);
	}

}
