package engine;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * @author gRuFtY
 * 
 * A Vector-like class, created for the RPG-Reader.
 * 
 * THIS SHOULD BE REPLACED BY AN LINKEDLIST!
 *
 * @param <T>
 */
public class BetterArray<T> implements List<T> {
	
	private List<T> internalList;
	private Class<LinkedList<T>> listType;
	
	/**
	 * empty constructor
	 */
	public BetterArray() {
		//try {
		//TODO: sollte dynamisch listType instanziieren
			internalList = new LinkedList<T>();
		//} catch (InstantiationException e) {
		//	throw new RuntimeException("Could not create list for BetterArray. Exiting.");
		//} catch (IllegalAccessException e) {
		//	throw new RuntimeException("Could not create list for BetterArray. Exiting.");
		//}
	}
	
	/**
	 * Constructor, that ensures, that the capacity is at least size
	 * 
	 * @param size minimum capacity
	 */
	public BetterArray(int size) {
		//try {
			internalList = new LinkedList<T>();
			//The following *should* ensure a capacity of size
			//internalList.set(size, null);
			for (int i = 0; i < size; i++) {
				internalList.add(null);
			}
		//} catch (InstantiationException e) {
		//	throw new RuntimeException("Could not create list for BetterArray. Exiting.");
		//} catch (IllegalAccessException e) {
		//	throw new RuntimeException("Could not create list for BetterArray. Exiting.");
		//}
	}
	
	/**
	 * Constructs a list containing the elements of the specified collection, in the order they are returned by the 
 	 * collection's iterator.
	 * 
	 * @param c the collection whose elements are to be placed into this list
	 */
	public BetterArray(Collection<? extends T> c) {
		try {
			internalList = listType.newInstance();
			internalList.addAll(c);
		} catch (InstantiationException e) {
			throw new RuntimeException("Could not create list for BetterArray. Exiting.");
		} catch (IllegalAccessException e) {
			throw new RuntimeException("Could not create list for BetterArray. Exiting.");
		}
	}

	@Override
	public boolean add(
			T e) {
		return internalList.add(e);
	}

	@Override
	public void add(
			int index, T element) {
		internalList.add(index, element);
		
	}

	@Override
	public void clear() {
		internalList.clear();
		
	}

	@Override
	public boolean contains(
			Object o) {
		return internalList.contains(o);
	}

	@Override
	public int indexOf(
			Object o) {
		return internalList.indexOf(o);
	}

	@Override
	public boolean isEmpty() {
		return internalList.isEmpty();
	}

	@Override
	public int lastIndexOf(
			Object o) {
		return internalList.lastIndexOf(o);
	}


	@Override
	public boolean remove(
			Object o) {
		return internalList.remove(o);
	}

	@Override
	public int size() {
		return internalList.size();
	}


	@Override
	public Object[] toArray() {
		return internalList.toArray();
	}


	@Override
	public boolean addAll(
			Collection<? extends T> c) {
		return internalList.addAll(c);
	}

	@Override
	public boolean addAll(
			int index, Collection<? extends T> c) {
		return internalList.addAll(index, c);
	}

	@Override
	public boolean removeAll(
			Collection<?> c) {
		return internalList.removeAll(c);
	}

	@Override
	public boolean retainAll(
			Collection<?> c) {
		return internalList.retainAll(c);
	}

	@Override
	public T set(
			int index, T element) {
		return internalList.set(index, element);
	}

	@SuppressWarnings("hiding")
	@Override
	public <T> T[] toArray(
			T[] a) {
		return internalList.toArray(a);
	}

	@Override
	public boolean containsAll(
			Collection<?> c) {
		return internalList.containsAll(c);
	}

	@Override
	public T get(
			int index) {
		return internalList.get(index);
	}

	@Override
	public Iterator<T> iterator() {
		return internalList.iterator();
	}

	@Override
	public ListIterator<T> listIterator() {
		return internalList.listIterator();
	}

	@Override
	public ListIterator<T> listIterator(
			int index) {
		return internalList.listIterator(index);
	}

	@Override
	public T remove(
			int index) {
		return internalList.remove(index);
	}

	@Override
	public List<T> subList(
			int fromIndex, int toIndex) {
		return internalList.subList(fromIndex, toIndex);
	}
	
	
}