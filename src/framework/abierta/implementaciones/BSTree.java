package framework.abierta.implementaciones;

import framework.Estado;
import framework.abierta.AbstractTree;
import framework.abierta.NodeTree;

public class BSTree extends AbstractTree<BinaryNode> {

	@Override
	public Estado findMin() {
		return elementAt(findMin(root));
	}

	protected Estado elementAt(BinaryNode t) {
		return t == null ? null : t.element;
	}

	@Override
	protected BinaryNode insert(Estado x, BinaryNode t) {
		if (t == null)
			t = new BinaryNode(x, null, null);
		else if (x.compareTo(t.element) < 0)
			t.left = insert(x, t.left);
		else
			t.right = insert(x, t.right);
		return t;
	}

	@Override
	protected BinaryNode remove(Estado x, BinaryNode t) {
		if (t == null)
			return t;
		if (x.compareTo(t.element) < 0)
			t.left = remove(x, t.left);
		else if (x.compareTo(t.element) > 0)
			t.right = remove(x, t.right);
		else
			t = (t.left != null) ? t.left : t.right;
		return t;
	}

	private BinaryNode findMin(BinaryNode t) {
		if (t == null)
			return null;
		else if (t.left == null)
			return t;
		return findMin(t.left);
	}

	@Override
	public String toString() {
		return "BSTree";
	}
}

class BinaryNode extends NodeTree<BinaryNode> {
	BinaryNode(Estado theElement) {
		this(theElement, null, null);
	}

	BinaryNode(Estado theElement, BinaryNode lt, BinaryNode rt) {
		super(theElement, lt, rt);

	}
}