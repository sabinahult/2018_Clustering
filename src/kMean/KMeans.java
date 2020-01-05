package kMean;
import java.util.*;
import data.*;

public class KMeans {
    public static ArrayList<KMeanCluster> KMeansPartition(int k, ArrayList<Iris> data) {
        ArrayList<KMeanCluster> partitions = new ArrayList<>();
        Integer[] randomIndex = getRandomIndices(k, data.size());

        // set the right number of clusters with a random object's values as center
        for(int i = 0; i < k; i++) {
            Iris randomIris = data.get(randomIndex[i]);
            double[] values = {randomIris.getSepal_Length(), randomIris.getSepal_Width(),
                    randomIris.getPetal_Length(), randomIris.getPetal_Width()};
            partitions.add(new KMeanCluster(values));
        }

        //System.out.println("Initial partitioning of objects");
        relocate(partitions, data);
        int reassignments;
        do {
            //System.out.println("Relocation...");
            reassignments = relocate(partitions, data);
        } while (reassignments > 0);

        return partitions;
    }

    private static int relocate(ArrayList<KMeanCluster> partitions, ArrayList<Iris> data) {
        int reassignments = 0;
        for(Iris flower : data) {
            // something big so even the first calculated distance is smaller
            double minDist = Math.pow(10, 10);

            Map<Double, KMeanCluster> distanceToClusterCenters = new HashMap<>();
            // calculate distance to centers and map those
            for(KMeanCluster cluster : partitions) {
                distanceToClusterCenters.put(euclideanDistanceToClusterCenter(flower, cluster.getCenter()), cluster);
                if(euclideanDistanceToClusterCenter(flower, cluster.getCenter()) < minDist) {
                    minDist = euclideanDistanceToClusterCenter(flower, cluster.getCenter());
                }
            }

            KMeanCluster minDistanceCluster = distanceToClusterCenters.get(minDist);
            // add flower to cluster with nearest center
            if(!minDistanceCluster.getClusterMembers().contains(flower)) {
                distanceToClusterCenters.get(minDist).addMember(flower);
            }

            // if this is not the first run, a flower could be in a cluster that it's now been "relocated" from
            // and so should be removed from that cluster
            for(KMeanCluster cluster : partitions) {
                if(!cluster.equals(minDistanceCluster)) {
                    if(cluster.getClusterMembers().contains(flower)) {
                        cluster.getClusterMembers().remove(flower);
                        reassignments++;
                    }
                }
            }
        }

        for(KMeanCluster cluster : partitions) cluster.updateCenter();

        return reassignments;
    }

    /**
     * Method to return k random indices without duplicates
     * @param k number of indices needed
     * @param max maximum value of index
     * @return array of random Integers without duplicates
     */
    private static Integer[] getRandomIndices(int k, int max) {
        Random randi = new Random();

        // trying to avoid duplicate index, which is a possibility when just getting a random int
        Set<Integer> randomIndex = new HashSet<>();
        while(randomIndex.size() <= k) {
            randomIndex.add(randi.nextInt(max));
        }

        return randomIndex.toArray(new Integer[randomIndex.size()]);
    }

    /**
     * Calculates the Euclidean Distance between to Iris objects
     * @param one the Iris object we're interested in
     * @param center the center values of cluster to compare with
     * @return the Euclidean distance as a double
     */
    private static double euclideanDistanceToClusterCenter(Iris one, double[] center){
        double sum = Math.pow(one.getSepal_Length() - center[0], 2) +
                        Math.pow(one.getSepal_Width() - center[1], 2) +
                        Math.pow(one.getPetal_Length() - center[2], 2) +
                        Math.pow(one.getPetal_Width() - center[3], 2);

        return Math.sqrt(sum);
    }
}