package dynarray;

import java.util.List;
import java.util.function.Function;
import java.util.ArrayList;
import java.util.Arrays;

import linearpub.DynamicArrayFactory;
import linearpub.DynamicList;

public class DynamicArray<E> implements DynamicList<E> {

	public static void main(String[] args) {

//		DynamicList<Integer> testArr = new DynamicArray<>();

		DynamicList<String> testArr = DynamicArrayFactory.newList();
		
//		test isEmpty
		System.out.println("test is empty: " + testArr.isEmpty());

//		test add
		testArr.add("apple");
		testArr.add("bananna");
		testArr.addLast("pizza");
		testArr.add("ice cream");
		testArr.add("cake");
		testArr.add("pepper");
		testArr.insert(1, "steak");
		testArr.insert(5, "tea");
		testArr.insert(0, "potato");
		System.out.println("test size: " + testArr.size());

//		first, last
		System.out.println("First, Last: " + testArr.get(0) + " " + testArr.get(8));

		for (int i = 0; i < 9; i++) {
			System.out.println("Initial Array: " + testArr.get(i));
		}
		// test removal function
		System.out.println("Remove Function: " + testArr.remove(i -> {
			return i.valueOf(i) == "apple";
		}));
		for (int i = 0; i < 8; i++) {
			System.out.println("After removed: " + testArr.get(i));
		}
		System.out.println("test size: " + testArr.size());

		// test remove index
		System.out.println("Remove index 4: " + testArr.removeIndex(4));
		for (int i = 0; i < 7; i++) {
			System.out.println("After removed: " + testArr.get(i));
		}
		System.out.println("test size: " + testArr.size());

		// test remove first
		System.out.println("Remove first: " + testArr.removeFirst());
		for (int i = 0; i < 6; i++) {
			System.out.println("After removed: " + testArr.get(i));
		}
		System.out.println("test size: " + testArr.size());

		// test remove last
		System.out.println("Removed Last: " + testArr.removeLast());
		for (int i = 0; i < 5; i++) {
			System.out.println("After removed: " + testArr.get(i));
		}
		System.out.println("test size: " + testArr.size());

//		test insert
		System.out.println("Insert 17 at pos 1: ");
		testArr.insert(1, "pho");
		System.out.println("Get index 1: " + testArr.get(1));
		System.out.println("test size: " + testArr.size());

//		test isEmpty
		System.out.println("test is empty: " + testArr.isEmpty());

//		test sublist
		System.out.println("Create sublist 1, 3: ");
		DynamicList<String> subArray = new DynamicArray<>();
		subArray = testArr.subList(1, 3);
		System.out.println("sublist size: " + subArray.size());
		System.out.println("sublist first elem: " + subArray.get(0));
		System.out.println("sublist second elem: " + subArray.get(1));

		// test remove all
		System.out.println("Removing testArr: ");
		testArr.removeAll();
		System.out.println("testArr size: " + testArr.size());
		
		// test toString()
		System.out.println("Testing the to string method: " + testArr.toString());

		//		for (int i = 0; i < 4; i++) {
//			System.out.println("After removed: " + testArr.get(i));
//		}

	}

	private E[] array;
	// holds the current amount of elements of array
	private int size;
	// holds the total capacity of array
	private int capacity;
	private double growthFactor;

	@SuppressWarnings("unchecked")
	public DynamicArray() {
		size = 0;
		capacity = 100;
		growthFactor = 2;
		this.array = (E[]) new Object[capacity];
	}

	public DynamicArray(int capacity, double aGrowthFactor) {
		size = 0;
		capacity = 10;
		growthFactor = aGrowthFactor;
		this.array = (E[]) new Object[capacity];
	}

	public DynamicArray(E array[]) {
//		possibly change the capacity. 	
		this.array = array;
		this.size = this.array.length;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

// may need to change how isEmpty is calculated. 
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return size == 0;
	}

	@Override
	public E get(int index) {
		// if index is within the range
		if (index < size && index >= 0) {
			return array[index];
		} else {
			throw new IndexOutOfBoundsException();
		}
	}

	@Override
	public DynamicList<E> subList(int start, int stop) {
		if (start < size && stop < size) {
			// Get the slice of the Array
			E[] slice = (E[]) new Object[stop - start];

			// Copy elements of array to slice
			for (int i = 0; i < slice.length; i++) {
				slice[i] = array[start + i];
			}

			// return the slice
			DynamicList<E> result = new DynamicArray<E>(slice);
			return result;
		} else {
			throw new IndexOutOfBoundsException();
		}
	}

	@Override
	public E first() {
		if (isEmpty()) {
			throw new RuntimeException();
		}
		return this.get(0);
	}

	@Override
	public E last() {
		if (isEmpty()) {
			throw new RuntimeException();
		}
		return this.get(this.array.length - 1);
	}

	@Override
	public int find(Function<E, Boolean> searchFct) {
		for (int i = 0; i < array.length; i++) {
			if (searchFct.apply(this.get(i)) == true) {
				return i;
			}
		}
		return -1;
	}

