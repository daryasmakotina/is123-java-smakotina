package org.example.storage;

import java.io.*;
import java.util.ArrayList;

public class JsonStorage implements DataStorage {

    @Override
    public int[] loadData(String path) {

        ArrayList<Integer> list = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {

            String line = reader.readLine();
            if (line == null) return new int[0];

            // убираем [ ] и пробелы
            line = line.replace("[", "")
                    .replace("]", "")
                    .replace(" ", "");

            if (!line.isEmpty()) {
                String[] parts = line.split(",");
                for (String part : parts) {
                    list.add(Integer.parseInt(part));
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        int[] result = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }

        return result;
    }

    @Override
    public void saveData(String path, int[] data) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {

            writer.write("[");
            for (int i = 0; i < data.length; i++) {
                writer.write(String.valueOf(data[i]));
                if (i < data.length - 1) {
                    writer.write(",");
                }
            }
            writer.write("]");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}