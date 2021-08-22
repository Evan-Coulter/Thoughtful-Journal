package com.coulter.thoughtfuljournal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.coulter.thoughtfuljournal.fragments.appbarfragments.AppBarEdit;
import com.coulter.thoughtfuljournal.fragments.FABFragment;
import com.coulter.thoughtfuljournal.fragments.appbarfragments.AppBarFragment;
import com.coulter.thoughtfuljournal.fragments.appbarfragments.AppBarMain;
import com.coulter.thoughtfuljournal.fragments.appbarfragments.AppBarRead;
import com.coulter.thoughtfuljournal.recyclerview.SortObserver;
import com.coulter.thoughtfuljournal.recyclerview.SortSubject;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener, SortSubject {
    private NavController navController;
    private SortObserver observer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupNavigation();
        setupFragment(R.id.app_bar_container_main, new AppBarMain());
        setupFragment(R.id.fab_container_main, new FABFragment());
        //Main content fragment set by navigation api.
    }

    private void setupFragment(int fragmentID, Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .replace(fragmentID, fragment)
                .commit();
    }

    @SuppressLint("NonConstantResourceId")
    private void setupNavigation() {
        NavHostFragment navHost = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.content_container_main);
        if(navHost!=null) {
            navController = navHost.getNavController();
            navController.addOnDestinationChangedListener((controller, destination, arguments) -> {
                switch(destination.getId()) {
                    case R.id.editJournalFragment:
                        setAction(View.GONE, new AppBarEdit());
                        break;
                    case R.id.readJournalFragment:
                        setAction(View.GONE, new AppBarRead());
                        break;
                    case R.id.recyclerViewFragment:
                        setAction(View.VISIBLE, new AppBarMain());
                        break;
                }
            });
        }
    }

    private void setAction(int fabViewStyle, AppBarFragment appBar) {
        findViewById(R.id.fab_container_main).setVisibility(fabViewStyle);
        setupFragment(R.id.app_bar_container_main, appBar);
    }

    public void navigate(int actionID) {
        navController.navigate(actionID);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    @Override
    public void attachSortObserver(SortObserver observer) {
        this.observer = observer;
    }

    @Override
    public boolean notifySortButtonClicked(MenuItem clickedMenuItem) {
        if(observer!=null) {
            observer.onSortButtonClicked(clickedMenuItem);
        }
        return true;
    }
}