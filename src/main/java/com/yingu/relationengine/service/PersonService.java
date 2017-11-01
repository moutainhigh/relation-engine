package com.yingu.relationengine.service;

import com.yingu.relationengine.domain.Person;
import com.yingu.relationengine.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

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


@Service
public class PersonService {

    @Autowired
    PersonRepository personRepository;


    public Person saveOrUpdate(Person person){
        Person temp = personRepository.findByIdCard(person.getIdCard());
        if(ObjectUtils.isEmpty(temp)){
            return personRepository.save(person);
        }
        return temp;
    }

}
