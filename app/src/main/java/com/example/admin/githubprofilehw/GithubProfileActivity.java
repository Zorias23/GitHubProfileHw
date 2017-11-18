package com.example.admin.githubprofilehw;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.githubprofilehw.model.github.GithubProfile;
import com.example.admin.githubprofilehw.model.github.GithubRepository;

public class GithubProfileActivity extends AppCompatActivity {
    TextView tvProfileName;
    TextView tvCompanyName;
    TextView tvPublicRepos;
    TextView tvCreated;
    ImageView profileImage;
    String profileName;
    public static final String TAG = "GithubProfileActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_github_profile);
        profileName = getIntent().getExtras().getString("profileName");
        tvProfileName = findViewById(R.id.tvProfileName);
        tvCompanyName = findViewById(R.id.tvCompanyName);
        tvPublicRepos = findViewById(R.id.tvPublicRepos);
        tvCreated = findViewById(R.id.tvCreated);
        profileImage = findViewById(R.id.ivProfileImage);
       // tvProfileName.setText(profileName);

        RetrofitHelper.getMyProfile(profileName)
                .enqueue(new retrofit2.Callback<GithubProfile>() {
                    @Override
                    public void onResponse(retrofit2.Call<GithubProfile> call, retrofit2.Response<GithubProfile> response) {

                        Log.d(TAG, "onResponse: " + response.body().getName());
                        tvProfileName.setText("Profile name: " + response.body().getName());
                        tvCompanyName.setText("Company: " + response.body().getCompany());
                        tvPublicRepos.setText("Public Repos: " + response.body().getPublicRepos());
                        tvCreated.setText("Created At: " + response.body().getCreatedAt());
                        MyAsyncTask myAsyncTask = new MyAsyncTask(profileImage);
                        myAsyncTask.execute(response.body().getAvatarUrl());
                    }

                    @Override
                    public void onFailure(retrofit2.Call<GithubProfile> call, Throwable t) {

                    }
                });
    }

    public void goToRepoActivity(View view) {
        Intent intent = new Intent(view.getContext(), GithubRepositoryActivity.class);
        intent.putExtra("profileName", profileName);
        view.getContext().startActivity(intent);
    }
}
