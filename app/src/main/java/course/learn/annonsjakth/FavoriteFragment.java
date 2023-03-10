package course.learn.annonsjakth;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
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
    private List<FavoriteItem> mFavorites;

    public FavoriteFragment() {
        // Required empty public constructor :)
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFavorites = new ArrayList<>();
        loadFavoritesFromSharedPreferences();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_favorite, container, false);

        mRecyclerView = rootView.findViewById(R.id.favorite_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new FavoriteItemAdapter(getContext(), mFavorites);
        mRecyclerView.setAdapter(mAdapter);

        return rootView;
    }

    private void loadFavoritesFromSharedPreferences() {
        SharedPreferences prefs = getActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        String imageUrl = prefs.getString("favorite_imageUrl", "");
        String type = prefs.getString("favorite_type", "");
        String amount = prefs.getString("favorite_amount", "");
        if (!TextUtils.isEmpty(imageUrl) && !TextUtils.isEmpty(type) && !TextUtils.isEmpty(amount)) {
            FavoriteItem item = new FavoriteItem(imageUrl, type, amount);
            mFavorites.add(item);
        }
    }
}
