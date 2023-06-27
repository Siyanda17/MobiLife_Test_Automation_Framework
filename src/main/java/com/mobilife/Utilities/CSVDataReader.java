package com.mobilife.Utilities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CSVDataReader {
    private String csvFilePath;
    private String delimiter;

    public CSVDataReader(String csvFilePath, String delimiter) {
        this.csvFilePath = csvFilePath;
        this.delimiter = delimiter;
    }

    public Map<String, String> getDataMap() throws IOException {
        Map<String, String> dataMap = new HashMap<>();

        BufferedReader br = new BufferedReader(new FileReader(csvFilePath));
        String line;
        while ((line = br.readLine()) != null) {
            String[] data = line.split(delimiter);
            if (data.length >= 2) {
                String key = data[0].trim();
                String value = data[1].trim();
                dataMap.put(key, value);
            }
        }

        br.close();
        return dataMap;
    }
}

