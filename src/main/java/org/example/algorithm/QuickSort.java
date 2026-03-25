package org.example.algorithm;

public class QuickSort implements SortAlgorithm {

    @Override
    public void sort(int[] array, SortObserver observer) {
        quickSort(array, 0, array.length - 1, observer);
    }

    private void quickSort(int[] array, int low, int high, SortObserver observer) {

        if (low < high) {
            int pi = partition(array, low, high, observer);
            quickSort(array, low, pi - 1, observer);
            quickSort(array, pi + 1, high, observer);
        }
    }

    private int partition(int[] array, int low, int high, SortObserver observer) {

        int pivot = array[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {

            // подсветка сравниваемых элементов
            observer.update(array.clone(), j, high);

            if (array[j] < pivot) {
                i++;
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;

                observer.update(array.clone(), i, j);
            }
        }

        int temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;

        observer.update(array.clone(), i + 1, high);

        return i + 1;
    }
}