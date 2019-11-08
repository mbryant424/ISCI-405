package Homework.Assignment4;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.TreeMap;

/**
 * A {@code MultiValuedTreeMap} associates each key with a set of values.
 * 
 * @author Max Bryant (mbryant3@albany.edu)
 * @param <K>
 *            the type of keys.
 * @param <V>
 *            the type of values.
 */
public class MultiValuedTreeMap<K, V> extends TreeMap<K, LinkedList<V>> implements Iterable<Pair<K, V>> {

	/**
	 * An automatically generated serial version ID.
	 */
	private static final long serialVersionUID = -6229569372944782075L;
	
	/**
	 * Adds the specified key and value.
	 * 
	 * @param k
	 *            the key to add.
	 * @param v
	 *            the value to add.
	 */
	
	public void add(K k, V v) { // Problem 1
		LinkedList<V> values = new LinkedList<V>();
		if (this.get(k) == null) {
			values.addLast(v);
			this.put(k,values);
			return;
		}
		values = this.get(k);
		values.addLast(v);
		this.put(k,values);
	}

	/**
	 * Removes the first value associated with the specified key.
	 * 
	 * @param k
	 *            a key.
	 * @return the first value associated with the specified key; {@code null} if no such value.
	 */
	public V removeFirst(K k) { // Problem 2
		LinkedList<V> values = new LinkedList<V>();
		if (this.containsKey(k) == false) {
			return null;
		}
		values = this.get(k);
		if (values.isEmpty() == true) {
			return null;
		}
		V v = values.removeFirst();
		return v;
	}

	/**
	 * Removes the first value associated with the first key.
	 * 
	 * @return a {@code Pair} containing the first key and its first value; {@code null} if this
	 *         {@code MultiValuedTreeMap} is empty.
	 */
	public Pair<K, V> removeFirst() { // Problem 3
		if (this.isEmpty() == true) {
			return null;
		}
		K k = this.firstKey();
		V v = this.removeFirst(k);
		this.remove(k);
		Pair<K, V> p = new Pair<K, V>(k, v);
		return p;
	}

	/**
	 * Returns an iterator over the key-value pairs contained in this {@code MultiValuedTreeMap}.
	 * 
	 * @return an iterator over the key-value pairs contained in this {@code MultiValuedTreeMap}.
	 */
	
	public Iterator<Pair<K, V>> iterator() { // Problem 6
		// TODO complete this method
		TreeMap<K, LinkedList<V>> test = this;
		return new Iterator<Pair<K,V>>() { 
			private int keysP = 0;
			private int valuesP = 0;
			LinkedList<V> values = new LinkedList<V>();
			
			@Override
			public boolean hasNext() {
				if (test.isEmpty()) {
					return false;
				}
				return true;
			}
			
			@Override
			public Pair<K,V> next() {
				K key = test.firstKey();
				values = test.get(key);
				V value = values.get(valuesP);
				Pair<K,V> p = new Pair<K, V> (key, value);
				
				if (valuesP == values.size() - 1) {
					keysP++;
					valuesP = 0;
					test.remove(key);
					return p;
				}
				valuesP++;
				return p;
								
			}
		};
			
				
	}

	/**
	 * The main method.
	 * 
	 * @param args
	 *            the program arguments.
	 */
	public static void main(String[] args) {
		MultiValuedTreeMap<Integer, String> m = new MultiValuedTreeMap<Integer, String>();
		m.add(1, "b");
		m.add(1, "a");
		m.add(0, "c");
		System.out.println(m);

		System.out.println(m.removeFirst(1));
		System.out.println(m);
		System.out.println(m.removeFirst());
		System.out.println(m.removeFirst());
		System.out.println(m.removeFirst());

		m.add(1, "b");
		m.add(1, "a");
		m.add(0, "c");
		System.out.println(m);
		for (Pair<Integer, String> p : m) {
			System.out.println(p);
		}
	}
}
