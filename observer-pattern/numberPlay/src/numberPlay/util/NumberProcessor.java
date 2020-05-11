package numberPlay.util;

import numberPlay.driver.Driver;
import numberPlay.filter.EventFilter;
import numberPlay.subject.SubjectI;
import java.io.IOException;

public class NumberProcessor {

    private String number;
    FileProcessor fileProcessor;

    public void runNumberPlay(String inputFilePath, SubjectI subject) throws IOException {

        fileProcessor = new FileProcessor(inputFilePath);
        number = fileProcessor.poll();
        while (number != null){

            try {

                subject.notifyObservers(Integer.parseInt(number), Driver.Events.INT);
            } catch (NumberFormatException nfe){

                subject.notifyObservers(Float.parseFloat(number), Driver.Events.FLOAT);
            }
            number = fileProcessor.poll();
        }

        subject.notifyObservers(number, Driver.Events.PROCESSING_COMPLETE);
    }
}
