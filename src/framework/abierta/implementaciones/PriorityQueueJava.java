package framework.abierta.implementaciones;

import java.util.PriorityQueue;

import framework.ABIERTA;
import framework.Estado;

public class PriorityQueueJava implements ABIERTA {
	private PriorityQueue<Estado> nodos;

	public PriorityQueueJava() {
		nodos = new PriorityQueue<Estado>();
	}

	@Override
	public void añadir(Estado nodo) {
		nodos.add(nodo);
	}

	@Override
	public Estado primero() {
		return nodos.poll();
	}

	@Override
	public boolean vacia() {
		return nodos.isEmpty();
	}

	public void crearVacia() {
		nodos.clear();
	}

	public int estimacionMejor() {
		return !nodos.isEmpty() ? nodos.peek().f() : 0;
	}

	@Override
	public void limpia() {
		nodos.clear();
	}

	@Override
	public String toString() {
		return "PriorityQueue";
	}
}
