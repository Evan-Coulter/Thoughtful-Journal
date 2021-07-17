package com.coulter.thoughtfuljournal.fragments.appbarfragments;

import android.view.MenuItem;
import android.widget.Toast;

import com.coulter.thoughtfuljournal.R;

public class AppBarMain extends AppBarFragment {
    @Override
    protected boolean handleOnOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.sortButton:
                Toast.makeText(requireActivity(), "Show Sort Menu", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.searchButton:
                Toast.makeText(requireActivity(), "Show Search View", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected int getMenuID() {
        return R.menu.main_app_bar_menu;
    }
}
