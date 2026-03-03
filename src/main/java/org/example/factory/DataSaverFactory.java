package org.example.factory;

import org.example.data.*;

public class DataSaverFactory {

    public static DataSaver create(String filePath) {

        if (filePath.endsWith(".json")) {
            return new JsonDataSaver();
        }

        if (filePath.endsWith(".csv")) {
            return new CsvDataSaver();
        }

        if (filePath.endsWith(".txt")) {
            return new TxtDataSaver();
        }

        throw new IllegalArgumentException("Неподдерживаемый формат файла: " + filePath);
    }
}