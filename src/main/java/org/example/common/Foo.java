package org.example.common;

import org.example.interfaces.Action;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Foo {
    private final ArrayList<BufferedReader> fileReaders = new ArrayList<>();
    private Action action;

    public Foo(List<String> filenames) {
        for (String filename : filenames) {
            this.addNewFilename(filename);
        }
    }

    public void addNewFilename(String filename) {
        try {
            fileReaders.add(new BufferedReader(new FileReader(filename)));
        } catch (FileNotFoundException e) {
            System.out.printf("File not found: %s%n", filename);
        }
    }

    public BufferedReader getFileReader(int index) {
        return this.fileReaders.get(index);
    }

    public Foo setAction(Action action) {
        this.action = action;
        return this;
    }

    public Foo doMyJob() throws IOException {
        for (BufferedReader reader : this.fileReaders) {
            while (true) {
                String line = reader.readLine();
                if (line == null) break;
                action.invoke(line);
            }
            reader.close();
        }
        return this;
    }
}
