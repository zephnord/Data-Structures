import java.util.List;
import java.util.NoSuchElementException;

/**
 * Provided BST class skeleton for students to complete. This class makes use of
 * Object Oriented structural recursion to solve the problem.
 *
 * Original Revision:
 * 
 * @author Hatalsky/Jones
 * @version Lab 8
 *
 *          Completed By:
 * @author Zeph Nord
 * @version Winter 2017 - CPE 103-03
 */
public class BST<T extends Comparable<? super T>> {
	// Instance variables for BST class.
	// These are the only ones allowed!
	private final BSTNode<T> EMPTY_NODE = new EmptyNode();
	private BSTNode<T> root = EMPTY_NODE;
	private int size;

	// Polymorphic BST node type!
	private interface BSTNode<T> {
		public BSTNode<T> insert(T element);

		public boolean contains(T element);

		public T minimum(T minimum);

		public T maximum(T maximum);

		public void toSortedList(List<T> list);

		public BSTNode<T> remove(T element);

		public boolean isEmpty();
		
		public int treeHeight(BSTNode<T> node);
		
		public long internalPathLength(int depth);
	}

	////////////////////////////////////////////////////////////////////////////
	// BST class methods...
	//

	/**
	 * Inserts an element into the BST. If the element is already in the BST,
	 * the element being inserted is discarded.
	 *
	 * @param element
	 *            The element to insert into the BST
	 *
	 * @throws IllegalArgumentException
	 *             if the element is null
	 */
	public void insert(T element) {
		if (element == null) {
			throw new IllegalArgumentException();
		}

		root = root.insert(element);
	}

	/**
	 * Determines whether or not an element is in the BST.
	 *
	 * @param element
	 *            the element to search for in the BST
	 *
	 * @return true if the element is in the BST, false otherwise
	 */
	public boolean contains(T element) {
		if (element == null) {
			return false;
		}

		return root.contains(element);
	}

	/**
	 * Finds the minimum element in the BST.
	 *
	 * @return the minimum element in the BST
	 * 
	 * @throws NoSuchElementException
	 *             if the BST is empty
	 */
	public T minimum() {
		if (size == 0) {
			throw new NoSuchElementException();
		}

		return root.minimum(((Node) root).element);
	}

	/**
	 * Finds the maximum element in the BST.
	 *
	 * @return the maximum element in the BST
	 *
	 * @throws NoSuchElementException
	 *             if the BST is empty
	 */
	public T maximum() {
		if (size == 0) {
			throw new NoSuchElementException();
		}

		return root.maximum(((Node) root).element);
	}

	/**
	 * Takes the elements in the BST and places them in ascending order into the
	 * list.
	 *
	 * @param list
	 *            the list in which you will place elements
	 */
	public void toSortedList(List<T> list) {
		root.toSortedList(list);
	}

	public int size() {
		return size;
	}

	/**
	 * 
	 * @param element
	 */
	public void remove(T element) {
		if (element == null) {
			throw new IllegalArgumentException();
		}
		root = root.remove(element);
		size--;
	}
	
	public int treeHeight() {
		return root.treeHeight(root) - 1;
	}
	
	public long internalPathLength() {
		return root.internalPathLength(0);
	}

	////////////////////////////////////////////////////////////////////////////
	// private EmptyNode class...
	//
	private class EmptyNode implements BSTNode<T> {
		// No instance variables needed or allowed!

		public BSTNode<T> insert(T element) {
			size++;
			return new Node(element, EMPTY_NODE, EMPTY_NODE);
		}

		public boolean contains(T element) {
			return false;
		}

		public T minimum(T element) {
			return element;
		}

		public T maximum(T element) {
			return element;
		}

		public void toSortedList(List<T> list) {

		}

		public BSTNode<T> remove(T element) {
			throw new NoSuchElementException();
		}

		public boolean isEmpty() {
			return true;
		}

		public long internalPathLength(int depth) {
			if(size == 0) {
				return -1;
			}
			else {
				return 0;
			}
		}

		public int treeHeight(BSTNode<T> node) {
			return 0;
		}
	}

	////////////////////////////////////////////////////////////////////////////
	// private Node class...
	//
	private class Node implements BSTNode<T> {
		// These are the only instance variables needed and the only ones
		// allowed!
		T element;
		BSTNode<T> left, right;

		// You may (and probably want to) write constructor(s)

		public Node(T element, BSTNode<T> left, BSTNode<T> right) {
			this.element = element;
			this.left = left;
			this.right = right;
		}

		public BSTNode<T> insert(T element) {
			// make a constant value with compare to then check those so we only
			// call compareTo once
			int compare = this.element.compareTo(element);
			
			if (compare > 0) {
				this.left = left.insert(element);
			} else if (compare < 0) {
				this.right = right.insert(element);
			} 
			return this;
		}

		public boolean contains(T element) {
			int compare = this.element.compareTo(element);

			if (compare == 0) {
				return true;
			} else if (compare > 0) {
				return this.left.contains(element);
			} else {
				return this.right.contains(element);
			}
		}

		public T minimum(T element) {
			return left.minimum(this.element);
		}

		public T maximum(T element) {
			return right.maximum(this.element);
		}

		public void toSortedList(List<T> list) {
			this.left.toSortedList(list);
			list.add(this.element);
			this.right.toSortedList(list);
		}

		public BSTNode<T> remove(T element) {
			int compare = this.element.compareTo(element);
			
			if (compare == 0) {

				//--size;
				/*
				 * Possibility 1: the element is a leaf node. Remove by
				 * returning EMPTY_NODE to whomever called you which they will
				 * use to update
				 */
				if (this.left.isEmpty() && this.right.isEmpty()) {
					return EMPTY_NODE;
				}
				/*
				 * Possibility 2: The element is a node with a left child only;
				 * remove by returning the left child to whomever called you to
				 * update their appropriate reference
				 */
				else if (!this.left.isEmpty() && this.right.isEmpty()) {
					return this.left;
				}

				/*
				 * Possibility 3: The element is a node with a right child only;
				 * remove it by returning the right child to whomever called you
				 */
				else if (this.left.isEmpty() && !this.right.isEmpty()) {
					return this.right;
				}

				/*
				 * Possibility 4: The element is a node with both children Find
				 * the minimum element of the right subtree and replace the
				 * element you are removing with it Remove the minimum you just
				 * found by recursively calling remove on the right subtree
				 ** Return yourself call minimum on the right child, call remove
				 * on that
				 */
				else {
					this.element = this.right.minimum(element);
					this.right = this.right.remove(this.element);
					return this;					
				}
			} 
			else if (compare > 0) {
				this.left = this.left.remove(element);
				return this;
			} 
			else if(compare < 0) {
				this.right = this.right.remove(element);
				return this;
			}

			//size--;
			return this;
		}

		public boolean isEmpty() {
			return false;
		}

		public long internalPathLength(int depth) {
			return depth + this.left.internalPathLength(depth + 1) + this.right.internalPathLength(depth + 1);
		}


		public int treeHeight(BSTNode<T> node) {
			return Math.max(this.left.treeHeight(this.left), this.right.treeHeight(this.right)) + 1;
		}
	}
}