	@Override
	public void addFirst(E newElem) {
		// add functionality if we size > 0
		array[0] = newElem;
		size++;
	}

	@Override
	public void addLast(E newElem) {
		if (!this.hasCapacity()) {
			grow();
		}
		array[size] = newElem;
		size++;
	}

	@Override
	public void add(E newElem) {

		if (!this.hasCapacity()) {
			grow();
		}
		if (this.size() == 0) {
			this.addFirst(newElem);
		} else {
			array[size] = newElem;
			size++;
		}

	}

	@Override
	public void insert(int index, E newElem) {
		if (index >= size || index < 0) {
			throw new IndexOutOfBoundsException();
		}

		if (!this.hasCapacity()) {
			grow();
		}
		int j = 0, i = 0;
		E result = null;
		E[] insertedArray = (E[]) new Object[this.array.length];
		while (i < size) {
			// if found, do own count and return inside.
			if (i == index) {
				insertedArray[i] = newElem;
				i++;
				while (i < size + 1) {
					insertedArray[i] = this.array[j];
					i++;
					j++;
				}
				array = insertedArray;
//				have to increment size or the getters will see it later. 
				size++;
				return;
			}
			// dont think I need this
			insertedArray[i] = this.array[j];
			i++;
			j++;
		}
	}

//	@Override
//	public E removeFirst() {
//		if (this.isEmpty()) {
//			throw new RuntimeException();
//		}
//
//		E result = this.array[0];
//		E[] removedArray = (E[]) new Object[this.array.length - 1];
//		for (int i = 1; i < array.length; i++) {
//			removedArray[i - 1] = array[i];
//		}
//		array = removedArray;
//		size--;
//		this.shrink();
//		return result;
//	}

	@Override
	public E removeFirst() {
		if (this.isEmpty()) {
			throw new RuntimeException();
		}

		E result = this.array[0];
		this.removeIndex(0);
		return result;
	}

//	@Override
//	public E removeLast() {
//		if (this.isEmpty()) {
//			throw new RuntimeException();
//		}
//
//		E result = this.array[size - 1];
////		System.out.println("Remove Last counter size: " + size);
//		E[] removedArray = (E[]) new Object[this.array.length - 1];
//		for (int i = 0; i < size - 1; i++) {
//			removedArray[i] = array[i];
//		}
//		this.array = removedArray;
//		size--;
//		this.shrink();
//		return result;
//	}
	
	@Override
	public E removeLast() {
		if (this.isEmpty()) {
			throw new RuntimeException();
		}

		E result = this.array[size - 1];
		this.removeIndex(size - 1);
		return result;
	}

	@Override
	public void removeAll() {
		if (this.isEmpty()) {
			throw new RuntimeException();
		}

		this.array = (E[]) new Object[this.array.length];
		this.shrink();
		this.size = 0;
	}

	@Override
	public E removeIndex(int index) {
		int j = 0, i = 0;
		E result = null;
		E[] removedArray = (E[]) new Object[this.array.length];
		while (i < this.array.length) {
			// if found, do own count and return inside.
			if (i == index) {
				result = this.get(i);
				j++;

				while (i < this.array.length - 1) {
					removedArray[i] = this.array[j];
					i++;
					j++;
				}
				array = removedArray;
//				have to deincrement size or the getters will see it later. 
				size--;
				this.shrink();
				return result;
			}
			removedArray[i] = this.array[j];
			i++;
			j++;
		}
		return null;
	}

	@Override
	public E remove(Function<E, Boolean> searchFct) {
		int j = 0, i = 0;
		E result = null;
		E[] removedArray = (E[]) new Object[this.array.length];
		while (i < this.array.length) {
			// if found, do own count and return inside.
			if (searchFct.apply(this.get(i)) == true) {
				result = this.get(i);
				j++;

				while (i < this.array.length - 1) {
					removedArray[i] = this.array[j];
					i++;
					j++;
				}
				array = removedArray;
//				have to deincrement size or the getters will see it later. 
				size--;
				this.shrink();
				return result;
			}
			removedArray[i] = this.array[j];
			i++;
			j++;
		}
		return null;
	}

	@Override
	public List<E> toJavaList() {
		// Create an empty List
		List<E> list = new ArrayList<>();

		// Iterate through the array
		for (E t : this.array) {
			// Add each element into the list
			list.add(t);
		}

		// Return the converted List
		return list;
	}
	
	public String toString() {
		return "Here's a dynamic array!";
	}

	private Boolean hasCapacity() {
		return this.array.length - size > 0;
	}

// only call grow after hasCapacity returns 0
	private void grow() {
		E[] grownArray = (E[]) new Object[(int) Math.round(this.array.length * growthFactor)];
		for (int i = 0; i < this.array.length; i++) {
			grownArray[i] = this.array[i];
		}
		array = grownArray;
	}
	
//	extra credit: 
	public void shrink() {
		if (this.capacity >= 3 * size) {
			this.array = Arrays.copyOf(this.array, (int) this.growthFactor*2 * this.size);
			this.capacity = this.array.length;			
		} else {			
		}
	}

}
