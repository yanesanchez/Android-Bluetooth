package com.example.a12_bt;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.a12_bt.databinding.FragmentDriveBinding;

public class DriveFragment extends Fragment {
    private FragmentDriveBinding binding;
    private FragmentDataPassListener cv_listener;

    public DriveFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    // assign cv_listener to MainActivity (which implements MyFragmentDataPassListener)
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof FragmentDataPassListener) {
            cv_listener = (FragmentDataPassListener) context;
        } else {
            throw new ClassCastException(context.toString() + " must MyFragmentDataPassListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentDriveBinding.inflate(inflater, container, false);
        // TODO Add button & seekbar listeners

        return binding.getRoot();
    }
}