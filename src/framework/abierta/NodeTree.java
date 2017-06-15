package framework.abierta;

import framework.Estado;

@SuppressWarnings("rawtypes")
public abstract class NodeTree<T extends NodeTree> {

	public NodeTree(Estado theElement) {
		this(theElement, null, null);
	}

	public NodeTree(Estado theElement, T lt, T rt) {
		element = theElement;
		left = lt;
		right = rt;
	}

	public Estado element;
	public T left;
	public T right;
}