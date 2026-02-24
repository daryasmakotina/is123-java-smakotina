package org.example.storage;

public interface DataStorage {
    int[] loadData(String path);
    void saveData(String path, int[] data);
}
