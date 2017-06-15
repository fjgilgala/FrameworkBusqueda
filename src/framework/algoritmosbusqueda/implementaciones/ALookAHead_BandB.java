package framework.algoritmosbusqueda.implementaciones;

import framework.Estado;
import framework.algoritmosbusqueda.ALookAHead;

public class ALookAHead_BandB extends ALookAHead {

	public Estado lookHead(Estado nodo) {
		return dfs(nodo);
	}

	@Override
	public String toString() {
		return "A Look_A_Head B&B";
	}
}