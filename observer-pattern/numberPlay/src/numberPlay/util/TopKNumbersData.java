package numberPlay.util;


import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TopKNumbersData implements PersisterI, TopKNumbersResultsI {

	String outputFilePath = "";
	public static int topK = 0;
	List<List<Double>> topKList = new ArrayList<>();
	FileWriter fileWriter;
	List<Double> l;

	public TopKNumbersData(String topKIn, String outputFileNameIn){

		outputFilePath = outputFileNameIn;
		topK = Integer.parseInt(topKIn);
	}

	@Override
	public void store(List<Double> topKListIn) {

		l = topKListIn;
		Collections.sort(l, Collections.reverseOrder());
		topKList.add(l);
	}

	@Override
	public void writeToFile() {

		try {
			fileWriter = new FileWriter(outputFilePath);
			for (List<Double> subList : topKList){
				fileWriter.write(subList.toString() + "\n");
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
