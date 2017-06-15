package framework.abierta.implementaciones;

import framework.Estado;
import framework.abierta.LinkedList;
import framework.abierta.NodeList;

public class StackLinkedList extends LinkedList {

	@Override
	public void añadir(Estado nodo) {
		NodeList new_node = new NodeList(nodo);
		if (head == null)
			head = new_node;
		else {
			new_node.Next = head;
			head = new_node;
		}
		size++;
	}

	@Override
	public String toString() {
		return "PilaListaEnlazada";
	}
}