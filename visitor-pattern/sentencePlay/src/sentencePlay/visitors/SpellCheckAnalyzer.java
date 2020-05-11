package visitors;

import elements.ElementI;
import elements.MyElement;
import results.ResultsI;
import util.FileProcessor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SpellCheckAnalyzer implements VisitorI {

    String acceptableWordsInputFile;
    String acceptableWord;
    ResultsI spellCheckResults;
    FileProcessor fileProcessor;
    List<String> correctedWords;

    public SpellCheckAnalyzer(String acceptableWordsInputFilePath, ResultsI spellCheckResultsIn) {

        acceptableWordsInputFile = acceptableWordsInputFilePath;
        spellCheckResults = spellCheckResultsIn;
    }

    @Override
    public void visit(ElementI myElement) {

        correctedWords = new ArrayList<>();
        for (String word : ((MyElement) myElement).currentSentence.split("[-,/ ]")){

            spellCheck(word.trim());
        }

    }

    /**
     * Processes the acceptable file and does a spell check on words with length greater than 2
     * and send the corrected words to the Results interface
     *
     * @param  input word
     * @exception IOException for file input error
     */
    public void spellCheck(String word) {

        if (word.length() > 2){
            try {
                fileProcessor = new FileProcessor(acceptableWordsInputFile);
                acceptableWord = fileProcessor.poll();
                while (acceptableWord != null){

                    if (word.length() == acceptableWord.length()){

                        int k = 0;
                        for (int i = 0; i < word.length(); i++){

                            if (word.charAt(i) != acceptableWord.charAt(i)){
                                k++;
                            }

                            if (k > 1){break;}
                        }

                        if (1 == k){

                            correctedWords.add(word + "::[" + acceptableWord + "]");
                            System.out.println(correctedWords);
                            spellCheckResults.sendResults(correctedWords);
                            correctedWords = new ArrayList<>();
                        }
                    }
                    acceptableWord = fileProcessor.poll();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
