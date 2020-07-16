package com.example.planosycentellas.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.planosycentellas.databinding.ElementInListBinding;
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

        ElementInListBinding binding =
                ElementInListBinding.inflate(inflater, parent, false);

        return new EpisodeHolder(binding);
    }

    public void setEpisodes(List<Episode> episodes){
        updateEpisodes(episodes);
        notifyDataSetChanged();
    }

    private void updateEpisodes(List<Episode> episodeList){
        this.episodes = episodeList;
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
        void onItemClick(int clickedItem);
    }

    class EpisodeHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ElementInListBinding binding;

        public EpisodeHolder(@NonNull ElementInListBinding binding) {
            super(binding.getRoot());

            setBinding(binding);
            setOnClickListener();
        }

        private void setBinding(ElementInListBinding binding){
            this.binding = binding;
        }

        private void setOnClickListener(){
            itemView.setOnClickListener(this);
        }

        void bind(String name, String image){
            setElementText(name);
            setElementImage(image);
        }

        private void setElementText(String name){
            binding.elementText.setText(name);
        }

        private void setElementImage(String image){
            Picasso.get().load(image).resize(75,75).into(binding.elementImage);
        }

        @Override
        public void onClick(View v) {
            setOnClick(getAdapterPosition());
        }

        private void setOnClick(int pos){
            mItemClickListener.onItemClick(pos);
        }
    }
}
