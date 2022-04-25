package com.example.a12_bt;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.a12_bt.databinding.FragmentSoundBinding;

import java.io.InputStream;
import java.io.OutputStream;

public class SoundFragment extends Fragment {
    FragmentSoundBinding binding;

    // Data stream to/from NXT bluetooth
    OutputStream cv_os = null;

    public SoundFragment() {
        // Required empty public constructor
    }

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

        binding.vvBtnPlaySound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cpf_EV3PlayTone();
            }
        });
        binding.vvBtnPauseSound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cpf_EV3PauseTone();
            }
        });
        binding.vvBtnVolumeUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO implement volume up
                cpf_volumeUp();
            }
        });
        binding.vvBtnVolumeDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO implement volume down
                cpf_volumeDown();
            }
        });

        return binding.getRoot();
    }

    // --- PLAY TONE
    // 4.2.5 Play a 1Kz tone at level 2 for 1 sec.
    // Communication Developer Kit, pg 30
    private void cpf_EV3PlayTone() {
        // +++
        try {
            byte[] buffer = new byte[17];       // 0x12 command length

            buffer[0] = (byte) (17 - 2);
            buffer[1] = 0;

            buffer[2] = 34;
            buffer[3] = 12;

            buffer[4] = (byte) 0x80;    // command type
            buffer[5] = 0;
            buffer[6] = 0;

            buffer[7] = (byte) 0x94;    // op code - opSound(CMD...)
            buffer[8] = 1;              // CMD: TONE

            buffer[9] = (byte) 0x81;    // op code - opUI_Read (CMD, ...)
            buffer[10] = 2;             // level 2

            buffer[11] = (byte) 0x82;   // op code - opUI_WRITE (CMD, ...)
            buffer[12] = (byte) 0xE8;
            buffer[13] = 3;

            buffer[14] = (byte) 0x82;   // op code - opUI_WRITE (CMD, ...)
            buffer[15] = (byte) 0xE8;
            buffer[16] = 3;

            cv_os.write(buffer);
            cv_os.flush();
        }
        catch (Exception e) {
            binding.tvSoundStatus.setText("Error in PlayTone(" + e.getMessage() + ")");
        }
    }


    private void cpf_EV3PauseTone() {
        // +++
        try {
            byte[] buffer = new byte[17];       // 0x12 command length

            buffer[0] = (byte) (17 - 2);
            buffer[1] = 0;

            buffer[2] = 34;
            buffer[3] = 12;

            buffer[4] = (byte) 0x80;    // command type
            buffer[5] = 0;
            buffer[6] = 0;

            buffer[7] = (byte) 0x94;    // op code - opSound(CMD...)
            buffer[8] = (byte) 0x00;    // CMD: BREAK

            cv_os.write(buffer);
            cv_os.flush();
        }
        catch (Exception e) {
            binding.tvSoundStatus.setText("Error in PauseTone(" + e.getMessage() + ")");
        }
    }

    private void cpf_volumeUp(){
        // +++
        try {
            // TODO implement Volume Up ----------------------
            byte[] buffer = new byte[17];       // 0x12 command length

            buffer[0] = (byte) (17 - 2);
            buffer[1] = 0;

            buffer[2] = 34;
            buffer[3] = 12;

            buffer[4] = (byte) 0x80;    // command type
            buffer[5] = 0;
            buffer[6] = 0;

            buffer[7] = (byte) 0x94;    // op code - opSound(CMD...)
            buffer[8] = (byte) 0x00;    // CMD: BREAK

            cv_os.write(buffer);
            cv_os.flush();
        }
        catch (Exception e) {
            binding.tvSoundStatus.setText("Error in VolumeUp(" + e.getMessage() + ")");
        }
    }

    private void cpf_volumeDown(){
        // +++
        try {
            // TODO implement Volume Down ----------------------
            byte[] buffer = new byte[17];       // 0x12 command length

            buffer[0] = (byte) (17 - 2);
            buffer[1] = 0;

            buffer[2] = 34;
            buffer[3] = 12;

            buffer[4] = (byte) 0x80;    // command type
            buffer[5] = 0;
            buffer[6] = 0;

            buffer[7] = (byte) 0x94;    // op code - opSound(CMD...)
            buffer[8] = (byte) 0x00;    // TODO change here i think

            cv_os.write(buffer);
            cv_os.flush();
        }
        catch (Exception e) {
            binding.tvSoundStatus.setText("Error in VolumeDown(" + e.getMessage() + ")");
        }
    }


}