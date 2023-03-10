package course.learn.annonsjakth;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class FavoriteItemAdapter extends RecyclerView.Adapter<FavoriteItemAdapter.ViewHolder> {
    private List<FavoriteItem> mFavorites;
    private LayoutInflater mInflater;
    private Context context;

    public FavoriteItemAdapter(Context context, List<FavoriteItem> favorites) {
        mFavorites = favorites;
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;
        public TextView mTypeView;
        public TextView mAmountView;

        public ViewHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.favorite_item_image);
            mTypeView = itemView.findViewById(R.id.favorite_item_type);
            mAmountView = itemView.findViewById(R.id.favorite_item_amount);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.favorite_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        FavoriteItem item = mFavorites.get(position);
        Picasso.get().load(item.getImageUrl()).into(holder.mImageView);
        holder.mTypeView.setText(item.getType());
        holder.mAmountView.setText(item.getAmount());
    }

    @Override
    public int getItemCount() {
        return mFavorites.size();
    }
}
