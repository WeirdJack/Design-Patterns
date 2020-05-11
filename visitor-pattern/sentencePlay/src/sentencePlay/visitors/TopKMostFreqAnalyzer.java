package visitors;

import elements.ElementI;
import elements.MyElement;
import results.ResultsI;

import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Map;
import java.util.ArrayList;
import java.util.Comparator;

public class TopKMostFreqAnalyzer implements VisitorI {

    int topKSize;
    ResultsI topKFreqWordsResults;

    public TopKMostFreqAnalyzer(String topK, ResultsI topKFreqWordsResultsIn) {

        topKSize = Integer.valueOf(topK);
        topKFreqWordsResults = topKFreqWordsResultsIn;
    }

    @Override
    public void visit(ElementI myElement) {

        calculateTopKWords(((MyElement) myElement).currentSentence.toLowerCase());
    }

    /**
     * calculates the Top K most frequent words in a sentence and
     * sends those words to the Results interface
     *
     * @param sentence
     */
    public void calculateTopKWords(String sentence){

        Queue<Word> pq = new PriorityQueue<Word>(new WordComparator());
        HashMap<String, Integer> map = new HashMap<>();
        List<String> list;

        for (String word : sentence.split("[,-/ ]")){

            if (map.containsKey(word) && !word.equals("")){
                map.put(word, map.get(word) + 1);
            } else {
                map.put(word, 1);
            }
        }

        for (Map.Entry<String, Integer> entry : map.entrySet())
        {
            pq.add(new Word(entry.getKey(), entry.getValue()));
        }

        list = new ArrayList<String>();
        Word p = null;
        while ((p = pq.poll()) != null) { // poll() returns null if queue gets empty.
            if (topKSize == list.size()){break;}
            list.add(p.toString());
        }

        System.out.println(list);

        topKFreqWordsResults.sendResults(list);
    }

    class Word {
        private String word;
        private int frequency;

        public Word(String wordIn, int frequencyIn) {
            word = wordIn;
            frequency = frequencyIn;
        }

        public int getfrequency() {
            return frequency;
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Word)) return false;
            Word oWord = (Word) o;
            return oWord.word.equalsIgnoreCase(word) && (oWord.frequency == frequency);
        }

        @Override
        public String toString() {
            return word;
        }
    }

    class WordComparator implements Comparator<Word> {
        @Override
        public int compare(Word p1, Word p2) {
            int p1Frequency = p1.getfrequency();
            int p2Frequency = p2.getfrequency();
            if (p1Frequency == p2Frequency) {
                return 0;
            }
            if (p1Frequency < p2Frequency) {
                return 1; // higher frequency => higher priority.
            }
            return -1;
        }
    }
}
