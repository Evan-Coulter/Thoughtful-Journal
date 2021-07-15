package com.coulter.thoughtfuljournal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.content.SharedPreferences;
import android.os.Bundle;

import com.coulter.thoughtfuljournal.fragments.AppBarFragment;
import com.coulter.thoughtfuljournal.fragments.FABFragment;
import com.coulter.thoughtfuljournal.fragments.RecyclerViewFragment;
import com.coulter.thoughtfuljournal.room.Journal;
import com.coulter.thoughtfuljournal.viewmodel.JournalViewModel;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupFragment(R.id.app_bar_container, new AppBarFragment());
        setupFragment(R.id.fab_container, new FABFragment());
        setupFragment(R.id.main_content_container, new RecyclerViewFragment());
    }

    private void setupFragment(int app_bar_container, Fragment appBarFragment) {
        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .replace(app_bar_container, appBarFragment)
                .commit();
    }
}