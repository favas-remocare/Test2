package com.favas.myapplication.activity;

import android.Manifest;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.BluetoothLeScanner;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;


import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.favas.myapplication.DeviceCompare;
import com.favas.myapplication.R;
import com.favas.myapplication.adapter.BleScanViewAdapter;
import com.favas.myapplication.adapter.CustomLogAdapter;
import com.favas.myapplication.adapter.DividerItemDecoration;
import com.favas.myapplication.adapter.OnRecycleViewClickCallback;
import com.inuker.bluetooth.library.Code;
import com.inuker.bluetooth.library.Constants;
import com.inuker.bluetooth.library.model.BleGattProfile;
import com.inuker.bluetooth.library.search.SearchResult;
import com.inuker.bluetooth.library.search.response.SearchResponse;
import com.inuker.bluetooth.library.utils.BluetoothUtils;
import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;

import com.veepoo.protocol.VPOperateManager;
import com.veepoo.protocol.listener.base.IABleConnectStatusListener;
import com.veepoo.protocol.listener.base.IABluetoothStateListener;
import com.veepoo.protocol.listener.base.IConnectResponse;
import com.veepoo.protocol.listener.base.INotifyResponse;
import com.veepoo.protocol.util.VPLogger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends Activity implements SwipeRefreshLayout.OnRefreshListener, OnRecycleViewClickCallback {
    private final static String TAG = MainActivity.class.getSimpleName();
    private final static String YOUR_APPLICATION = "timaimee";
    Context mContext = MainActivity.this;
    private final int REQUEST_CODE = 1;
    private static final int MY_PERMISSIONS_REQUEST_CODE = 123;
    List<SearchResult> mListData = new ArrayList<>();
    List<String> mListAddress = new ArrayList<>();
    SwipeRefreshLayout mSwipeRefreshLayout;
    BleScanViewAdapter bleConnectAdatpter;

    private BluetoothManager mBManager;
    private BluetoothAdapter mBAdapter;
    private BluetoothLeScanner mBScanner;
    final static int MY_PERMISSIONS_REQUEST_BLUETOOTH = 0x55;
    RecyclerView mRecyclerView;
    TextView mTitleTextView;
    VPOperateManager mVpoperateManager;
    private boolean mIsOadModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Toast.makeText(getApplicationContext(),"Bismillah",Toast.LENGTH_LONG).show();
        initLog();
        Logger.t(TAG).i("onSearchStarted");
        mVpoperateManager = mVpoperateManager.getMangerInstance(mContext.getApplicationContext());
        VPLogger.setDebug(true);
        initRecyleView();
        checkPermission();
        registerBluetoothStateListener();

    }

    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.scan:
                scanDevice();
                break;
        }
    }

    private void initRecyleView() {
        mSwipeRefreshLayout = (SwipeRefreshLayout) super.findViewById(R.id.mian_swipeRefreshLayout);
        mRecyclerView = (RecyclerView) super.findViewById(R.id.main_recylerlist);
        mTitleTextView = (TextView) super.findViewById(R.id.main_title);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        bleConnectAdatpter = new BleScanViewAdapter(this, mListData);
        mRecyclerView.setAdapter(bleConnectAdatpter);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL_LIST));
        bleConnectAdatpter.setBleItemOnclick(this);
        mSwipeRefreshLayout.setOnRefreshListener(this);

        mTitleTextView.setText("扫描设备 V" + getAppVersion(mContext));
    }


    private void checkPermission() {
        Logger.t(TAG).i("Build.VERSION.SDK_INT =" + Build.VERSION.SDK_INT);
        if (Build.VERSION.SDK_INT <= 22) {
            initBLE();
            return;
        }








        /*
        int permissionCheck = ContextCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION);
        int permissionCheck2 = ContextCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_BACKGROUND_LOCATION);
        int permissionCheck3 = ContextCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION);
        int permissionCheck4 = ContextCompat.checkSelfPermission(mContext, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permissionCheck == PackageManager.PERMISSION_GRANTED  && permissionCheck2 == PackageManager.PERMISSION_GRANTED  && permissionCheck3 == PackageManager.PERMISSION_GRANTED   ) {
            Logger.t(TAG).i("checkPermission,PERMISSION_GRANTED");
            initBLE();
        } else if (permissionCheck == PackageManager.PERMISSION_DENIED) {
            requestPermission();
            Logger.t(TAG).i("checkPermission,PERMISSION_DENIED");
        }

         */



        if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION)+ ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION)+ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_BACKGROUND_LOCATION)+ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) + ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_PHONE_STATE) + ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE)  != PackageManager.PERMISSION_GRANTED)
        {

            if(ActivityCompat.shouldShowRequestPermissionRationale(
                    MainActivity.this,Manifest.permission.ACCESS_COARSE_LOCATION)   || ActivityCompat.shouldShowRequestPermissionRationale(
                    MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)    || ActivityCompat.shouldShowRequestPermissionRationale(
                    MainActivity.this, Manifest.permission.ACCESS_BACKGROUND_LOCATION) || ActivityCompat.shouldShowRequestPermissionRationale(
                    MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)  ||  ActivityCompat.shouldShowRequestPermissionRationale(
                    MainActivity.this, Manifest.permission.READ_PHONE_STATE) ||  ActivityCompat.shouldShowRequestPermissionRationale(
                    MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) )
            {




                Request_Perm();
            }
            else
            {

                Request_Perm();

            }




        }
        else
        {
            initBLE();
        }






    }


