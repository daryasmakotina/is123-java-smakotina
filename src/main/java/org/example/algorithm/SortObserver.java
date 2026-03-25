package org.example.algorithm;

public interface SortObserver {
    default void update(int[] array, int index1, int index2) {

    }

    default void update(int[] array) {
        update(array, -1, -1);
    }
}