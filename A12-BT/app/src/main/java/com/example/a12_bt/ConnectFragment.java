package com.example.a12_bt;

import android.Manifest;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.Set;
import java.util.UUID;

import com.example.a12_bt.databinding.FragmentConnectBinding;

public class ConnectFragment extends Fragment {
    private FragmentConnectBinding binding;
    private FragmentDataPassListener cv_listener;
    Activity mainActivity;

    // Data stream to/from NXT bluetooth
    private InputStream cv_is = null;
    private OutputStream cv_os = null;

    // BT Variables
    private final String CV_ROBOTNAME = "EV3A";
    private BluetoothAdapter cv_btInterface = null;
    private Set<BluetoothDevice> cv_pairedDevices = null;
    private BluetoothDevice cv_btDevice = null;
    private BluetoothSocket cv_btSocket = null;


    public ConnectFragment() {
        // Required empty public constructor
    }

    public ConnectFragment(Activity mainActivity) {
        this.mainActivity = mainActivity;
    }

    // assign cv_listener to MainActivity (which implements MyFragmentDataPassListener)
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof FragmentDataPassListener) {
            cv_listener = (FragmentDataPassListener) context;
        } else {
            throw new ClassCastException(context.toString() + " must FragmentDataPassListener");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentConnectBinding.inflate(inflater, container, false);
        binding.vvBtnConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cpf_checkBTPermissions();
                cpf_requestBTPermissions();
                cv_btDevice = cpf_locateInPairedBTList(CV_ROBOTNAME);
                cpf_connectToEV3(cv_btDevice);
            }
        });
        binding.vvBtnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cpf_disconnFromEV3(cv_btDevice);
            }
        });

        return binding.getRoot();
    }


    // BLUETOOTH ===============================

    private void cpf_checkBTPermissions() {
        if (ContextCompat.checkSelfPermission(mainActivity,
                Manifest.permission.BLUETOOTH_SCAN) == PackageManager.PERMISSION_GRANTED) {
            binding.vvTvOut1.setText("BLUETOOTH_SCAN already granted.\n");
        }
        else {
            binding.vvTvOut1.setText("BLUETOOTH_SCAN NOT granted.\n");
        }
        if (ContextCompat.checkSelfPermission(mainActivity,
                Manifest.permission.BLUETOOTH_CONNECT) == PackageManager.PERMISSION_DENIED) {
            binding.vvTvOut2.setText("BLUETOOTH_CONNECT NOT granted.\n");
        }
        else {
            binding.vvTvOut2.setText("BLUETOOTH_CONNECT already granted.\n");
        }
    }

    // --- MENU OPTION 1
    // https://www.geeksforgeeks.org/android-how-to-request-permissions-in-android-application/
    private void cpf_requestBTPermissions() {
        // We can give any value but unique for each permission.
        final int BLUETOOTH_SCAN_CODE = 100;
        final int BLUETOOTH_CONNECT_CODE = 101;

        if (ContextCompat.checkSelfPermission(binding.getRoot().getContext(),
                Manifest.permission.BLUETOOTH_SCAN) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(mainActivity,
                    new String[]{Manifest.permission.BLUETOOTH_SCAN},
                    BLUETOOTH_SCAN_CODE);
        }
        else {
            Toast.makeText(binding.getRoot().getContext(),
                    "BLUETOOTH_SCAN already granted", Toast.LENGTH_SHORT) .show();
        }

        if (ContextCompat.checkSelfPermission(mainActivity,
                Manifest.permission.BLUETOOTH_CONNECT) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(mainActivity,
                    new String[]{Manifest.permission.BLUETOOTH_CONNECT},
                    BLUETOOTH_CONNECT_CODE);
        }
        else {
            Toast.makeText(mainActivity,
                    "BLUETOOTH_CONNECT already granted", Toast.LENGTH_SHORT) .show();
        }
    }



    // --- MENU OPTION 2
    // Modify from chap14, pp390 findRobot()
    private BluetoothDevice cpf_locateInPairedBTList(String name) {
        BluetoothDevice lv_bd = null;
        try {
            cv_btInterface = BluetoothAdapter.getDefaultAdapter();
            cv_pairedDevices = cv_btInterface.getBondedDevices();
            Iterator<BluetoothDevice> lv_it = cv_pairedDevices.iterator();
            while (lv_it.hasNext())  {
                lv_bd = lv_it.next();
                if (lv_bd.getName().equalsIgnoreCase(name)) {
                    binding.vvTvOut1.setText(name + " is in paired list");
                    return lv_bd;
                }
            }
            binding.vvTvOut1.setText(name + " is NOT in paired list");
        }
        catch (Exception e) {
            //binding.vvTvOut1.setText("Failed in findRobot() " + e.getMessage());
            binding.vvTvOut1.setText("Not Connected");
        }
        return null;
    }

    // --- MENU OPTION 3
    // Modify from chap14, pp391 connectToRobot()
    private void cpf_connectToEV3(BluetoothDevice bd) {
        try  {
            cv_btSocket = bd.createRfcommSocketToServiceRecord
                    (UUID.fromString("00001101-0000-1000-8000-00805F9B34FB"));
            cv_btSocket.connect();
            binding.vvTvOut2.setText("Connect to " + bd.getName() + " at " + bd.getAddress());
        }
        catch (Exception e) {
            binding.vvTvOut2.setText("Error interacting with remote device [" + e.getMessage() + "]");
        }
    }

    // --- MENU OPTION 6
    private void cpf_disconnFromEV3(BluetoothDevice bd) {
        try {
            cv_btSocket.close();
            cv_is.close();
            cv_os.close();
            binding.vvTvOut2.setText(bd.getName() + " is disconnect " );
        } catch (Exception e) {
            binding.vvTvOut2.setText("Error in disconnect -> " + e.getMessage());
        }
    }

}