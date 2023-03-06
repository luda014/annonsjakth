package course.learn.annonsjakth;

import java.io.*;
import java.net.*;

public class SellPyAPI {
    private final String algoliaApiKey;
    private final String algoliaApplicationId;
    private final String algoliaApiEndpoint;

    public SellPyAPI(String apiKey, String applicationId, String apiEndpoint) {
        this.algoliaApiKey = apiKey;
        this.algoliaApplicationId = applicationId;
        this.algoliaApiEndpoint = apiEndpoint;
    }

    public void getTrendingItems() throws IOException {
        URL url = new URL(algoliaApiEndpoint);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("x-algolia-api-key", algoliaApiKey);
        connection.setRequestProperty("x-algolia-application-id", algoliaApplicationId);
        connection.setDoOutput(true);

        String params = "{ \"params\": \"hitsPerPage=100\" }";
        OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
        writer.write(params);
        writer.flush();

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line;
        StringBuilder response = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();

        // Save the response to a file
        try (FileWriter fileWriter = new FileWriter("trending_items.json")) {
            fileWriter.write(response.toString());
        }
    }

    public static void api() {
        String apiKey = "3f5fce6ab498cad98a47abf2ac74c783";
        String applicationId = "M6WNFR0LVI";
        String apiEndpoint = "https://m6wnfr0lvi-dsn.algolia.net/1/indexes/prod_marketItem_se_relevance/query";

        SellPyAPI client = new SellPyAPI(apiKey, applicationId, apiEndpoint);

        try {
            client.getTrendingItems();
            System.out.println("Trending items saved to trending_items.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
