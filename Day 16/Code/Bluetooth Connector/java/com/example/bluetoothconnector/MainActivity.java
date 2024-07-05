package com.example.bluetoothconnector;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    ImageButton toggleBluetooth;
    private final static int REQUEST_ENABLE_BT = 10;
    private final static String TAG ="nik";
    BluetoothAdapter bluetoothAdapter;
    IntentFilter filter;
    ArrayList<String> availableDeviceName;
    CustomAdapter customAdapter;
    TextView textView2;
    ListView listView;
    BluetoothManager bluetoothManager;
    private final BroadcastReceiver receiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            Log.d(TAG, "onReceive: "+"I am Here");
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                String deviceName = device.getName();
                availableDeviceName.add(deviceName);
                Log.d("nik", "onReceive: "+deviceName);
                customAdapter.notifyDataSetChanged();
                String deviceHardwareAddress = device.getAddress(); // MAC address
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        toggleBluetooth = findViewById(R.id.toggleBluetooth);
        listView=findViewById(R.id.listView);
        availableDeviceName=new ArrayList<String>();
        customAdapter = new CustomAdapter(this, R.layout.bluetooth_list_layout, availableDeviceName);
        listView.setAdapter(customAdapter);
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        textView2=findViewById(R.id.textView2);
        if (!bluetoothAdapter.isEnabled()) {
            toggleBluetooth.setImageResource(R.drawable.bluetoothoff);
        } else {
            toggleBluetooth.setImageResource(R.drawable.bluetoothon);
        }
        toggleBluetooth.setOnClickListener(v -> {
            if (!bluetoothAdapter.isEnabled()) {
                textView2.setText("Turn Off Bluetooth");
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);


            } else {
                textView2.setText("Turn On Bluetooth");
                bluetoothAdapter.disable();
            }
        });

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_ENABLE_BT && resultCode == RESULT_OK) {
            bluetoothAdapter=BluetoothAdapter.getDefaultAdapter();
            Set<BluetoothDevice> pairedDevices = bluetoothAdapter.getBondedDevices();
            for(BluetoothDevice bt : pairedDevices)
                availableDeviceName.add(bt.getName());
            Log.d(TAG, "onCreate: "+pairedDevices);
            customAdapter.notifyDataSetChanged();
//            bluetoothAdapter.startDiscovery();
//            registerReceiver(receiver, filter);
//            customAdapter = new CustomAdapter(this,R.layout.bluetooth_list_layout,availableDeviceName);
        }
        else{
            Toast.makeText(this, "Kindly Accept The Permission To Use Our App", Toast.LENGTH_SHORT).show();
        }
    }
}