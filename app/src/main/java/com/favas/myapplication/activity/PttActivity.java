package com.favas.myapplication.activity;

import android.app.Activity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.favas.myapplication.R;
import com.orhanobut.logger.Logger;

import com.veepoo.protocol.VPOperateManager;
import com.veepoo.protocol.listener.base.IBleWriteResponse;
import com.veepoo.protocol.listener.data.IPttDetectListener;
import com.veepoo.protocol.model.datas.EcgDetectInfo;
import com.veepoo.protocol.model.datas.EcgDetectResult;
import com.veepoo.protocol.model.datas.EcgDetectState;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
//import butterknife.OnClick;


public class PttActivity extends Activity {
    private final static String TAG = PttActivity.class.getSimpleName();
    WriteResponse writeResponse = new WriteResponse();
   // @BindView(R.id.ptt_model)
    TextView mPttModelTv;
  //  @BindView(R.id.ptt_real_view)
    EcgHeartRealthView ecgHeartRealthView;
    Button btnread;

    IPttDetectListener iPttDetectListener = new IPttDetectListener() {
        @Override
        public void onEcgDetectInfoChange(EcgDetectInfo ecgDetectInfo) {
            Logger.t(TAG).i("ECG measurement basic information (waveform frequency, sampling frequency):" + ecgDetectInfo.toString());


        }

        @Override
        public void onEcgDetectStateChange(EcgDetectState ecgDetectState) {
            Logger.t(TAG).i("ECG measurement process status, set the top text:" + ecgDetectState.toString());


        }

        @Override
        public void onEcgDetectResultChange(EcgDetectResult ecgDetectResult) {
            Logger.t(TAG).i("PTT out value package (the final result of ECG measurement, in PTT mode, only when abnormal (ie, there is a disease), only out of value)");


        }

        @Override
        public void onEcgADCChange(final int[] data) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Logger.t(TAG).i("PTT的波形数据:" + Arrays.toString(data));
                    ecgHeartRealthView.changeData(data, 25);
                }
            });

        }

        @Override
        public void inPttModel() {
            Logger.t(TAG).i("进入ptt模式");
            mPttModelTv.setText("The watch is displayed in PTT mode");
        }

        @Override
        public void outPttModel() {
            Logger.t(TAG).i("退出ptt模式");
            mPttModelTv.setText("Watch shows exiting PTT mode");
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_ptt);
       // ButterKnife.bind(PttActivity.this);
        ecgHeartRealthView=findViewById(R.id.ptt_real_view);
        mPttModelTv=findViewById(R.id.ptt_model);
        btnread=findViewById(R.id.ptt_sign_open);


        boolean inPttModel = getIntent().getBooleanExtra("inPttModel", false);
        String ptStr = inPttModel ? "The watch is displayed in PTT mode" : "Watch shows exiting PTT mode";
        mPttModelTv.setText(ptStr);
        listenModel();


        btnread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String a;
                a="vv";
             //   VPOperateManager.getMangerInstance(getApplicationContext()).settingPttModelListener(iPttDetectListener);
                ecgHeartRealthView.clearData();
                Logger.t(TAG).i("Read ptt signal");
                VPOperateManager.getMangerInstance(getApplicationContext()).startReadPttSignData(writeResponse, true, iPttDetectListener);
            }
        });



    }

    private void listenModel() {
        VPOperateManager.getMangerInstance(getApplicationContext()).settingPttModelListener(iPttDetectListener);
    }


    /*
    @OnClick(R.id.ptt_sign_open)
    public void enter() {


        ecgHeartRealthView.clearData();
        Logger.t(TAG).i("Read ptt signal");
        VPOperateManager.getMangerInstance(getApplicationContext()).startReadPttSignData(writeResponse, true, iPttDetectListener);




    }

    @OnClick(R.id.ptt_sign_close)
    public void exitModel() {
        Logger.t(TAG).i("关闭ptt信号");
        VPOperateManager.getMangerInstance(getApplicationContext()).stopReadPttSignData(writeResponse, false, iPttDetectListener);
    }

     */

    /**
     * 写入的状态返回
     */
            class WriteResponse implements IBleWriteResponse {

                @Override
                public void onResponse(int code) {
            Logger.t(TAG).i("write cmd status:" + code);

        }
    }
}
