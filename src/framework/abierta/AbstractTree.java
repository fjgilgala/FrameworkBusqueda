package framework.abierta;

import framework.ABIERTA;
import framework.Estado;

@SuppressWarnings("rawtypes")
public abstract class AbstractTree<T extends NodeTree> implements ABIERTA, Tree {

	protected T root;

	public AbstractTree() {
		root = null;
	}

	/* ABIERTA */
	@Override
	public void añadir(Estado nodo) {
		insert(nodo);
	}

	@Override
	public Estado primero() {
		Estado mejor = findMin();
		remove(mejor);
		return mejor;
	}

	@Override
	public boolean vacia() {
		return root == null;
	}

	@Override
	public int estimacionMejor() {
		if (vacia())
			return 0;
		return findMin().f();
	}

	@Override
	public void limpia() {
		root = null;
	}

	/* Tree */
	@Override
	public void insert(Estado x) {
		root = insert(x, root);
	}

	protected abstract T insert(Estado x, T root2);

	@Override
	public void remove(Estado x) {
		root = remove(x, root);
	}

	protected abstract T remove(Estado x, T root2);

	@SuppressWarnings("unchecked")
	protected T rotateWithLeftChild(T k2) {
		T k1 = (T) k2.left;
		k2.left = k1.right;
		k1.right = k2;
		return k1;
	}

	@SuppressWarnings("unchecked")
	protected T rotateWithRightChild(T k1) {
		T k2 = (T) k1.right;
		k1.right = k2.left;
		k2.left = k1;
		return k2;
	}
}