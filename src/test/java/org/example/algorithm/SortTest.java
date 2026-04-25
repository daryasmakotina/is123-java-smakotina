package org.example.algorithm;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.example.data.TxtDataLoader;
import org.example.data.CsvDataLoader;
import org.example.data.JsonDataLoader;

public class SortTest {

    @Test
    void testSelectionSort() {
        int[] data = {9, 1, 5};

        SortAlgorithm algo = new SelectionSort();

        algo.sort(data, new SortObserver() {
            @Override
            public void update(int[] array, int i, int j) {}
        });

        assertArrayEquals(new int[]{1, 5, 9}, data);
    }

    @Test
    void testTxtLoader() {
        TxtDataLoader loader = new TxtDataLoader();

        int[] result = loader.load("src/test/resources/test.txt");

        assertArrayEquals(new int[]{5, 3, 1}, result);
    }
    @Test
    void testCsvLoader() {
        CsvDataLoader loader = new CsvDataLoader();

        int[] result = loader.load("src/test/resources/test.csv");

        assertArrayEquals(new int[]{5, 3, 1}, result);
    }

    @Test
    void testJsonLoader() {

        JsonDataLoader loader = new JsonDataLoader();

        String path = getClass()
                .getClassLoader()
                .getResource("test.json")
                .getPath();

        int[] result = loader.load(path);

        assertArrayEquals(new int[]{5, 3, 1}, result);
    }
}


