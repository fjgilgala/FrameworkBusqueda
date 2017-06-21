package framework.basic;

import framework.Estado;
import framework.Heuristico;
import framework.conf.Configuracion;

public class Hnull implements Heuristico {

	@Override
	public int calcula(Estado nodo) {
		return 0;
	}

	@Override
	public String toString() {
		return this.getClass().getName().replaceAll(Configuracion.paquete_heuristicos + ".", "");
	}
}