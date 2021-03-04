package linearpub;
/**
	DynamicList
		Idea for structure that:
			- supports indexed access
			- dynamic size (add/remove supported -- in contrast to a fixed structure)
			
	See "Dynamic List ADT" for method descriptions"		  
*/

import java.util.Iterator;
import java.util.List;
import java.util.function.Function;


public interface DynamicList<E> extends Iterable<E> {
	
	//-------------------- Basic Statistics ---------------------
	
	/**
	 * Return number of elements in this list. 
	 */
	int size();
	
	/**
	 * Return true is this list contains no elements.
	 */
	boolean isEmpty();

	//--------------------- Accessing ---------------------
	
	/**	
	 * Return element at given index.
	 * Throws IndexOutOfBoundsException if passed index is invalid. 
	 */
	E get(int index);
	
	/**
	 * Return a new list containing the elements of this list
	 * between the given index "start" (inclusive) and
	 * the given index "stop" (exclusive).
	 * Throws IndexOutOfBoundsException if either passed index is invalid.
	 */	
	DynamicList<E> subList(int start, int stop);
	
	/**
	 * Return first element 
	 * Throws RuntimeException if list is empty
	 */	
	E first();
	
	/**
	 * Return last element 
	 * Throws RuntimeException if list is empty
	 */	
	E last();
	
	/**
	 * Return index of first matching element (where searchFct outputs true) 
	 * Return -1 if no match
	 */	
	int find(Function<E, Boolean> searchFct);
 		
	//--------------------- Adding ---------------------
	
	/**
	 * Add the passed element to start of list 
	 */	
	void addFirst(E newElem);
	
	/**
	 * Add the passed element to end of list 
	 */	
	void addLast(E newElem);
	
	/**
	 * Alias for "addLast" (same functionality). 
	 */	
	void add(E newElem);
	
	/**
	 * Insert passed element into list at the passed index
 	 * Throws IndexOutOfBoundsException if passed index is invalid. 
	 */		
	void insert(int index, E newElem);	
 		
	//--------------------- Removing ---------------------
	
	/**
	 * Remove first element 
	 * Return removed element
	 * Throws RuntimeException if list is empty 
	 */	
	E removeFirst();
	
	/**
	 * Remove last element 
	 * Return removed element
	 * Throws RuntimeException if list is empty 
	 */		
	E removeLast();
	
	/**
	 * Reset the list so it is empty.
	 * If list is already empty, then do nothing
	 * No action is performed on the elements.
	 *
	 */	
	void removeAll();
	
	/**
	 * Remove elem at index 
	 * Return the removed element
 	 * Throws IndexOutOfBoundsException if passed index is invalid.
	 */	
	public E removeIndex(int index);	
	
	/**
	 * Remove first matching element (where searchFct outputs true) 
	 * Return the removed element
	 * If no match, return null
	 */	
	E remove(Function<E, Boolean> searchFct);	
 	
	//--------------------- Convenience ---------------------

	/**
	 * Return a Java "List<E>" containing all elements in this list. 
	 */	
	List<E> toJavaList();
	
	
	/**
	 * Returns one-line user-friendly message about this object 
	 * Helpful method especially for debugging.
	 */	
	String toString();
	
	//---------------------------------------------------------------------
	//--------------------- Optional ---------------------

	/**
	 * Return iterator on this list 
	 */	
	default Iterator<E>	iterator() { throw notImplemented(); }		

	private static RuntimeException notImplemented() {
		return new RuntimeException("Not Implemented");
	}


	
}
