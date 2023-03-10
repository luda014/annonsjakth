package course.learn.annonsjakth;

import android.content.Context;
import android.content.SharedPreferences;
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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
public class FavoriteFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private FavoriteItemAdapter mAdapter;
    private SharedPreferences mPrefs;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);
        mPrefs = getContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);

        mRecyclerView = view.findViewById(R.id.favorite_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mAdapter = new FavoriteItemAdapter(getContext(), getFavorites());
        mRecyclerView.setAdapter(mAdapter);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mAdapter.setItems(getFavorites());
    }



    private List<FavoriteItem> getFavorites() {
        List<FavoriteItem> favorites = new ArrayList<>();
        Map<String, ?> allEntries = mPrefs.getAll();
        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            String key = entry.getKey();
            if (key.startsWith("favorite_")) {
                String value = entry.getValue().toString();
                String[] parts = value.split(",");
                if (parts.length == 3) {
                    String imageUrl = parts[0];
                    String type = parts[1];
                    String amount = parts[2];
                    favorites.add(new FavoriteItem(imageUrl, type, amount));
                }
            }
        }
        return favorites;
    }

