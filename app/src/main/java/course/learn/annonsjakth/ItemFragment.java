package course.learn.annonsjakth;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class ItemFragment extends Fragment {
    SharedPreferences mPrefs;

    private ImageView mImageView;
    private TextView mTypeTextView;
    private TextView mAmountTextView;

    private String mImageUrl;
    private String mType;
    private String mAmount;

    public ItemFragment() {
        // Required empty public constructor
    }

    public static ItemFragment newInstance(String imageUrl, String type, String amount) {
        ItemFragment fragment = new ItemFragment();
        Bundle args = new Bundle();
        args.putString("imageUrl", imageUrl);
        args.putString("type", type);
        args.putString("amount", amount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mImageUrl = getArguments().getString("imageUrl");
            mType = getArguments().getString("type");
            mAmount = getArguments().getString("amount");
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_item_activity, container, false);
        mPrefs = getContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);

        mImageView = rootView.findViewById(R.id.img_item3);
        mTypeTextView = rootView.findViewById(R.id.textView3);
        mAmountTextView = rootView.findViewById(R.id.textView4);

        // Load the image with Picasso or Glide
        Picasso.get().load(mImageUrl).into(mImageView);

        // Set the text into the TextViews
        mTypeTextView.setText(mType);
        mAmountTextView.setText(mAmount + " SEK");



        Button button = rootView.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = mPrefs.edit();
                editor.putString("favorite_imageUrl", mImageUrl);
                editor.putString("favorite_type", mType);
                editor.putString("favorite_amount", mAmount);
                editor.apply();
                Toast.makeText(getContext(), "Saved to favorites", Toast.LENGTH_SHORT).show();
            }
        });




        return rootView;
    }
}
