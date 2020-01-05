package data;
import java.util.ArrayList;
import java.util.List;

public class Normalizer {
    private float sepal_length_mean, sepal_length_stddev, sepal_width_mean, sepal_width_stddev;
    private float petal_length_mean, petal_length_stddev, petal_width_mean, petal_width_stddev;

    public Normalizer() {
        sepal_length_mean = 0;
        sepal_length_stddev = 0;
        sepal_width_mean = 0;
        sepal_width_stddev = 0;
        petal_length_mean = 0;
        petal_length_stddev = 0;
        petal_width_mean = 0;
        petal_width_stddev = 0;
    }

    /**
     * Takes a list of Iris objects, and creates a new list of Iris objects with
     * normalized (z-score) values.
     * @param irises Iris objects to be converted/normalized
     * @return a list of iris objects with normalized attribute values
     */
    public ArrayList<Iris> zScore (ArrayList<Iris> irises) {
        setMeanAndStdDevPerAttribute(irises);
        ArrayList<Iris> normalized = new ArrayList<>();

        for(Iris flower : irises) {
            float norm_sep_len = (flower.getSepal_Length() - sepal_length_mean) / sepal_length_stddev;
            float norm_sep_wid = (flower.getSepal_Width() - sepal_width_mean) / sepal_width_stddev;
            float norm_pet_len = (flower.getPetal_Length() - petal_length_mean) / petal_length_stddev;
            float norm_pet_wid = (flower.getPetal_Width() - petal_width_mean) / petal_width_stddev;

            normalized.add(new Iris(norm_sep_len, norm_sep_wid, norm_pet_len, norm_pet_wid, flower.getClassLabel()));
        }

        return normalized;
    }


    private void setMeanAndStdDevPerAttribute(List<Iris> irisList) {
        ArrayList<Float> sepal_length = new ArrayList<>();
        ArrayList<Float> sepal_width = new ArrayList<>();
        ArrayList<Float> petal_length = new ArrayList<>();
        ArrayList<Float> petal_width = new ArrayList<>();

        for(Iris flower : irisList) {
            sepal_length.add(flower.getSepal_Length());
            sepal_width.add(flower.getPetal_Width());
            petal_length.add(flower.getPetal_Length());
            petal_width.add(flower.getPetal_Width());
        }

        sepal_length_mean = calculateMean(sepal_length);
        sepal_length_stddev = calculateStdDev(sepal_length, petal_length_mean);

        sepal_width_mean = calculateMean(sepal_width);
        sepal_width_stddev = calculateStdDev(sepal_width, sepal_width_mean);

        petal_length_mean = calculateMean(petal_width);
        petal_length_stddev = calculateStdDev(petal_width, petal_width_mean);

        petal_width_mean = calculateMean(petal_width);
        petal_width_stddev = calculateStdDev(petal_width, petal_width_mean);
    }

    private float calculateMean(ArrayList<Float> values) {
        float sum = 0;
        for(Float value : values) sum += value;
        return sum / values.size();
    }

    private float calculateStdDev(ArrayList<Float> values, float mean) {
        ArrayList<Double> newValues = new ArrayList<>();

        for(Float value : values) newValues.add(Math.pow((value-mean), 2));

        double sum = 0;
        for(Double value : newValues) sum += value;

        sum = sum / newValues.size();
        sum = Math.sqrt(sum);

        return (float) sum;
    }
}
