package numberPlay.util;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NumberPeaksData implements PersisterI, NumberPeaksResultsI{

	String outputFilePath = "";
	FileWriter myWriter;

	public NumberPeaksData(String outputFilePathIn){

		outputFilePath = outputFilePathIn;
	}

	List<Double> numberSet = new ArrayList<>();

	@Override
	public void store(Double d) {

		numberSet.add(d);
	}

	@Override
	public void writeToFile() {

		try {
			myWriter = new FileWriter(outputFilePath);
			for (Double d : numberSet){
				myWriter.write(String.valueOf(d) + "\n");
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void close() throws IOException {

		myWriter.close();
	}
}
