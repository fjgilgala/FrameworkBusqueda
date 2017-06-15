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
public class ANoRectifica extends AlgoritmoBusqueda {

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
					mezcla(q);
				}
			}
		}
		return null;
	}

	protected void mezcla(Estado q) {
		ABIERTA.añadir(q);
	}

	@Override
	public String toString() {
		return "A No Rectifica";
	}
}