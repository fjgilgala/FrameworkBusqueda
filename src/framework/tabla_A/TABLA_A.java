package framework.tabla_A;

import java.util.HashMap;
import java.util.Map;

import framework.Estado;

public class TABLA_A {

	private Map<Estado, ValueTABLA_A> tabla = new HashMap<Estado, ValueTABLA_A>();

	public void insertarQ(Estado nodo, Estado anteriorQ, int costeDeInicialaQ) {
		tabla.put(nodo, new ValueTABLA_A(anteriorQ, costeDeInicialaQ));
	}

	public void insertarQ(Estado nodo, Estado anteriorQ, int g, int h) {
		tabla.put(nodo, new ValueTABLA_A(anteriorQ, g, h));
	}

	public void limpia() {
		tabla.clear();
	}

	public boolean contiene(Estado trabajo) {
		return tabla.containsKey(trabajo);
	}

	public void modifica(Estado nodo, int coste) {
		tabla.get(nodo).costeDeInicialaQ = coste;
	}
}

class ValueTABLA_A {
	public Estado anteriorQ;
	public int costeDeInicialaQ;
	public int g;
	public int h;

	public ValueTABLA_A(Estado anteriorQ, int costeDeInicialaQ) {
		this.anteriorQ = anteriorQ;
		this.costeDeInicialaQ = costeDeInicialaQ;
	}

	public ValueTABLA_A(Estado anteriorQ, int g, int h) {
		this.anteriorQ = anteriorQ;
		this.g = g;
		this.h = h;
	}
}