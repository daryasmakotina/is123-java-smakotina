package org.example.storage;

import java.io.*;

public class CsvStorage implements DataStorage {

    @Override
    public int[] loadData(String path) {

        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {

            String line = reader.readLine();
            if (line == null) return new int[0];

            String[] parts = line.split(",");
            int[] result = new int[parts.length];

            for (int i = 0; i < parts.length; i++) {
                result[i] = Integer.parseInt(parts[i].trim());
            }

            return result;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return new int[0];
    }

    @Override
    public void saveData(String path, int[] data) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {

            for (int i = 0; i < data.length; i++) {
                writer.write(String.valueOf(data[i]));
                if (i < data.length - 1) {
                    writer.write(",");
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
