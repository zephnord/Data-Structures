import java.util.Random;

/**
 * This program captures the running times of various sort algorithms in the QuadraticSorts class
 * @author Zeph Nord
 * @version Lab10 
 * @version CPE103-03
 * @version Winter 2017
 */
public class Timing {
	private static Integer seed = 20000;
	private static Random random = new Random(seed);
	
	public static void main(String[] args) {
		Integer[] array2500 = new Integer[2500];
		for(int i = 0; i < 2500; i++) {
			array2500[i] = random.nextInt(100000);
		}
		Integer[] array5000 = new Integer[5000];
		for(int i = 0; i < 5000; i++) {
			array5000[i] = random.nextInt(100000);
		}
		Integer[] array10000 = new Integer[10000];
		for(int i = 0; i < 10000; i++) {
			array10000[i] = random.nextInt(100000);
		}
		Integer[] array20000 = new Integer[20000];
		for(int i = 0; i < 20000; i++) {
			array20000[i] = random.nextInt(100000);
		}
		Integer[] array40000 = new Integer[40000];
		for(int i = 0; i < 40000; i++) {
			array40000[i] = random.nextInt(100000);
		}
		Integer[] array500000 = new Integer[500000];
		for(int i = 0; i < 500000; i++) {
			array500000[i] = random.nextInt(500000);
		}
		
		//insertionSortTiming(array2500);
		insertionSortTiming(array2500);
		insertionSortTiming(array5000);
		insertionSortTiming(array10000);
		insertionSortTiming(array20000);
		insertionSortTiming(array40000);
		//insertionSortTiming(array500000);
	}

	
	public static <T> void bubbleSort1Timing(Integer[] array) {
		long startTime = System.nanoTime();
		
		QuadraticSorts.bubbleSort1(array);
		
		
		long stopTime = System.nanoTime();
		long elapsedTime = stopTime - startTime;
		System.out.println("bubbleSort1: " + array.length + " " +  elapsedTime/array.length + " ns");
	}
	
	public static <T> void bubbleSort2Timing(Integer[] array) {
		long startTime = System.nanoTime();
		
		QuadraticSorts.bubbleSort2(array);
		
		
		long stopTime = System.nanoTime();
		long elapsedTime = stopTime - startTime;
		System.out.println("bubbleSort2: " + array.length + " " +  elapsedTime/array.length + " ns");
	}
	
	public static <T> void insertionSortTiming(Integer[] array) {
		long startTime = System.nanoTime();
		
		QuadraticSorts.insertionSort(array);
		
		
		long stopTime = System.nanoTime();
		long elapsedTime = stopTime - startTime;
		System.out.println("Insertion Sort: " + array.length + " " +  elapsedTime/array.length + " ns");
	}
	
	public static <T> void selectionSortTiming(Integer[] array) {
		long startTime = System.nanoTime();
		
		QuadraticSorts.selectionSort(array);
		
		
		long stopTime = System.nanoTime();
		long elapsedTime = stopTime - startTime;
		System.out.println("Selection Sort: " + array.length + " " +  elapsedTime/array.length + " ns");
	}
}
