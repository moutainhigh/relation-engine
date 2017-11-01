package com.yingu.relationengine.rpc.mq.channel;

import java.math.BigDecimal;
import java.util.List;

/**
 * <B>文件名称：</B><BR>
 * <B>文件描述：</B><BR>
 * <BR>
 * <B>版权声明：</B>(C)2016-2018<BR>
 * <B>公司部门：</B>东方银谷 研发二部 CBG<BR>
 * <B>创建时间：</B>2017/8/20<BR>
 *
 * @author 李云龙  liyunlong@yingu.com
 * @version 1.0
 **/


public class RequestThreeParty {
    private String globalId;
    private String name;
    private String idCard;
    private String phone;
    private String bankCard;
    private String deviceID;
    private String imei;
    private String idfa;
    //经度
    private BigDecimal longitude;
    //纬度
    private BigDecimal latitude;
    private List<ChannelBean> channels;

    //商户号
    private String secretId;
    //商户名
    private String secretName;
    //是否强制刷新
    private Boolean doesForceRefresh = false;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBankCard() {
        return bankCard;
    }

    public void setBankCard(String bankCard) {
        this.bankCard = bankCard;
    }

    public String getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getIdfa() {
        return idfa;
    }

    public void setIdfa(String idfa) {
        this.idfa = idfa;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public List<ChannelBean> getChannels() {
        return channels;
    }

    public void setChannels(List<ChannelBean> channels) {
        this.channels = channels;
    }

    public String getGlobalId() {
        return globalId;
    }

    public void setGlobalId(String globalId) {
        this.globalId = globalId;
    }

    public String getSecretId() {
        return secretId;
    }

    public void setSecretId(String secretId) {
        this.secretId = secretId;
    }

    public String getSecretName() {
        return secretName;
    }

    public void setSecretName(String secretName) {
        this.secretName = secretName;
    }

    public Boolean getDoesForceRefresh() {
        return doesForceRefresh;
    }

    public void setDoesForceRefresh(Boolean doesForceRefresh) {
        this.doesForceRefresh = doesForceRefresh;
    }
}
