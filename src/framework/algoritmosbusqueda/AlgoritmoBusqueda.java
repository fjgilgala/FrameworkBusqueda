package framework.algoritmosbusqueda;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import framework.ABIERTA;
import framework.Estado;
import framework.Heuristico;
import framework.conf.Configuracion;
import framework.tabla_A.TABLA_A;
import framework.util.Metrica;

public abstract class AlgoritmoBusqueda {

	protected Estado solucion;
	protected TABLA_A TABLA_A;
	protected ABIERTA ABIERTA;
	protected List<Estado> sucesores;

	public Estado apply(ABIERTA abierta, Estado nodo, Heuristico heuristico) {

		ExecutorService service = null;
		try {
			service = Executors.newSingleThreadExecutor();
			prepara(abierta);

			ABIERTA.añadir(nodo);
			Metrica.renuevaCotaInferiorInicial(nodo.f());

			Runnable r = new Runnable() {
				@Override
				public void run() {
					solucion = ejecuta(abierta, nodo, heuristico);
				}
			};
			Future<?> f = service.submit(r);
			f.get(Configuracion.timeout, TimeUnit.MINUTES);
		} catch (InterruptedException e) {
		} catch (TimeoutException e) {
			Metrica.OutTime = true;
		} catch (ExecutionException e) {
			e.printStackTrace();
			Metrica.OutMemory = true;
		} finally {
			Metrica.para();
			service.shutdown();
			Metrica.renuevaCotaInferiorFinal(ABIERTA);
			liberaMemoria();
			try {
				Thread.sleep(1000);
				System.gc();
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
		}
		if (solucion != null && solucion.esSolucion()) {
			Metrica.renuevaCotaSuperiorFinal(solucion);
			return solucion;
		} else
			return Metrica.getSolucion();
	}

	private void liberaMemoria() {
		TABLA_A.limpia();
		TABLA_A = null;
		ABIERTA.limpia();
		ABIERTA = null;
		sucesores.clear();
		sucesores = null;
	}

	protected abstract Estado ejecuta(ABIERTA abierta, Estado nodoInicial, Heuristico heuristico);

	/**
	 * Método encargado de preparar las estructuras necesarias y de ordenar la
	 * lectura del fichero por parte del estado inicial
	 * 
	 * @param fichero
	 * @param nodo
	 * @param abierta
	 */
	protected void prepara(ABIERTA abierta) {
		TABLA_A = new TABLA_A();
		ABIERTA = abierta;
		ABIERTA.limpia();
		solucion = null;
		System.gc();
		Metrica.reinicia();
	}

	/**
	 * Ordena los sucesores con mayor valor f primero
	 * 
	 * @param hijos
	 */
	protected void ordenaSucesores(List<Estado> hijos) {
		UtilBusqueda.ordenaSucesores(hijos);
	}

	/**
	 * Ordena los sucesores con menor valor f primero
	 * 
	 * @param hijos
	 */
	protected void ordenaSucesoresInverso(List<Estado> hijos) {
		UtilBusqueda.ordenaSucesoresInverso(hijos);
	}

	protected Estado dfs(Estado nodo) {
		return UtilBusqueda.dfs(nodo);
	}

	protected Estado solucionVoraz(Estado nodo) {
		return nodo.solucionVoraz();
	}
}