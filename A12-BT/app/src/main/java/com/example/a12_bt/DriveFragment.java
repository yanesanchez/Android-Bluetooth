package com.example.a12_bt;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

import com.example.a12_bt.databinding.FragmentDriveBinding;

public class DriveFragment extends Fragment implements SeekBar.OnSeekBarChangeListener {
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

        binding.vvSeekBar1.setOnSeekBarChangeListener(this);
        binding.vvSeekBar2.setOnSeekBarChangeListener(this);

        return binding.getRoot();
    }


    // SEEKBAR METHODS ==========
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
        switch (seekBar.getId()){
            case R.id.vv_seekBar1:
                binding.vvTvPower1.setText("Power: " + progress);
                break;
            case R.id.vv_seekBar2:
                binding.vvTvPower2.setText("Power: " + progress);
                break;
        }
        //binding.vvTvPower1.setText("Power: " + progress);
        //binding.vvTvPower2.setText("Power: " + progress);

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}