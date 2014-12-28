package com.vci;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

public class CoordsFileReader {
    public static void main(String[] args) throws Exception {

        // The name of the file to open.
        String fileName = "/home/pratik/Desktop/coords.txt";

        // This will reference one line at a time
        String line = null;

        FileReader fileReader = new FileReader(fileName);

        // Always wrap FileReader in BufferedReader.
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String[] split=null;
        while ((line = bufferedReader.readLine()) != null) {
            System.out.println(line);
            split = line.split("#");
        }


        List<JSONObject> jsonObjects = new ArrayList<JSONObject>();
        for (int i = 0; i < split.length; i++) {
            String[] split2 = split[i].split(",");
            JSONObject e = new JSONObject();
            e.put("lon", Double.parseDouble(split2[0]));
            e.put("lat", Double.parseDouble(split2[1]));
            jsonObjects.add(e);
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("coords", jsonObjects.toString());

        // Always close files.
        bufferedReader.close();

        writeData("orissa", jsonObject.toString());

    }

    private static void writeData(String cityName, String response) {
        Writer writer = null;
        try {
            String name = "/home/pratik/Desktop/" + cityName;
            File file = new File(name);
            file.createNewFile();
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(name), "utf-8"));
            writer.write(response);
        } catch (IOException ex) {
        } finally {
            try {
                writer.close();
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
    }
}
