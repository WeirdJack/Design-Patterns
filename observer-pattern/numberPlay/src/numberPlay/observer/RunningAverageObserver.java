package numberPlay.observer;

import numberPlay.filter.FilterI;
import numberPlay.util.PersisterI;
import numberPlay.util.RunningAverageData;
import numberPlay.util.RunningAverageResultsI;
import numberPlay.util.TopKNumbersData;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Processes the input number and calculates running average, gets notified by 1 events INTEGER_EVENT,
 * PROCESSING_COMPLETE_EVENT
 *
 * @param  integerFilter  INTEGER_EVENT filter
 * @param  processingFilter PROCESSING_COMPLETE_EVENT filter
 * @param runningAverageResultsIn results object
 * @exception IOException for file input error
 * @exception NullPointerException for null number
 */
public class RunningAverageObserver implements ObserverI {

    public static List<FilterI> runningAverageFiltersList = new ArrayList<>();
    RunningAverageResultsI runningAverageResults;
    PersisterI runningAveragePersister;
    PriorityQueue<Integer> pQueue = new PriorityQueue<Integer>();
    List<Integer> list;
    float total;


    public RunningAverageObserver(FilterI integerFilter, FilterI processingFilter, RunningAverageResultsI runningAverageResultsIn) {

        runningAverageFiltersList.add(integerFilter);
        runningAverageFiltersList.add(processingFilter);
        runningAverageResults = runningAverageResultsIn;
        runningAveragePersister = (PersisterI) runningAverageResultsIn;
    }

    public static List<FilterI> getRunningAverageFiltersList(){

        return runningAverageFiltersList;
    }

    @Override
    public void update(Object numberIn) throws IOException {

        try {
            BigDecimal runningAverage;
            total = 0;
            pQueue.add(Integer.parseInt(numberIn.toString()));
            if (pQueue.size() > RunningAverageData.windowSize){
                pQueue.poll();
            }
            list = new ArrayList<>(pQueue);
            for (int i : list){
                total += i;
            }
            runningAverage = new BigDecimal(total/list.size()).setScale(2, BigDecimal.ROUND_DOWN);

            runningAverageResults.store(runningAverage.doubleValue());
        }catch (NullPointerException npe){
            runningAveragePersister.writeToFile();
            runningAveragePersister.close();
        }
    }
}
