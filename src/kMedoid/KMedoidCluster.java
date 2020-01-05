package kMedoid;
import java.util.ArrayList;
import data.Iris;

public class KMedoidCluster {

public ArrayList<Iris> ClusterMembers;
public Iris Medoid;
	
public KMedoidCluster(Iris medoid) {
	this.ClusterMembers = new ArrayList<>();
	this.Medoid = medoid;
}
	
	@Override
	public String toString() {
		String toPrintString =
				"----------------------------------- CLUSTER START ------------------------------------------" +
						System.getProperty("line.separator");

		toPrintString += "Medoid: "+this.Medoid.toString() + System.getProperty("line.separator");
		for(Iris i : this.ClusterMembers) {
			toPrintString += i.toString() + System.getProperty("line.separator");
		}

		toPrintString +=
				"------------------------------------ CLUSTER END --------------------------------------------" +
						System.getProperty("line.separator");
		
		return toPrintString;
	}
}