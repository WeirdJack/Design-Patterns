package numberPlay.observer;

import numberPlay.filter.FilterI;
import numberPlay.util.PersisterI;
import numberPlay.util.NumberPeaksResultsI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Processes the input number and checks for peaks, gets notified by 3 events INTEGER_EVENT,
 * FLOAT_EVENT, PROCESSING_COMPLETE_EVENT
 *
 * @param  integerFilter  INTEGER_EVENT filter
 * @param  floatFilter FLOAT_EVENT filter
 * @param  processingFilter PROCESSING_COMPLETE_EVENT filter
 * @param numberPeaksResultsIn results object
 * @exception IOException for file input error
 * @exception NullPointerException for null number
 */

public class NumberPeaksObserver implements ObserverI {

    private static List<FilterI> numberPeaksFiltersList = new ArrayList<>();
    List<Double> integerList = new ArrayList<>();
    NumberPeaksResultsI numberPeaksResults;
    PersisterI numberPeaksPersister;

    public NumberPeaksObserver(FilterI integerFilter, FilterI floatFilter, FilterI processingFilter, NumberPeaksResultsI numberPeaksResultsIn) {

        numberPeaksFiltersList.add(integerFilter);
        numberPeaksFiltersList.add(floatFilter);
        numberPeaksFiltersList.add(processingFilter);
        numberPeaksResults = numberPeaksResultsIn;
        numberPeaksPersister = (PersisterI) numberPeaksResultsIn;
    }

    public static List<FilterI> getNumberPeaksFiltersList(){

        return numberPeaksFiltersList;

    }

    @Override
    public void update(Object numberIn) throws IOException {

        try{
            Double lastNumber = Double.valueOf(numberIn.toString());
            if (integerList.size() > 0){
                lastNumber = integerList.get(integerList.size() - 1);
            }


            integerList.add(Double.valueOf(numberIn.toString()));

            if (integerList.size() > 1){

                if (Double.valueOf(numberIn.toString()) < lastNumber){

                    numberPeaksResults.store(lastNumber);
                }
            }
        }catch(NullPointerException npe){
            numberPeaksPersister.writeToFile();
            numberPeaksPersister.close();
        }
    }
}
