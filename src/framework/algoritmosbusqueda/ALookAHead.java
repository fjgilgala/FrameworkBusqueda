package framework.algoritmosbusqueda;

import framework.ABIERTA;
import framework.Estado;
import framework.Heuristico;
import framework.algoritmosbusqueda.implementaciones.A;
import framework.conf.Configuracion;
import framework.util.Metrica;

/**
 * Modificación del algoritmo A* con el objetivo de sacar cotas superiores
 * aunque no se encuentre soluciones óptimas.
 * 
 * @author fran
 *
 */
public abstract class ALookAHead extends A implements LookAHead {

	@Override
	protected Estado ejecuta(ABIERTA abierta, Estado nodo, Heuristico heuristico) {
		Metrica.renuevaCotaSuperior(solucionVoraz(nodo));
		while (!ABIERTA.vacia() && ABIERTA.estimacionMejor() < Metrica.cotaSuperior) {
			nodo = ABIERTA.primero();
			if (nodo.esSolucion())
				return nodo;
			else {
				if (Metrica.expandidos % Configuracion.frecuenciaLookAHead == 0)
					Metrica.renuevaCotaSuperior(lookHead(nodo));
				Metrica.expande();
				sucesores = nodo.expandir();
				for (Estado q : sucesores) {
					if (TABLA_A.contiene(q))
						rectifica(q, nodo);
					else {
						TABLA_A.insertarQ(q, nodo, q.g(), q.h());
						mezcla(q);
					}
				}
			}
		}
		return null;
	}
}