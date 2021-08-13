package com.coulter.thoughtfuljournal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.Bundle;
import android.view.View;

import com.coulter.thoughtfuljournal.fragments.appbarfragments.AppBarEdit;
import com.coulter.thoughtfuljournal.fragments.FABFragment;
import com.coulter.thoughtfuljournal.fragments.appbarfragments.AppBarMain;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private int destination = R.id.editToList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupFragment(R.id.app_bar_container_main, new AppBarMain());
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
            if(destination.getId() == R.id.editJournalFragment){
                findViewById(R.id.fab_container_main).setVisibility(View.GONE);
                setupFragment(R.id.app_bar_container_main, new AppBarEdit());
                this.destination = R.id.editToList;
            } else {
                findViewById(R.id.fab_container_main).setVisibility(View.VISIBLE);
                setupFragment(R.id.app_bar_container_main, new AppBarMain());
                this.destination = R.id.listToEdit;
            }
        });
        if(this.destination == R.id.listToEdit){
            navController.navigate(this.destination);
        } else {
            navController.popBackStack();
        }
    }
}