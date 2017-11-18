package com.example.admin.githubprofilehw;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText etProfileName;
    Button btnGoToProfile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etProfileName = findViewById(R.id.profileName);
        btnGoToProfile = findViewById(R.id.btnGoToProfileActivity);
    }

    public void goToProfileActivity(View view) {
        Intent intent = new Intent(view.getContext(), GithubProfileActivity.class);
        String profileName = etProfileName.getText().toString();
        intent.putExtra("profileName", profileName);
        view.getContext().startActivity(intent);
    }
}
