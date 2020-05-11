package results;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TopKFreqWordsResults implements ResultsI {

    String outputFile;
    FileWriter fileWriter;
    public List<List<String>> wordsList;

    public TopKFreqWordsResults(String outputFileName) {

        outputFile = outputFileName;
        wordsList = new ArrayList<>();
    }

    @Override
    public void sendResults(List<String> wordsListIn){

        wordsList.add(wordsListIn);
    }

    @Override
    public void writeToFile() {
        try {
            fileWriter = new FileWriter(outputFile,false);
            for (List<String> subList : wordsList){
                fileWriter.write(Arrays.toString(subList.toArray())+ "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
