package com.yingu.relationengine.repository;

import org.springframework.util.StringUtils;

/**
 */
public enum ThreePartyType {

    SUCCESS(                "SUCCESS",          "100", "成功",        "描述通过"),
    FAIL(                   "FAIL",             "200", "失败",        "拒绝此人");

    String name;
    String value;
    String alias;
    String desc;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    ThreePartyType(String name, String value, String alias, String desc) {
        this.name = name;
        this.value= value;
        this.alias= alias;
        this.desc = desc;
    }

    public static String getValue(String name){
        ThreePartyType os = ThreePartyType.valueOf(name);
        if (os == null) {
            return null;
        }
        return os.getValue();
    }

    public static String getAlias(String name){
        ThreePartyType os = ThreePartyType.valueOf(name);
        if (os == null) {
            return null;
        }
        return os.getAlias();
    }

    public static String getDesc(String name){
        ThreePartyType os = ThreePartyType.valueOf(name);
        if (os == null) {
            return null;
        }
        return os.getDesc();
    }

    public static ThreePartyType getCustomStatus(String value){
        ThreePartyType[] values = ThreePartyType.values();
        if(StringUtils.isEmpty(value)){
            return null;
        }
        for(ThreePartyType status:values){
            if(status.value.equals(value)){
                return status;
            }
        }
        return null;
    }
}
