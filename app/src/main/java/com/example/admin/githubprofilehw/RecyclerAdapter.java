package com.example.admin.githubprofilehw;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.admin.githubprofilehw.model.github.GithubRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 11/14/2017.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    public static List<GithubRepository> repoList = new ArrayList<>();

    public List<GithubRepository> getRepoList() {
        return repoList;
    }


    public static final String TAG = "RecyclerAdapter";

    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: ");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, null);
        return new ViewHolder(view);
    }

    public RecyclerAdapter(List<GithubRepository> repoList) {
        this.repoList = repoList;
    }


    @Override
    public void onBindViewHolder(RecyclerAdapter.ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: ");
        GithubRepository gr = repoList.get(position);

        if (gr != null) {
            holder.tvRepoName.setText(gr.getName());
            holder.tvRepoCreated.setText(gr.getCreatedAt());
            holder.tvRepoLanguage.setText(gr.getLanguage());

        }

    }

    @Override
    public int getItemCount() {
        return repoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView tvRepoName;
        private final TextView tvRepoCreated;
        private final TextView tvRepoLanguage;


        public ViewHolder(View itemView) {
            super(itemView);
            tvRepoName = itemView.findViewById(R.id.tvRepoName);
            tvRepoCreated = itemView.findViewById(R.id.tvRepoCreated);
            tvRepoLanguage = itemView.findViewById(R.id.tvRepoLanguage);

        }

        @Override
        public void onClick(View view) {
            /*
            ArrayList<Animal> animals = AnimalsRecyclerAdapter.animalsList;
            Log.d("AnimalsRecyclerAdapter", "onClick: you clicked position " + getPosition());
            Log.d(TAG, "onClick: this results in value from list: " + animals.get(getPosition()).getName());
            Intent intent = new Intent(view.getContext(), ViewAnimalActivity.class);
            intent.putExtra("animal", animals.get(getPosition()).getName());
            view.getContext().startActivity(intent); */
        }
    }
}