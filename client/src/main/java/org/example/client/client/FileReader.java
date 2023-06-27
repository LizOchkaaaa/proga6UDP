package org.example.client.client;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**A class that reads data from a file*/
public class FileReader {
    public ArrayList<String> readFile(String fileName) {
        ArrayList<String> linesArrayList = new ArrayList<>();
        String filepath = fileName;
        OutStream.outputIntoCLI("Name of file: '" + fileName + "'.\nFilePath: '" + filepath + "'.");
        try {
            Scanner sc = new Scanner(new File(filepath));
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                linesArrayList.add(line);
            }
            sc.close();
            OutStream.outputIntoCLI("File was readed successfully.");
            return linesArrayList;
        } catch (IOException | NullPointerException e) {
            OutStream.outputIntoCLI("Error with file, check path of the file. Check file's format: '<filename>.<FileFormat>'.`");
            return new ArrayList<>();
        }
    }

}
