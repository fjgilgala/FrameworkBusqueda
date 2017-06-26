package nqueen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import framework.Estado;
import framework.basic.EstadoBasic;

public class EstadoReinas extends EstadoBasic {
	private static int numeroReinas;
	private int[] sol; // indica nº fila en que se coloca una reina
	private boolean[] a;// indica si se puede colocar una reina en la fila i
	private boolean[] b;// indica si se puede colocar una reina en la diagonal
						// i+j
	private boolean[] c;// indica si se puede colocar una reina en la diagonal
						// i-j+n-1

	public EstadoReinas(int numReinas) {
		EstadoReinas.numeroReinas = numReinas;
		generaEstadoInicial();
	}

	public EstadoReinas(EstadoReinas padre, int j, int i) {
		sol = Arrays.copyOf(padre.sol, numeroReinas);
		a = Arrays.copyOf(padre.a, numeroReinas);
		b = Arrays.copyOf(padre.b, 2 * numeroReinas - 1);
		c = Arrays.copyOf(padre.c, 2 * numeroReinas - 1);

		this.profundidad = j;
		sol[j] = i;
		a[i] = false;
		b[i + j] = false;
		c[i - j + numeroReinas - 1] = false;
		this.profundidad++;
	}

	@Override
	public boolean esSolucion() {
		return profundidad == numeroReinas;
	}

	@Override
	public void generaEstadoInicial() {
		sol = new int[numeroReinas];
		for (int i = 0; i < numeroReinas; i++)
			sol[i] = -1;

		a = new boolean[numeroReinas];
		for (int i = 0; i < numeroReinas; i++)
			a[i] = true;

		b = new boolean[2 * numeroReinas - 1];
		for (int i = 0; i < 2 * numeroReinas - 2; i++)
			b[i] = true;

		c = new boolean[2 * numeroReinas - 1];
		for (int i = 0; i < 2 * numeroReinas - 2; i++)
			c[i] = true;
		profundidad = 0;
	}

	@Override
	public List<Estado> expandir() {
		List<Estado> listaHijos = new ArrayList<Estado>();
		for (int i = 0; i < numeroReinas; i++)
			if (a[i] && b[i + profundidad] && c[i - profundidad + numeroReinas - 1]) {
				Estado estadoHijo = new EstadoReinas(this, profundidad, i);
				listaHijos.add(estadoHijo);
			}
		return listaHijos;
	}

	@Override
	public String toString() {
		StringBuffer cad = new StringBuffer();
		cad.append("\n");
		for (int i = 0; i < numeroReinas; i++) {
			for (int j = 0; j < numeroReinas; j++)
				if (sol[j] == i)
					cad.append("X");
				else
					cad.append("0");
			cad.append("\n");
		}
		return cad.toString();
	}
}
