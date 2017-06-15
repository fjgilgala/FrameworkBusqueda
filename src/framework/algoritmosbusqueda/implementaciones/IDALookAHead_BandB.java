package framework.algoritmosbusqueda.implementaciones;

import framework.Estado;
import framework.algoritmosbusqueda.IDALookAHead;

public class IDALookAHead_BandB extends IDALookAHead {

	public Estado lookHead(Estado nodo) {
		return dfs(nodo);
	}

	@Override
	public String toString() {
		return "IDA Look_A_Head B&B";
	}
}