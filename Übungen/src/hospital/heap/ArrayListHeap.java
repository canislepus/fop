package hospital.heap;

import java.util.ArrayList;

public class ArrayListHeap<T extends Comparable<T>> extends AbstractBinaryHeap<T> {
	
	private ArrayList<T> data;
	private int size;

	public ArrayListHeap(int initialSize) {
		data = new ArrayList<T>(initialSize);
		size = 0;
	}

	/**
	 * Returns the number of elements contained in the heap.
	 * @return the number of elements in the heap
	 */
	@Override
	public int getSize() {
		return size;
	}
	
	/**
	 * Checks if the heap contains zero elements (is empty).
	 * @return True if the heap is empty.
	 */
	@Override
	public boolean isEmpty() {
		return (size <= 0);			//should not be less but... cmove vs. cmovng ...
	}

	/**
	 * Checks if parent has higher priority (lower value) than child (heap condition).
	 * Returns true if it has; false otherwise.
	 * 
	 * @param parentIndex the index of the parent object
	 * @param childIndex the index of the child object
	 * @return Whether or not this parent-child-pair meets the heap condition
	 */
	@Override
	protected boolean isHeap(int parentIndex, int childIndex) {
		return (data.get(parentIndex).compareTo(data.get(childIndex)) < 0);
	}

	/**
	 * Swaps the elements at parent- and childIndex with each other.
	 * @param parentIndex The index of the parent element
	 * @param childIndex The index of the child element
	 */
	@Override
	protected void swapNodes(int parentIndex, int childIndex) {
		T temp = data.get(parentIndex);
		data.set(parentIndex, data.get(childIndex));
		data.set(childIndex, temp);
	}

	@Override
	public void push(T element) {
		
		size++;
	}

	@Override
	public T top() {
		
		return null;
	}

	@Override
	public T pop() {
		
		size--;
		return null;
	}

}
