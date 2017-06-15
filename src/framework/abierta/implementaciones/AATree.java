package framework.abierta.implementaciones;

import framework.Estado;
import framework.abierta.AbstractTree;
import framework.abierta.NodeTree;

public class AATree extends AbstractTree<AANode> {

	private static AANode deletedNode;
	private static AANode lastNode;
	protected static AANode nullNode;

	static {
		nullNode = new AANode(null);
		nullNode.left = nullNode.right = nullNode;
		nullNode.level = 0;
	}

	public AATree() {
		root = nullNode;
	}

	@Override
	public boolean vacia() {
		return root == nullNode;
	}

	@Override
	public void limpia() {
		root = nullNode;
	}

	@Override
	public void remove(Estado x) {
		deletedNode = nullNode;
		root = remove(x, root);
	}

	@Override
	public Estado findMin() {
		if (vacia())
			return null;
		AANode ptr = root;
		while (ptr.left != nullNode)
			ptr = ptr.left;
		return ptr.element;
	}

	@Override
	protected AANode insert(Estado x, AANode t) {
		if (t == nullNode)
			t = new AANode(x, nullNode, nullNode);
		else if (x.compareTo(t.element) < 0) // !
			t.left = insert(x, t.left);
		else
			t.right = insert(x, t.right);

		t = skew(t);
		t = split(t);
		return t;
	}

	@Override
	protected AANode remove(Estado x, AANode t) {
		if (t != nullNode) {
			lastNode = t;
			if (x.compareTo(t.element) < 0) // !
				t.left = remove(x, t.left);
			else {
				deletedNode = t;
				t.right = remove(x, t.right);
			}

			if (t == lastNode) {
				if (deletedNode == nullNode || x.compareTo(deletedNode.element) != 0)
					return t;
				deletedNode.element = t.element;
				t = t.right;
			}

			else if (t.left.level < t.level - 1 || t.right.level < t.level - 1) {
				if (t.right.level > --t.level)
					t.right.level = t.level;
				t = skew(t);
				t.right = skew(t.right);
				t.right.right = skew(t.right.right);
				t = split(t);
				t.right = split(t.right);
			}
		}
		return t;
	}

	private AANode skew(AANode t) {
		if (t.left.level == t.level)
			t = rotateWithLeftChild(t);
		return t;
	}

	private AANode split(AANode t) {
		if (t.right.right.level == t.level) {
			t = rotateWithRightChild(t);
			t.level++;
		}
		return t;
	}

	@Override
	public String toString() {
		return "AATree";
	}
}

class AANode extends NodeTree<AANode> {
	public AANode(Estado theElement) {
		this(theElement, null, null);
	}

	public AANode(Estado theElement, AANode lt, AANode rt) {
		super(theElement, lt, rt);
		level = 1;
	}

	int level;
}