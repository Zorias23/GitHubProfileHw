package com.example.admin.githubprofilehw;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.admin.githubprofilehw.model.github.GithubProfile;
import com.example.admin.githubprofilehw.model.github.GithubRepository;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class GithubRepositoryActivity extends AppCompatActivity {
    String profileName;
    List<GithubRepository> repoList = new ArrayList<>();
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_github_repository);
        profileName = getIntent().getExtras().getString("profileName");
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView = findViewById(R.id.rvMain);
        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        recyclerView.setLayoutManager(layoutManager); //need layoutManager
        recyclerView.setItemAnimator(itemAnimator); //don't need this but it allows animation for each item
        RetrofitHelper.getMyRepositories(profileName)
                .enqueue(new retrofit2.Callback<List<GithubRepository>>() {
                    @Override
                    public void onResponse(Call<List<GithubRepository>> call, Response<List<GithubRepository>> response) {
                        repoList = response.body();
                        RecyclerAdapter ra = new RecyclerAdapter(repoList);
                        recyclerView.setAdapter(ra); //need adapter
                    }

                    @Override
                    public void onFailure(Call<List<GithubRepository>> call, Throwable t) {

                    }

                });

    }
}
