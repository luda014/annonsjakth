package course.learn.annonsjakth;

import static course.learn.annonsjakth.gettrend.getTrendingItems;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ListFragment extends Fragment {
    private RecyclerView recyclerView;
    private MyAdapter adapter;
    private List<String> typesList = new ArrayList<>();
    private List<String> imagesList = new ArrayList<>();
    private List<String> amountList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        adapter = new MyAdapter(getContext(), typesList, imagesList, amountList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        new MyTask().execute();

        return view;
    }


    private class MyTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            try {
                String url = "https://raw.githubusercontent.com/luda014/annosjakt/main/trending_items.json";
                URL githubUrl = new URL(url);
                InputStreamReader reader = new InputStreamReader(githubUrl.openStream());

                JsonParser parser = new JsonParser();
                JsonElement element = parser.parse(reader);

                JsonObject jsonObject = element.getAsJsonObject();
                JsonArray hitsArray = jsonObject.getAsJsonArray("hits");

                int count = 0; // to keep track of the number of items
                for (JsonElement hitElement : hitsArray) {
                    if (count >= 10) { // break the loop after getting 10 items
                        break;
                    }
                    JsonObject hitObject = hitElement.getAsJsonObject();
                    JsonObject metadataObject = hitObject.getAsJsonObject("metadata");
                    String type = metadataObject.get("type").getAsString();
                    typesList.add(type);
                    JsonArray imagesArray = hitObject.getAsJsonArray("images");
                    String firstImage = imagesArray.get(0).getAsString();
                    imagesList.add(firstImage);

                    JsonObject pricingObject = hitObject.getAsJsonObject("pricing");
                    int amount = pricingObject.get("amount").getAsInt();
                    amountList.add(String.valueOf(amount));
                    count++;
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            adapter.notifyDataSetChanged();
        }
    }
}