private void Request_Perm()
{

    ActivityCompat.requestPermissions(
            MainActivity.this,
            new String[]{

                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_BACKGROUND_LOCATION,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_PHONE_STATE,
                    Manifest.permission.READ_EXTERNAL_STORAGE

            },
            MY_PERMISSIONS_REQUEST_CODE
    );


}




    private void requestPermission() {
        if (ContextCompat.checkSelfPermission(mContext,
                Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
                    Manifest.permission.ACCESS_COARSE_LOCATION)) {
                Logger.t(TAG).i("requestPermission,shouldShowRequestPermissionRationale");

            } else {
                Logger.t(TAG).i("requestPermission,shouldShowRequestPermissionRationale else");
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                        MY_PERMISSIONS_REQUEST_BLUETOOTH);
            }
        } else {
            Logger.t(TAG).i("requestPermission,shouldShowRequestPermissionRationale hehe");
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_BLUETOOTH: {
                Logger.t(TAG).i("onRequestPermissionsResult,MY_PERMISSIONS_REQUEST_BLUETOOTH ");
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    initBLE();
                } else {
                }
                return;
            }


            case MY_PERMISSIONS_REQUEST_CODE:{
                // When request is cancelled, the results array are empty
                if(
                        (grantResults.length >0) &&
                                (grantResults[0]+   grantResults[1] + grantResults[2]+grantResults[3]+grantResults[4]+grantResults[5] == PackageManager.PERMISSION_GRANTED)
                )
                {

                    Toast.makeText(getApplicationContext(),"permn granted",Toast.LENGTH_LONG).show();
                    // Permissions are granted
                 //   ModNep.btntoast(getApplicationContext(),getLayoutInflater(),"PERMISSION GRANTED");

               //     Create_Dir_and_DB();
                }




                else {

                   // lmain.setVisibility(View.GONE);
                    // Permissions are denied
                  //  ModNep.btntoast(getApplicationContext(),getLayoutInflater(),"PERMISSION DENIED");
                }
                return;
            }




        }
    }

    private void initLog() {
        Logger.init(YOUR_APPLICATION)
                .methodCount(0)
                .methodOffset(0)
                .hideThreadInfo()
                .logLevel(LogLevel.FULL)
                .logAdapter(new CustomLogAdapter());
    }

    private boolean scanDevice() {
        if (!mListAddress.isEmpty()) {
            mListAddress.clear();
        }
        if (!mListData.isEmpty()) {
            mListData.clear();
            bleConnectAdatpter.notifyDataSetChanged();
        }

        if (!BluetoothUtils.isBluetoothEnabled()) {
            Toast.makeText(mContext, "Please enable bluetooth", Toast.LENGTH_SHORT).show();
            return true;
        }
        mVpoperateManager.startScanDevice(mSearchResponse);
        return false;
    }


    private void connectDevice(final String mac, final String deviceName) {

        mVpoperateManager.registerConnectStatusListener(mac, mBleConnectStatusListener);

        mVpoperateManager.connectDevice(mac, deviceName, new IConnectResponse() {

            @Override
            public void connectState(int code, BleGattProfile profile, boolean isoadModel) {
                if (code == Code.REQUEST_SUCCESS) {
                    //蓝牙与设备的连接状态
                    Logger.t(TAG).i("connection succeeded");
                    Logger.t(TAG).i("Whether it is firmware upgrade mode=" + isoadModel);
                    mIsOadModel = isoadModel;
                } else {
                    Logger.t(TAG).i("Connection failed");
                }
            }
        }, new INotifyResponse() {
            @Override
            public void notifyState(int state) {
                if (state == Code.REQUEST_SUCCESS) {
                    //蓝牙与设备的连接状态
                    Logger.t(TAG).i("Successful monitoring-can perform other operations");

                    Intent intent = new Intent(mContext, OperaterActivity.class);
                    intent.putExtra("isoadmodel", mIsOadModel);
                    intent.putExtra("deviceaddress", mac);
                    startActivity(intent);
                } else {
                    Logger.t(TAG).i("监听失败，重新连接");
                }

            }
        });
    }


    /**
     * 蓝牙打开or关闭状态
     */
    private void registerBluetoothStateListener() {
        mVpoperateManager.registerBluetoothStateListener(mBluetoothStateListener);
    }


    /**
     * 监听系统蓝牙的打开和关闭的回调状态
     */
    private final IABleConnectStatusListener mBleConnectStatusListener = new IABleConnectStatusListener() {

        @Override
        public void onConnectStatusChanged(String mac, int status) {
            if (status == Constants.STATUS_CONNECTED) {
                Logger.t(TAG).i("STATUS_CONNECTED");
            } else if (status == Constants.STATUS_DISCONNECTED) {
                Logger.t(TAG).i("STATUS_DISCONNECTED");
            }
        }
    };

    /**
     * 监听蓝牙与设备间的回调状态
     */
    private final IABluetoothStateListener mBluetoothStateListener = new IABluetoothStateListener() {
        @Override
        public void onBluetoothStateChanged(boolean openOrClosed) {
            Logger.t(TAG).i("open=" + openOrClosed);
        }
    };


    /**
     * 扫描的回调
     */
    private final SearchResponse mSearchResponse = new SearchResponse() {
        @Override
        public void onSearchStarted() {
            Logger.t(TAG).i("onSearchStarted");
        }

        @Override
        public void onDeviceFounded(final SearchResult device) {
            Logger.t(TAG).i(String.format("device for %s-%s-%d", device.getName(), device.getAddress(), device.rssi));

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (!mListAddress.contains(device.getAddress())) {
                        mListData.add(device);
                        mListAddress.add(device.getAddress());
                    }
                    Collections.sort(mListData, new DeviceCompare());
                    bleConnectAdatpter.notifyDataSetChanged();
                }
            });
        }

        @Override
        public void onSearchStopped() {
            refreshStop();
            Logger.t(TAG).i("onSearchStopped");
        }

        @Override
        public void onSearchCanceled() {
            refreshStop();
            Logger.t(TAG).i("onSearchCanceled");
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE) {
            if (BluetoothUtils.isBluetoothEnabled()) {
                scanDevice();
            } else {
                refreshStop();
            }
        }

    }

    @Override
    public void onRefresh() {
        Logger.t(TAG).i("onRefresh");
        if (checkBLE()) {
            scanDevice();
        }
    }

    private void initBLE() {
        mBManager = (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
        if (null != mBManager) {
            mBAdapter = mBManager.getAdapter();
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mBScanner = mBAdapter.getBluetoothLeScanner();
        }
        checkBLE();

    }

    /**
     * 检测蓝牙设备是否开启
     *
     * @return
     */
    private boolean checkBLE() {
        if (!BluetoothUtils.isBluetoothEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_CODE);
            return false;
        } else {
            return true;
        }
    }

    /**
     * 结束刷新
     */
    void refreshStop() {
        Logger.t(TAG).i("refreshComlete");
        if (mSwipeRefreshLayout.isRefreshing()) {
            mSwipeRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void OnRecycleViewClick(int position) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(mContext, "Connecting, please wait...", Toast.LENGTH_SHORT).show();
            }
        });
       SearchResult searchResult = mListData.get(position);
        connectDevice(searchResult.getAddress(), searchResult.getName());

       // Toast.makeText(mContext, ""+ searchResult.getScanRecord(), Toast.LENGTH_SHORT).show();


    }

    public String getAppVersion(Context context) {
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            String version = info.versionName;
            return version;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
