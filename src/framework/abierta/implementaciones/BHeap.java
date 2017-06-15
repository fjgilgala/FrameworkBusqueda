package framework.abierta.implementaciones;

import java.util.Arrays;

import framework.ABIERTA;
import framework.Estado;

public class BHeap implements ABIERTA {
	private static final int DEFAULT_CAPACITY = 10;
	protected Estado[] array;
	protected int size;

	public BHeap() {
		array = new Estado[DEFAULT_CAPACITY];
		size = 0;
	}

	@Override
	public void añadir(Estado nodo) {
		if (size >= array.length - 1)
			array = resize(nodo);
		size++;
		int index = size;
		array[index] = nodo;
		bubbleUp();
	}

	@Override
	public Estado primero() {
		if (vacia())
			return null;
		Estado result = array[1];
		array[1] = array[size];
		array[size] = null;
		size--;
		bubbleDown();
		return result;
	}

	@Override
	public boolean vacia() {
		return size == 0;
	}

	@Override
	public int estimacionMejor() {
		if (vacia())
			return 0;
		return array[1].f();
	}

	@Override
	public void limpia() {
		array = new Estado[DEFAULT_CAPACITY];
		size = 0;
	}

	@Override
	public String toString() {
		return "BHeap";
	}

	protected void bubbleDown() {
		int index = 1;
		while (hasLeftChild(index)) {
			int smallerChild = leftIndex(index);
			if (hasRightChild(index) && array[leftIndex(index)].compareTo(array[rightIndex(index)]) >= 0)
				smallerChild = rightIndex(index);
			if (array[index].compareTo(array[smallerChild]) >= 0)
				swap(index, smallerChild);
			else
				break;
			index = smallerChild;
		}
	}

	protected void bubbleUp() {
		int index = this.size;
		while (hasParent(index) && (parent(index).compareTo(array[index]) >= 0)) {
			swap(index, parentIndex(index));
			index = parentIndex(index);
		}
	}

	protected boolean hasParent(int i) {
		return i > 1;
	}

	protected int leftIndex(int i) {
		return i * 2;
	}

	protected int rightIndex(int i) {
		return i * 2 + 1;
	}

	protected boolean hasLeftChild(int i) {
		return leftIndex(i) <= size;
	}

	protected boolean hasRightChild(int i) {
		return rightIndex(i) <= size;
	}

	protected Estado parent(int i) {
		return array[parentIndex(i)];
	}

	protected int parentIndex(int i) {
		return i / 2;
	}

	protected Estado[] resize(Estado nodo) {
		return Arrays.copyOf(array, array.length * 2);
	}

	protected void swap(int index1, int index2) {
		Estado tmp = array[index1];
		array[index1] = array[index2];
		array[index2] = tmp;
	}
}