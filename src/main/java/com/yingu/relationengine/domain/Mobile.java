package com.yingu.relationengine.domain;

import com.querydsl.core.annotations.QueryEntity;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;

/**
 * <B>文件名称：</B><BR>
 * <B>文件描述：</B><BR>
 * <BR>
 * <B>版权声明：</B>(C)2016-2018<BR>
 * <B>公司部门：</B>东方银谷 研发二部 CBG<BR>
 * <B>创建时间：</B>2017/10/26<BR>
 *
 * @author 李云龙  liyunlong@yingu.com
 * @version 1.0
 **/

@NodeEntity
@QueryEntity
public class Mobile {

    @GraphId
    private Long    uid;

//    @Indexed(unique=true)
    private String  phoneNumber;

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
