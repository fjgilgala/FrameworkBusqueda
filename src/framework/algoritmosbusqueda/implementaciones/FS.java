package framework.algoritmosbusqueda.implementaciones;

import framework.ABIERTA;
import framework.Estado;
import framework.Heuristico;
import framework.algoritmosbusqueda.AlgoritmoBusqueda;
import framework.util.Metrica;

public class FS extends AlgoritmoBusqueda {

	@Override
	protected Estado ejecuta(ABIERTA abierta, Estado nodo, Heuristico heuristico) {
		while (!ABIERTA.vacia()) {
			solucion = ABIERTA.primero();
			if (solucion.esSolucion())
				return solucion;
			sucesores = solucion.expandir();
			Metrica.expande();
			for (Estado estadoHijo : sucesores) {
				ABIERTA.añadir(estadoHijo);
			}
		}
		return solucion;
	}

	@Override
	public String toString() {
		return "FS";
	}
}