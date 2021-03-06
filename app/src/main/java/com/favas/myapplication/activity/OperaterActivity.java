package com.favas.myapplication.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.room.Room;

import com.favas.myapplication.Database.Db;
import com.favas.myapplication.Database.TableBP_30MN;
import com.favas.myapplication.R;
import com.favas.myapplication.adapter.GridAdatper;
import com.favas.myapplication.oad.activity.OadActivity;
import com.orhanobut.logger.Logger;


import com.veepoo.protocol.VPOperateManager;
import com.veepoo.protocol.listener.base.IBleWriteResponse;
import com.veepoo.protocol.listener.data.IAlarm2DataListListener;
import com.veepoo.protocol.listener.data.IAlarmDataListener;
import com.veepoo.protocol.listener.data.IAllHealthDataListener;
import com.veepoo.protocol.listener.data.IAllSetDataListener;
import com.veepoo.protocol.listener.data.IAutoDetectOriginDataListener;
import com.veepoo.protocol.listener.data.IBPDetectDataListener;
import com.veepoo.protocol.listener.data.IBPSettingDataListener;
import com.veepoo.protocol.listener.data.IBatteryDataListener;
import com.veepoo.protocol.listener.data.ICameraDataListener;
import com.veepoo.protocol.listener.data.ICheckWearDataListener;
import com.veepoo.protocol.listener.data.ICountDownListener;
import com.veepoo.protocol.listener.data.ICustomProtocolStateListener;
import com.veepoo.protocol.listener.data.ICustomSettingDataListener;
import com.veepoo.protocol.listener.data.IDeviceControlPhoneModelState;
import com.veepoo.protocol.listener.data.IDeviceFuctionDataListener;
import com.veepoo.protocol.listener.data.IDrinkDataListener;
import com.veepoo.protocol.listener.data.IFatigueDataListener;
import com.veepoo.protocol.listener.data.IFindDeviceDatalistener;
import com.veepoo.protocol.listener.data.IFindPhonelistener;
import com.veepoo.protocol.listener.data.IHRVOriginDataListener;
import com.veepoo.protocol.listener.data.IHeartDataListener;
import com.veepoo.protocol.listener.data.IHeartWaringDataListener;
import com.veepoo.protocol.listener.data.ILanguageDataListener;
import com.veepoo.protocol.listener.data.ILightDataCallBack;
import com.veepoo.protocol.listener.data.ILongSeatDataListener;
import com.veepoo.protocol.listener.data.ILowPowerListener;
import com.veepoo.protocol.listener.data.INightTurnWristeDataListener;
import com.veepoo.protocol.listener.data.IOriginData3Listener;
import com.veepoo.protocol.listener.data.IOriginDataListener;
import com.veepoo.protocol.listener.data.IOriginProgressListener;
import com.veepoo.protocol.listener.data.IPersonInfoDataListener;
import com.veepoo.protocol.listener.data.IPwdDataListener;
import com.veepoo.protocol.listener.data.IScreenLightListener;
import com.veepoo.protocol.listener.data.IScreenStyleListener;
import com.veepoo.protocol.listener.data.ISleepDataListener;
import com.veepoo.protocol.listener.data.ISocialMsgDataListener;
import com.veepoo.protocol.listener.data.ISpo2hDataListener;
import com.veepoo.protocol.listener.data.ISpo2hOriginDataListener;
import com.veepoo.protocol.listener.data.ISportDataListener;
import com.veepoo.protocol.listener.data.ISportModelOriginListener;
import com.veepoo.protocol.listener.data.ISportModelStateListener;
import com.veepoo.protocol.listener.data.IWomenDataListener;
import com.veepoo.protocol.model.datas.AlarmData;
import com.veepoo.protocol.model.datas.AlarmData2;
import com.veepoo.protocol.model.datas.AllSetData;
import com.veepoo.protocol.model.datas.AutoDetectOriginData;
import com.veepoo.protocol.model.datas.AutoDetectStateData;
import com.veepoo.protocol.model.datas.BatteryData;
import com.veepoo.protocol.model.datas.BpData;
import com.veepoo.protocol.model.datas.BpSettingData;
import com.veepoo.protocol.model.datas.CheckWearData;
import com.veepoo.protocol.model.datas.CountDownData;
import com.veepoo.protocol.model.datas.DrinkData;
import com.veepoo.protocol.model.datas.FatigueData;
import com.veepoo.protocol.model.datas.FindDeviceData;
import com.veepoo.protocol.model.datas.FunctionDeviceSupportData;
import com.veepoo.protocol.model.datas.FunctionSocailMsgData;
import com.veepoo.protocol.model.datas.HRVOriginData;
import com.veepoo.protocol.model.datas.HalfHourBpData;
import com.veepoo.protocol.model.datas.HalfHourRateData;
import com.veepoo.protocol.model.datas.HeartData;
import com.veepoo.protocol.model.datas.HeartWaringData;
import com.veepoo.protocol.model.datas.LanguageData;
import com.veepoo.protocol.model.datas.LongSeatData;
import com.veepoo.protocol.model.datas.LowPowerData;
import com.veepoo.protocol.model.datas.NightTurnWristeData;
import com.veepoo.protocol.model.datas.OriginData;
import com.veepoo.protocol.model.datas.OriginData3;
import com.veepoo.protocol.model.datas.OriginHalfHourData;
import com.veepoo.protocol.model.datas.PersonInfoData;
import com.veepoo.protocol.model.datas.PwdData;
import com.veepoo.protocol.model.datas.ScreenLightData;
import com.veepoo.protocol.model.datas.ScreenStyleData;
import com.veepoo.protocol.model.datas.SleepData;
import com.veepoo.protocol.model.datas.SleepPrecisionData;
import com.veepoo.protocol.model.datas.Spo2hData;
import com.veepoo.protocol.model.datas.Spo2hOriginData;
import com.veepoo.protocol.model.datas.SportData;
import com.veepoo.protocol.model.datas.SportModelOriginHeadData;
import com.veepoo.protocol.model.datas.SportModelOriginItemData;
import com.veepoo.protocol.model.datas.SportModelStateData;
import com.veepoo.protocol.model.datas.TimeData;
import com.veepoo.protocol.model.datas.WomenData;
import com.veepoo.protocol.model.enums.EAllSetType;
import com.veepoo.protocol.model.enums.EBPDetectModel;
import com.veepoo.protocol.model.enums.ECameraStatus;
import com.veepoo.protocol.model.enums.EFunctionStatus;
import com.veepoo.protocol.model.enums.ELanguage;
import com.veepoo.protocol.model.enums.EOprateStauts;
import com.veepoo.protocol.model.enums.ESex;
import com.veepoo.protocol.model.enums.ESocailMsg;
import com.veepoo.protocol.model.enums.ESpo2hDataType;
import com.veepoo.protocol.model.enums.EWomenStatus;
import com.veepoo.protocol.model.settings.Alarm2Setting;
import com.veepoo.protocol.model.settings.AlarmSetting;
import com.veepoo.protocol.model.settings.AllSetSetting;
import com.veepoo.protocol.model.settings.AutoDetectStateSetting;
import com.veepoo.protocol.model.settings.BpSetting;
import com.veepoo.protocol.model.settings.CheckWearSetting;
import com.veepoo.protocol.model.settings.ContentPhoneSetting;
import com.veepoo.protocol.model.settings.ContentSetting;
import com.veepoo.protocol.model.settings.ContentSmsSetting;
import com.veepoo.protocol.model.settings.ContentSocailSetting;
import com.veepoo.protocol.model.settings.CountDownSetting;
import com.veepoo.protocol.model.settings.CustomSetting;
import com.veepoo.protocol.model.settings.CustomSettingData;
import com.veepoo.protocol.model.settings.HeartWaringSetting;
import com.veepoo.protocol.model.settings.LongSeatSetting;
import com.veepoo.protocol.model.settings.NightTurnWristSetting;
import com.veepoo.protocol.model.settings.ScreenSetting;
import com.veepoo.protocol.model.settings.WomenSetting;
import com.veepoo.protocol.util.Spo2hOriginUtil;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import static com.favas.myapplication.activity.Oprate.AIM_SPROT_CALC;
import static com.favas.myapplication.activity.Oprate.ALARM_NEW_ADD;
import static com.favas.myapplication.activity.Oprate.ALARM_NEW_DELETE;
import static com.favas.myapplication.activity.Oprate.ALARM_NEW_MODIFY;
import static com.favas.myapplication.activity.Oprate.ALARM_NEW_READ;
import static com.favas.myapplication.activity.Oprate.ALARM_READ;
import static com.favas.myapplication.activity.Oprate.ALARM_SETTING;
import static com.favas.myapplication.activity.Oprate.BATTERY;
import static com.favas.myapplication.activity.Oprate.BP_DETECTMODEL_READ;
import static com.favas.myapplication.activity.Oprate.BP_DETECTMODEL_SETTING;
import static com.favas.myapplication.activity.Oprate.BP_DETECTMODEL_SETTING_ADJUSTE;
import static com.favas.myapplication.activity.Oprate.BP_DETECTMODEL_SETTING_ADJUSTE_CANCEL;
import static com.favas.myapplication.activity.Oprate.BP_DETECT_START;
import static com.favas.myapplication.activity.Oprate.BP_DETECT_STOP;
import static com.favas.myapplication.activity.Oprate.CAMERA_START;
import static com.favas.myapplication.activity.Oprate.CAMERA_STOP;
import static com.favas.myapplication.activity.Oprate.CHECK_WEAR_SETING_CLOSE;
import static com.favas.myapplication.activity.Oprate.CHECK_WEAR_SETING_OPEN;
import static com.favas.myapplication.activity.Oprate.CLEAR_DEVICE_DATA;
import static com.favas.myapplication.activity.Oprate.COUNT_DOWN_APP;
import static com.favas.myapplication.activity.Oprate.COUNT_DOWN_APP_READ;
import static com.favas.myapplication.activity.Oprate.COUNT_DOWN_WATCH;
import static com.favas.myapplication.activity.Oprate.DETECT_PTT;
import static com.favas.myapplication.activity.Oprate.DETECT_START_ECG;
import static com.favas.myapplication.activity.Oprate.DETECT_STOP_ECG;
import static com.favas.myapplication.activity.Oprate.DEVICE_CONTROL_PHONE;
import static com.favas.myapplication.activity.Oprate.DEVICE_COUSTOM_READ;
import static com.favas.myapplication.activity.Oprate.DEVICE_COUSTOM_SETTING;
import static com.favas.myapplication.activity.Oprate.DISCONNECT;
import static com.favas.myapplication.activity.Oprate.FATIGUE_CLOSE;
import static com.favas.myapplication.activity.Oprate.FATIGUE_OPEN;
import static com.favas.myapplication.activity.Oprate.FINDDEVICE_READ;
import static com.favas.myapplication.activity.Oprate.FINDDEVICE_SETTING_CLOSE;
import static com.favas.myapplication.activity.Oprate.FINDDEVICE_SETTING_OPEN;
import static com.favas.myapplication.activity.Oprate.FINDPHONE;
import static com.favas.myapplication.activity.Oprate.HEARTWRING_CLOSE;
import static com.favas.myapplication.activity.Oprate.HEARTWRING_OPEN;
import static com.favas.myapplication.activity.Oprate.HEARTWRING_READ;
import static com.favas.myapplication.activity.Oprate.HEART_DETECT_START;
import static com.favas.myapplication.activity.Oprate.HEART_DETECT_STOP;
import static com.favas.myapplication.activity.Oprate.HRV_ORIGIN_READ;
import static com.favas.myapplication.activity.Oprate.INSTITUTION_TRANSLATION;
import static com.favas.myapplication.activity.Oprate.LANGUAGE_CHINESE;
import static com.favas.myapplication.activity.Oprate.LANGUAGE_ENGLISH;
import static com.favas.myapplication.activity.Oprate.LONGSEAT_READ;
import static com.favas.myapplication.activity.Oprate.LONGSEAT_SETTING_CLOSE;
import static com.favas.myapplication.activity.Oprate.LONGSEAT_SETTING_OPEN;
import static com.favas.myapplication.activity.Oprate.LOW_POWER_CLOSE;
import static com.favas.myapplication.activity.Oprate.LOW_POWER_OPEN;
import static com.favas.myapplication.activity.Oprate.LOW_POWER_READ;
import static com.favas.myapplication.activity.Oprate.NIGHT_TURN_WRIST_CLOSE;
import static com.favas.myapplication.activity.Oprate.NIGHT_TURN_WRIST_CUSTOM_TIME;
import static com.favas.myapplication.activity.Oprate.NIGHT_TURN_WRIST_CUSTOM_TIME_LEVEL;
import static com.favas.myapplication.activity.Oprate.NIGHT_TURN_WRIST_OPEN;
import static com.favas.myapplication.activity.Oprate.NIGHT_TURN_WRIST_READ;
import static com.favas.myapplication.activity.Oprate.OAD;
import static com.favas.myapplication.activity.Oprate.PERSONINFO_SYNC;
import static com.favas.myapplication.activity.Oprate.PWD_COMFIRM;
import static com.favas.myapplication.activity.Oprate.PWD_MODIFY;
import static com.favas.myapplication.activity.Oprate.READ_HEALTH;
import static com.favas.myapplication.activity.Oprate.READ_HEALTH_DRINK;
import static com.favas.myapplication.activity.Oprate.READ_HEALTH_ORIGINAL;
import static com.favas.myapplication.activity.Oprate.READ_HEALTH_ORIGINAL_FROM;
import static com.favas.myapplication.activity.Oprate.READ_HEALTH_ORIGINAL_SINGLEDAY;
import static com.favas.myapplication.activity.Oprate.READ_HEALTH_SLEEP;
import static com.favas.myapplication.activity.Oprate.READ_HEALTH_SLEEP_FROM;
import static com.favas.myapplication.activity.Oprate.READ_HEALTH_SLEEP_SINGLEDAY;
import static com.favas.myapplication.activity.Oprate.S22_READ_DATA;
import static com.favas.myapplication.activity.Oprate.S22_READ_STATE;
import static com.favas.myapplication.activity.Oprate.S22_SETTING_STATE_CLOSE;
import static com.favas.myapplication.activity.Oprate.S22_SETTING_STATE_OPEN;
import static com.favas.myapplication.activity.Oprate.SCREEN_LIGHT_READ;
import static com.favas.myapplication.activity.Oprate.SCREEN_LIGHT_SETTING;
import static com.favas.myapplication.activity.Oprate.SCREEN_STYLE_READ;
import static com.favas.myapplication.activity.Oprate.SCREEN_STYLE_SETTING;
import static com.favas.myapplication.activity.Oprate.SHOW_SP;
import static com.favas.myapplication.activity.Oprate.SOCIAL_MSG_READ;
import static com.favas.myapplication.activity.Oprate.SOCIAL_MSG_SEND;
import static com.favas.myapplication.activity.Oprate.SOCIAL_MSG_SETTING;
import static com.favas.myapplication.activity.Oprate.SOCIAL_PHONE_IDLE_OR_OFFHOOK;
import static com.favas.myapplication.activity.Oprate.SPO2H_AUTO_DETECT_CLOSE;
import static com.favas.myapplication.activity.Oprate.SPO2H_AUTO_DETECT_OPEN;
import static com.favas.myapplication.activity.Oprate.SPO2H_AUTO_DETECT_READ;
import static com.favas.myapplication.activity.Oprate.SPO2H_CLOSE;
import static com.favas.myapplication.activity.Oprate.SPO2H_OPEN;
import static com.favas.myapplication.activity.Oprate.SPO2H_ORIGIN_READ;
import static com.favas.myapplication.activity.Oprate.SPORT_CURRENT_READ;
import static com.favas.myapplication.activity.Oprate.SPORT_MODE_ORIGIN_END;
import static com.favas.myapplication.activity.Oprate.SPORT_MODE_ORIGIN_READ;
import static com.favas.myapplication.activity.Oprate.SPORT_MODE_ORIGIN_READSTAUTS;
import static com.favas.myapplication.activity.Oprate.SPORT_MODE_ORIGIN_START;
import static com.favas.myapplication.activity.Oprate.WOMEN_READ;
import static com.favas.myapplication.activity.Oprate.WOMEN_SETTING;
import static com.favas.myapplication.activity.Oprate.oprateStr;

