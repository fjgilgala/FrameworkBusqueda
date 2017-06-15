package framework.algoritmosbusqueda.implementaciones;

import java.util.ArrayList;

import framework.ABIERTA;
import framework.Estado;
import framework.Heuristico;
import framework.algoritmosbusqueda.AlgoritmoBusqueda;
import framework.util.Metrica;

/**
 * Algoritmo IDA* canónico
 * 
 * @author fran
 *
 */
public class IDA extends AlgoritmoBusqueda {

	private int limite;

	@Override
	public Estado ejecuta(ABIERTA abierta, Estado nodo, Heuristico heuristico) {
		sucesores = new ArrayList<Estado>();
		limite = heuristico.calcula(nodo);
		solucion = bandb_Limitada(nodo);
		while (solucion == null)
			solucion = bandb_Limitada(nodo);
		return solucion;
	}

	protected Estado bandb_Limitada(Estado nodo) {
		ABIERTA.limpia();
		ABIERTA.añadir(nodo);
		int menor = Integer.MAX_VALUE;
		Estado local = null;
		while (!ABIERTA.vacia()) {
			local = ABIERTA.primero();
			sucesores.clear();
			if (local.f() <= limite) {
				sucesores = local.expandir();
				Metrica.expande();
				ordenaSucesores(sucesores);
				for (Estado estadoHijo : sucesores)
					if (estadoHijo.esSolucion()) {
						local = estadoHijo;
						Metrica.renuevaCotaSuperior(local);
						return local;
					} else
						ABIERTA.añadir(estadoHijo);
			} else {
				if (local.f() < menor)
					menor = local.f();
			}
		}
		limite = menor;
		return null;
	}

	@Override
	public String toString() {
		return "IDA";
	}
}