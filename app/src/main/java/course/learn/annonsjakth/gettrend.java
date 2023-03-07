package course.learn.annonsjakth;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class gettrend {

    private static final String JSON_URL = "https://raw.githubusercontent.com/luda014/annosjakt/main/trending_items.json";
    private static final int NUM_ITEMS = 10;

    public static List<TrendingItem> getTrendingItems() throws IOException {
        OkHttpClient client;
        client = new OkHttpClient();
        Request request = new Request.Builder().url(JSON_URL).build();
        Response response = client.newCall(request).execute();
        String jsonData = response.body().string();

        Gson gson = new Gson();
        Type listType = new TypeToken<List<TrendingItem>>() {}.getType();
        List<TrendingItem> items = gson.fromJson(jsonData, listType);

        List<TrendingItem> topItems = new ArrayList<>();
        for (int i = 0; i < NUM_ITEMS && i < items.size(); i++) {
            TrendingItem item = items.get(i);
            if (item.getPrice() > 0 && item.getType() != null && !item.getImages().isEmpty()) {
                topItems.add(item);
            }
        }

        return topItems;
    }

}
