package com.yingu.relationengine.neo4j;

import com.yingu.relationengine.domain.Custom;
import com.yingu.relationengine.utils.JsonMapper;

/**
 * <B>文件名称：</B><BR>
 * <B>文件描述：</B><BR>
 * <BR>
 * <B>版权声明：</B>(C)2016-2018<BR>
 * <B>公司部门：</B>东方银谷 研发二部 CBG<BR>
 * <B>创建时间：</B>2017/11/1<BR>
 *
 * @author 李云龙  liyunlong@yingu.com
 * @version 1.0
 **/


public class Test {
    public  static  void main(String[] args){
        String json = "{\"id\":4325,\"globalId\":\"KHZX-TS-1030466190\",\"name\":\"刘贺松\",\"idCard\":\"41282519904276135\",\"bankCard\":\"620522002550269492\",\"mobile\":\"13600988465\",\"status\":\"900\",\"deviceId\":null,\"callback\":\"http://172.24.133.68:20220/v1/risk/callBack\",\"retryNum\":0,\"loanApps\":0,\"ip\":null,\"qq\":null,\"wechat\":null,\"headPortrait\":null,\"latitude\":null,\"longitude\":null,\"analysisType\":false,\"conclusion\":\"200\",\"badPoints\":0,\"goodPoints\":0,\"source\":null,\"appKey\":\"39c90f28083e490ea7f397593b727728\",\"createTime\":\"2017-10-30 20:19:59\",\"imei\":null,\"idfa\":null,\"reason\":null}";
        Custom custom = JsonMapper.nonDefaultMapper().fromJson(json, Custom.class);

        System.out.println(custom.getName());
    }
}
