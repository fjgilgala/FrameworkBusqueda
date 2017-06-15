package framework;

/**
 * Interfaz genérica que deben implementar todas estructuras que se quieran
 * utilizar para implementar las operaciones de ABIERTA
 * 
 * @author fran
 *
 */
public interface ABIERTA {

	public void añadir(Estado nodo);

	public Estado primero();

	public boolean vacia();

	public int estimacionMejor();

	public void limpia();

	public String toString();
}