import static com.veepoo.protocol.model.enums.EFunctionStatus.SUPPORT;
import static com.veepoo.protocol.model.enums.EFunctionStatus.SUPPORT_CLOSE;
import static com.veepoo.protocol.model.enums.EFunctionStatus.SUPPORT_OPEN;
import static com.veepoo.protocol.model.enums.ELanguage.CHINA;

/**
 * Created by timaimee on 2017/2/8.
 */
public class OperaterActivity extends Activity implements AdapterView.OnItemClickListener {
    private final static String TAG = OperaterActivity.class.getSimpleName();



   private Db db;

    TextView tv1, tv2, tv3,txt1;
    GridView mGridView;
    List<Map<String, String>> mGridData = new ArrayList<>();
    GridAdatper mGridAdapter;
    Context mContext = OperaterActivity.this;
    private String deviceaddress;
    boolean isSleepPrecision = false;
    Message msg;
    int count=0,datachange_count_half_hour=0,len_arr=0;

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String s = msg.obj.toString();
//            Toast.makeText(mContext, s, Toast.LENGTH_LONG).show();

            switch (msg.what) {
                case 1:
                    tv1.setText(s + "\n");
                    break;
                case 2:
                    tv2.setText(s + "\n");
                    break;
                case 3:
                    tv3.setText(s + "\n");
                    break;
            }
        }
    };
    WriteResponse writeResponse = new WriteResponse();


    /**
     * 密码验证获取以下信息
     */
    int watchDataDay = 3;
    int contactMsgLength = 0;
    int allMsgLenght = 4;
    private int deviceNumber = -1;
    private String deviceVersion;
    private String deviceTestVersion;
    boolean isOadModel = false;
    boolean isNewSportCalc = false;
    boolean isInPttModel = false;

    int  len_cardiac_loads=0;

    private ArrayList<TableBP_30MN> list;


    String MSG_Heart="",Msg_today_full="";
    int special_cnt=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operate);

        db= Room.databaseBuilder(getApplicationContext(),Db.class,"Remo").allowMainThreadQueries().build();




        mContext = getApplicationContext();
        deviceaddress = getIntent().getStringExtra("deviceaddress");

        txt1 = (TextView) super.findViewById(R.id.txt1);
        tv1 = (TextView) super.findViewById(R.id.tv1);
        tv2 = (TextView) super.findViewById(R.id.tv2);
        tv3 = (TextView) super.findViewById(R.id.tv3);
        initGridView();
        listenDeviceCallbackData();


        txt1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {








           //    db.getDAO_TbleBP_30MN().Add_Full_Record(list);
           //     int len=     db.getDAO_TbleBP_30MN().getsize();
//
             //   WriteFile("testing"+"\n\n\n"+Msg_today_full,"test1.txt");


          //     WriteFile("BP  datas of specif date"+"\n\n\n"+Msg_today_full,"specific_day_data.txt");

                Toast.makeText(getApplicationContext(),""+ special_cnt+"--"+Msg_today_full,Toast.LENGTH_LONG).show();

                return false;
            }
        });

    }

    private void initGridView() {
        mGridView = (GridView) findViewById(R.id.main_gridview);
        for (int i = 0; i < oprateStr.length; i++) {
            Map<String, String> map = new HashMap<>();
            map.put("str", oprateStr[i]);
            mGridData.add(map);
        }
        mGridAdapter = new GridAdatper(this, mGridData);
        mGridView.setAdapter(mGridAdapter);
        mGridView.setOnItemClickListener(this);
    }


    @Override
    public void onItemClick(final AdapterView<?> parent, View view, int position, long id) {
        String oprater = mGridData.get(position).get("str");
        Toast.makeText(mContext,"item clicked="+ oprater, Toast.LENGTH_SHORT).show();
        tv1.setText("");
        tv2.setText("");
        tv3.setText("");


     //   txt1.setText(""+oprater);
txt1.setText(""+Msg_today_full);

        if (oprater.equals(HEART_DETECT_START)) {


            VPOperateManager.getMangerInstance(mContext).startDetectHeart(writeResponse, new IHeartDataListener() {

                @Override
                public void onDataChange(HeartData heart) {

                    count=count+1;
                    String message = "heart:\n" + heart.toString();

                    txt1.setText(""+heart.getData());
                   // heart.getData().

                    Logger.t(TAG).i(message);
                    sendMsg(message, 1);
                    MSG_Heart=MSG_Heart+message;

                    if(count ==25)
                    {

                        WriteFile(MSG_Heart,"LiveData.txt");
                    }



                }
            });
        } else if (oprater.equals(LOW_POWER_READ)) {
            VPOperateManager.getMangerInstance(mContext).readLowPower(writeResponse, new ILowPowerListener() {
                @Override
                public void onLowpowerDataDataChange(LowPowerData lowPowerData) {
                    String message = "onLowpowerDataDataChange read:\n" + lowPowerData.toString();



                    Logger.t(TAG).i(message);
                    Log.e("bismillahi",message);
                }
            });
        } else if (oprater.equals(LOW_POWER_OPEN)) {
            VPOperateManager.getMangerInstance(mContext).settingLowpower(writeResponse, new ILowPowerListener() {
                @Override
                public void onLowpowerDataDataChange(LowPowerData lowPowerData) {
                    String message = "onLowpowerDataDataChange open:\n" + lowPowerData.toString();
                    Logger.t(TAG).i(message);
                }
            }, true);
        } else if (oprater.equals(LOW_POWER_CLOSE)) {
            VPOperateManager.getMangerInstance(mContext).settingLowpower(writeResponse, new ILowPowerListener() {
                @Override
                public void onLowpowerDataDataChange(LowPowerData lowPowerData) {
                    String message = "onLowpowerDataDataChange close:\n" + lowPowerData.toString();
                    Logger.t(TAG).i(message);
                }
            }, false);
        } else if (oprater.equals(DETECT_PTT)) {

            Intent intent = new Intent(OperaterActivity.this, PttActivity.class);
            intent.putExtra("inPttModel", isInPttModel);
            startActivity(intent);
            

        } else if (oprater.equals(DETECT_START_ECG) || oprater.equals(DETECT_STOP_ECG)) {
            startActivity(new Intent(OperaterActivity.this, EcgDetectActivity.class));
        } else if (oprater.equals(HEART_DETECT_STOP)) {
            Logger.t(TAG).i("HEART_DETECT_STOP");
            VPOperateManager.getMangerInstance(mContext).stopDetectHeart(writeResponse);
        } else if (oprater.equals(BP_DETECT_START)) {
            tv1.setText(BP_DETECT_START + ",Wait 50s...");
            VPOperateManager.getMangerInstance(mContext).startDetectBP(writeResponse, new IBPDetectDataListener() {
                @Override
                public void onDataChange(BpData bpData) {

                    String message = "BpData date statues:\n" + bpData.toString();
                    Logger.t(TAG).i(message);
                    sendMsg(message, 1);

                    txt1.setText(""+message);
                }
            }, EBPDetectModel.DETECT_MODEL_PUBLIC);
        } else if (oprater.equals(BP_DETECT_STOP)) {
            tv1.setText(BP_DETECT_STOP);
            VPOperateManager.getMangerInstance(mContext).stopDetectBP(writeResponse, EBPDetectModel.DETECT_MODEL_PUBLIC);
        } else if (oprater.equals(BP_DETECTMODEL_SETTING)) {
            boolean isOpenPrivateModel = true;
            boolean isAngioAdjuste = false;
            BpSetting bpSetting = new BpSetting(isOpenPrivateModel, 111, 88);
            //是否开启动态血压调整模式，功能标志位在密码验证的返回
            bpSetting.setAngioAdjuste(isAngioAdjuste);
            VPOperateManager.getMangerInstance(mContext).settingDetectBP(writeResponse, new IBPSettingDataListener() {
                @Override
                public void onDataChange(BpSettingData bpSettingData) {
                    String message = "BpSettingData:\n" + bpSettingData.toString();
                    Logger.t(TAG).i(message);
                    sendMsg(message, 1);
                }
            }, bpSetting);
        } else if (oprater.equals(BP_DETECTMODEL_READ)) {
            VPOperateManager.getMangerInstance(mContext).readDetectBP(writeResponse, new IBPSettingDataListener() {
                @Override
                public void onDataChange(BpSettingData bpSettingData) {


                //    bpSettingData.getHighPressure()

                    String message = "BpSettingData:\n" + bpSettingData.toString();

                    Logger.t(TAG).i(message);
                   sendMsg(message, 17595);

                }
            });
        } else if (oprater.equals(BP_DETECTMODEL_SETTING_ADJUSTE)) {
            boolean isOpenPrivateModel = false;
            boolean isAngioAdjuste = true;
            BpSetting bpSetting = new BpSetting(isOpenPrivateModel, 111, 88);
            //是否开启动态血压调整模式，功能标志位在密码验证的返回
            bpSetting.setAngioAdjuste(isAngioAdjuste);
            VPOperateManager.getMangerInstance(mContext).settingDetectBP(writeResponse, new IBPSettingDataListener() {
                @Override
                public void onDataChange(BpSettingData bpSettingData) {
                    String message = "BpSettingData:\n" + bpSettingData.toString();
                    Logger.t(TAG).i(message);
//                    sendMsg(message, 1);
                }
            }, bpSetting);
        } else if (oprater.equals(BP_DETECTMODEL_SETTING_ADJUSTE_CANCEL)) {
            boolean isOpenPrivateModel = false;
            boolean isAngioAdjuste = true;
            BpSetting bpSetting = new BpSetting(isOpenPrivateModel, 111, 88);
            //是否开启动态血压调整模式，功能标志位在密码验证的返回
            bpSetting.setAngioAdjuste(isAngioAdjuste);
            VPOperateManager.getMangerInstance(mContext).cancelAngioAdjust(writeResponse, new IBPSettingDataListener() {
                @Override
                public void onDataChange(BpSettingData bpSettingData) {
                    String message = "BpSettingData:\n" + bpSettingData.toString();
                    Logger.t(TAG).i(message);
                    sendMsg(message, 1);
                }
            }, bpSetting);
        } else if (oprater.equals(PWD_COMFIRM)) {



            boolean is24Hourmodel = false;
            VPOperateManager.getMangerInstance(mContext).confirmDevicePwd(writeResponse, new IPwdDataListener() {
                @Override
                public void onPwdDataChange(PwdData pwdData) {
                    String message = "PwdData:\n" + pwdData.toString();

                    Logger.t(TAG).i(message);
                    sendMsg(message, 1);

                    deviceNumber = pwdData.getDeviceNumber();
                    deviceVersion = pwdData.getDeviceVersion();
                    deviceTestVersion = pwdData.getDeviceTestVersion();

                    txt1.setText(""+message);
                }
            }, new IDeviceFuctionDataListener() {
                @Override
                public void onFunctionSupportDataChange(FunctionDeviceSupportData functionSupport) {
                    String message = "FunctionDeviceSupportData:\n" + functionSupport.toString();
                    Logger.t(TAG).i(message);
                    sendMsg(message, 2);
                    EFunctionStatus newCalcSport = functionSupport.getNewCalcSport();
                    if (newCalcSport != null && newCalcSport.equals(SUPPORT)) {
                        isNewSportCalc = true;
                    } else {
                        isNewSportCalc = false;
                    }
                    watchDataDay = functionSupport.getWathcDay();
                    contactMsgLength = functionSupport.getContactMsgLength();
                    allMsgLenght = functionSupport.getAllMsgLength();
                    isSleepPrecision = functionSupport.getPrecisionSleep() == SUPPORT;
                 //   Toast.makeText(getApplicationContext(),""+watchDataDay,Toast.LENGTH_LONG).show();

                }
            }, new ISocialMsgDataListener() {
                @Override
                public void onSocialMsgSupportDataChange(FunctionSocailMsgData socailMsgData) {
                    String message = "FunctionSocailMsgData:\n" + socailMsgData.toString();
                    Logger.t(TAG).i(message);
                    sendMsg(message, 3);
                }
            }, new ICustomSettingDataListener() {
                @Override
                public void OnSettingDataChange(CustomSettingData customSettingData) {
                    String message = "FunctionCustomSettingData:\n" + customSettingData.toString();
                    Logger.t(TAG).i(message);
                    sendMsg(message, 4);
                }
            }, "3282", is24Hourmodel);

        } else if (oprater.equals(PWD_MODIFY)) {
            VPOperateManager.getMangerInstance(mContext).modifyDevicePwd(writeResponse, new IPwdDataListener() {
                @Override
                public void onPwdDataChange(PwdData pwd) {
                    String message = "PwdData:\n" + pwd.toString();
                    Logger.t(TAG).i(message);
                    sendMsg(message, 1);
                    txt1.setText("Password Changed");
                }
            }, "3282");
        } else if (oprater.equals(SPORT_CURRENT_READ)) {
            VPOperateManager.getMangerInstance(mContext).readSportStep(writeResponse, new ISportDataListener() {
                @Override
                public void onSportDataChange(SportData sportData) {
                    String message = "Current step count:\n" + sportData.toString();
                    Logger.t(TAG).i(message);
                    sendMsg(message, 1);
                    txt1.setText(sportData.getStep()+"----"+sportData.getKcal()+"---"+"\n"+message);
                }
            });
        } else if (oprater.equals(PERSONINFO_SYNC)) {
            VPOperateManager.getMangerInstance(mContext).syncPersonInfo(writeResponse, new IPersonInfoDataListener() {
                @Override
                public void OnPersoninfoDataChange(EOprateStauts EOprateStauts) {
                    String message = "同步个人信息:\n" + EOprateStauts.toString();
                    Logger.t(TAG).i(message);
                    sendMsg(message, 1);
                }
            }, new PersonInfoData(ESex.MAN, 178, 60, 20, 8000));
        } else if (oprater.equals(CAMERA_START)) {
            VPOperateManager.getMangerInstance(mContext).startCamera(writeResponse, new ICameraDataListener() {
                @Override
                public void OnCameraDataChange(ECameraStatus oprateStauts) {

                }


            });
        } else if (oprater.equals(CAMERA_STOP)) {
            VPOperateManager.getMangerInstance(mContext).stopCamera(writeResponse, new ICameraDataListener() {
                @Override
                public void OnCameraDataChange(ECameraStatus oprateStauts) {

                }


            });
        } else if (oprater.equals(ALARM_SETTING)) {
            List<AlarmSetting> alarmSettingList = new ArrayList<>(3);

            AlarmSetting alarmSetting1 = new AlarmSetting(14, 10, true);
            AlarmSetting alarmSetting2 = new AlarmSetting(15, 20, true);
            AlarmSetting alarmSetting3 = new AlarmSetting(16, 30, true);

            alarmSettingList.add(alarmSetting1);
            alarmSettingList.add(alarmSetting2);
            alarmSettingList.add(alarmSetting3);

            VPOperateManager.getMangerInstance(mContext).settingAlarm(writeResponse, new IAlarmDataListener() {
                @Override
                public void onAlarmDataChangeListener(AlarmData alarmData) {
                    String message = "设置闹钟:\n" + alarmData.toString();
                    Logger.t(TAG).i(message);
                    sendMsg(message, 1);
                }
            }, alarmSettingList);
        } else if (oprater.equals(ALARM_READ)) {
            VPOperateManager.getMangerInstance(mContext).readAlarm(writeResponse, new IAlarmDataListener() {
                @Override
                public void onAlarmDataChangeListener(AlarmData alarmData) {
                    String message = "读取闹钟:\n" + alarmData.toString();
                    Logger.t(TAG).i(message);
                    sendMsg(message, 1);
                }
            });
        } else if (oprater.equals(ALARM_NEW_READ)) {
            VPOperateManager.getMangerInstance(mContext).readAlarm2(writeResponse, new IAlarm2DataListListener() {
                @Override
                public void onAlarmDataChangeListListener(AlarmData2 alarmData2) {
                    String message = "读取闹钟[新版]:\n" + alarmData2.toString();
                    Logger.t(TAG).i(message);
                    sendMsg(message, 1);
                }
            });
        } else if (oprater.equals(ALARM_NEW_DELETE)) {
            int deleteID = 1;
            Alarm2Setting alarm2Setting = getMultiAlarmSetting();
            alarm2Setting.setAlarmId(deleteID);
            VPOperateManager.getMangerInstance(mContext).deleteAlarm2(writeResponse, new IAlarm2DataListListener() {
                @Override
                public void onAlarmDataChangeListListener(AlarmData2 alarmData2) {
                    String message = "删除闹钟[新版]:\n" + alarmData2.toString();
                    Logger.t(TAG).i(message);
                    sendMsg(message, 1);
                }
                //String bluetoothAddress, int alarmId, int alarmHour, int alarmMinute, String repeatStatus, int scene, String unRepeatDate, boolean isOpen
            }, alarm2Setting);
        } else if (oprater.equals(ALARM_NEW_ADD)) {
            Alarm2Setting alarm2Setting = getMultiAlarmSetting();
            VPOperateManager.getMangerInstance(mContext).addAlarm2(writeResponse, new IAlarm2DataListListener() {
                @Override
                public void onAlarmDataChangeListListener(AlarmData2 alarmData2) {
                    String message = "添加闹钟[新版]:\n" + alarmData2.toString();
                    Logger.t(TAG).i(message);
                    sendMsg(message, 1);
                }
            }, alarm2Setting);
        } else if (oprater.equals(ALARM_NEW_MODIFY)) {
            Alarm2Setting alarm2Setting = getMultiAlarmSetting();
            int modifyID = 2;
            alarm2Setting.setAlarmId(modifyID);
            alarm2Setting.setAlarmHour(10);
            alarm2Setting.setOpen(false);
            VPOperateManager.getMangerInstance(mContext).modifyAlarm2(writeResponse, new IAlarm2DataListListener() {
                @Override
                public void onAlarmDataChangeListListener(AlarmData2 alarmData2) {
                    String message = "修改闹钟[新版]:\n" + alarmData2.toString();
                    Logger.t(TAG).i(message);
                    sendMsg(message, 1);
                }
            }, alarm2Setting);
        } else if (oprater.equals(LONGSEAT_SETTING_OPEN)) {
            VPOperateManager.getMangerInstance(mContext).settingLongSeat(writeResponse, new LongSeatSetting(10, 35, 11, 45, 60, true), new ILongSeatDataListener() {
                @Override
                public void onLongSeatDataChange(LongSeatData longSeat) {
                    String message = "设置久坐-打开:\n" + longSeat.toString();
                    Logger.t(TAG).i(message);
                    sendMsg(message, 1);
                }
            });
        } else if (oprater.equals(LONGSEAT_SETTING_CLOSE)) {
            VPOperateManager.getMangerInstance(mContext).settingLongSeat(writeResponse, new LongSeatSetting(10, 40, 12, 40, 40, false), new ILongSeatDataListener() {

                @Override
                public void onLongSeatDataChange(LongSeatData longSeat) {
                    String message = "设置久坐-关闭:\n" + longSeat.toString();
                    Logger.t(TAG).i(message);
                    sendMsg(message, 1);
                }
            });
        } else if (oprater.equals(LONGSEAT_READ)) {
            VPOperateManager.getMangerInstance(mContext).readLongSeat(writeResponse, new ILongSeatDataListener() {
                @Override
                public void onLongSeatDataChange(LongSeatData longSeat) {
                    String message = "设置久坐-读取:\n" + longSeat.toString();
                    Logger.t(TAG).i(message);
                    sendMsg(message, 1);
                }
            });
        } else if (oprater.equals(LANGUAGE_CHINESE)) {
            LanguageData languageData=new LanguageData() ;
            languageData.setLanguage(CHINA);
            VPOperateManager.getMangerInstance(mContext).settingDeviceLanguage(writeResponse, new ILanguageDataListener() {
                @Override
                public void onLanguageDataChange(LanguageData languageData) {
                    String message = "设置语言(中文):\n" + languageData.toString();
                    Logger.t(TAG).i(message);
                    sendMsg(message, 1);
                }
            }, CHINA);
        } else if (oprater.equals(LANGUAGE_ENGLISH)) {
            VPOperateManager.getMangerInstance(mContext).settingDeviceLanguage(writeResponse, new ILanguageDataListener() {
                @Override
                public void onLanguageDataChange(LanguageData languageData) {
                    String message = "设置语言(英文):\n" + languageData.toString();
                    Logger.t(TAG).i(message);
                    sendMsg(message, 1);
                }
            }, ELanguage.ENGLISH);
        } else if (oprater.equals(BATTERY)) {
            VPOperateManager.getMangerInstance(mContext).readBattery(writeResponse, new IBatteryDataListener() {
                @Override
                public void onDataChange(BatteryData batteryData) {
                    String message = "电池等级:\n" + batteryData.getBatteryLevel() + "\n" + "电量:" + batteryData.getBatteryLevel() * 25 + "%";
                    Logger.t(TAG).i(message);
                    sendMsg(message, 1);
                }
            });
        } else if (oprater.equals(NIGHT_TURN_WRIST_READ)) {
            VPOperateManager.getMangerInstance(mContext).readNightTurnWriste(writeResponse, new INightTurnWristeDataListener() {
                @Override
                public void onNightTurnWristeDataChange(NightTurnWristeData nightTurnWristeData) {
                    String message = "夜间转腕-读取:\n" + nightTurnWristeData.toString();
                    Logger.t(TAG).i(message);
                    sendMsg(message, 1);
                }
            });
        } else if (oprater.equals(NIGHT_TURN_WRIST_OPEN)) {
            VPOperateManager.getMangerInstance(mContext).settingNightTurnWriste(writeResponse, new INightTurnWristeDataListener() {
                @Override
                public void onNightTurnWristeDataChange(NightTurnWristeData nightTurnWristeData) {
                    String message = "夜间转腕-打开:\n" + nightTurnWristeData.toString();
                    Logger.t(TAG).i(message);
                    sendMsg(message, 1);
                }
            }, true);
        } else if (oprater.equals(NIGHT_TURN_WRIST_CLOSE)) {
            VPOperateManager.getMangerInstance(mContext).settingNightTurnWriste(writeResponse, new INightTurnWristeDataListener() {
                @Override
                public void onNightTurnWristeDataChange(NightTurnWristeData nightTurnWristeData) {
                    String message = "夜间转腕-关闭:\n" + nightTurnWristeData.toString();
                    Logger.t(TAG).i(message);
                    sendMsg(message, 1);
                }
            }, false);
        } else if (oprater.equals(NIGHT_TURN_WRIST_CUSTOM_TIME)) {
            final boolean isOpen = true;
            TimeData startTime = new TimeData(10, 0);
            TimeData endTime = new TimeData(20, 0);
            VPOperateManager.getMangerInstance(mContext).settingNightTurnWriste(writeResponse, new INightTurnWristeDataListener() {
                @Override
                public void onNightTurnWristeDataChange(NightTurnWristeData nightTurnWristeData) {
                    String message = "夜间转腕-" + isOpen + ":\n" + nightTurnWristeData.toString();
                    Logger.t(TAG).i(message);
                    sendMsg(message, 1);
                }
            }, isOpen, startTime, endTime);
        } else if (oprater.equals(NIGHT_TURN_WRIST_CUSTOM_TIME_LEVEL)) {
            final boolean isOpen = true;
            TimeData startTime = new TimeData(10, 0);
            TimeData endTime = new TimeData(20, 0);
            int level = 2;
            VPOperateManager.getMangerInstance(mContext).settingNightTurnWriste(writeResponse, new INightTurnWristeDataListener() {
                @Override
                public void onNightTurnWristeDataChange(NightTurnWristeData nightTurnWristeData) {
                    String message = "夜间转腕-" + isOpen + ":\n" + nightTurnWristeData.toString();
                    Logger.t(TAG).i(message);
                    sendMsg(message, 1);
                }
            }, new NightTurnWristSetting(isOpen, startTime, endTime, level));
        } else if (oprater.equals(DISCONNECT)) {
            VPOperateManager.getMangerInstance(mContext).disconnectWatch(writeResponse);
            finish();
        } else if (oprater.equals(FINDPHONE)) {
            VPOperateManager.getMangerInstance(mContext).settingFindPhoneListener(new IFindPhonelistener() {
                @Override
                public void findPhone() {
                    String message = "(监听到手环要查找手机)-where is the phone,make some noise!";
                    Logger.t(TAG).i(message);
                    sendMsg(message, 1);
                }
            });
        } else if (oprater.equals(DEVICE_COUSTOM_READ)) {
            VPOperateManager.getMangerInstance(mContext).readCustomSetting(writeResponse, new ICustomSettingDataListener() {
                @Override
                public void OnSettingDataChange(CustomSettingData customSettingData) {
                    String message = "个性化状态-公英制/时制(12/24)/5分钟测量开关(心率/血压)-读取:\n" + customSettingData.toString();
                    Logger.t(TAG).i(message);
                    sendMsg(message, 1);
                }
            });
        } else if (oprater.equals(DEVICE_COUSTOM_SETTING)) {
            boolean isHaveMetricSystem = true;
            boolean isMetric = true;
            boolean is24Hour = true;
            boolean isOpenAutoHeartDetect = true;
            boolean isOpenAutoBpDetect = true;
            EFunctionStatus isOpenSportRemain = EFunctionStatus.UNSUPPORT;
            EFunctionStatus isOpenVoiceBpHeart = EFunctionStatus.UNSUPPORT;
            EFunctionStatus isOpenFindPhoneUI = EFunctionStatus.UNSUPPORT;
            EFunctionStatus isOpenStopWatch = EFunctionStatus.UNSUPPORT;
            EFunctionStatus isOpenSpo2hLowRemind = EFunctionStatus.UNSUPPORT;
            EFunctionStatus isOpenWearDetectSkin = EFunctionStatus.UNSUPPORT;
            EFunctionStatus isOpenAutoInCall = EFunctionStatus.UNSUPPORT;
            EFunctionStatus isOpenAutoHRV = EFunctionStatus.UNSUPPORT;
            EFunctionStatus isOpenDisconnectRemind = EFunctionStatus.UNSUPPORT;
            CustomSetting customSetting = new CustomSetting(isHaveMetricSystem, isMetric, is24Hour, isOpenAutoHeartDetect, isOpenAutoBpDetect
                    , isOpenSportRemain, isOpenVoiceBpHeart, isOpenFindPhoneUI, isOpenStopWatch,
                    isOpenSpo2hLowRemind, isOpenWearDetectSkin, isOpenAutoInCall, isOpenAutoHRV, isOpenDisconnectRemind
            );
            customSetting.setIsOpenLongClickLockScreen(SUPPORT_CLOSE);
            VPOperateManager.getMangerInstance(mContext).changeCustomSetting(writeResponse, new ICustomSettingDataListener() {
                @Override
                public void OnSettingDataChange(CustomSettingData customSettingData) {
                    String message = "个性化状态-公英制/时制(12/24)/5分钟测量开关(心率/血压)-设置:\n" + customSettingData.toString();
                    Logger.t(TAG).i(message);
                    sendMsg(message, 1);
                }
            }, customSetting);
        } else if (oprater.equals(CHECK_WEAR_SETING_OPEN)) {
            CheckWearSetting checkWearSetting = new CheckWearSetting();
            checkWearSetting.setOpen(true);
            VPOperateManager.getMangerInstance(mContext).setttingCheckWear(writeResponse, new ICheckWearDataListener() {
                @Override
                public void onCheckWearDataChange(CheckWearData checkWearData) {
                    String message = "佩戴检测-打开:\n" + checkWearData.toString();
                    Logger.t(TAG).i(message);
                    sendMsg(message, 1);
                }
            }, checkWearSetting);
        } else if (oprater.equals(CHECK_WEAR_SETING_CLOSE)) {
            CheckWearSetting checkWearSetting = new CheckWearSetting();
            checkWearSetting.setOpen(false);
            VPOperateManager.getMangerInstance(mContext).setttingCheckWear(writeResponse, new ICheckWearDataListener() {
                @Override
                public void onCheckWearDataChange(CheckWearData checkWearData) {
                    String message = "佩戴检测-关闭:\n" + checkWearData.toString();
                    Logger.t(TAG).i(message);
                    sendMsg(message, 1);
                }
            }, checkWearSetting);
        } else if (oprater.equals(FINDDEVICE_SETTING_OPEN)) {
            VPOperateManager.getMangerInstance(mContext).settingFindDevice(writeResponse, new IFindDeviceDatalistener() {
                @Override
                public void onFindDevice(FindDeviceData findDeviceData) {
                    String message = "防丢-打开:\n" + findDeviceData.toString();
                    Logger.t(TAG).i(message);
                    sendMsg(message, 1);
                }
            }, true);
        } else if (oprater.equals(FINDDEVICE_SETTING_CLOSE)) {
            VPOperateManager.getMangerInstance(mContext).settingFindDevice(writeResponse, new IFindDeviceDatalistener() {
                @Override
                public void onFindDevice(FindDeviceData findDeviceData) {
                    String message = "防丢-关闭:\n" + findDeviceData.toString();
                    Logger.t(TAG).i(message);
                    sendMsg(message, 1);
                }
            }, false);
        } else if (oprater.equals(FINDDEVICE_READ)) {
            VPOperateManager.getMangerInstance(mContext).readFindDevice(writeResponse, new IFindDeviceDatalistener() {
                @Override
                public void onFindDevice(FindDeviceData findDeviceData) {
                    String message = "防丢-读取:\n" + findDeviceData.toString();
                    Logger.t(TAG).i(message);
                    sendMsg(message, 1);
                }
            });
        } else if (oprater.equals(SOCIAL_MSG_READ)) {
            VPOperateManager.getMangerInstance(mContext).readSocialMsg(writeResponse, new ISocialMsgDataListener() {
                @Override
                public void onSocialMsgSupportDataChange(FunctionSocailMsgData socailMsgData) {
                    String message = " 社交信息提醒-读取:\n" + socailMsgData.toString();
                    Logger.t(TAG).i(message);
                    sendMsg(message, 1);
                }
            });
        } else if (oprater.equals(SOCIAL_MSG_SETTING)) {
            FunctionSocailMsgData socailMsgData = new FunctionSocailMsgData();
            socailMsgData.setPhone(SUPPORT);
            socailMsgData.setMsg(SUPPORT);
            socailMsgData.setWechat(SUPPORT_OPEN);
            socailMsgData.setQq(SUPPORT_OPEN);
            socailMsgData.setFacebook(SUPPORT_CLOSE);
            socailMsgData.setTwitter(SUPPORT_OPEN);
            socailMsgData.setWhats(SUPPORT_OPEN);
            socailMsgData.setSina(EFunctionStatus.UNSUPPORT);
            socailMsgData.setFlickr(EFunctionStatus.UNSUPPORT);
            socailMsgData.setLinkin(EFunctionStatus.UNSUPPORT);
            socailMsgData.setLine(EFunctionStatus.UNSUPPORT);
            socailMsgData.setInstagram(EFunctionStatus.UNSUPPORT);
            socailMsgData.setSnapchat(EFunctionStatus.UNSUPPORT);
            socailMsgData.setSkype(EFunctionStatus.UNSUPPORT);
            VPOperateManager.getMangerInstance(mContext).settingSocialMsg(writeResponse, new ISocialMsgDataListener() {
                @Override
                public void onSocialMsgSupportDataChange(FunctionSocailMsgData socailMsgData) {
                    String message = " 社交信息提醒-设置:\n" + socailMsgData.toString();
                    Logger.t(TAG).i(message);
                    sendMsg(message, 1);
                }
            }, socailMsgData);
        } else if (oprater.equals(SOCIAL_MSG_SEND)) {
            /**电话,可以只传电话号码**/
            ContentSetting contentphoneSetting0 = new ContentPhoneSetting(ESocailMsg.PHONE, "0755-86562490");
            /**电话,传联系人姓名以及电话号码，最终显示的联系人姓名**/
            ContentSetting contentphoneSetting1 = new ContentPhoneSetting(ESocailMsg.PHONE, "深圳市维亿魄科技有限公司", "0755-86562490");

            /**短信，可以只传电话号码**/
            ContentSetting contentsmsSetting2 = new ContentSmsSetting(ESocailMsg.SMS, "0755-86562490", "公司研发的项目主要在医疗健康智能穿戴、智能家居、新型智能交友产品、飞机航模、智能安全锁五个领域方面");
            /**短信，传联系人姓名以及电话号码，最终显示的联系人姓名**/
            ContentSetting contentsmsSetting3 = new ContentSmsSetting(ESocailMsg.SMS, "深圳市维亿魄科技有限公司", "0755-86562490", "公司研发的项目主要在医疗健康智能穿戴、智能家居、新型智能交友产品、飞机航模、智能安全锁五个领域方面");

            /**第三方APP推送,发送前先通过密码验证获取FunctionSocailMsgData的状态**/
            ContentSetting contentsociaSetting4 = new ContentSocailSetting(ESocailMsg.WECHAT, "veepoo", "公司研发的项目主要在医疗健康智能穿戴、智能家居、新型智能交友产品、飞机航模、智能安全锁五个领域方面");
            VPOperateManager.getMangerInstance(mContext).sendSocialMsgContent(writeResponse, contentphoneSetting0);
        } else if (oprater.equals(SOCIAL_PHONE_IDLE_OR_OFFHOOK)) {
            VPOperateManager.getMangerInstance(mContext).offhookOrIdlePhone(writeResponse);
        } else if (oprater.equals(DEVICE_CONTROL_PHONE)) {
            VPOperateManager.getMangerInstance(mContext).settingDeviceControlPhone(new IDeviceControlPhoneModelState() {
                @Override
                public void inPttModel() {
                    String message = "手表提示:手表进入ptt模式\n";
                    Logger.t(TAG).i(message);
                }

                @Override
                public void outPttModel() {
                    String message = "手表提示:手表退出ptt模式\n";
                    Logger.t(TAG).i(message);
                }

                @Override
                public void rejectPhone() {
                    String message = "手表提示:请挂断来电\n";
                    Logger.t(TAG).i(message);
                    sendMsg(message, 1);
                }

                @Override
                public void cliencePhone() {
                    String message = "手表提示:请来电静音\n";
                    Logger.t(TAG).i(message);
                    sendMsg(message, 1);
                }

                @Override
                public void knocknotify(int type) {
                    String message = "手表提示:敲击提醒，1表示单击，2表示双击\n";
                    Logger.t(TAG).i(message);
                }

                @Override
                public void sos() {
                    String message = "手表提示:sos\n";
                    Logger.t(TAG).i(message);
                }

                public void nextMusic() {
                    String message = "手表提示:下一曲\n";
                    Logger.t(TAG).i(message);
                }

                public void previousMusic() {
                    String message = "手表提示:上一曲\n";
                    Logger.t(TAG).i(message);
                }

                public void pauseAndPlayMusic() {
                    String message = "手表提示:暂停和播放\n";
                    Logger.t(TAG).i(message);
                }

            });
        } else if (oprater.equals(CLEAR_DEVICE_DATA)) {
            VPOperateManager.getMangerInstance(mContext).clearDeviceData(writeResponse);
            finish();
        } else if (oprater.equals(HEARTWRING_READ)) {
            VPOperateManager.getMangerInstance(mContext).readHeartWarning(writeResponse, new IHeartWaringDataListener() {
                @Override
                public void onHeartWaringDataChange(HeartWaringData heartWaringData) {
                    String message = "心率报警-读取:\n" + heartWaringData.toString();
                    Logger.t(TAG).i(message);
                    sendMsg(message, 1);
                }
            });
        } else if (oprater.equals(HEARTWRING_OPEN)) {
            VPOperateManager.getMangerInstance(mContext).settingHeartWarning(writeResponse, new IHeartWaringDataListener() {
                @Override
                public void onHeartWaringDataChange(HeartWaringData heartWaringData) {
                    String message = "心率报警-打开:\n" + heartWaringData.toString();
                    Logger.t(TAG).i(message);
                    sendMsg(message, 1);
                }
            }, new HeartWaringSetting(120, 110, true));
        } else if (oprater.equals(HEARTWRING_CLOSE)) {
            VPOperateManager.getMangerInstance(mContext).settingHeartWarning(writeResponse, new IHeartWaringDataListener() {
                @Override
                public void onHeartWaringDataChange(HeartWaringData heartWaringData) {
                    String message = "心率报警-关闭:\n" + heartWaringData.toString();
                    Logger.t(TAG).i(message);
                    sendMsg(message, 1);
                }
            }, new HeartWaringSetting(120, 110, false));
        } else if (oprater.equals(SPO2H_OPEN)) {
            VPOperateManager.getMangerInstance(mContext).startDetectSPO2H(writeResponse, new ISpo2hDataListener() {
                @Override
                public void onSpO2HADataChange(Spo2hData spo2HData) {
                    String message = "Blood Oxygen-Start:\n" + spo2HData.toString();
                    Logger.t(TAG).i(message);
                    sendMsg(message, 1);
                    txt1.setText(spo2HData.getCheckingProgress() +"--"+spo2HData.getRateValue()+"--"+spo2HData.getDeviceState()+"--"+spo2HData.getValue()+"--"+spo2HData.isChecking()+"\n"+message);
                }
            }, new ILightDataCallBack() {
                @Override
                public void onGreenLightDataChange(int[] data) {
                    String message = "血氧-光电信号:\n" + Arrays.toString(data);
                    Logger.t(TAG).i(message);
                }
            });
        } else if (oprater.equals(SPO2H_CLOSE)) {
            VPOperateManager.getMangerInstance(mContext).stopDetectSPO2H(writeResponse, new ISpo2hDataListener() {
                @Override
                public void onSpO2HADataChange(Spo2hData spo2HData) {
                    String message = "血氧-结束:\n" + spo2HData.toString();
                    Logger.t(TAG).i(message);
                    sendMsg(message, 1);
                }
            });
        } else if (oprater.equals(SPO2H_AUTO_DETECT_READ))
            VPOperateManager.getMangerInstance(mContext).readSpo2hAutoDetect(writeResponse, new IAllSetDataListener() {
                @Override
                public void onAllSetDataChangeListener(AllSetData allSetData) {
                    String message = "血氧自动检测-读取\n" + allSetData.toString();
                    Logger.t(TAG).i(message);
                    sendMsg(message, 1);
                }
            });
        else if (oprater.equals(SPO2H_AUTO_DETECT_OPEN)) {
            int setting = 0, open = 1;
            AllSetSetting mAlarmSetting = new AllSetSetting(EAllSetType.SPO2H_NIGHT_AUTO_DETECT, 22, 0, 8, 0, setting, open);
            VPOperateManager.getMangerInstance(mContext).settingSpo2hAutoDetect(writeResponse, new IAllSetDataListener() {
                @Override
                public void onAllSetDataChangeListener(AllSetData allSetData) {
                    String message = "血氧自动检测-打开\n" + allSetData.toString();
                    Logger.t(TAG).i(message);
                    sendMsg(message, 1);
                }
            }, mAlarmSetting);
        } else if (oprater.equals(SPO2H_AUTO_DETECT_CLOSE)) {
            int setting = 0, colse = 0;
            AllSetSetting mAlarmSetting = new AllSetSetting(EAllSetType.SPO2H_NIGHT_AUTO_DETECT, 22, 0, 8, 0, setting, colse);
            VPOperateManager.getMangerInstance(mContext).settingSpo2hAutoDetect(writeResponse, new IAllSetDataListener() {
                @Override
                public void onAllSetDataChangeListener(AllSetData allSetData) {
                    String message = "血氧自动检测-打开\n" + allSetData.toString();
                    Logger.t(TAG).i(message);
                    sendMsg(message, 1);
                }
            }, mAlarmSetting);
        } else if (oprater.equals(FATIGUE_OPEN)) {
            VPOperateManager.getMangerInstance(mContext).startDetectFatigue(writeResponse, new IFatigueDataListener() {
                @Override
                public void onFatigueDataListener(FatigueData fatigueData) {
                    String message = "疲劳度-开始:\n" + fatigueData.toString();
                    Logger.t(TAG).i(message);
                    sendMsg(message, 1);
                }
            });
        } else if (oprater.equals(FATIGUE_CLOSE)) {
            VPOperateManager.getMangerInstance(mContext).stopDetectFatigue(writeResponse, new IFatigueDataListener() {
                @Override
                public void onFatigueDataListener(FatigueData fatigueData) {
                    String message = "疲劳度-结束:\n" + fatigueData.toString();
                    Logger.t(TAG).i(message);
                    sendMsg(message, 1);
                }
            });
        } else if (oprater.equals(WOMEN_SETTING)) {
            VPOperateManager.getMangerInstance(mContext).settingWomenState(writeResponse, new IWomenDataListener() {
                @Override
                public void onWomenDataChange(WomenData womenData) {
                    String message = "女性状态-设置:\n" + womenData.toString();
                    Logger.t(TAG).i(message);
                    sendMsg(message, 1);
                }
            }, new WomenSetting(EWomenStatus.PREING, new TimeData(2016, 3, 1), new TimeData(2017, 1, 14)));
        } else if (oprater.equals(WOMEN_READ)) {
            VPOperateManager.getMangerInstance(mContext).readWomenState(writeResponse, new IWomenDataListener() {
                @Override
                public void onWomenDataChange(WomenData womenData) {
                    String message = "女性状态-读取:\n" + womenData.toString();
                    Logger.t(TAG).i(message);
                    sendMsg(message, 1);
                }
            });
        } else if (oprater.equals(COUNT_DOWN_WATCH)) {
            int second = 7;
            boolean isOpenWatchUI = false;
            boolean isCountDownByWatch = false;
            CountDownSetting countDownSetting = new CountDownSetting(second, isOpenWatchUI, isCountDownByWatch);
            VPOperateManager.getMangerInstance(mContext).settingCountDown(writeResponse, countDownSetting, new ICountDownListener() {
                @Override
                public void OnCountDownDataChange(CountDownData countDownData) {
                    String message = "倒计时-watch:\n" + countDownData.toString();
                    Logger.t(TAG).i(message);
                    sendMsg(message, 1);
                }
            });
        } else if (oprater.equals(COUNT_DOWN_APP_READ)) {
            VPOperateManager.getMangerInstance(mContext).readCountDown(writeResponse, new ICountDownListener() {
                @Override
                public void OnCountDownDataChange(CountDownData countDownData) {
                    String message = "倒计时-读取:\n" + countDownData.toString();
                    Logger.t(TAG).i(message);
                    sendMsg(message, 1);
                }
            });
        } else if (oprater.equals(COUNT_DOWN_APP)) {
            int second = 11;
            boolean isOpenWatchUI = true;
            boolean isCountDownByWatch = false;
            CountDownSetting countDownSetting = new CountDownSetting(second, isOpenWatchUI, isCountDownByWatch);
            VPOperateManager.getMangerInstance(mContext).settingCountDown(writeResponse, countDownSetting, new ICountDownListener() {
                @Override
                public void OnCountDownDataChange(CountDownData countDownData) {
                    String message = "倒计时-App:\n" + countDownData.toString();
                    Logger.t(TAG).i(message);
                    sendMsg(message, 1);
                }
            });
        } else if (oprater.equals(AIM_SPROT_CALC)) {
            Intent intent = new Intent(OperaterActivity.this, AimSportCalcActivity.class);
            intent.putExtra("isnewsportcalc", isNewSportCalc);
            startActivity(intent);
        } else if (oprater.equals(SCREEN_LIGHT_SETTING)) {
            //默认的是【22:00-07:00】设置成2档，其他时间设置成4档，用户可以自定义
            VPOperateManager.getMangerInstance(mContext).settingScreenLight(writeResponse, new IScreenLightListener() {
                @Override
                public void onScreenLightDataChange(ScreenLightData screenLightData) {
                    String message = "屏幕调节数据-设置:" + screenLightData.toString();
                    Logger.t(TAG).i(message);
                    sendMsg(message, 1);
                }
            }, new ScreenSetting(22, 0, 7, 0, 2, 4));
        } else if (oprater.equals(SCREEN_LIGHT_READ)) {
            VPOperateManager.getMangerInstance(mContext).readScreenLight(writeResponse, new IScreenLightListener() {
                @Override
                public void onScreenLightDataChange(ScreenLightData screenLightData) {
                    String message = "屏幕调节数据-读取:" + screenLightData.toString();
                    Logger.t(TAG).i(message);
                    sendMsg(message, 1);
                }
            });
        } else if (oprater.equals(SCREEN_STYLE_READ)) {
            VPOperateManager.getMangerInstance(mContext).readScreenStyle(writeResponse, new IScreenStyleListener() {
                @Override
                public void onScreenStyleDataChange(ScreenStyleData screenLightData) {
                    String message = "屏幕样式-读取:" + screenLightData.toString();
                    Logger.t(TAG).i(message);
                    sendMsg(message, 1);
                }
            });
        } else if (oprater.equals(SCREEN_STYLE_SETTING)) {
            int screenstyle = 2;
            VPOperateManager.getMangerInstance(mContext).settingScreenStyle(writeResponse, new IScreenStyleListener() {
                @Override
                public void onScreenStyleDataChange(ScreenStyleData screenLightData) {
                    String message = "屏幕样式-设置:" + screenLightData.toString();
                    Logger.t(TAG).i(message);
                    sendMsg(message, 1);
                }
            }, screenstyle);
        } else if (oprater.equals(INSTITUTION_TRANSLATION)) {
            Intent intent = new Intent(OperaterActivity.this, InstitutionActivity.class);
            startActivity(intent);

        } else if (oprater.equals(READ_HEALTH_SLEEP)) {
            VPOperateManager.getMangerInstance(mContext).readSleepData(writeResponse, new ISleepDataListener() {
                        @Override
                        public void onSleepDataChange(SleepData sleepData) {
                            String message = "";
                            if (sleepData instanceof SleepPrecisionData && isSleepPrecision) {
                                SleepPrecisionData sleepPrecisionData = (SleepPrecisionData) sleepData;
                                message = "精准睡眠数据-返回:" + sleepPrecisionData.toString();
                            } else {
                                message = "普通睡眠数据-返回:" + sleepData.toString();
                            }
                            Logger.t(TAG).i(message);
                            sendMsg(message, 1);
                        }

                        @Override
                        public void onSleepProgress(float progress) {

                            String message = "睡眠数据-读取进度:" + "progress=" + progress;
                            Logger.t(TAG).i(message);
                        }

                        @Override
                        public void onSleepProgressDetail(String day, int packagenumber) {
                            String message = "睡眠数据-读取进度:" + "day=" + day + ",packagenumber=" + packagenumber;
                            Logger.t(TAG).i(message);
                        }

                        @Override
                        public void onReadSleepComplete() {
                            String message = "睡眠数据-读取结束";
                            Logger.t(TAG).i(message);
                        }
                    }, watchDataDay
            );
        } else if (oprater.equals(READ_HEALTH_SLEEP_FROM)) {
            int beforeYesterday = 2;
            VPOperateManager.getMangerInstance(mContext).readSleepDataFromDay(writeResponse, new ISleepDataListener() {
                        @Override
                        public void onSleepDataChange(SleepData sleepData) {
                            String message = "睡眠数据-返回:" + sleepData.toString();
                            Logger.t(TAG).i(message);
                            sendMsg(message, 1);
                        }

                        @Override
                        public void onSleepProgress(float progress) {
                            String message = "睡眠数据-读取进度:" + "progress=" + progress;
                            Logger.t(TAG).i(message);
                        }

                        @Override
                        public void onSleepProgressDetail(String day, int packagenumber) {
                            String message = "睡眠数据-读取进度:" + "day=" + day + ",packagenumber=" + packagenumber;
                            Logger.t(TAG).i(message);
                        }

                        @Override
                        public void onReadSleepComplete() {
                            String message = "睡眠数据-读取结束";
                            Logger.t(TAG).i(message);
                        }
                    }
                    , beforeYesterday, watchDataDay);
        } else if (oprater.equals(READ_HEALTH_SLEEP_SINGLEDAY)) {
            int yesterday = 1;
            VPOperateManager.getMangerInstance(mContext).readSleepDataSingleDay(writeResponse, new ISleepDataListener() {
                @Override
                public void onSleepDataChange(SleepData sleepData) {
                    String message = "睡眠数据-返回:" + sleepData.toString();
                    Logger.t(TAG).i(message);
                    sendMsg(message, 1);
                }

                @Override
                public void onSleepProgress(float progress) {
                    String message = "睡眠数据-读取进度:" + "progress=" + progress;
                    Logger.t(TAG).i(message);
                }

                @Override
                public void onSleepProgressDetail(String day, int packagenumber) {
                    String message = "睡眠数据-读取进度:" + "day=" + day + ",packagenumber=" + packagenumber;
                    Logger.t(TAG).i(message);
                }

                @Override
                public void onReadSleepComplete() {
                    String message = "睡眠数据-读取结束";
                    Logger.t(TAG).i(message);
                }
            }, yesterday, watchDataDay);
        } else if (oprater.equals(READ_HEALTH_DRINK)) {
            VPOperateManager.getMangerInstance(mContext).readDrinkData(writeResponse, new IDrinkDataListener() {
                @Override
                public void onDrinkDataChange(int packagenumber, DrinkData drinkdata) {
                    String message = "饮酒数据-返回:" + drinkdata.toString();
                    Logger.t(TAG).i(message);
                    sendMsg(message, 1);
                }

                @Override
                public void onReadDrinkComplete() {
                    String message = "饮酒数据-读取结束";
                    Logger.t(TAG).i(message);
                }
            });
        } else if (oprater.equals(READ_HEALTH_ORIGINAL)) {

            new GetResults_5min_interval().execute();
            /*
            VPOperateManager.getMangerInstance(mContext).readOriginData(writeResponse, new IOriginData3Listener() {

                @Override
                public void onOriginFiveMinuteListDataChange(List<OriginData3> originDataList) {
                    String message = "Health data - returns:" + originDataList.toString();
                    Logger.t(TAG).i(message);


                    txt1.setText( ""+originDataList.get(0).getDisValue());
                }

                @Override
                public void onOriginHalfHourDataChange(OriginHalfHourData originHalfHourDataList) {
                    String message = "健康数据[30分钟]-返回:" + originHalfHourDataList.toString();
                    Logger.t(TAG).i(message);
                }

                @Override
                public void onOriginHRVOriginListDataChange(List<HRVOriginData> originHrvDataList) {
                    String message = "健康数据[30分钟]-HRV:" + originHrvDataList.size();
                    Logger.t(TAG).i(message);
                }

                @Override
                public void onOriginSpo2OriginListDataChange(List<Spo2hOriginData> originSpo2hDataList) {

                }


                @Override
                public void onReadOriginProgress(float progress) {
                    String message = "健康数据[5分钟]-读取进度:" + progress;
                    Logger.t(TAG).i(message);
                }

                @Override
                public void onReadOriginProgressDetail(int date, String dates, int all, int num) {
                    String message = "健康数据[5分钟]-读取进度detail:" + date + ",dates=" + dates;
                    Logger.t(TAG).i(message);
                }


                @Override
                public void onReadOriginComplete() {
                    String message = "健康数据-读取结束";
                    Logger.t(TAG).i(message);
                }
            }, 3);


             */
        } else if (oprater.equals(READ_HEALTH_ORIGINAL_FROM)) {


            new GetResults_Specific_Day().execute();
            /*
            int day = 2;
            VPOperateManager.getMangerInstance(mContext).readOriginDataFromDay(writeResponse, new IOriginData3Listener() {
                @Override
                public void onReadOriginProgressDetail(int i, String s, int i1, int i2) {

                }

                @Override
                public void onReadOriginProgress(float v) {

                }

                @Override
                public void onReadOriginComplete() {

                }





                @Override
                public void onOriginFiveMinuteListDataChange(List<OriginData3> list) {

                    int len= list.size();
               //     len_arr=     list.size();
                    for(int j=0;j<=len;j++)
                    {
                        String message=""+  list.get(j).getHighValue();

                        Logger.t(TAG).i("bismillah.."+message);

                       // Logger.t(TAG).i(message);
                      //  Msg_today_full="4---"+Msg_today_full+message;
                    }





                }

                @Override
                public void onOriginHalfHourDataChange(OriginHalfHourData originHalfHourData) {


                int len=    originHalfHourData.getHalfHourBps().size();



                    
                    for(int j=0;j<=len;j++)
                    {
                        String message=""+   originHalfHourData.getHalfHourBps().get(j).getDate();

                        Logger.t(TAG).i(message);


                        Msg_today_full="4---"+Msg_today_full+message;

                        Logger.t(TAG).i("bismillah--"+message);
                    }







                }

                @Override
                public void onOriginHRVOriginListDataChange(List<HRVOriginData> list) {






                }

                @Override
                public void onOriginSpo2OriginListDataChange(List<Spo2hOriginData> list) {
                    len_arr=     list.size();

                }
*/

                /*

                @Override
                public void onOringinFiveMinuteDataChange(OriginData originData) {
                    String message = "Health data [5 minutes]-return:" + originData.toString();
                    Logger.t(TAG).i(message);


                    Msg_today_full="4---"+Msg_today_full+message;
                }

                @Override
                public void onOringinHalfHourDataChange(OriginHalfHourData originHalfHourData) {
                    String message = "Health data [30 minutes]-return:\n" +
                            "\n" + originHalfHourData.toString();
                    Logger.t(TAG).i(message);
                    Msg_today_full="4---"+Msg_today_full+message;
                }

                @Override
                public void onReadOriginProgress(float progress) {
                    String message = "Health data [5 minutes]-read progress:" + progress;
                    Logger.t(TAG).i(message);
                    Msg_today_full="4---"+Msg_today_full+message;
                }

                @Override
                public void onReadOriginProgressDetail(int date, String dates, int all, int num) {
                    Msg_today_full="4---"+Msg_today_full;
                }

                @Override
                public void onReadOriginComplete() {
                    String message = "Health data-end of reading";
                    Logger.t(TAG).i(message);

                    Msg_today_full="4---"+Msg_today_full+message;
                   // watchDataDay
                }

                */
         //   }, day, 10, watchDataDay);


        }


        else if (oprater.equals(READ_HEALTH_ORIGINAL_SINGLEDAY)) {
            int today = 0;
            int originProtocolVersion = 3;
            IOriginProgressListener originDataListener = new IOriginDataListener() {
                @Override
                public void onOringinFiveMinuteDataChange(OriginData originData) {
                    String message = "健康数据[5分钟]-返回:" + originData.toString();
                    Logger.t(TAG).i(message);
                }

                @Override
                public void onOringinHalfHourDataChange(OriginHalfHourData originHalfHourData) {
                    String message = "健康数据[30分钟]-返回:" + originHalfHourData.toString();
                    Logger.t(TAG).i(message);
                }

                @Override
                public void onReadOriginProgress(float progress) {
                    String message = "健康数据[5分钟]-读取进度:" + progress;
                    Logger.t(TAG).i(message);
                }

                @Override
                public void onReadOriginProgressDetail(int date, String dates, int all, int num) {

                }

                @Override
                public void onReadOriginComplete() {
                    String message = "健康数据-读取结束";
                    Logger.t(TAG).i(message);
                }
            };
            IOriginProgressListener originData3Listener = new IOriginData3Listener() {
                @Override
                public void onOriginFiveMinuteListDataChange(List<OriginData3> originData3List) {
                    String message = "健康数据[5分钟]-返回:" + originData3List.size();
                    for (int i = 0; i < originData3List.size(); i++) {
                       String s = originData3List.get(i).toString();
                       Logger.t(TAG).i(s);


                   }
                    Logger.t(TAG).i(message);
                }

                @Override
                public void onOriginHalfHourDataChange(OriginHalfHourData originHalfHourData) {
                    String message = "健康数据[30分钟]-返回:" + originHalfHourData.toString();
                    Logger.t(TAG).i(message);

                }

                @Override
                public void onOriginHRVOriginListDataChange(List<HRVOriginData> originHrvDataList) {
                    String message = "健康数据[HRV]-返回:" + originHrvDataList.size();
//                    for (int i = 0; i < originHrvDataList.size(); i++) {
//                        String s = originHrvDataList.get(i).toString();
//                        Logger.t(TAG).i(s);
//                    }
                    Logger.t(TAG).i(message);
                }

                @Override
                public void onOriginSpo2OriginListDataChange(List<Spo2hOriginData> originSpo2hDataList) {
                    String message = "健康数据[Spo2h]-返回:" + originSpo2hDataList.size();
                    Spo2hOriginUtil spo2hOriginUtil = new Spo2hOriginUtil(originSpo2hDataList);
                    List<Map<String, Float>> tenMinuteData = spo2hOriginUtil.getTenMinuteData(ESpo2hDataType.TYPE_SLEEP);
                    for (int i = 0; i < originSpo2hDataList.size(); i++) {
                        String s = originSpo2hDataList.get(i).toString();
                        Logger.t(TAG).i(s);
                    }
                    Logger.t(TAG).i(message);
                }

                @Override
                public void onReadOriginProgressDetail(int day, String date, int allPackage, int currentPackage) {

                }

                @Override
                public void onReadOriginProgress(float progress) {
                    String message = "健康数据[5分钟]-读取进度:" + progress;
                    Logger.t(TAG).i(message);
                }

                @Override
                public void onReadOriginComplete() {
                    String message = "健康数据-读取结束";
                    Logger.t(TAG).i(message);
                }
            };
            IOriginProgressListener originProgressListener;
            if (originProtocolVersion == 3) {
                originProgressListener = originData3Listener;
            } else {
                originProgressListener = originDataListener;
            }
            VPOperateManager.getMangerInstance(mContext).readOriginDataSingleDay(writeResponse, originProgressListener, today, 1, watchDataDay);
        } else if (oprater.equals(READ_HEALTH)) {
            VPOperateManager.getMangerInstance(mContext).readAllHealthData(new IAllHealthDataListener() {
                @Override
                public void onProgress(float progress) {
                    String message = "onAllProgress:" + progress;
                    Logger.t(TAG).i(message);
                }

                @Override
                public void onOringinFiveMinuteDataChange(OriginData originData) {
                    String message = "onOringinFiveMinuteDataChange:" + originData;
                    Logger.t(TAG).i(message);
                }

                @Override
                public void onOringinHalfHourDataChange(OriginHalfHourData originHalfHourData) {
                    String message = "onOringinHalfHourDataChange:" + originHalfHourData;
                    Logger.t(TAG).i(message);
                }

                @Override
                public void onReadOriginComplete() {
                    String message = "onReadOriginComplete";
                    Logger.t(TAG).i(message);
                }

                @Override
                public void onSleepDataChange(SleepData sleepData) {
                    String message = "onSleepDataChange:" + sleepData;
                    Logger.t(TAG).i(message);
                }

                @Override
                public void onReadSleepComplete() {
                    String message = "onReadSleepComplete";
                    Logger.t(TAG).i(message);

                }
            }, watchDataDay);
        } else if (oprater.equals(OAD)) {
            if (deviceNumber < 0) {
                Toast.makeText(mContext, "请先通过密码验证，获取版本号!", Toast.LENGTH_LONG).show();
                return;
            }
            boolean isOadModel = getIntent().getBooleanExtra("isoadmodel", false);
            deviceaddress = getIntent().getStringExtra("deviceaddress");

            Intent intent = new Intent(OperaterActivity.this, OadActivity.class);
            intent.putExtra("deviceaddress", deviceaddress);
            intent.putExtra("isoadmodel", isOadModel);
            intent.putExtra("devicenumber", deviceNumber);
            intent.putExtra("deviceversion", deviceVersion);
            intent.putExtra("devicetestversion", deviceTestVersion);
            startActivity(intent);

        } else if (oprater.equals(SHOW_SP)) {
            String shareperence = VPOperateManager.getMangerInstance(mContext).traversalShareperence();
            Logger.t(TAG).i(shareperence);
        } else if (oprater.equals(SPORT_MODE_ORIGIN_END)) {
            VPOperateManager.getMangerInstance(mContext).stopSportModel(writeResponse, new ISportModelStateListener() {
                @Override
                public void onSportModelStateChange(SportModelStateData sportModelStateData) {
                    String message = "运动模式状态:" + sportModelStateData.toString();
                    Logger.t(TAG).i(message);
                }
            });
        } else if (oprater.equals(SPORT_MODE_ORIGIN_READSTAUTS)) {
            VPOperateManager.getMangerInstance(mContext).readSportModelState(writeResponse, new ISportModelStateListener() {
                @Override
                public void onSportModelStateChange(SportModelStateData sportModelStateData) {
                    String message = "运动模式状态" + sportModelStateData.toString();
                    Logger.t(TAG).i(message);
                }
            });
        } else if (oprater.equals(SPORT_MODE_ORIGIN_START)) {
            VPOperateManager.getMangerInstance(mContext).startSportModel(writeResponse, new ISportModelStateListener() {
                @Override
                public void onSportModelStateChange(SportModelStateData sportModelStateData) {
                    String message = "运动模式状态" + sportModelStateData.toString();
                    Logger.t(TAG).i(message);
                }
            });
        } else if (oprater.equals(SPORT_MODE_ORIGIN_READ)) {
            VPOperateManager.getMangerInstance(mContext).readSportModelOrigin(writeResponse, new ISportModelOriginListener() {
                @Override
                public void onReadOriginProgress(float progress) {
                    String message = "运动模式数据[读取进度]:" + progress;
                    Logger.t(TAG).i(message);
                }

                @Override
                public void onReadOriginProgressDetail(int day, String date, int allPackage, int currentPackage) {
                    String message = "运动模式数据[读取详情]:" + day +
                            ",allPackage=" + allPackage + ",currentPackage=" + currentPackage;
                    Logger.t(TAG).i(message);
                }

                @Override
                public void onHeadChangeListListener(SportModelOriginHeadData sportModelHeadData) {
                    String message = "运动模式数据[头部]:" + sportModelHeadData.toString();
                    Logger.t(TAG).i(message);
                }

                @Override
                public void onItemChangeListListener(List<SportModelOriginItemData> sportModelItemData) {
                    StringBuffer message = new StringBuffer();
                    message.append("运动模式数据[详细]:");
                    for (SportModelOriginItemData sportModelOriginItemData : sportModelItemData) {
                        message.append("\n");
                        message.append(sportModelOriginItemData.toString());
                    }
                    Logger.t(TAG).i(message.toString());

                }

                @Override
                public void onReadOriginComplete() {
                    String message = "运动模式数据[读取结束]";
                    Logger.t(TAG).i(message);
                }
            });
        } else if (oprater.equals(HRV_ORIGIN_READ)) {
            VPOperateManager.getMangerInstance(mContext).readHRVOrigin(writeResponse, new IHRVOriginDataListener() {
                @Override
                public void onReadOriginProgress(float progress) {
                    Logger.t(TAG).i("onReadOriginProgress=" + progress);
                }

                @Override
                public void onReadOriginProgressDetail(int day, String date, int allPackage, int currentPackage) {
                    Logger.t(TAG).i("onReadOriginProgressDetail,day=" + day + ",date=" + date + ",allPackage=" + allPackage + ",currentPackage=" + currentPackage);
                }

                @Override
                public void onHRVOriginListener(HRVOriginData hrvOriginData) {
                    Logger.t(TAG).i("onHRVOriginListener=" + hrvOriginData.toString());
                }

                @Override
                public void onDayHrvScore(int day, String date, int hrvSocre) {

                }

                @Override
                public void onReadOriginComplete() {
                    Logger.t(TAG).i("onReadOriginComplete");

                }
            }, watchDataDay);
        } else if (oprater.equals(S22_READ_DATA)) {
            TimeData timeData = new TimeData(2017, 9, 11, 8, 13, 20);
//            timeData.setCurrentTime();
            Logger.t(TAG).i("timeData:" + timeData.toString());
            VPOperateManager.getMangerInstance(mContext).readAutoDetectOriginDataFromS22(writeResponse, new IAutoDetectOriginDataListener() {

                @Override
                public void onAutoDetectOriginDataChangeListener(List<AutoDetectOriginData> autoDetectOriginDataList) {
                    for (AutoDetectOriginData autoDetectOriginData : autoDetectOriginDataList) {
                        Logger.t(TAG).i("autoDetectOriginData:" + autoDetectOriginData.toString());
                    }
                }
            }, timeData);
        } else if (oprater.equals(S22_READ_STATE)) {
            VPOperateManager.getMangerInstance(mContext).readAutoDetectStateFromS22(writeResponse, new ICustomProtocolStateListener() {

                @Override
                public void onS22AutoDetectStateChangeListener(AutoDetectStateData autoDetectStateData) {
                    Logger.t(TAG).i("autoDetectStateData:" + autoDetectStateData.toString());
                }
            });
        } else if (oprater.equals(S22_SETTING_STATE_OPEN)) {
            AutoDetectStateSetting autoDetectStateSetting = new AutoDetectStateSetting();
            autoDetectStateSetting.setSpo2h24Hour(SUPPORT_OPEN);
            VPOperateManager.getMangerInstance(mContext).setAutoDetectStateToS22(writeResponse, new ICustomProtocolStateListener() {
                @Override
                public void onS22AutoDetectStateChangeListener(AutoDetectStateData autoDetectStateData) {
                    Logger.t(TAG).i("autoDetectStateData:" + autoDetectStateData.toString());
                }
            }, autoDetectStateSetting);
        } else if (oprater.equals(S22_SETTING_STATE_CLOSE)) {
            AutoDetectStateSetting autoDetectStateSetting = new AutoDetectStateSetting();
            autoDetectStateSetting.setSpo2h24Hour(SUPPORT_CLOSE);
            VPOperateManager.getMangerInstance(mContext).setAutoDetectStateToS22(writeResponse, new ICustomProtocolStateListener() {
                @Override
                public void onS22AutoDetectStateChangeListener(AutoDetectStateData autoDetectStateData) {
                    Logger.t(TAG).i("autoDetectStateData:" + autoDetectStateData.toString());
                }
            }, autoDetectStateSetting);
        } else if (oprater.equals(SPO2H_ORIGIN_READ)) {
            VPOperateManager.getMangerInstance(mContext).readSpo2hOrigin(writeResponse, new ISpo2hOriginDataListener() {
                @Override
                public void onReadOriginProgress(float progress) {
                    Logger.t(TAG).i("onReadOriginProgress:" + progress);
                }

                @Override
                public void onReadOriginProgressDetail(int day, String date, int allPackage, int currentPackage) {
                    Logger.t(TAG).i("onReadOriginProgressDetail:allPackage=" + allPackage + ",currentPackage=" + currentPackage);
                }

                @Override
                public void onSpo2hOriginListener(Spo2hOriginData sportOriginData) {
                    Logger.t(TAG).i("Spo2hOriginData:" + sportOriginData.toString());
                }

                @Override
                public void onReadOriginComplete() {
                    Logger.t(TAG).i("onReadOriginComplete");
                }
            }, watchDataDay);
        }






    }

    private void WriteFile(String msg1,String sFileName) {



        if (ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.ACCESS_BACKGROUND_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(OperaterActivity.this,
                    new String[]{Manifest.permission.ACCESS_BACKGROUND_LOCATION}, 11);
        }
        else
        {
            File dir  = new File(Environment.getExternalStorageDirectory().getAbsolutePath()   + "/NEPTON");


            try{

                if (!dir.exists())
                {
                    dir.mkdir();
                }





                File gpxfile = new File(dir, sFileName);
                FileWriter writer = new FileWriter(gpxfile);
                writer.append(msg1);
                writer.flush();
                writer.close();

                Toast.makeText(getApplicationContext(),"File created ",Toast.LENGTH_LONG).show();
            }
            catch (Exception e)
            {
                String error=e.getLocalizedMessage();
            }
        }



    }

    @NonNull
    private Alarm2Setting getMultiAlarmSetting() {
        int hour = 16;
        int minute = 33;
        int scene = 1;
        boolean isOpen = true;
        String repestStr = "1000010";
        String unRepeatDdate = "0000-00-00";
        return new Alarm2Setting(hour, minute, repestStr, scene, unRepeatDdate, isOpen);
    }

    private void sendMsg(String message, int what) {
        msg = Message.obtain();
        msg.what = what;
        msg.obj = message;
        mHandler.sendMessage(msg);
    }

    /**
     * 写入的状态返回
     */
    class WriteResponse implements IBleWriteResponse {

        @Override
        public void onResponse(int code) {
            Logger.t(TAG).i("write cmd status:" + code);

        }
    }

    /**
     *
     * 	密码验证之前，要调用这个方法
     * 	因为在密码验证之后，inPttModel/outPttModel其中一个会有回调
     */
    public void listenDeviceCallbackData() {
        VPOperateManager.getMangerInstance(mContext).settingDeviceControlPhone(new IDeviceControlPhoneModelState() {
            @Override
            public void knocknotify(int type) {

            }

            @Override
            public void nextMusic() {

            }

            @Override
            public void previousMusic() {

            }

            @Override
            public void pauseAndPlayMusic() {

            }

            @Override
            public void rejectPhone() {

            }

            @Override
            public void cliencePhone() {

            }

            @Override
            public void inPttModel() {
                Logger.t(TAG).i("inPttModel");
                isInPttModel = true;
            }

            @Override
            public void outPttModel() {
                Logger.t(TAG).i("outPttModel");
                isInPttModel = false;
            }

            @Override
            public void sos() {

            }
        });
    }



    private class GetResults_Specific_Day extends AsyncTask<Void, Void, Void> {


        @Override
        protected Void doInBackground(Void... voids) {

            int day = 2;
            VPOperateManager.getMangerInstance(mContext).readOriginDataFromDay(writeResponse, new IOriginData3Listener() {
                @Override
                public void onReadOriginProgressDetail(int i, String s, int i1, int i2) {

                }

                @Override
                public void onReadOriginProgress(float v) {

                }

                @Override
                public void onReadOriginComplete() {

                }





                @Override
                public void onOriginFiveMinuteListDataChange(List<OriginData3> list) {

                   int len= list.size();

                    loop:      for(int j=0;j<=len;j++)
                    {








                   /*     int[] apneaResults=       list.get(j).getApneaResults();
                        int   len_apnea=apneaResults.length;

                        for(int k=0;k<len_apnea;k++)
                        {
                            int a;

                            try
                            {
                                a= apneaResults[k];
                            }
                            catch(Exception e)
                            {
                                a=0;
                            }

                            Msg_today_full=Msg_today_full+a;
                           if(len_apnea==k+1)
                            {
                                break loop;
                            }










                        }



                    */






/* ppgs
                        int[] ppgs=       list.get(j).getPpgs();

                        int   len_ppgs=ppgs.length;

                        for(int k=0;k<len_ppgs;k++)
                        {
                            int a;

                            try
                            {
                                a= ppgs[k];
                            }
                            catch(Exception e)
                            {
                                a=0;
                            }

                            Msg_today_full=Msg_today_full+a;
                            if(len_ppgs==k+1)
                            {
                                break loop;
                            }








                        }



 */


/*
                        int[] hypoxiaTimes=       list.get(j).getHypoxiaTimes();

                         int   len_hypoxia_times=hypoxiaTimes.length;

                        for(int k=0;k<len_hypoxia_times;k++)
                        {
                            int a;

                            try
                            {
                                a= hypoxiaTimes[k];
                            }
                            catch(Exception e)
                            {
                                a=0;
                            }

                            Msg_today_full=Msg_today_full+a;
                            if(len_hypoxia_times==k+1)
                            {
                                break loop;
                            }








                        }



 */
                        //cardiac loads
                        /*
                        int[] cardiacLoads=  list.get(j).getCardiacLoads();
                        len_cardiac_loads=cardiacLoads.length;

                        for(int k=0;k<len_cardiac_loads;k++)
                        {
                            int a;

                            try
                            {
                                a= cardiacLoads[k];
                            }
                            catch(Exception e)
                            {
                                a=0;
                            }

                            Msg_today_full=Msg_today_full+a;
                            if(len_cardiac_loads==k+1)
                            {
                                break loop;
                            }





                        }

                         */




                            //       BP  String message=""+  list.get(j).getHighValue();




                     // ecg
                    /*    int[] ecgs=list.get(j).getEcgs();
                          int len_ecg=ecgs.length;

                          for(int k=0;k<len_ecg;k++)
                          {

                              int a;

                              try
                              {
                                  a=ecgs[k];
                              }
                              catch(Exception e)
                              {
                                  a=0;
                              }


                              Msg_today_full=Msg_today_full+a;
                          }


                     */





                    //    Msg_today_full=Msg_today_full+ecgs.toString();












                       // Logger.t(TAG).i("ecg.."+message);

                      //  len_arr=    len_arr+message.length();

                        // Logger.t(TAG).i(message);

                    }





                }

                @Override
                public void onOriginHalfHourDataChange(OriginHalfHourData originHalfHourData) {


                  //     originHalfHourData.getAllStep();
               /*     List<HalfHourRateData> list=        originHalfHourData.getHalfHourRateDatas();

                    int len=    list.size();
                    special_cnt=len;

                    for(int j=0;j<len;j++)
                    {
                    int a=    list.get(j).getEcgCount();

                        Msg_today_full=Msg_today_full+a;

                    }

                */


/*
                    int len=    originHalfHourData.getHalfHourBps().size();




                    for(int j=0;j<=len;j++)
                    {
                        String message=""+   originHalfHourData.getHalfHourBps().get(j).getDate();

                        Logger.t(TAG).i(message);


                    //    Msg_today_full="4---"+Msg_today_full+message;

                        Logger.t(TAG).i("bismillah--"+message);
                    }






 */


                }

                @Override
                public void onOriginHRVOriginListDataChange(List<HRVOriginData> list) {







                    int len=         list.size();
                 // no records herer



                }

                @Override
                public void onOriginSpo2OriginListDataChange(List<Spo2hOriginData> list) {
                    int len=         list.size();
                    special_cnt=len;

                    for(int j=0;j<len;j++)
                    {
                       int a= list.get(j).getOxygenValue();
                        Msg_today_full=Msg_today_full+a;

                    }

                 //   list.get(0).getOxygenValue();

                }



            }, day, 10, watchDataDay);




            return null;
        }


    }



    private class GetResults_5min_interval extends AsyncTask<Void, Void, Void> {


        @Override
        protected Void doInBackground(Void... voids) {


            db.getDAO_TbleBP_30MN().Del_All_Record();

            list=new ArrayList<>();


            VPOperateManager.getMangerInstance(mContext).readOriginData(writeResponse, new IOriginData3Listener() {

                @Override
                public void onOriginFiveMinuteListDataChange(List<OriginData3> originDataList) {
                    String message = "Health data - returns:" + originDataList.toString();
                  //  Logger.t(TAG).i(message);

               int len=     originDataList.size();

            /*   for(int j=0;j<len;j++)
               {
                   originDataList.get(j).getEcgs();

               }


             */


                 //   Msg_today_full="1"+Msg_today_full+message;
                }

                @Override
                public void onOriginHalfHourDataChange(OriginHalfHourData originHalfHourDataList) {
                    String message = "Health data [30 minutes]-return:" + originHalfHourDataList.toString();
              //      Logger.t(TAG).i(message);




                   int len= originHalfHourDataList.getHalfHourBps().size();



                   for(int j=0;j< len;j++)
                   {
                       HalfHourBpData row= originHalfHourDataList.getHalfHourBps().get(j);

                    String hbp=""+   row.getHighValue();
                     String lbp=""+  row.getLowValue();
                      String dat= row.getDate();
                       String tim=""+ row.getTime();


                       db.getDAO_TbleBP_30MN().Add_Record(new TableBP_30MN(0,""+dat,""+tim,""+hbp,""+lbp));
                       //list.add(new TableBP_30MN(0,"s","d","d","df"));


                   }








                }

                @Override
                public void onOriginHRVOriginListDataChange(List<HRVOriginData> originHrvDataList) {
                    String message = "Health data [30 minutes]-HRV:" + originHrvDataList.size();
                    Logger.t(TAG).i(message);


                    int len= originHrvDataList.size();

/*

                    for(int j=0;j< len;j++)
                    {

                    }




                    for(HRVOriginData row:originHrvDataList)
                    {
                        Msg_today_full= Msg_today_full+"******"+row.getHrvValue();
                    }


                  */


                }

                @Override
                public void onOriginSpo2OriginListDataChange(List<Spo2hOriginData> originSpo2hDataList) {

                }


                @Override
                public void onReadOriginProgress(float progress) {
                    String message = "Health data [5 minutes]-read progress:" + progress;
                   Logger.t(TAG).i(message);
                   Msg_today_full="4---"+Msg_today_full+message;
                }

                @Override
                public void onReadOriginProgressDetail(int date, String dates, int all, int num) {
                    String message = "Health data [5 minutes]-reading progress detail\n"  + date + ",dates=" + dates;
                   Logger.t(TAG).i(message);
                //    Msg_today_full="5"+Msg_today_full+message;
                }


                @Override
                public void onReadOriginComplete() {
                    String message = "健康数据-读取结束";
                    Logger.t(TAG).i(message);
                //    Msg_today_full="6"+Msg_today_full+message;
                }
            }, 3);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }




}
