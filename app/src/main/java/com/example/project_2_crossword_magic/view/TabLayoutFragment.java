package com.example.project_2_crossword_magic.view;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.project_2_crossword_magic.databinding.TabFragmentBinding;

public class TabLayoutFragment extends Fragment {
    private TabFragmentBinding binding;
    public static final String ARG_ID = "id";

    private final String TAG = "TabLayoutFragment Mine";

    @Nullable
    @Override
    public View onCreateView( @NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                              @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView");
        binding = TabFragmentBinding.inflate(getLayoutInflater(), container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onViewCreated");
        Bundle args = getArguments();
        String id = Integer.toString(args.getInt(ARG_ID));
    }
}