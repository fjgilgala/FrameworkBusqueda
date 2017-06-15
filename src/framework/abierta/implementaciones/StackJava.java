package framework.abierta.implementaciones;

import java.util.Stack;

import framework.ABIERTA;
import framework.Estado;

public class StackJava implements ABIERTA {
	private Stack<Estado> nodos;

	public StackJava() {
		nodos = new Stack<Estado>();
	}

	@Override
	public void añadir(Estado nodo) {
		nodos.add(nodo);
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
		return "Stack";
	}
}
