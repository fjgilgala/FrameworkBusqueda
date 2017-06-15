package framework;

import java.util.List;

/**
 * Estado genérico que deberá ser implementado por la clase que represente el
 * estado concreto del problema a resolver
 * 
 * @author fran
 *
 */
public interface Estado extends Problema, Comparable<Estado> {

	public int f();

	public int h();

	/**
	 * Es su definición se debe incluir una llamada a @Metrica.genera() cada vez
	 * que se genere un nuevo estado sucesor.
	 * 
	 * @return devuelve una lista de estados sucesores
	 */
	public List<Estado> expandir();

	public int compareTo(Estado estado);

	public String resultado();

	public boolean esSolucion();

	public Estado clone();

	/**
	 * A través de un algoritmo voraz se llega a una solución completa del
	 * problema. Se debe implementar si se quieren utilizar los algoritmos
	 * Look_A_Head.
	 * 
	 * En caso de no utilizarse se debe implementar para que devuelva el estado
	 * inicial.
	 * 
	 * @return devuelve un estado completo, o en su defecto el estado inicial.
	 */
	public Estado solucionVoraz();

	public int getExpandidosMaximo();

}
