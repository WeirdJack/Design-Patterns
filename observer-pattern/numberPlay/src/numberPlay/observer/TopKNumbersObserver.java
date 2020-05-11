package numberPlay.observer;

import numberPlay.filter.FilterI;
import numberPlay.util.PersisterI;
import numberPlay.util.TopKNumbersData;
import numberPlay.util.TopKNumbersResultsI;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

/**
 * Processes the input number and checks for top K numbers, gets notified by 3 events INTEGER_EVENT,
 * FLOAT_EVENT, PROCESSING_COMPLETE_EVENT
 *
 * @param  integerFilter  INTEGER_EVENT filter
 * @param  floatFilter FLOAT_EVENT filter
 * @param  processingFilter PROCESSING_COMPLETE_EVENT filter
 * @param topKNumbersResultsIn results object
 * @exception IOException for file input error
 * @exception NullPointerException for null number
 */
public class TopKNumbersObserver implements ObserverI {

    public static List<FilterI> topKNumbersFiltersList = new ArrayList<>();
    TopKNumbersResultsI topKNumbersResults;
    PersisterI topKNumbersPersister;
    BigDecimal topKArray[] = new BigDecimal[3];
    Double number = 0.0;
    PriorityQueue<Double> pQueue = new PriorityQueue<Double>();
    List<Double> list;

    public TopKNumbersObserver(FilterI integerFilter, FilterI floatFilter, FilterI processingFilter, TopKNumbersResultsI topKNumbersResultsIn) throws IOException {

        topKNumbersFiltersList.add(integerFilter);
        topKNumbersFiltersList.add(floatFilter);
        topKNumbersFiltersList.add(processingFilter);
        topKNumbersResults = topKNumbersResultsIn;
        topKNumbersPersister = (PersisterI) topKNumbersResultsIn;
    }

    public static List<FilterI> getTopKNumbersFiltersList(){

        return topKNumbersFiltersList;
    }

    @Override
    public void update(Object numberIn) throws IOException {

        try {
            pQueue.add(Double.parseDouble(numberIn.toString()));
            if (pQueue.size() > TopKNumbersData.topK){
                pQueue.poll();
            }
            list = new ArrayList<>(pQueue);

            topKNumbersResults.store(list);
        }catch (NullPointerException npe){

            topKNumbersPersister.writeToFile();
            topKNumbersPersister.close();
        }

    }
}
