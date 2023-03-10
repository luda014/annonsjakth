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
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private List<String> typesList;
    private List<String> imagesList;
    private List<String> amountList;
    private LayoutInflater mInflater;
    private Context context;

    public MyAdapter(Context context, List<String> typesList, List<String> imagesList, List<String> amountList) {
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        this.typesList = typesList;
        this.imagesList = imagesList;
        this.amountList = amountList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_trending, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        String type = typesList.get(position);
        String imageUrl = imagesList.get(position);
        String amount = amountList.get(position);

        holder.typeTextView.setText(type);
        holder.amountTextView.setText(amount + " SEK");

        // Use Picasso or Glide for loading the image
        Picasso.get().load(imageUrl).into(holder.imageImgView);
    }

    @Override
    public int getItemCount() {
        return typesList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imageImgView;
        TextView typeTextView;
        TextView amountTextView;

        public MyViewHolder(View itemView) {
            super(itemView);

            imageImgView = itemView.findViewById(R.id.imageImgView);
            typeTextView = itemView.findViewById(R.id.typeTextView);
            amountTextView = itemView.findViewById(R.id.amountTextView);
        }
    }
}
