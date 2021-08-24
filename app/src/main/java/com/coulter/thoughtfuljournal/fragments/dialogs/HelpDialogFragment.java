package com.coulter.thoughtfuljournal.fragments.dialogs;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.DialogFragment;

import com.coulter.thoughtfuljournal.R;
import com.coulter.thoughtfuljournal.databinding.HelpDialogFragmentBinding;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class HelpDialogFragment extends DialogFragment {

    public HelpDialogFragment(){}

    @Nullable
    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        HelpDialogFragmentBinding binding = HelpDialogFragmentBinding.inflate(inflater, container, true);
        binding.okButton.setOnClickListener(v->dismiss());
        if(Objects.requireNonNull(getDialog()).getWindow()!=null) {
            getDialog().getWindow().setBackgroundDrawable(ResourcesCompat.getDrawable(getResources(),R.drawable.dialog_background,null));
        }
        return binding.getRoot();
    }

}










