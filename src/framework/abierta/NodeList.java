package framework.abierta;

import framework.Estado;

public class NodeList {
	public Estado elem;
	public NodeList Next;

	public NodeList(Estado o) {
		elem = o;
		Next = null;
	}
}