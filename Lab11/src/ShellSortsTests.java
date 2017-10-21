import static org.junit.Assert.*;
import org.junit.*;
import java.lang.reflect.*;
import java.util.Random;
import java.util.Arrays;
import org.junit.runners.MethodSorters;
import org.junit.rules.*;
import org.junit.runner.Description;
import java.util.concurrent.TimeUnit;

/**
 * Contains the unit tests for various Shell sorts within ShellSorts class
 * 
 * @author Zeph Nord
 * @version Lab11
 * @version CPE103-03
 * @version Winter 2017
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ShellSortsTests

{
	/**
	 * JUnit tests for Quadratic sorts lab assignment.
	 *
	 * @author Paul Hatalsky
	 * @version 2/18/2016 Developed for CPE 103 Lab 10
	 */
	@Test
	public void test01_shell_sort() {
		Integer[] array = new Integer[] { 10, 5 };
		ShellSorts.shell(array);
		assertEquals(array[0], new Integer(5));
		assertEquals(array[1], new Integer(10));
	}

	@Test
	public void test02_hibbard_sort() {
		String[] array = new String[] { "World", "Hello" };
		ShellSorts.hibbard(array);
		assertEquals(array[0], "Hello");
		assertEquals(array[1], "World");
	}

	@Test
	public void test03_sedgewick_sort() {
		Integer[] array = new Integer[] { 10, 5 };
		ShellSorts.sedgewick(array);
		assertEquals(array[0], new Integer(5));
		assertEquals(array[1], new Integer(10));
	}

	@Test
	public void test04_heapSort_sort() {
		Integer[] array = new Integer[] { 10, 5 };
		ShellSorts.heapSort(array);
		assertEquals(array[0], new Integer(5));
		assertEquals(array[1], new Integer(10));
	}
	/*
	 * End sample tests developed by Paul Hatalsky
	 */

	/*
	 * Set up a large random arrays for testing
	 */
	private static Integer seed = 20000;
	private static Random random = new Random(seed);
	Integer[] array2000000 = new Integer[2000000];
	Integer[] sorted = new Integer[array2000000.length];

	@Test
	public void test05_shell_sort_advanced() {
		for (int i = 0; i < 2000000; i++) {
			int j = random.nextInt(1000000);
			array2000000[i] = j;
			sorted[i] = j;
		}
		Arrays.sort(sorted);
		ShellSorts.shell(array2000000);
		for(int i = 0; i < sorted.length; i++) {
			assertEquals(sorted[i], array2000000[i]);
		}
	}
	
	@Test
	public void test06_hibbard_sort_advanced() {
		for (int i = 0; i < 2000000; i++) {
			int j = random.nextInt(1000000);
			array2000000[i] = j;
			sorted[i] = j;
		}
		Arrays.sort(sorted);
		ShellSorts.hibbard(array2000000);
		for(int i = 0; i < sorted.length; i++) {
			assertEquals(sorted[i], array2000000[i]);
		}
	}
	
	@Test
	public void test07_sedgewick_sort_advanced() {
		for (int i = 0; i < 2000000; i++) {
			int j = random.nextInt(1000000);
			array2000000[i] = j;
			sorted[i] = j;
		}
		Arrays.sort(sorted);
		ShellSorts.sedgewick(array2000000);
		for(int i = 0; i < sorted.length; i++) {
			assertEquals(sorted[i], array2000000[i]);
		}
	}
	
	@Test
	public void test08_heap_sort_advanced() {
		for (int i = 0; i < 2000000; i++) {
			int j = random.nextInt(1000000);
			array2000000[i] = j;
			sorted[i] = j;
		}
		Arrays.sort(sorted);
		ShellSorts.heapSort(array2000000);
		for(int i = 0; i < sorted.length; i++) {
			assertEquals(sorted[i], array2000000[i]);
		}
	}
	
	@Test
	public void calculateGaps() {
		//replace gaps equation to match gaps type
		int gaps = 1;
		int i = 0;
		while(gaps < 2000000) {
			//System.out.println(gaps);
			gaps = (int) ((int) Math.pow(4, i) + (3 * Math.pow(2, i - 1)) + 1);
			i++;
		}
 	}
}
