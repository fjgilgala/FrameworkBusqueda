package framework.abierta;

import framework.Estado;

public interface Tree {

	void insert(Estado x);

	void remove(Estado x);

	Estado findMin();
}
