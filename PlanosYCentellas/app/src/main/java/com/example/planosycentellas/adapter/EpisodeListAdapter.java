package com.example.planosycentellas.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.planosycentellas.databinding.EpisodeInListBinding;
import com.example.planosycentellas.model.Episode;
import com.squareup.picasso.Picasso;

import java.util.List;

public class EpisodeListAdapter extends RecyclerView.Adapter<EpisodeListAdapter.EpisodeHolder> {

    private List<Episode> episodes;
    private final ItemClickListener mItemClickListener;

    public EpisodeListAdapter(ItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    @NonNull
    @Override
    public EpisodeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        EpisodeInListBinding binding =
                EpisodeInListBinding.inflate(inflater, parent, false);

        return new EpisodeHolder(binding);
    }

    public void setEpisodes(List<Episode> episodes){
        this.episodes = episodes;
        this.notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull EpisodeHolder holder, int position) {
        holder.bind(
                episodes.get(position).getTitle(),
                episodes.get(position).getImage());
    }

    @Override
    public int getItemCount() {

        if(episodes != null){
            return episodes.size();
        }
        return 0;
    }


    public interface ItemClickListener{
        void onItemClick(int clickedItem, boolean delete);
    }

    class EpisodeHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private EpisodeInListBinding binding;

        public EpisodeHolder(@NonNull EpisodeInListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            itemView.setOnClickListener(this);
        }

        void bind(String name, String image){
            Log.d("TESTING__", "NAME: " + name);
            binding.episodeTitle.setText(name);
            Picasso.get().load(image).into(binding.episodeImage);
        }

        @Override
        public void onClick(View v) {
            int pos = getAdapterPosition();
            mItemClickListener.onItemClick(pos,false);
        }
    }

}
