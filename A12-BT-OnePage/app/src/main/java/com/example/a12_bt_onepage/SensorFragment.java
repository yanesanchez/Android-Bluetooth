package com.example.a12_bt_onepage;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.a12_bt_onepage.databinding.FragmentSensorBinding;

import java.io.OutputStream;

public class SensorFragment extends Fragment {
    FragmentSensorBinding binding;
    OutputStream cv_os = null;

    public SensorFragment() {
        // Required empty public constructor
    }

    public static SensorFragment newInstance(String param1, String param2) {
        SensorFragment fragment = new SensorFragment();
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
        binding = FragmentSensorBinding.inflate(inflater, container, false);

        binding.vvBtnLightSensorPort1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cpf_lightSensorPort1();

            }
        });
        binding.vvBtnLightSensorPort3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cpf_lightSensorPort3();
            }
        });
        binding.vvBtnDisplayPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cpf_drawToDisplay();
            }
        });

        return binding.getRoot();
    }



    // opINPUT_DEVICE,LC0(READY_SI),LC0(LAYER_0),LC0(SENSOR_PORT_1),LC0(DO_NOT_CHANGE_TYPE), LC0(MODE_2),LC0(ONE_DATA_SET),LCO(GLOBAL_VAR_INDEX0)
    // Communication Developer Kit, pg 29
    // read light sensor connected to port 1 as COLOR
    public void cpf_lightSensorPort1() {
        try {
            binding.tvSensorStatus.setText("System Power: ON");
            //0D00xxxx 00 04 00 99 1D 00 00 00 02 01 60
            byte[] buffer = new byte[15];       // command length

            buffer[0] = (byte) (15-2);
            buffer[1] = 0;

            buffer[2] = 34;     // message counter
            buffer[3] = 12;

            buffer[4] = (byte) 0x00; // command type

            buffer[5] = (byte) 0x04;      // header alloc
            buffer[6] = (byte) 0x00;

            // Firmware Developer Kit, pg 46
            // opInput_Device (CMD, ...)
            buffer[7] = (byte) 0x99;    // --- Op Code
            // arguments
            buffer[8] = (byte) 0x1D;     // CMD: READY_SI = 0x1D
            buffer[9] = (byte) 0x00;     // LC0(LAYER_0)
            buffer[10] = (byte) 0x00;    // LC0(SENSOR_PORT_1)
            buffer[11] = (byte) 0x00;    // LC0(DO_NOT_CHANGE_TYPE)

            buffer[12] = (byte) 0x02;    // LC0(MODE_2)
            buffer[13] = (byte) 0x01;    // LC0(ONE_DATA _SET)
            buffer[14] = (byte) 0x60;    // LC0(GLOBAL_VAR_INDEX0)

            cv_os.write(buffer);
            cv_os.flush();
        }
        catch (Exception e) {
            binding.tvSensorStatus.setText("Error in LightSensorPort1(" + e.getMessage() + ")");
        }
    }


    // opINPUT_DEVICE,LC0(READY_SI),LC0(LAYER_0),LC0(SENSOR_PORT_3),LC0(DO_NOT_CHANGE_TYPE), LC0(MODE_0),LC0(ONE_DATA_SET),LCO(GLOBAL_VAR_INDEX0)
    // Communication Developer Kit, pg 28
    // read light sensor value on sensor port 3
    public void cpf_lightSensorPort3() {
        try {
            binding.tvSensorStatus.setText("System Power: ON");
            // 0D00xxxx 0004 00 99 1D 00 02 00 00 01 60
            byte[] buffer = new byte[15];       // command length

            buffer[0] = (byte) (15-2);
            buffer[1] = 0;

            buffer[2] = 34;     // message counter
            buffer[3] = 12;

            buffer[4] = (byte) 0x00; // command type

            buffer[5] = (byte) 0x04;      // header alloc
            buffer[6] = (byte) 0x00;

            // Firmware Developer Kit, pg 46
            // opInput_Device (CMD, ...)
            buffer[7] = (byte) 0x99;    // --- Op Code
            // arguments
            buffer[8] = (byte) 0x1D;     // CMD: READY_SI = 0x1D
            buffer[9] = (byte) 0x00;     // LC0(LAYER_0)
            buffer[10] = (byte) 0x02;    // LC0(SENSOR_PORT_3)
            buffer[11] = (byte) 0x00;    // LC0(DO_NOT_CHANGE_TYPE)

            buffer[12] = (byte) 0x00;    // LC0(MODE_0)
            buffer[13] = (byte) 0x01;    // LC0(ONE_DATA _SET)
            buffer[14] = (byte) 0x60;    // LC0(GLOBAL_VAR_INDEX0)

            cv_os.write(buffer);
            cv_os.flush();
        }
        catch (Exception e) {
            binding.tvSensorStatus.setText("Error in LightSensorPort3(" + e.getMessage() + ")");
        }
    }

    // Communication Developer Kit, pg 31
    // show a picture in the display
    // 2C000000800000 84 13 00 82 00 00 82 00 00 84  1C 01 82  00 00 82   32 00 84   75 69 2F    6D 69 6E   64 73 74   6F 72 6D   73 2E 72   67 66 00 84 00
    public void cpf_drawToDisplay() {
        try {
            binding.tvSensorStatus.setText("System Power: ON");
            byte[] buffer = new byte[46];       // command length

            buffer[0] = (byte) 0x2C;
            buffer[1] = (byte) 0x00;

            buffer[2] = (byte) 0x00;     // message counter
            buffer[3] = (byte) 0x00;

            buffer[4] = (byte) 0x80;    // command type

            buffer[5] = (byte) 0x00;    // header alloc
            buffer[6] = (byte) 0x00;

            // Firmware Developer Kit, pg 46
            // opUI_DRAW
            buffer[7] = (byte) 0x84;    // --- Op Code
            // arguments
            buffer[8] = (byte) 0x13;     //
            buffer[9] = (byte) 0x00;     //
            buffer[10] = (byte) 0x82;    //
            buffer[11] = (byte) 0x00;    //
            buffer[12] = (byte) 0x00;    //
            buffer[13] = (byte) 0x82;    //
            buffer[14] = (byte) 0x00;    //
            buffer[15] = (byte) 0x00;

            buffer[16] = (byte) 0x84;   // op code - opUI_DRAW
            buffer[17] = (byte) 0x1C;
            buffer[18] = (byte) 0x01;
            buffer[19] = (byte) 0x82;
            buffer[20] = (byte) 0x00;
            buffer[21] = (byte) 0x00;
            buffer[22] = (byte) 0x82;
            buffer[23] = (byte) 0x32;
            buffer[24] = (byte) 0x00;

            buffer[25] = (byte) 0x84;   // op code - opUI_DRAW
            buffer[26] = (byte) 0x75;
            buffer[27] = (byte) 0x69;
            buffer[28] = (byte) 0x2F;
            buffer[29] = (byte) 0x6D;
            buffer[30] = (byte) 0x69;
            buffer[31] = (byte) 0x6E;

            buffer[32] = (byte) 0x64;
            buffer[33] = (byte) 0x73;
            buffer[34] = (byte) 0x74;

            buffer[35] = (byte) 0x6F;
            buffer[36] = (byte) 0x72;
            buffer[37] = (byte) 0x6D;

            buffer[38] = (byte) 0x73;
            buffer[39] = (byte) 0x2E;
            buffer[40] = (byte) 0x72;

            buffer[41] = (byte) 0x67;
            buffer[42] = (byte) 0x66;
            buffer[43] = (byte) 0x00;

            buffer[44] = (byte) 0x84;
            buffer[45] = (byte) 0x00;

            cv_os.write(buffer);
            cv_os.flush();
        }
        catch (Exception e) {
            binding.tvSensorStatus.setText("Error in Draw to Display(" + e.getMessage() + ")");
        }
    }

}