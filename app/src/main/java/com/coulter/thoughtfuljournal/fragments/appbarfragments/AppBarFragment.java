package com.coulter.thoughtfuljournal.fragments.appbarfragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.coulter.thoughtfuljournal.R;

import org.jetbrains.annotations.NotNull;

public abstract class AppBarFragment extends Fragment {
    public AppBarFragment(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.app_bar_fragment, container, false);
        setupAppBar(view);
        return view;
    }

    private void setupAppBar(View view) {
        Toolbar toolbar = view.findViewById(R.id.app_bar);
        AppCompatActivity activity = (AppCompatActivity)getActivity();
        if(activity != null) {
            activity.setSupportActionBar(toolbar);
        }
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, MenuInflater inflater) {
        inflater.inflate(getMenuID(), menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull @NotNull MenuItem item) {
        return handleOnOptionsItemSelected(item);
    }

    protected abstract boolean handleOnOptionsItemSelected(MenuItem item);
    protected abstract int getMenuID();
}