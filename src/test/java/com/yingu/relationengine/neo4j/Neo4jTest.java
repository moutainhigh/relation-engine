package com.yingu.relationengine.neo4j;

import com.fasterxml.jackson.core.type.TypeReference;
import com.yingu.relationengine.RelationengineApp;
import com.yingu.relationengine.config.SecurityBeanOverrideConfiguration;
import com.yingu.relationengine.domain.relationships.CallRecord;
import com.yingu.relationengine.domain.Mobile;
import com.yingu.relationengine.domain.Person;
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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RelationengineApp.class, SecurityBeanOverrideConfiguration.class})
public class Neo4jTest {

    private Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    MobileRepository mobileRepository;
    @Autowired
    PersonRepository personRepository;

    @Test
    public void save(){
        Mobile mobile = new Mobile();
        mobile.setPhoneNumber(  "18651698213"   );
        mobileRepository.save(  mobile);

        Mobile mobile1 = new Mobile();
        mobile1.setPhoneNumber(  "13895836506"   );
        mobileRepository.save(  mobile1);
    }

    @Test
    public void save2(){
        String ab = "[{\\\"duration\\\":67,\\\"tel\\\":\\\"01060554540\\\",\\\"time\\\":1.507682662e+09,\\\"type\\\":1},{\\\"duration\\\":16,\\\"tel\\\":\\\"18612464139\\\",\\\"time\\\":1.507661962e+09,\\\"type\\\":1},{\\\"duration\\\":28,\\\"tel\\\":\\\"18612464139\\\",\\\"time\\\":1.507661794e+09,\\\"type\\\":1},{\\\"duration\\\":9,\\\"tel\\\":\\\"18701419767\\\",\\\"time\\\":1.507609137e+09,\\\"type\\\":1},{\\\"duration\\\":8,\\\"tel\\\":\\\"125909818001\\\",\\\"time\\\":1.507601406e+09,\\\"type\\\":1}]";
        ab = ab.replaceAll("\\\\", "");
        List<CallRecord> list       = JsonMapper.nonDefaultMapper().fromJson(ab, new TypeReference<List<CallRecord>>() { });
        Set<Mobile>  callrecord     = makeCallReocord(list);
        Person person               = new Person();
        person.setName("龙哥");
//        person.setCallRecord(   callrecord    );
        personRepository.save(  person);
    }

    @Test
    public void save4(){
        String ab = "[{\\\"duration\\\":67,\\\"tel\\\":\\\"01060554540\\\",\\\"time\\\":1.507682662e+09,\\\"type\\\":1},{\\\"duration\\\":16,\\\"tel\\\":\\\"18612464139\\\",\\\"time\\\":1.507661962e+09,\\\"type\\\":1},{\\\"duration\\\":28,\\\"tel\\\":\\\"18612464139\\\",\\\"time\\\":1.507661794e+09,\\\"type\\\":1},{\\\"duration\\\":9,\\\"tel\\\":\\\"18701419767\\\",\\\"time\\\":1.507609137e+09,\\\"type\\\":1},{\\\"duration\\\":8,\\\"tel\\\":\\\"125909818001\\\",\\\"time\\\":1.507601406e+09,\\\"type\\\":1}]";
        ab = ab.replaceAll("\\\\", "");
        List<CallRecord> list       = JsonMapper.nonDefaultMapper().fromJson(ab, new TypeReference<List<CallRecord>>() { });
        Set<Mobile>  callrecord     = makeCallReocord(list);
        Set<Mobile>  owner          = makeOwner();
        Person person               = new Person();
        person.setName("发哥");
//        person.setCallRecord(   callrecord      );
//        person.setOwner(        owner           );
        personRepository.save(  person);
    }



    private Set<Mobile> makeOwner() {
        Set<Mobile> mobiles = new HashSet<>();
        Mobile mobile = new Mobile();
        mobile.setPhoneNumber("18651698213");
        mobiles.add(mobile);
        return mobiles;
    }


    @Test
    public void save3(){
        Person person = personRepository.findByName("龙哥");

        Mobile mobile = mobileRepository.findByPhoneNumber("01060554540");

        List<Mobile> mobile2 =StreamSupport.stream(mobileRepository.findByNameContaining("01060554540").spliterator(), false ).collect(Collectors.toList());


        Set<Mobile> mobile3 = mobileRepository.findByCallRecord("龙哥");

        System.out.println("");
    }





    private Set<Mobile> makeCallReocord(List<CallRecord> records) {
        List<Mobile> mobiles = new ArrayList<>();
        for(CallRecord record : records){
            Mobile mobile = new Mobile();
            mobile.setPhoneNumber(  record.getTel() );
            mobiles.add(mobile);
        }
        Set<Mobile> result = new HashSet<>();
        result.addAll(mobiles);
        return result;
    }
}
