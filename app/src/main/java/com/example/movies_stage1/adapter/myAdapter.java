package com.example.movies_stage1.adapter;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movies_stage1.R;
import com.example.movies_stage1.R2;
import com.example.movies_stage1.parsing.Movies;
import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;


import java.util.List;

import butterknife.ButterKnife;


public class myAdapter extends RecyclerView.Adapter<myAdapter.myHolder>{
    private List<Movies> dataList;
    private Context context;
    final private MovieItemClickListener mOnMovieItemClickListener;


    public interface MovieItemClickListener {
        void onMovieItemClick(int clickedItemIndex);
    }
    public myAdapter(Context context, List<Movies> dataList, MovieItemClickListener listener) {
        this.dataList = dataList;
        this.context = context;
        this.mOnMovieItemClickListener = listener;
    }

    @Override
    public myHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.layout_grid_movie_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;
        View view = inflater.inflate(layoutIdForListItem, parent, shouldAttachToParentImmediately);
        return new myHolder(view);
    }

    @Override
    public void onBindViewHolder( myHolder holder, int position) {
        holder.bindMovie(dataList.get(position));
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class myHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        Context mContext;

        //@BindView(R.id.tvMovieTitle)
        TextView title;

        //@BindView(R.id.ivMovieImage)
        ImageView poster;
        //@BindView(R.id.tvReleaseDate)
        TextView releaseDate;

        myHolder(View itemView) {
            super(itemView);

            mContext = itemView.getContext();
            releaseDate=(TextView) itemView.findViewById(R.id.tvReleaseDate);
            poster=(ImageView) itemView.findViewById(R.id.ivMovieImage);
            title=(TextView) itemView.findViewById(R.id.tvMovieTitle);
            itemView.setOnClickListener(this);
        }

        void bindMovie(Movies movie) {
            StringBuilder releaseText = new StringBuilder().append("Release Date: ");
            releaseText.append(movie.getReleaseDate());

            title.setText(movie.getOriginalTitle());
            releaseDate.setText(releaseText);
            Picasso.Builder builder = new Picasso.Builder(context);
            builder.downloader(new OkHttp3Downloader(context));
            builder.build().load(context.getResources().getString(R.string.IMAGE_BASE_URL)+ movie.getPosterPath())
                    .placeholder((R.drawable.gradient_background))
                    .error(R.drawable.ic_launcher_background)
                    .into(poster);
        }

        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            mOnMovieItemClickListener.onMovieItemClick(clickedPosition);
        }
    }
}