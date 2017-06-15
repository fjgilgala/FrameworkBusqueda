package asignaciontrabajos.util;

public class OrdenarTareas {

	/**
	 * Ordenar las tareas en orden no decreciente por sus due dates
	 */
	public static int[] ordenaTodasLasTareasPorDueDate() {
		return ordenaTareas(ValoresInstanciaAsignacionTrabajos.fechaVencimiento.clone());
	}

	/**
	 * Ordenar las tareas en orden no decreciente por sus tiempos de
	 * procesamiento
	 */
	public static int[] ordenaTodasLasTareasPorTiempoProcesamiento() {
		return ordenaTareas(ValoresInstanciaAsignacionTrabajos.duracion.clone());
	}

	private static int[] ordenaTareas(int[] copia) {
		int[] tareasOrdenadas = new int[ValoresInstanciaAsignacionTrabajos.duracion.length];
		for (int i = 0; i < tareasOrdenadas.length; i++)
			tareasOrdenadas[i] = i;
		QuickSort.sort(0, copia.length - 1, copia, tareasOrdenadas);
		return tareasOrdenadas;
	}

}
