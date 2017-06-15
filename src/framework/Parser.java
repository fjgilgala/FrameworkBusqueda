package framework;

/**
 * Encargada de cargar e interpretar el archivo de instancia.
 * 
 * Se debe redefinir para que cargue los datos iniciales del problema a
 * resolver.
 * 
 * @author fran
 *
 */
public interface Parser {

	/**
	 * Método de interpretar el fichero, que además tendrá que generar el estado
	 * inicial y asignar el heuristico: ValoresInstancia.heuristico =
	 * heuristico; se puede usar la clase abstracta ParserBasic para no tener
	 * que asignar ValoresInstancia.heuristico = heuristico;
	 * 
	 * @param fichero
	 * @param heuristico
	 * @return
	 */
	public Estado run(String fichero, Heuristico heuristico);

}
