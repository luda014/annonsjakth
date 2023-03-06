package course.learn.annonsjakth;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class FavoriteFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);

        ImageView img_i1 = view.findViewById(R.id.img_i1);
        ImageView img_i2 = view.findViewById(R.id.img_i2);
        ImageView img_i3 = view.findViewById(R.id.img_i3);
        ImageView img_i4 = view.findViewById(R.id.img_i4);
        ImageView img_i5 = view.findViewById(R.id.img_i5);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // navigate to the ItemFragment when an ImageView is clicked
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frameLayout, new ItemFragment());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        };

        img_i1.setOnClickListener(onClickListener);
        img_i2.setOnClickListener(onClickListener);
        img_i3.setOnClickListener(onClickListener);
        img_i4.setOnClickListener(onClickListener);
        img_i5.setOnClickListener(onClickListener);

        return view;
    }
}





