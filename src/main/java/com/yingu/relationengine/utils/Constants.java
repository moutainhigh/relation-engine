package com.yingu.relationengine.utils;

/**
 * Application constants.
 */
public final class Constants {

    //Regex for acceptable logins
    public static final String LOGIN_REGEX = "^[_'.@A-Za-z0-9-]*$";

    public static final String SYSTEM_ACCOUNT = "system";
    public static final String ANONYMOUS_USER = "anonymoususer";

    public static final String APPKEYS = "appKeys";

    public static final String AUTO_CALLBACK = "AUTO_CALLBACK";

    public static final String CONTACTS_ARRT_CODE = "SM-A088";
    public static final String CONTACTS_CHANNEL_CODE = "SM-I010";

    public static final String CONTACTS_SMS_CONTENT  = "SM-A087";       //短信内容

    public static final String CONTACTS_ADDRESS_LIST = "SM-A088";       //通讯录

    public static final String CONTACTS_CALL_RECORD  = "SM-A089";       //通话记录

    public static final String CONTACTS_SM_SMS_CONTENT  = "SM-I009";       //数美-短信查询服务

    public static final String CONTACTS_SM_ADDRESS_LIST = "SM-I010";       //数美-通讯录查询服务

    public static final String CONTACTS_SM_CALL_RECORD  = "SM-I011";       //数美-通话记录查询服务

    private Constants() {
    }
}
