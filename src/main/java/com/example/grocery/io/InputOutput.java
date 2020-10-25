package com.example.grocery.io;

import org.springframework.stereotype.Service;

import java.io.*;

@Service
public class InputOutput {

    private final PrintStream out;
    private final BufferedReader reader;

    InputOutput(InputStream in, PrintStream out) {
        this.reader = new BufferedReader(new InputStreamReader(in));
        this.out = out;
    }

    public InputOutput() {
        this(System.in, System.out);
    }

    public String readLine(String format, Object... args) throws IOException {
        System.out.printf(format, args);
        return reader.readLine();
    }

    public InputOutput printf(String format, Object... args) {
        System.out.printf(format, args);
        return this;
    }
}
