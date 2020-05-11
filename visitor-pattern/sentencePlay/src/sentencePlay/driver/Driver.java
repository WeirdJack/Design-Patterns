package driver;

import elements.ElementI;
import elements.MyArrayList;
import results.ResultsI;
import results.SpellCheckResults;
import results.TopKFreqWordsResults;
import util.FileProcessor;
import validator.ValidatorUtil;
import visitors.SpellCheckAnalyzer;
import visitors.TopKMostFreqAnalyzer;
import visitors.VisitorI;

/**
 * @author Bhargav Choudhury
 */
public class Driver {

    private static void runAnalysis(FileProcessor fileProcessor, VisitorI... visitors) {

        ElementI myArrayList = new MyArrayList.Builder()
                .withFileProcessor(fileProcessor)
                .build();

        for (VisitorI visitor : visitors) {
            myArrayList.accept(visitor);
        }
    }

    private static void persistResults(ResultsI... analysisResults) {
        for (ResultsI results : analysisResults) {
            results.writeToFile();
        }
    }

    public static void main(String[] args) throws Exception {

        /*
         * As the build.xml specifies the arguments as argX, in case the
         * argument value is not given java takes the default value specified in
         * build.xml. To avoid that, below condition is used
         * FIXME Refactor commandline validation using the validation design taught in class.
         */
        final int REQUIRED_NUMBER_OF_ARGS = 5;
        try {
            if ((args.length != REQUIRED_NUMBER_OF_ARGS) ||
                    (args[0].equals("${input}")) ||
                    (args[1].equals("${acceptableWordsFile}")) ||
                    (args[2].equals("${k}")) ||
                    (args[3].equals("${topKOutputFile}")) ||
                    (args[4].equals("${spellCheckOutputFile}"))) {

                System.err.printf("Error: Incorrect number of arguments. Program accepts %d arguments.", REQUIRED_NUMBER_OF_ARGS);
                System.exit(0);
            }
        }catch (NumberFormatException nfe){
            nfe.printStackTrace();
        }catch (NullPointerException npe){
            npe.printStackTrace();
        }

        new ValidatorUtil(args);

        FileProcessor fileProcessor = new FileProcessor(args[0]);

        ResultsI topKFreqWordsResults = new TopKFreqWordsResults(args[3]);
        VisitorI topKMostFreqAnalyzer = new TopKMostFreqAnalyzer(args[2], topKFreqWordsResults);

        ResultsI spellCheckResults = new SpellCheckResults(args[4]);
        VisitorI spellCheckAnalyzer = new SpellCheckAnalyzer(args[1], spellCheckResults);

        runAnalysis(fileProcessor, topKMostFreqAnalyzer, spellCheckAnalyzer);

        persistResults(topKFreqWordsResults, spellCheckResults);
    }
}