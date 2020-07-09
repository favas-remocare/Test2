package com.favas.myapplication.activity;

/**
 * Created by Administrator on 2017/11/25.
 */

public interface Oprate {
    public final static String PWD_COMFIRM = "1. Verify Device password";
    public final static String PERSONINFO_SYNC = "2. Personal Information-Settings";
    public final static String SETTING_FIRST = " Previous operation 1, 2";
    public final static String PWD_MODIFY = "Modify Device password";
    public final static String HEART_DETECT_START = "Measuring heart rate-start";
    public final static String HEART_DETECT_STOP = "Measuring heart rate-end";
    public final static String BP_DETECT_START = "Measuring blood pressure-start";
    public final static String BP_DETECT_STOP = "Measuring blood pressure-stop";
    public final static String BP_DETECTMODEL_SETTING = "Blood pressure mode-settings";
    public final static String BP_DETECTMODEL_SETTING_ADJUSTE = "Blood Pressure Mode [Dynamic Adjustment]-Setting";
    public final static String BP_DETECTMODEL_SETTING_ADJUSTE_CANCEL = "Blood Pressure Mode [Dynamic Adjustment]-Cancel";
    public final static String BP_DETECTMODEL_READ = "Blood pressure mode-read";
    public final static String SPORT_CURRENT_READ = "Current step count-read";
    public final static String CAMERA_START = "Photo mode-start";
    public final static String CAMERA_STOP = "Photo mode-stop";
    public final static String ALARM_SETTING = "Alarm clock-setting";
    public final static String ALARM_READ = "Alarm clock-read";
    public final static String ALARM_NEW_READ = "New alarm clock-read";
    public final static String ALARM_NEW_ADD = "New alarm clock-add";
    public final static String ALARM_NEW_MODIFY = "New alarm clock-modified";
    public final static String ALARM_NEW_DELETE = "New alarm-delete";
    public final static String LONGSEAT_SETTING_OPEN = "久坐-打开";
    public final static String LONGSEAT_SETTING_CLOSE = "久坐-关闭";
    public final static String LONGSEAT_READ = "久坐-读取";
    public final static String LANGUAGE_CHINESE = "Language setting-Chinese";
    public final static String LANGUAGE_ENGLISH = "Language setting-English";
    public final static String BATTERY = "Battery status-read";
    public final static String NIGHT_TURN_WRIST_OPEN = "夜间转腕-打开";
    public final static String NIGHT_TURN_WRIST_CLOSE = "夜间转腕-关闭";
    public final static String NIGHT_TURN_WRIST_READ = "夜间转腕-读取";
    public final static String NIGHT_TURN_WRIST_CUSTOM_TIME = "夜间转腕-自定义时间";
    public final static String NIGHT_TURN_WRIST_CUSTOM_TIME_LEVEL = "夜间转腕-自定义时间和等级";
    public final static String FINDPHONE = "手机防丢";
    public final static String CHECK_WEAR_SETING_OPEN = "佩戴检测-打开";
    public final static String CHECK_WEAR_SETING_CLOSE = "佩戴检测-关闭";
    public final static String FINDDEVICE_SETTING_OPEN = "设备防丢-打开";
    public final static String FINDDEVICE_SETTING_CLOSE = "设备防丢-关闭";
    public final static String FINDDEVICE_READ = "设备防丢-读取";
    public final static String DEVICE_COUSTOM_READ = "个性化-读取";
    public final static String DEVICE_COUSTOM_SETTING = "个性化-设置";
    public final static String SOCIAL_MSG_SETTING = "社交消息提醒-设置";
    public final static String SOCIAL_MSG_READ = "社交消息提醒-读取设置";
    public final static String SOCIAL_MSG_SEND = "社交消息提醒-发送内容";
    public final static String SOCIAL_PHONE_IDLE_OR_OFFHOOK = "社交消息提醒-接听了来电";
    public final static String DEVICE_CONTROL_PHONE = "监听手环-挂断，静音";
    public final static String HEARTWRING_READ = "心率报警-读取";
    public final static String HEARTWRING_OPEN = "心率报警-打开";
    public final static String HEARTWRING_CLOSE = "心率报警-关闭";

