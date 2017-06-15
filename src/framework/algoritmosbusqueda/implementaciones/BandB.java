package framework.algoritmosbusqueda.implementaciones;

import framework.ABIERTA;
import framework.Estado;
import framework.Heuristico;
import framework.algoritmosbusqueda.AlgoritmoBusqueda;
import framework.util.Metrica;

/**
 * Funciona sobre Monticulo binario.
 * 
 * @author fran
 *
 */
public class BandB extends AlgoritmoBusqueda {

	@Override
	protected Estado ejecuta(ABIERTA abierta, Estado nodo, Heuristico heuristico) {
		Metrica.renuevaCotaSuperior(solucionVoraz(nodo));
		while (!ABIERTA.vacia()) {
			solucion = ABIERTA.primero();
			sucesores = solucion.expandir();
			Metrica.expande();
			ordenaSucesores(sucesores);
			for (Estado estadoHijo : sucesores){
				if (estadoHijo.f() < Metrica.cotaSuperior)
					if (estadoHijo.esSolucion()) {
						solucion = estadoHijo;
						Metrica.renuevaCotaSuperior(solucion);
					} else
						ABIERTA.añadir(estadoHijo);
			}
		}
		return solucion;
	}

	@Override
	public String toString() {
		return "B&B";
	}
}