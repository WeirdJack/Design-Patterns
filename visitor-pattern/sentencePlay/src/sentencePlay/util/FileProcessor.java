package util;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;

public final class FileProcessor{
    private BufferedReader reader;
    private String line;

    public FileProcessor(String inputFilePath)
            throws InvalidPathException, SecurityException, IOException {

        if (!Files.exists(Paths.get(inputFilePath))) {
            throw new FileNotFoundException("invalid input file or input file in incorrect location");
        }

        reader = new BufferedReader(new FileReader(new File(inputFilePath)));
        line = reader.readLine();
    }

    public String poll() throws IOException {
        if (null == line) return null;

        String newValue = line.trim();
        line = reader.readLine();
        return newValue;
    }

    public void close() throws IOException {
        try {
            reader.close();
            line = null;
        } catch (IOException e) {
            throw new IOException("failed to close file", e);
        }
    }
}
