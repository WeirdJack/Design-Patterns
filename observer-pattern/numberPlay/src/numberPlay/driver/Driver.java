package numberPlay.driver;

import numberPlay.filter.EventFilter;
import numberPlay.filter.FilterI;
import numberPlay.observer.NumberPeaksObserver;
import numberPlay.observer.ObserverI;
import numberPlay.observer.RunningAverageObserver;
import numberPlay.observer.TopKNumbersObserver;
import numberPlay.subject.EventTracker;
import numberPlay.subject.SubjectI;
import numberPlay.util.*;
import numberPlay.validation.Inputs;

import java.io.IOException;

/**
 * @author Bhargav Choudhury
 */
public class Driver {

	public enum Events{INT, FLOAT, PROCESSING_COMPLETE;}

	public static void main(String[] args) throws Exception {

		/*
		 * As the build.xml specifies the arguments as argX, in case the
		 * argument value is not given java takes the default value specified in
		 * build.xml. To avoid that, below condition is used
		 * FIXME Refactor commandline validation using the validation design taught in class.
		 */
		final int REQUIRED_NUMBER_OF_ARGS = 6;
		try {
			if ((args.length != REQUIRED_NUMBER_OF_ARGS) ||
					(args[0].equals("${inputNumStream}")) ||
					(args[1].equals("${runAvgWindowSize}")) ||
					(args[2].equals("${runAvgOutFile}")) ||
					(args[3].equals("${k}")) ||
					(args[4].equals("${topKNumOutFile}")) ||
					(args[5].equals("${numPeaksOutFile}"))) {

				System.err.printf("Error: Incorrect number of arguments. Program accepts %d arguments.", REQUIRED_NUMBER_OF_ARGS);
				System.exit(0);
			}
		}catch (NumberFormatException nfe){
			nfe.printStackTrace();
		}catch (NullPointerException npe){
			npe.printStackTrace();
		}

		new Inputs(args[0]);
		new Inputs(args[2]);
		new Inputs(args[4]);
		new Inputs(args[5]);
		new Inputs(Integer.parseInt(args[1]), Integer.parseInt(args[3]));

		// FIXME Create an instance of each of the classes implementing PersisterI and the 
		// corresponding ResultsI interface.
		// Observers use these objects to dump data to be stored and eventually persisted 
		// to the corresponding output file.

		NumberPeaksResultsI numberPeaksResults = new NumberPeaksData(args[5]);
		RunningAverageResultsI runningAverageResults = new RunningAverageData(args[1], args[2]);
		TopKNumbersResultsI topKNumbersResults = new TopKNumbersData(args[3], args[4]);

		// FIXME Instantiate the subject.
		SubjectI subject = new EventTracker();

		FilterI integerFilter = new EventFilter(Events.INT);
		FilterI floatFilter = new EventFilter(Events.FLOAT);
		FilterI processingFilter = new EventFilter(Events.PROCESSING_COMPLETE);

		// FIXME Instantiate the observers, providing the necessary filter and the results object.
		ObserverI numberPeaksObserver = new NumberPeaksObserver(integerFilter, floatFilter, processingFilter, numberPeaksResults);
		ObserverI runningAverageObserver = new RunningAverageObserver(integerFilter, processingFilter, runningAverageResults);
		ObserverI topKNumbersObserver = new TopKNumbersObserver(integerFilter, floatFilter, processingFilter, topKNumbersResults);

		// FIXME Register each observer with the subject for the necessary set of events.

		subject.registerObserver(numberPeaksObserver, NumberPeaksObserver.getNumberPeaksFiltersList());
		subject.registerObserver(runningAverageObserver, RunningAverageObserver.getRunningAverageFiltersList());
		subject.registerObserver(topKNumbersObserver, TopKNumbersObserver.getTopKNumbersFiltersList());


		// FIXME Delegate control to a separate utility class/method that provides numbers one at a time, from the FileProcessor,
		// to the subject.
		NumberProcessor numberProcessor = new NumberProcessor();
		numberProcessor.runNumberPlay(args[0], subject);
	}
}
