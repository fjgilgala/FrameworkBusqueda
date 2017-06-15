package framework.algoritmosbusqueda.implementaciones;

import framework.Estado;
import framework.algoritmosbusqueda.IDALookAHead;

public class IDALookAHead_EDD extends IDALookAHead {

	@Override
	public Estado lookHead(Estado nodo) {
		return solucionVoraz(nodo);
	}

	@Override
	public String toString() {
		return "IDA Look_A_Head EDD";
	}
}