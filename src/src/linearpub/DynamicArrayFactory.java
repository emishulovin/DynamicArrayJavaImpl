package linearpub;

import dynarray.DynamicArray;

/* To Do:
 * 		See "//TODO" tags
*/

/*
 * DynamicArrayFactory
 * 	I am a factory that generates and returns a dynamic array
 */

public class DynamicArrayFactory {

	public static <T> DynamicList<T> newList() {
		//return new empty dynamic array
		//TODO -- replace "XXXXXXXXX" with your dynamic array class name (and un-comment next line) 
		return new DynamicArray<>();

	}
	

	public static <T> DynamicList<T> newList(double growthFactor) {
		//return new empty dynamic array
		//TODO -- replace "XXXXXXXXX" with your dynamic array class name (and un-comment next line) 
		int capacity = 10;
		return new DynamicArray<T>(capacity, growthFactor);

	}	
	
	
}
