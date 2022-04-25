package com.example.a12_bt;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.a12_bt.databinding.FragmentSoundBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SoundFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SoundFragment extends Fragment {
    FragmentSoundBinding binding;

    public SoundFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static SoundFragment newInstance(String param1, String param2) {
        SoundFragment fragment = new SoundFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentSoundBinding.inflate(inflater, container, false);

        
        return inflater.inflate(R.layout.fragment_sound, container, false);
    }
}