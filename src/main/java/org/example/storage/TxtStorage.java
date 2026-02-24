package org.example.storage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TxtStorage implements DataStorage {

    @Override
    public int[] loadData(String path) {

        List<Integer> numbers = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {

            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.isEmpty()) {
                    numbers.add(Integer.parseInt(line.trim()));
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        int[] result = new int[numbers.size()];
        for (int i = 0; i < numbers.size(); i++) {
            result[i] = numbers.get(i);
        }

        return result;
    }

    @Override
    public void saveData(String path, int[] data) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {

            for (int num : data) {
                writer.write(String.valueOf(num));
                writer.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}