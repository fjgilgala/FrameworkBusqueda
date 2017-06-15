package framework.algoritmosbusqueda.implementaciones;

import framework.ABIERTA;
import framework.Estado;
import framework.Heuristico;
import framework.algoritmosbusqueda.AlgoritmoBusqueda;
import framework.util.Metrica;

/**
 * Algoritmo A* canónico
 * 
 * @author fran
 *
 */
public class A extends AlgoritmoBusqueda {

	@Override
	protected Estado ejecuta(ABIERTA abierta, Estado nodo, Heuristico heuristico) {
		while (!ABIERTA.vacia()) {
			solucion = ABIERTA.primero();
			if (solucion.esSolucion())
				return solucion;
			else {
				Metrica.expande();
				sucesores = solucion.expandir();
				for (Estado q : sucesores) {
					if (TABLA_A.contiene(q))
						rectifica(q, solucion);
					else {
						TABLA_A.insertarQ(q, solucion, q.g(), q.h());
						mezcla(q);
					}
				}
			}
		}
		return null;
	}

	protected void rectifica(Estado q, Estado nodo) {
		if (q.g() < nodo.g()) {
			TABLA_A.modifica(nodo, nodo.g());
			rectificarLista(nodo);
		}
	}

	protected void rectificarLista(Estado nodo) {
		for (Estado q : sucesores)
			rectifica(q, nodo);
	}

	protected void mezcla(Estado q) {
		ABIERTA.añadir(q);
	}

	@Override
	public String toString() {
		return "A";
	}
}