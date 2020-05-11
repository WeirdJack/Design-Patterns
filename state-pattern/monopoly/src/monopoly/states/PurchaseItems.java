package monopoly.states;

import monopoly.util.FileProcessor;
import monopoly.util.Metrics;
import monopoly.util.Results;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;

public class PurchaseItems {

    String item, input;
    HashMap<String, String> availableItems = new HashMap<>();
    FileProcessor inputFileProcessor, availableItemsFileProcessor;
    SpendingStateI basic, extravagant, luxurious, currentState;
    BigDecimal currentRunningAverage;
    Metrics metrics = new Metrics();
    Results results = new Results();

    public PurchaseItems(){

        basic = new Basic();
        extravagant = new Extravagant();
        luxurious = new Luxurious();
        currentState = basic;
    }

    /**
     * Processes all the available items
     *
     * @param  availableItemsFilename  available items file name
     * @exception IOException for file input error
     */
    public void setGoodsList(String availableItemsFilename) throws IOException {

        availableItemsFileProcessor = new FileProcessor(availableItemsFilename);
        item = availableItemsFileProcessor.poll();
        while (item != null){

            availableItems.put(item.split(":")[1], item.split(":")[0]);
            item = availableItemsFileProcessor.poll();

        }
    }

    /**
     * Processes input file one by one, sets current state according to the running average
     *
     * @param  inputFilename  input file name
     * @param  runningWindowSize  running window size
     * @exception IOException for file input error
     */
    public void startPurchase(String inputFilename, String runningWindowSize) throws IOException {

        inputFileProcessor = new FileProcessor(inputFilename);
        input = inputFileProcessor.poll();
        while (input != null){


            if (input.split(":")[0].equals("money")){
                if (Integer.parseInt(input.split(":")[1]) > 0){
                    currentRunningAverage = metrics.calculateRunningAverage(input.split(":")[1], runningWindowSize);
                }else {
                    System.err.println("Amount be greater 0");
                }
                currentState = currentState.setCurrentState(currentRunningAverage);
            } else if(input.split(":")[0].equals("item")){

                currentState.decisionToPurchase(input.split(":")[1], availableItems);
                currentState.update();
            }

            input = inputFileProcessor.poll();
        }


    }

    /**
     * writes results to the output file
     *
     * @param  outputFile  output file name
     */
    public void getResults(String outputFile) throws IOException {

        results.writeToFile(outputFile);
        results.close();
    }
}
