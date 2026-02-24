package org.example.factory;

import org.example.data.*;

public class DataLoaderFactory {

    public static DataLoader create(String filePath) {

        if (filePath.endsWith(".json")) {
            return new JsonDataLoader();
        }

        if (filePath.endsWith(".csv")) {
            return new CsvDataLoader();
        }

        if (filePath.endsWith(".txt")) {
            return new TxtDataLoader();
        }

        throw new IllegalArgumentException("Неподдерживаемый формат файла: " + filePath);
    }
}