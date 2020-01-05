package kMean;
import java.util.ArrayList;

import data.Iris;

public class KMeanCluster {
	private ArrayList<Iris> ClusterMembers;
	private double[] center;
	
	public KMeanCluster(double[] center) {
		this.ClusterMembers = new ArrayList<>();
		this.center = center;
	}

	public void addMember(Iris memberToAdd) {
		ClusterMembers.add(memberToAdd);
		//updateCenter();
	}

    public void updateCenter() {
        float sepal_length_mean = 0;
        float sepal_width_mean = 0;
        float petal_length_mean = 0;
        float petal_width_mean = 0;

	    for(Iris flower : ClusterMembers) {
	        sepal_length_mean += flower.getSepal_Length();
	        sepal_width_mean += flower.getSepal_Width();
	        petal_length_mean += flower.getPetal_Length();
	        petal_width_mean += flower.getPetal_Width();
        }

        center[0] = sepal_length_mean / ClusterMembers.size();
	    center[1] = sepal_width_mean / ClusterMembers.size();
	    center[2] = petal_length_mean / ClusterMembers.size();
	    center[3] = petal_width_mean / ClusterMembers.size();
    }

    public ArrayList<Iris> getClusterMembers() {
		return ClusterMembers;
	}

	public double[] getCenter() {
	    return center;
    }

    public String getCenterValues() {
		StringBuilder values = new StringBuilder();

		for(double value : center) {
			values.append(String.format("%.2f", value)).append(" ");
		}

		return values.toString();
	}


	@Override
	public String toString() {
		String toPrintString =
				"----------------------------------- CLUSTER START ------------------------------------------" +
				System.getProperty("line.separator");
		
		for(Iris i : this.ClusterMembers) {
			toPrintString += i.toString() + System.getProperty("line.separator");
		}
		toPrintString +=
				"------------------------------------ CLUSTER END -------------------------------------------" +
				System.getProperty("line.separator");
		
		return toPrintString;
	}
}