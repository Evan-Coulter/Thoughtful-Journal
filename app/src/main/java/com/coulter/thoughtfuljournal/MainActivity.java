package com.coulter.thoughtfuljournal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.Bundle;
import android.view.View;

import com.coulter.thoughtfuljournal.fragments.AppBarFragment;
import com.coulter.thoughtfuljournal.fragments.FABFragment;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupFragment(R.id.app_bar_container_main, new AppBarFragment());
        setupFragment(R.id.fab_container_main, new FABFragment());
        //recycler view and edit text fragments set by navigation api.
    }

    private void setupFragment(int fragmentID, Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .replace(fragmentID, fragment)
                .commit();
    }

    @Override
    public void onClick(View v) {
        NavController navController = Navigation.findNavController(findViewById(R.id.content_container_main));
        navController.addOnDestinationChangedListener((controller, destination, arguments) -> {
            if(destination.getId() == R.id.editJournalFragment2){
                findViewById(R.id.fab_container_main).setVisibility(View.GONE);
            } else {
                findViewById(R.id.fab_container_main).setVisibility(View.VISIBLE);
            }
        });
        navController.navigate(R.id.listToEdit);
    }
}