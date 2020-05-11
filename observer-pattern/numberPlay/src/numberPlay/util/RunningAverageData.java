package numberPlay.util;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RunningAverageData implements PersisterI, RunningAverageResultsI{

	String outputFilePath;
	public static int windowSize;
	List<Double> numberSet = new ArrayList<>();
	FileWriter fileWriter;

	public RunningAverageData(String windowSizeIn, String outputFilePathIn){

		outputFilePath = outputFilePathIn;
		windowSize = Integer.parseInt(windowSizeIn);
	}

	@Override
	public void store(Double d) {
		numberSet.add(d);
	}

	@Override
	public void writeToFile() {

		try {
			fileWriter = new FileWriter(outputFilePath);
			for (Double d: numberSet){
				fileWriter.write(String.valueOf(d) + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void close() throws IOException {

		fileWriter.close();
	}
}
