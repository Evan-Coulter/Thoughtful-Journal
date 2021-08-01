package com.coulter.thoughtfuljournal.fragments.appbarfragments;

import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
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
import androidx.core.content.ContextCompat;
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
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = setAndGetLayout(inflater, container, savedInstanceState);
        setupAppBar(view);
        return view;
    }

    protected abstract View setAndGetLayout(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    private void setupAppBar(View view) {
        Toolbar toolbar = view.findViewById(R.id.app_bar);
        toolbar.setTitleTextAppearance(requireActivity(), R.style.Widget_ThoughtfulJournal_Toolbar);
        ((AppCompatActivity) requireActivity()).setSupportActionBar(toolbar);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, MenuInflater inflater) {
        inflater.inflate(getMenuID(), menu);
        setIconColor(menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    private void setIconColor(Menu menu) {
        for(int i=0; i<menu.size(); i++) {
            Drawable drawable = menu.getItem(i).getIcon();
            if(drawable!=null) {
                drawable.mutate();
                drawable.setColorFilter(new PorterDuffColorFilter(ContextCompat.getColor(requireActivity(), R.color.black), PorterDuff.Mode.SRC_ATOP));
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull @NotNull MenuItem item) {
        return handleOnOptionsItemSelected(item);
    }

    protected abstract boolean handleOnOptionsItemSelected(MenuItem item);
    protected abstract int getMenuID();
}
