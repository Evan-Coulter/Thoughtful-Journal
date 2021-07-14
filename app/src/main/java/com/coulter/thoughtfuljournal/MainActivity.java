package com.coulter.thoughtfuljournal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.coulter.thoughtfuljournal.fragments.AppBarFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupFragment(R.id.app_bar_container, new AppBarFragment());
    }

    private void setupFragment(int app_bar_container, AppBarFragment appBarFragment) {
        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .replace(app_bar_container, appBarFragment)
                .commit();
    }
}