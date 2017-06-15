package framework.algoritmosbusqueda;

import java.util.List;

import framework.ABIERTA;
import framework.Estado;
import framework.abierta.implementaciones.StackLinkedList;
import framework.util.Metrica;

public class UtilBusqueda {

	private static List<Estado> sucesores;

	public static Estado dfs(Estado nodo) {
		int cota = Metrica.cotaSuperior;
		int expandidos = 0;
		int expandidosMaximos = nodo.getExpandidosMaximo();
		ABIERTA ABIERTA = new StackLinkedList();
		ABIERTA.añadir(nodo.clone());
		Estado local = null;
		while (!ABIERTA.vacia() && expandidos < expandidosMaximos) {
			Estado actual = ABIERTA.primero();
			expandidos++;
			sucesores = actual.expandir();
			ordenaSucesores(sucesores);
			for (Estado estadoHijo : sucesores)
				if (estadoHijo.f() < cota) {
					if (estadoHijo.esSolucion()) {
						local = estadoHijo;
						cota = local.f();
					} else
						ABIERTA.añadir(estadoHijo);
				}
		}
		ABIERTA.vacia();
		return local;
	}

	/**
	 * Ordena los sucesores con mayor valor f primero
	 * 
	 * @param hijos
	 */
	public static void ordenaSucesores(List<Estado> hijos) {

	}

	/**
	 * Ordena los sucesores con menor valor f primero
	 * 
	 * @param hijos
	 */
	public static void ordenaSucesoresInverso(List<Estado> hijos) {

	}
}