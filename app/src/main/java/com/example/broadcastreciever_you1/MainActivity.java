package com.example.broadcastreciever_you1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.example.broadcastreciever_you1.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding mBinding;
    LocalBroadcastManager localBroadcastManager;
    private LocalReceiver localReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        localBroadcastManager = LocalBroadcastManager.getInstance(this);

        regLocalReceiver();

        mBinding.btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.planet9.sx7");
                intent.putExtra("name","Akash");
                intent.putExtra("age","19");
                localBroadcastManager.sendBroadcast(intent);

            }
        });


    }

    private void regLocalReceiver() {
        localReceiver = new LocalReceiver();
        IntentFilter intentFilter = new IntentFilter("com.planet9.sx7");
        localBroadcastManager.registerReceiver(localReceiver,intentFilter);
    }

    private class LocalReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String name = intent.getStringExtra("name");
            String age = intent.getStringExtra("age");
            mBinding.tvDisplay1.setText(name);
            mBinding.tvDisplay2.setText(age);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(localReceiver);
    }
}