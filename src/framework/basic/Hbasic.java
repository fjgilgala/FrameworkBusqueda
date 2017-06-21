package framework.basic;

import framework.Heuristico;
import framework.conf.Configuracion;

public abstract class Hbasic implements Heuristico {

	@Override
	public String toString() {
		return this.getClass().getName().replaceAll(Configuracion.paquete_heuristicos + ".", "");
	}
}