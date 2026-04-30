package org.example.storage;
//хранение данных в ОЗУ

public interface DataStorage {
    int[] loadData(String path); //путь к файлу и возвращаем массив
    void saveData(String path, int[] data);
}
