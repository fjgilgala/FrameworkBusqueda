package asignaciontrabajos.util;

public class QuickSort {

	public static void sort(int lowerIndex, int higherIndex, int[] copiaDuracion, int[] copiaIndices) {
		int i = lowerIndex;
		int j = higherIndex;
		int pivot = copiaDuracion[lowerIndex + (higherIndex - lowerIndex) / 2];
		while (i <= j) {
			while (copiaDuracion[i] < pivot)
				i++;
			while (copiaDuracion[j] > pivot)
				j--;
			if (i <= j) {
				exchangeNumbers(i, j, copiaDuracion, copiaIndices);
				i++;
				j--;
			}
		}
		if (lowerIndex < j)
			sort(lowerIndex, j, copiaDuracion, copiaIndices);
		if (i < higherIndex)
			sort(i, higherIndex, copiaDuracion, copiaIndices);
	}

	private static void exchangeNumbers(int i, int j, int[] copiaDuracion, int[] copiaIndices) {
		int temp = copiaDuracion[i];
		copiaDuracion[i] = copiaDuracion[j];
		copiaDuracion[j] = temp;
		temp = copiaIndices[i];
		copiaIndices[i] = copiaIndices[j];
		copiaIndices[j] = temp;
	}
}