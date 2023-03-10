package course.learn.annonsjakth;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder> {
    private Context mContext;
    private List<FavoriteItem> mFavorites;

    public FavoriteAdapter(Context context, List<FavoriteItem> favorites) {
        mContext = context;
        mFavorites = favorites;
    }

    @NonNull
    @Override
    public FavoriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.favorite_item, parent, false);
        return new FavoriteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteViewHolder holder, int position) {
        FavoriteItem favoriteItem = mFavorites.get(position);
        Picasso.get().load(favoriteItem.getImageUrl()).into(holder.mImageView);
        holder.mTypeTextView.setText(favoriteItem.getType());
        holder.mAmountTextView.setText(favoriteItem.getAmount());
    }

    @Override
    public int getItemCount() {
        return mFavorites.size();
    }

    static class FavoriteViewHolder extends RecyclerView.ViewHolder {
        ImageView mImageView;
        TextView mTypeTextView;
        TextView mAmountTextView;

        public FavoriteViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.favorite_item_image);
            mTypeTextView = itemView.findViewById(R.id.favorite_item_type);
            mAmountTextView = itemView.findViewById(R.id.favorite_item_amount);
        }
    }
}
