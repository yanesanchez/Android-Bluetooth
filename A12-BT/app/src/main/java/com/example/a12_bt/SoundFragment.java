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
    InputStream cv_is = null;
    OutputStream cv_os = null;

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

        binding.vvPlaySound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        return inflater.inflate(R.layout.fragment_sound, container, false);
    }

    // --- MENU OPTION 5
    // 4.2.5 Play a 1Kz tone at level 2 for 1 sec.

    private void cpf_EV3PlayTone() {
        // +++
        try {
            byte[] buffer = new byte[17];       // 0x12 command length

            buffer[0] = (byte) (17 - 2);
            buffer[1] = 0;

            buffer[2] = 34;
            buffer[3] = 12;

            buffer[4] = (byte) 0x80;

            buffer[5] = 0;
            buffer[6] = 0;

            buffer[7] = (byte) 0x94;
            buffer[8] = 1;

            buffer[9] = (byte) 0x81;

            buffer[10] = 2;
            buffer[11] = (byte) 0x82;

            buffer[12] = (byte) 0xE8;

            buffer[13] = 3;
            buffer[14] = (byte) 0x82;
            buffer[15] = (byte) 0xE8;

            buffer[16] = 3;

            cv_os.write(buffer);
            cv_os.flush();
        }
        catch (Exception e) {
            // TODO add error to new text view
            binding.tvSoundStatus.setText("Error in PlayTone(" + e.getMessage() + ")");
        }
    }
}