package monopoly.util;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Results defines a class that intend to store the result entry when processing
 * a stream of data.
 */
public class
Results {

    public static List<String> list;
    FileWriter fileWriter;

    public Results() {

        list = new ArrayList<>();
    }

    /**
     * writes output to file
     *
     * @param  outputFileName  output file name
     */
    public void writeToFile(String outputFileName) {

        try {
            fileWriter = new FileWriter(outputFileName);
            for (String subList : list){
                fileWriter.write(subList + "\n");
                System.out.println(subList);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close() throws IOException {

        fileWriter.close();
    }
}

