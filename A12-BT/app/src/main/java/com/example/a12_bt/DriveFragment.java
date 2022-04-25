package com.example.a12_bt;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

import com.example.a12_bt.databinding.FragmentDriveBinding;

import java.io.InputStream;
import java.io.OutputStream;

public class DriveFragment extends Fragment implements SeekBar.OnSeekBarChangeListener {
    private FragmentDriveBinding binding;
    private FragmentDataPassListener cv_listener;

    // Data stream to/from NXT bluetooth
    InputStream cv_is = null;
    OutputStream cv_os = null;

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
        binding.vvSeekBar1.setOnSeekBarChangeListener(this);
        binding.vvSeekBar2.setOnSeekBarChangeListener(this);

        // TODO DIRECTION BUTTONS
        binding.vvBtnFORWARD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cpf_EV3MoveMotorFORWARD();
            }
        });
        binding.vvBtnBACK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cpf_EV3MoveMotorBACKWARD();
            }
        });
        binding.vvBtnLEFT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cpf_EV3MoveMotorLEFT();
            }
        });
        binding.vvBtnRIGHT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cpf_EV3MoveMotorRIGHT();
            }
        });

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

    /* DRIVE FUNCTIONS ============== */
    // --- MENU OPTION 4
    // Communication Developer Kit Page 27
    // 4.2.2 Start motor B & C forward at power 50 for 3 rotation and braking at destination
    public void cpf_EV3MoveMotor() {
        try {
            byte[] buffer = new byte[20];       // 0x12 command length

            buffer[0] = (byte) (20-2);
            buffer[1] = 0;

            buffer[2] = 34;
            buffer[3] = 12;

            buffer[4] = (byte) 0x80;

            buffer[5] = 0;
            buffer[6] = 0;

            buffer[7] = (byte) 0xae;
            buffer[8] = 0;

            buffer[9] = (byte) 0x06;

            buffer[10] = (byte) 0x81;
            buffer[11] = (byte) 0x32;

            buffer[12] = 0;

            buffer[13] = (byte) 0x82;
            buffer[14] = (byte) 0x84;
            buffer[15] = (byte) 0x03;

            buffer[16] = (byte) 0x82;
            buffer[17] = (byte) 0xB4;
            buffer[18] = (byte) 0x00;

            buffer[19] = 1;

            cv_os.write(buffer);
            cv_os.flush();
        }
        catch (Exception e) {
            // TODO add error to new textView
            // binding.vvTvOut1.setText("Error in MoveForward(" + e.getMessage() + ")");
        }
    }

    /* ^ FORWARD */
    public void cpf_EV3MoveMotorFORWARD() {
        try {
            binding.vvTvStatusLabel.setText(R.string.moveForward);
            /*
            byte[] buffer = new byte[20];       // 0x12 command length

            buffer[0] = (byte) (20-2);
            buffer[1] = 0;

            buffer[2] = 34;
            buffer[3] = 12;

            buffer[4] = (byte) 0x80;

            buffer[5] = 0;
            buffer[6] = 0;

            buffer[7] = (byte) 0xae;
            buffer[8] = 0;

            buffer[9] = (byte) 0x06;

            buffer[10] = (byte) 0x81;
            buffer[11] = (byte) 0x32;

            buffer[12] = 0;

            buffer[13] = (byte) 0x82;
            buffer[14] = (byte) 0x84;
            buffer[15] = (byte) 0x03;

            buffer[16] = (byte) 0x82;
            buffer[17] = (byte) 0xB4;
            buffer[18] = (byte) 0x00;

            buffer[19] = 1;

            cv_os.write(buffer);
            cv_os.flush();

             */
        }
        catch (Exception e) {
            // TODO add error to new textView
            // binding.vvTvOut1.setText("Error in MoveForward(" + e.getMessage() + ")");
        }
    }

    /* v BACKWARDS */
    public void cpf_EV3MoveMotorBACKWARD() {
        try {
            binding.vvTvStatusLabel.setText(R.string.moveBackwards);
            /*
            byte[] buffer = new byte[20];       // 0x12 command length

            buffer[0] = (byte) (20-2);
            buffer[1] = 0;

            buffer[2] = 34;
            buffer[3] = 12;

            buffer[4] = (byte) 0x80;

            buffer[5] = 0;
            buffer[6] = 0;

            buffer[7] = (byte) 0xae;
            buffer[8] = 0;

            buffer[9] = (byte) 0x06;

            buffer[10] = (byte) 0x81;
            buffer[11] = (byte) 0x32;

            buffer[12] = 0;

            buffer[13] = (byte) 0x82;
            buffer[14] = (byte) 0x84;
            buffer[15] = (byte) 0x03;

            buffer[16] = (byte) 0x82;
            buffer[17] = (byte) 0xB4;
            buffer[18] = (byte) 0x00;

            buffer[19] = 1;

            cv_os.write(buffer);
            cv_os.flush();

             */
        }
        catch (Exception e) {
            // TODO add error to new textView
            // binding.vvTvOut1.setText("Error in MoveForward(" + e.getMessage() + ")");
        }
    }

    /* <-- LEFT */
    public void cpf_EV3MoveMotorLEFT() {
        try {
            binding.vvTvStatusLabel.setText(R.string.moveLeft);
            /*
            byte[] buffer = new byte[20];       // 0x12 command length

            buffer[0] = (byte) (20-2);
            buffer[1] = 0;

            buffer[2] = 34;
            buffer[3] = 12;

            buffer[4] = (byte) 0x80;

            buffer[5] = 0;
            buffer[6] = 0;

            buffer[7] = (byte) 0xae;
            buffer[8] = 0;

            buffer[9] = (byte) 0x06;

            buffer[10] = (byte) 0x81;
            buffer[11] = (byte) 0x32;

            buffer[12] = 0;

            buffer[13] = (byte) 0x82;
            buffer[14] = (byte) 0x84;
            buffer[15] = (byte) 0x03;

            buffer[16] = (byte) 0x82;
            buffer[17] = (byte) 0xB4;
            buffer[18] = (byte) 0x00;

            buffer[19] = 1;

            cv_os.write(buffer);
            cv_os.flush();

             */
        }
        catch (Exception e) {
            // TODO add error to new textView
            // binding.vvTvOut1.setText("Error in MoveForward(" + e.getMessage() + ")");
        }
    }

    /* --> RIGHT */
    public void cpf_EV3MoveMotorRIGHT() {
        try {
            binding.vvTvStatusLabel.setText(R.string.moveRight);
            /*
            byte[] buffer = new byte[20];       // 0x12 command length

            buffer[0] = (byte) (20-2);
            buffer[1] = 0;

            buffer[2] = 34;
            buffer[3] = 12;

            buffer[4] = (byte) 0x80;

            buffer[5] = 0;
            buffer[6] = 0;

            buffer[7] = (byte) 0xae;
            buffer[8] = 0;

            buffer[9] = (byte) 0x06;

            buffer[10] = (byte) 0x81;
            buffer[11] = (byte) 0x32;

            buffer[12] = 0;

            buffer[13] = (byte) 0x82;
            buffer[14] = (byte) 0x84;
            buffer[15] = (byte) 0x03;

            buffer[16] = (byte) 0x82;
            buffer[17] = (byte) 0xB4;
            buffer[18] = (byte) 0x00;

            buffer[19] = 1;

            cv_os.write(buffer);
            cv_os.flush();

             */
        }
        catch (Exception e) {
            // TODO add error to new textView
            // binding.vvTvOut1.setText("Error in MoveForward(" + e.getMessage() + ")");
        }
    }



}