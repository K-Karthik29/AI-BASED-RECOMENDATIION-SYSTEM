import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.ThresholdUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.model.UserSimilarity;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.UserBasedRecommender;

import java.io.File;
import java.util.List;

public class RecommenderSystem {

    public static void main(String[] args) {
        try {
            // Load the data model
            DataModel model = new FileDataModel(new File("ratings.csv"));

            // Compute user similarity using Pearson Correlation
            UserSimilarity similarity = new PearsonCorrelationSimilarity(model);

            // Define the user neighborhood with a threshold of 0.1
            ThresholdUserNeighborhood neighborhood = new ThresholdUserNeighborhood(0.1, similarity, model);

            // Create the recommender
            UserBasedRecommender recommender = new GenericUserBasedRecommender(model, neighborhood, similarity);

            // Get recommendations for a specific user (e.g., user with ID 1)
            List<RecommendedItem> recommendations = recommender.recommend(1, 2);

            // Display the recommendations
            for (RecommendedItem recommendation : recommendations) {
                System.out.println("Recommended Item ID: " + recommendation.getItemID() + ", Predicted Rating: " + recommendation.getValue());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
