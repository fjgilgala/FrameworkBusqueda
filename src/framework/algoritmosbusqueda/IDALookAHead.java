package framework.algoritmosbusqueda;

import java.util.ArrayList;

import framework.ABIERTA;
import framework.Estado;
import framework.Heuristico;
import framework.algoritmosbusqueda.implementaciones.IDA;
import framework.conf.Configuracion;
import framework.util.HeuristicoActivo;
import framework.util.Metrica;

public abstract class IDALookAHead extends IDA implements LookAHead {

	private int limite;

	@Override
	public Estado ejecuta(ABIERTA abierta, Estado nodo, Heuristico heuristico) {
		Metrica.renuevaCotaSuperior(solucionVoraz(nodo));
		sucesores = new ArrayList<Estado>();
		limite = HeuristicoActivo.heuristico.calcula(nodo);
		solucion = bandb_Limitada(nodo);
		while (solucion == null && limite < Metrica.cotaSuperior)
			solucion = bandb_Limitada(nodo);
		return solucion;
	}

	protected Estado bandb_Limitada(Estado nodo) {
		ABIERTA.limpia();
		ABIERTA.añadir(nodo);
		int menor = Integer.MAX_VALUE;
		Estado local = null;
		while (!ABIERTA.vacia() && limite < Metrica.cotaSuperior) {
			local = ABIERTA.primero();
			sucesores.clear();
			if (local.f() <= limite) {
				if (Metrica.expandidos % Configuracion.frecuenciaLookAHead == 0)
					Metrica.renuevaCotaSuperior(lookHead(nodo));
				sucesores = local.expandir();
				Metrica.expande();
				ordenaSucesores(sucesores);
				for (Estado estadoHijo : sucesores)
					if (estadoHijo.esSolucion()) {
						local = estadoHijo;
						Metrica.renuevaCotaSuperior(local);
						return local;
					} else
						ABIERTA.añadir(estadoHijo);
			} else {
				if (local.f() < menor)
					menor = local.f();
			}
		}
		limite = menor;
		return null;
	}
}