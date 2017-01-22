package hospital.heap;

import java.util.ArrayList;

public class ArrayListHeap<T extends Comparable<T>> extends AbstractBinaryHeap<T> {
	
	private ArrayList<T> data;
	private int size;
	private int lastElement;			//Index of the last element in the tree. Used to reliably tell the end of the tree.

	public ArrayListHeap(int initialSize) {
		data = new ArrayList<T>(initialSize);
		size = 0;
		lastElement = 0;
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
	
	/**
	 * Pushes a new element onto the heap.
	 * The element is inserted at the end of the tree.
	 * The tree is then re-heapified and the index of the last element recalculated.
	 * @param element The element to be pushed onto the heap.
	 */
	@Override
	public void push(T element) {
		lastElement++;							//Yes, I'm incrementing this FIRST. And yes, I know that the compiler would probably do this for me anyways.
		data.set(lastElement, element);
		this.heapifyUp(lastElement);
		do{										//Set lastElement to the new last element.
			lastElement--;
		}while(data.get(lastElement) == null);	//Decrement lastElement until the next non-null element is reached.
		size++;
	}

	/**
	 * Gets the top element (the root node element) of the heap.
	 */
	@Override
	public T top() {
		return data.get(0);			//Return element at root
	}

	/**
	 * Gets and removes the top element of the heap.
	 * The last element is moved in place of the root node.
	 * Then the tree is re-heapified and the index of the last element re-calculated.
	 */
	@Override
	public T pop() {
		T retval = data.get(0);
		data.set(0, data.get(lastElement));		//
		data.set(lastElement, null);			//Set the last element to null
		this.heapifyDown(0); 
		do{										//Set lastElement to the new last element.
			lastElement--;
		}while(data.get(lastElement) == null);	//Decrement lastElement until the next non-null element is reached.
		size--;									//Decrement element count
		return retval;
	}

}
