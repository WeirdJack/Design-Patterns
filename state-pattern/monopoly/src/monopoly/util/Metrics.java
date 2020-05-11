package monopoly.util;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Metrics {

    PriorityQueue<Integer> pQueue = new PriorityQueue<Integer>();
    List<Integer> list;
    float total;

    /**
     * Sets current state according to the running average
     *
     * @param  numberIn  current amount
     * @param  windowSize running average window size
     */
    public BigDecimal calculateRunningAverage(String numberIn, String windowSize) throws IOException {

        BigDecimal runningAverage;
        total = 0;
        pQueue.add(Integer.parseInt(numberIn));
        if (pQueue.size() > Integer.parseInt(windowSize)){
            pQueue.poll();
        }
        list = new ArrayList<>(pQueue);
        for (int i : list){
            total += i;
        }
        runningAverage = new BigDecimal(total/list.size()).setScale(2, BigDecimal.ROUND_DOWN);

        return runningAverage;
    }
}