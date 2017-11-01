package com.yingu.relationengine.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yingu.relationengine.domain.relationships.AddressBook;
import com.yingu.relationengine.domain.relationships.CallRecord;
import com.yingu.relationengine.domain.relationships.Owner;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.HashSet;
import java.util.Set;

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
public class Person {

    @GraphId
    private Long    uid;
    private String  name;
    private String  idCard;
    private String  bankCard;
    private String  deviceId;
    private String  conclusion;

    @Relationship(type = "OWNER", direction = Relationship.INCOMING)
    private Set<Owner> owner = new HashSet<>();

//    @Relationship(type = "RELATIVES ", direction = Relationship.OUTGOING)
//    private Set<Mobile> relatives = new HashSet<>();

    @Relationship(type = "ADDRESSBOOK ", direction = Relationship.OUTGOING)
    private Set<AddressBook> addressBook = new HashSet<>();
    //, direction = Relationship.OUTGOING
    @Relationship(type = "CALLRECORD  ")
    @JsonIgnore
    private Set<CallRecord> callRecord = new HashSet<>();


    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

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

    public String getBankCard() {
        return bankCard;
    }

    public void setBankCard(String bankCard) {
        this.bankCard = bankCard;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getConclusion() {
        return conclusion;
    }

    public void setConclusion(String conclusion) {
        this.conclusion = conclusion;
    }

    public Set<Owner> getOwner() {
        return owner;
    }

    public void setOwner(Set<Owner> owner) {
        this.owner = owner;
    }

//    public Set<Mobile> getRelatives() {
//        return relatives;
//    }
//
//    public void setRelatives(Set<Mobile> relatives) {
//        this.relatives = relatives;
//    }

    public Set<AddressBook> getAddressBook() {
        return addressBook;
    }

    public void setAddressBook(Set<AddressBook> addressBook) {
        this.addressBook = addressBook;
    }

    public Set<CallRecord> getCallRecord() {
        return callRecord;
    }

    public void setCallRecord(Set<CallRecord> callRecord) {
        this.callRecord = callRecord;
    }
}
