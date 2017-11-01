package com.yingu.relationengine.neo4j;

import com.fasterxml.jackson.core.type.TypeReference;
import com.yingu.relationengine.RelationengineApp;
import com.yingu.relationengine.config.SecurityBeanOverrideConfiguration;
import com.yingu.relationengine.domain.relationships.AddressBook;
import com.yingu.relationengine.domain.relationships.CallRecord;
import com.yingu.relationengine.domain.Mobile;
import com.yingu.relationengine.domain.Person;
import com.yingu.relationengine.domain.relationships.Owner;
import com.yingu.relationengine.repository.MobileRepository;
import com.yingu.relationengine.repository.PersonRepository;
import com.yingu.relationengine.utils.JsonMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ObjectUtils;

import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RelationengineApp.class, SecurityBeanOverrideConfiguration.class})
public class Neo4jAllTest {

    private Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    MobileRepository mobileRepository;
    @Autowired
    PersonRepository personRepository;


    @Test
    public void testSaveCallRecords(){
        String ab = "[{\\\"duration\\\":67,\\\"tel\\\":\\\"01060554540\\\",\\\"time\\\":1.507682662e+09,\\\"type\\\":1},{\\\"duration\\\":16,\\\"tel\\\":\\\"18612464131\\\",\\\"time\\\":1.507661962e+09,\\\"type\\\":1},{\\\"duration\\\":28,\\\"tel\\\":\\\"18612464132\\\",\\\"time\\\":1.507661794e+09,\\\"type\\\":1},{\\\"duration\\\":9,\\\"tel\\\":\\\"18701419767\\\",\\\"time\\\":1.507609137e+09,\\\"type\\\":1},{\\\"duration\\\":8,\\\"tel\\\":\\\"125909818001\\\",\\\"time\\\":1.507601406e+09,\\\"type\\\":1}]";
        ab = ab.replaceAll("\\\\", "");
        List<CallRecord> list       = JsonMapper.nonDefaultMapper().fromJson(ab, new TypeReference<List<CallRecord>>() { });


        Set<CallRecord>  callRecords = new HashSet<>();
        Person person               = new Person();
        for(CallRecord record : list){
            Mobile m = new Mobile();
            m.setPhoneNumber(record.getTel());
            m = mobileRepository.save(m);
            record.setMobile(   m);
            record.setPerson(   person);

            callRecords.add(record);
        }

        person.setName("发哥");
        person.setCallRecord(       callRecords);
        person = personRepository.save(person);

    }

    @Test
    public void testSaveOwner(){

        Mobile m = mobileRepository.findByPhoneNumber("18651690001");
        if(ObjectUtils.isEmpty(m)) {
            m = new Mobile();
            m.setPhoneNumber("18651690001");
            m = mobileRepository.save(m);
        }

        Person person = personRepository.findByName("发哥");

        Owner owner = new Owner();
        owner.setPerson(    person);
        owner.setMobile(    m);

        Set<Owner> owners = new HashSet<>();
        owners.add(owner);

        person.setOwner(owners);
        personRepository.save(person);
    }


    @Test
    public void testSaveAddressBook(){

        String json = "[{\"name\":\"华为客服\",\"tel\":\"4008308300\"},{\"name\":\"赵本山\",\"tel\":\"15210770667\"}]";
        List<AddressBook> books       = JsonMapper.nonDefaultMapper().fromJson(json, new TypeReference<List<AddressBook>>() { });

        Set<AddressBook>  setbooks = new HashSet<>();
        Person person = personRepository.findByName("发哥");
        for(AddressBook book : books){
            Mobile m = new Mobile();
            m.setPhoneNumber(book.getTel());
            m = mobileRepository.save(m);
            book.setMobile(   m);
            book.setPerson(   person);
            setbooks.add(book);
        }

        person.setAddressBook(setbooks);
        person = personRepository.save(person);

    }
}
