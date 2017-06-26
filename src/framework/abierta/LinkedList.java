package framework.abierta;

import framework.ABIERTA;
import framework.Estado;

public abstract class LinkedList implements ABIERTA {

	protected NodeList head;
	protected int size;

	public LinkedList() {
		head = null;
		size = 0;
	}

	@Override
	public void añadir(Estado nodo) {
		if (size == 0) {
			head = new NodeList(nodo);
		} else {
			NodeList new_node = head;
			while (new_node.Next != null)
				new_node = new_node.Next;
			new_node.Next = new NodeList(nodo);
		}
		size++;
	}

	@Override
	public Estado primero() {
		if (head == null)
			return null;
		Estado o = head.elem;
		head = head.Next;
		size--;
		return o;
	}

	@Override
	public boolean vacia() {
		return size == 0;
	}

	@Override
	public int estimacionMejor() {
		if (!vacia()) {
			NodeList node = head;
			int mejorF = node.elem.f();
			while (node.Next != null) {
				if (node.elem.f() < mejorF)
					mejorF = node.elem.f();
				node = node.Next;
			}
			return mejorF;
		}
		return 0;
	}

	@Override
	public void limpia() {
		head = null;
		size = 0;
	}

}
