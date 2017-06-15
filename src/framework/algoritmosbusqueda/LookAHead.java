package framework.algoritmosbusqueda;

import framework.Estado;

public interface LookAHead {

	/**
	 * Lanza una implementación concreta de lookAHead para tratar de buscar una
	 * solución bien mediante un método voraz o bien mediante un método de
	 * búsqueda en profundidad
	 * 
	 * @param nodo
	 * @return
	 */
	abstract Estado lookHead(Estado nodo);
}