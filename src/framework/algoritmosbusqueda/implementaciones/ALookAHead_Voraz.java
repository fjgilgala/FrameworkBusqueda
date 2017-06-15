package framework.algoritmosbusqueda.implementaciones;

import framework.Estado;
import framework.algoritmosbusqueda.ALookAHead;

public class ALookAHead_Voraz extends ALookAHead {

	@Override
	public Estado lookHead(Estado nodo) {
		return solucionVoraz(nodo);
	}

	@Override
	public String toString() {
		return "A Look_A_Head Voraz";
	}
}