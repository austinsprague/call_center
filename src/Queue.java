import java.util.*;
import java.util.HashSet;

/**
 * Class LinkedList E can be used to store a list of values of type E.
 */

public class Queue<E> {

	/** first value in the list */
	private Node<E> front;

	/** last value in the list */
	private Node<E> back;

	/** current number of elements */
	private int size;

	/** Hashset of Nodes */
	HashSet<E> hashSet;

	/** Queue constructer creating empty list and hashset */
	public Queue() {
		front = new Node<E>(null);
		back = new Node<E>(null);
		hashSet = new HashSet<E>();
		clear();
	}

	/**
	 * Size of list
	 * 
	 * @return int of size
	 */
	public int size() {
		return size;
	}

	/**
	 * check if list is empty
	 * 
	 * @return True if empty, false if not
	 */
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Look at front of list, but not removing
	 * 
	 * @return E data
	 */
	public E peek() {
		if (isEmpty()) {
			return null;
		}
		return front.data;
	}

	/**
	 * Check HashSet if exists
	 * 
	 * @param value
	 *            E value to check
	 * @return Boolean true if it does exist
	 */
	public boolean contains(E value) {
		return hashSet.contains(value);
	}

	/**
	 * Add element in to the back of the list and add to Hashset
	 * 
	 * @param value
	 *            E element
	 * @return boolean if added
	 */
	public boolean add(E value) {

		if (!hashSet.add(value)) {
			return false;
		} else {
			Node<E> current = back;
			back = new Node<E>(value);

			if (size++ == 0) {
				front = back;
			} else {
				current.next = back;
			}

			return true;
		}

	}

	/**
	 * Remove element at the front of the list
	 * 
	 * @return E data
	 */
	public E remove() {
		if (size == 0) {
			throw new java.util.NoSuchElementException();
		}
		E data = front.data;
		hashSet.remove(front);
		front = front.next;
		size--;
		if (isEmpty()) {
			back = null;
		}
		return data;

	}

	/**
	 * post: list is empty
	 */
	public void clear() {
		front = null;
		back = null;
		hashSet.clear();
		size = 0;
	}

	/**
	 * Iterator for list
	 * 
	 * @return linkediterator
	 */
	public Iterator<E> iterator() {
		return new LinkedIterator();
	}

	/**
	 * Class of ListNode E that is used in the LinkedList
	 * 
	 * @param E
	 *            Generic type E
	 */
	private static class Node<E> {

		/**
		 * data stored in this node
		 */
		public E data;

		/**
		 * link to next node in the list
		 */
		public Node<E> next;

		/**
		 * post: constructs a node with given data and null links
		 * 
		 * @param data
		 *            type E of data for the Node
		 */
		public Node(E data) {
			this(data, null);
		}

		/**
		 * post: constructs a node with given data and given links
		 * 
		 * @param data
		 *            type E of the data of the node
		 * @param next
		 *            ListNode E next that assigns the node.next
		 */
		public Node(E data, Node<E> next) {
			this.data = data;
			this.next = next;
		}
	}

	/**
	 * LinkedIterator class that implements Iterator
	 *
	 */
	private class LinkedIterator implements Iterator<E> {
		/**
		 * location of next value to return
		 */
		private Node<E> current;
		/**
		 * whether it's okay to remove now
		 */
		private boolean removeOK;

		/**
		 * post: constructs an iterator for the given list
		 */
		public LinkedIterator() {
			current = front.next;
			removeOK = false;
		}

		/**
		 * post: constructs an iterator for the given list
		 */
		public boolean hasNext() {
			return current != back;
		}

		/**
		 * pre : hasNext() post: returns the next element in the iteration
		 */
		public E next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			E result = current.data;
			current = current.next;
			removeOK = true;
			return result;
		}

		/**
		 * pre : next() has been called without a call on remove (i.e., at most one call
		 * per call on next) post: removes the last element returned by the iterator
		 */
		public void remove() {
			if (!removeOK) {
				throw new IllegalStateException();
			}
			size--;
			removeOK = false;
		}
	}

	/**
	 * post: creates a comma-separated, bracketed version of the list
	 */
	public String toString() {
		if (size == 0) {
			return "[]";
		} else {
			String result = "[" + front.next.data;
			Node<E> current = front.next.next;
			while (current != back) {
				result += ", " + current.data;
				current = current.next;
			}
			result += "]";
			return result;
		}
	}
}
