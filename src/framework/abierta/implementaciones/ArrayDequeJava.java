package framework.abierta.implementaciones;

import java.util.ArrayDeque;

import framework.ABIERTA;
import framework.Estado;

public class ArrayDequeJava implements ABIERTA {
	private ArrayDeque<Estado> nodos;

	public ArrayDequeJava() {
		nodos = new ArrayDeque<Estado>();
	}

	@Override
	public void añadir(Estado nodo) {
		nodos.addFirst(nodo);
	}

	@Override
	public Estado primero() {
		return nodos.pop();
	}

	@Override
	public boolean vacia() {
		return nodos.isEmpty();
	}

	public void crearVacia() {
		nodos.clear();
	}

	public int estimacionMejor() {
		if (nodos.isEmpty())
			return 0;
		int mejorF = Integer.MAX_VALUE;
		for (Estado nodo : nodos)
			if (nodo.f() < mejorF)
				mejorF = nodo.f();
		return mejorF;
	}

	@Override
	public void limpia() {
		nodos.clear();
	}

	@Override
	public String toString() {
		return "ArrayDeque";
	}
}
