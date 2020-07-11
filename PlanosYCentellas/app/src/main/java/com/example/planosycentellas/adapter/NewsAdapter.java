package com.example.planosycentellas.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.planosycentellas.databinding.EpisodeInListBinding;
import com.example.planosycentellas.databinding.NewsLayoutBinding;
import com.squareup.picasso.Picasso;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsHolder>{

    private List<String> newsList;

    @NonNull
    @Override
    public NewsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        NewsLayoutBinding binding =
                NewsLayoutBinding.inflate(inflater, parent, false);

        return new NewsHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsHolder holder, int position) {
        holder.bind(newsList.get(position));
    }

    @Override
    public int getItemCount() {

        if(newsList != null){
            return newsList.size();
        }
        return 0;
    }

    public void setNews(List<String> news){
        this.newsList = news;
        notifyDataSetChanged();
    }


    class NewsHolder extends RecyclerView.ViewHolder {

        private NewsLayoutBinding binding;

        NewsHolder(@NonNull NewsLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }

        void bind(String image){
            Picasso.get().load(image).into(binding.newsImage);
        }
    }
}
