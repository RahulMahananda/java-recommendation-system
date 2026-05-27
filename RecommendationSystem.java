import java.io.*;
import java.util.*;

public class RecommendationSystem {

    public static void main(String[] args) {

        Map<String, List<String>> userPreferences =
                new HashMap<>();

        // Read data from file
        try {

            BufferedReader br =
                    new BufferedReader(new FileReader("data.txt"));

            String line;

          while ((line = br.readLine()) != null) {

    // Skip empty lines
    if (line.trim().isEmpty()) {
        continue;
    }

    String[] parts = line.split(",");

    // Skip invalid lines
    if (parts.length < 2) {
        continue;
    }

    String user = parts[0].trim();
    String product = parts[1].trim();

    userPreferences.putIfAbsent(
            user,
            new ArrayList<>());

    userPreferences.get(user).add(product);
}  

            br.close();

        } catch (IOException e) {

            System.out.println("Error reading file: "
                    + e.getMessage());

            return;
        }

        // Target user
        String targetUser = "Rahul";

        List<String> targetProducts =
                userPreferences.get(targetUser);

        Set<String> recommendations =
                new HashSet<>();

        // Recommendation Logic
        for (String user : userPreferences.keySet()) {

            if (!user.equals(targetUser)) {

                List<String> products =
                        userPreferences.get(user);

                boolean similarUser = false;

                // Check common products
                for (String product : products) {

                    if (targetProducts.contains(product)) {

                        similarUser = true;
                        break;
                    }
                }

                // Recommend new products
                if (similarUser) {

                    for (String product : products) {

                        if (!targetProducts.contains(product)) {

                            recommendations.add(product);
                        }
                    }
                }
            }
        }

        // Display Recommendations
        System.out.println(
                "===== RECOMMENDATION SYSTEM =====");

        System.out.println(
                "User: " + targetUser);

        System.out.println(
                "\nRecommended Products:");

        for (String item : recommendations) {

            System.out.println("- " + item);
        }
    }
}