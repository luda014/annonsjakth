package course.learn.annonsjakth;

import android.content.ClipData;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

public class ItemReader {
    private static final String JSON_URL = "https://raw.githubusercontent.com/luda014/annosjakt/main/trending_items.json";

    public static void Fetch() {
        String jsonString = readJsonFromUrl(JSON_URL);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonObject[] items = gson.fromJson(jsonString, JsonObject[].class);

        int counter = 0;
        for (JsonObject item : items) {
            if (counter >= 10) {
                break;
            }
            String name = item.get("name").getAsString();
            JsonObject price = item.getAsJsonObject("price_SE");
            int amount = price.get("amount").getAsInt();
            String currency = price.get("currency").getAsString();
            String type = item.get("type").getAsString();
            String[] images = gson.fromJson(item.get("images"), String[].class);

            System.out.printf("Name: %s\nPrice: %d %s\nType: %s\nImages: %s\n\n", name, amount, currency, type, String.join(", ", images));
            counter++;

        }
    }

    public static String readJsonFromUrl(String url) {
        String jsonString = "";

        try {
            URLConnection connection = new URL(url).openConnection();
            InputStream inputStream = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder stringBuilder = new StringBuilder();
            String line = "";
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
            jsonString = stringBuilder.toString();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return jsonString;
    }
}


