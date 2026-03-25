package org.example.algorithm;

public class SelectionSort implements SortAlgorithm {

    @Override
    public void sort(int[] array, SortObserver observer) {

        int n = array.length;

        for (int i = 0; i < n - 1; i++) {

            int minIndex = i;

            for (int j = i + 1; j < n; j++) {
                // подсветка сравниваемых элементов
                observer.update(array.clone(), j, minIndex);

                if (array[j] < array[minIndex]) {
                    minIndex = j;
                    observer.update(array.clone(), j, minIndex);
                }
            }

            if (minIndex != i) {
                int temp = array[i];
                array[i] = array[minIndex];
                array[minIndex] = temp;

                observer.update(array.clone(), i, minIndex);
            }
        }
    }
}