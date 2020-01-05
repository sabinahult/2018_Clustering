package data;
import java.io.IOException;
import java.util.ArrayList;

public class DataLoader {
	public static ArrayList<Iris> LoadAllIrisData() {
		ArrayList<Iris> data = new ArrayList<>();
		
		try {
			String[][] dataOrig = CSVFileReader.readDataFile("resources/iris.csv",",",
					"",true);
			
			for(int i = 0; i < dataOrig.length; i++) {
				float sepal_length = Float.parseFloat(dataOrig[i][0]);
				float sepal_width = Float.parseFloat(dataOrig[i][1]);
				float petal_length = Float.parseFloat(dataOrig[i][2]);
				float petal_width = Float.parseFloat(dataOrig[i][3]);
				String iris_class = dataOrig[i][4];
				
				data.add(new Iris(sepal_length, sepal_width, petal_length, petal_width, iris_class));				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Loaded " + data.size() + " iris flowers.");
		return data;
	}
}