    public final static String SPO2H_OPEN = "Blood Oxygen-Read";
    public final static String SPO2H_CLOSE = "Blood Oxygen-End";
    public final static String SPO2H_AUTO_DETECT_READ = "血氧自动检测-读取";
    public final static String SPO2H_AUTO_DETECT_OPEN = "血氧自动检测-打开";
    public final static String SPO2H_AUTO_DETECT_CLOSE = "血氧自动检测-关闭";

    public final static String FATIGUE_OPEN = "疲劳度-读取";
    public final static String FATIGUE_CLOSE = "疲劳度-结束";
    public final static String WOMEN_SETTING = "女性状态-设置";
    public final static String WOMEN_READ = "女性状态-读取";
    public final static String COUNT_DOWN_WATCH = "倒计时-手表单独使用";
    public final static String COUNT_DOWN_APP = "倒计时-App使用";
    public final static String COUNT_DOWN_APP_READ = "倒计时-读取";
    public final static String SCREEN_LIGHT_SETTING = "屏幕调节-设置";
    public final static String SCREEN_LIGHT_READ = "屏幕调节-读取";
    public final static String SCREEN_STYLE_READ = "屏幕样式-读取";
    public final static String SCREEN_STYLE_SETTING = "屏幕样式-设置";
    public final static String AIM_SPROT_CALC = "目标步数-计算";
    public final static String INSTITUTION_TRANSLATION = "公英制转换";
    public final static String READ_HEALTH_DRINK = "读取健康数据-饮酒";
    public final static String READ_HEALTH_SLEEP = "读取健康数据-睡眠";
    public final static String READ_HEALTH_SLEEP_FROM = "读取健康数据-睡眠-从哪天起";
    public final static String READ_HEALTH_SLEEP_SINGLEDAY = "读取健康数据-睡眠-读这天";
    public final static String READ_HEALTH_ORIGINAL = "Read health data - 5 minutes";
    public final static String READ_HEALTH_ORIGINAL_FROM = "Read health data - from what day";
    public final static String READ_HEALTH_ORIGINAL_SINGLEDAY = "Read Health Data - Read the Day";
    public final static String READ_HEALTH = "读取健康数据-全部";
    public final static String OAD = "Firmware upgrade";
    public final static String SHOW_SP = "显示sp";
    public final static String SPORT_MODE_ORIGIN_READ = "读取数据-运动模式";
    public final static String SPORT_MODE_ORIGIN_READSTAUTS = "读取状态-运动模式";
    public final static String SPORT_MODE_ORIGIN_START = "开启-运动模式";
    public final static String SPORT_MODE_ORIGIN_END = "结束-运动模式";
    public final static String SPO2H_ORIGIN_READ = "读取数据-血氧数据";
    public final static String HRV_ORIGIN_READ = "读取数据-HRV数据";
    public final static String CLEAR_DEVICE_DATA = "清除数据";
    public final static String DISCONNECT = "蓝牙连接-断开";
    public final static String DETECT_PTT = "PTT";
   // public final static String DETECT_START_ECG = "开始测量ECG";
    public final static String DETECT_START_ECG = "Start measuring ECG";
    public final static String DETECT_STOP_ECG = "结束测量ECG";
    public final static String LOW_POWER_READ = "Low power consumption-read";
    public final static String LOW_POWER_OPEN = "低功耗-开";
    public final static String LOW_POWER_CLOSE = "低功耗-关";
    public final static String S22_READ_DATA = "S22-数据读取";
    public final static String S22_READ_STATE = "S22-状态读取";
    public final static String S22_SETTING_STATE_OPEN = "S22-状态设置(开)";
    public final static String S22_SETTING_STATE_CLOSE = "S22-状态设置(关)";
    public final static String NONE = "NONE";
    public final static String[] oprateStr = new String[]{
            PWD_COMFIRM, PERSONINFO_SYNC, SETTING_FIRST, PWD_MODIFY,
            HEART_DETECT_START, HEART_DETECT_STOP, BP_DETECT_START, BP_DETECT_STOP, BP_DETECTMODEL_SETTING, BP_DETECTMODEL_READ,
            BP_DETECTMODEL_SETTING_ADJUSTE_CANCEL, BP_DETECTMODEL_SETTING_ADJUSTE,
            SPORT_CURRENT_READ, CAMERA_START, CAMERA_STOP, ALARM_SETTING, ALARM_READ, ALARM_NEW_READ, ALARM_NEW_ADD, ALARM_NEW_MODIFY, ALARM_NEW_DELETE,
            LONGSEAT_SETTING_OPEN, LONGSEAT_SETTING_CLOSE, LONGSEAT_READ, LANGUAGE_CHINESE, LANGUAGE_ENGLISH,
            BATTERY, NIGHT_TURN_WRIST_OPEN, NIGHT_TURN_WRIST_CLOSE, NIGHT_TURN_WRIST_READ, NIGHT_TURN_WRIST_CUSTOM_TIME, NIGHT_TURN_WRIST_CUSTOM_TIME_LEVEL,
            DEVICE_COUSTOM_READ, DEVICE_COUSTOM_SETTING, FINDPHONE,
            CHECK_WEAR_SETING_OPEN, CHECK_WEAR_SETING_CLOSE,
            FINDDEVICE_SETTING_OPEN, FINDDEVICE_SETTING_CLOSE, FINDDEVICE_READ,
            SOCIAL_MSG_SETTING, SOCIAL_MSG_READ, SOCIAL_MSG_SEND, DEVICE_CONTROL_PHONE, SOCIAL_PHONE_IDLE_OR_OFFHOOK, HEARTWRING_READ, HEARTWRING_OPEN, HEARTWRING_CLOSE,
            SPO2H_OPEN, SPO2H_CLOSE, SPO2H_AUTO_DETECT_READ, SPO2H_AUTO_DETECT_OPEN, SPO2H_AUTO_DETECT_CLOSE, FATIGUE_OPEN, FATIGUE_CLOSE, WOMEN_SETTING, WOMEN_READ, COUNT_DOWN_WATCH, COUNT_DOWN_APP, COUNT_DOWN_APP_READ, SCREEN_LIGHT_SETTING, SCREEN_LIGHT_READ, SCREEN_STYLE_READ, SCREEN_STYLE_SETTING, AIM_SPROT_CALC, INSTITUTION_TRANSLATION,
            READ_HEALTH_SLEEP, READ_HEALTH_SLEEP_FROM, READ_HEALTH_SLEEP_SINGLEDAY, READ_HEALTH_DRINK, READ_HEALTH_ORIGINAL,
            READ_HEALTH_ORIGINAL_FROM, READ_HEALTH_ORIGINAL_SINGLEDAY, READ_HEALTH,
            OAD, SHOW_SP, SPORT_MODE_ORIGIN_READ, SPORT_MODE_ORIGIN_READSTAUTS, SPORT_MODE_ORIGIN_START, SPORT_MODE_ORIGIN_END, SPO2H_ORIGIN_READ, HRV_ORIGIN_READ, CLEAR_DEVICE_DATA, DISCONNECT
            , DETECT_START_ECG, DETECT_STOP_ECG, NONE, LOW_POWER_READ, LOW_POWER_OPEN, LOW_POWER_CLOSE,S22_READ_DATA,S22_READ_STATE,S22_SETTING_STATE_OPEN,S22_SETTING_STATE_CLOSE,DETECT_PTT
    };
}
