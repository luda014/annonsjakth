package course.learn.annonsjakth;

import static course.learn.annonsjakth.gettrend.getTrendingItems;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.io.IOException;
import java.util.List;

public class ListFragment extends Fragment {


    private List<TrendingItem> trendingItems;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);


        // Retrieve the top 10 trending items
        try {
            trendingItems = getTrendingItems();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ImageView img_item1 = view.findViewById(R.id.img_item1);
        ImageView img_item2 = view.findViewById(R.id.img_item2);
        ImageView img_item3 = view.findViewById(R.id.img_item3);
        ImageView img_item4 = view.findViewById(R.id.img_item4);
        ImageView img_item5 = view.findViewById(R.id.img_item5);
        ImageView img_item6 = view.findViewById(R.id.img_item6);

        img_item1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               ChangeFragmentItem("1");
            }
        });

        img_item2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChangeFragmentItem("2");
            }
        });

        img_item3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChangeFragmentItem("3");
            }
        });

        img_item4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChangeFragmentItem("4");
            }
        });

        img_item5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChangeFragmentItem("5");
            }
        });

        img_item6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChangeFragmentItem("6");
            }
        });

        return view;
    }

    public void ChangeFragmentItem(String id){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, new ItemFragment());
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}

