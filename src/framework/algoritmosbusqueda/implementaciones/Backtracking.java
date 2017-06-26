package framework.algoritmosbusqueda.implementaciones;

import framework.ABIERTA;
import framework.Estado;
import framework.Heuristico;
import framework.algoritmosbusqueda.AlgoritmoBusqueda;
import framework.util.Metrica;

public class Backtracking extends AlgoritmoBusqueda {

	@Override
	protected Estado ejecuta(ABIERTA abierta, Estado nodo, Heuristico heuristico) {
		if (nodo.esSolucion())
			return nodo;
		if (!abierta.vacia()) {
			nodo = abierta.primero();
			sucesores = nodo.expandir();
			Metrica.expande();
			for (Estado estadoHijo : sucesores)
				ABIERTA.añadir(estadoHijo);
		} 
		return ejecuta(abierta, nodo, heuristico);
	}

	@Override
	public String toString() {
		return "Backtracking";
	}
}