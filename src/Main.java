import java.util.ArrayList;
import java.util.List;

import kMean.KMeanCluster;
import kMean.KMeans;
import kMedoid.KMedoid;
import kMedoid.KMedoidCluster;
import data.*;

public class Main {
    public static void main(String[] args) {
        // first step --> load in iris data
        ArrayList<Iris> irisData = DataLoader.LoadAllIrisData();

        Normalizer norma = new Normalizer();
        ArrayList<Iris> irisNormalized = norma.zScore(irisData);
        System.out.println("Number of Iris in normalized data set: " + irisNormalized.size());

        for(int i = 0; i < 10; i++) {
            System.out.println("\n *** TEST RUN " + i + " ***");
            // second step --> do the clustering using k-means!
            ArrayList<KMeanCluster> FoundClusters_KMeans = KMeans.KMeansPartition(3, irisNormalized);

            for(KMeanCluster cluster : FoundClusters_KMeans) {
                System.out.println("\nCluster of size " + cluster.getClusterMembers().size());

                int setosa = 0;
                int versicolor = 0;
                int virginica = 0;

                List<Iris> members = cluster.getClusterMembers();
                for(Iris flower : members) {
                    if(flower.getClassLabel() == IrisClass.Iris_setosa) setosa++;
                    if(flower.getClassLabel() == IrisClass.Iris_versicolor) versicolor++;
                    if(flower.getClassLabel() == IrisClass.Iris_virginica) virginica++;
                }

                System.out.println("Cluster Center Values: " + cluster.getCenterValues());
                System.out.println("Setosa: " + setosa + " (" + ((setosa * 100) /members.size()) + "%)");
                System.out.println("Versicolor: " + versicolor + " (" + ((versicolor * 100) /members.size()) + "%)");
                System.out.println("Virginica: " + virginica + " (" + ((virginica * 100)/members.size()) + "%)");
            }
        }

        // TODO: Implement the k-Medoids algorithm
        ArrayList<KMedoidCluster> FoundClusters_KMedoids = KMedoid.KMedoidPartition(3, irisNormalized);
    }
}