package results;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SpellCheckResults implements ResultsI {

    String outputFile;
    FileWriter fileWriter;
    List<List<String>> correctedWordsList;

    public SpellCheckResults(String outputFileName) {

        outputFile = outputFileName;
        correctedWordsList = new ArrayList<>();
    }

    @Override
    public void sendResults(List<String> correctedWordsListIn){

        correctedWordsList.add(correctedWordsListIn);
    }

    @Override
    public void writeToFile() {

        try{
            fileWriter = new FileWriter(outputFile, false);
            for (List<String> subList : correctedWordsList){
                fileWriter.write(Arrays.toString(subList.toArray()) + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
