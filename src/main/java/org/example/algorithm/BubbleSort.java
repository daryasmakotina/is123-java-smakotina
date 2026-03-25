package org.example.algorithm;

public class BubbleSort implements SortAlgorithm {

    @Override
    public void sort(int[] array, SortObserver observer) {

        int n = array.length;

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {

                observer.update(array.clone(), j, j + 1);

                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;

                    observer.update(array.clone(), j, j + 1);
                }
            }
        }
    }
}