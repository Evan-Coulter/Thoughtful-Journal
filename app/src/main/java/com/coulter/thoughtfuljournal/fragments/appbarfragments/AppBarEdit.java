package com.coulter.thoughtfuljournal.fragments.appbarfragments;

import android.view.MenuItem;
import android.widget.Toast;


import com.coulter.thoughtfuljournal.R;

public class AppBarEdit extends AppBarFragment {
    @Override
    protected boolean handleOnOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.saveButton) {
            Toast.makeText(requireActivity(), "Save Journal", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected int getMenuID() {
        return R.menu.edit_app_bar_menu;
    }
}
