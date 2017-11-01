package com.yingu.relationengine.repository;

import com.yingu.relationengine.domain.Person;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

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

@Repository
public interface PersonRepository extends GraphRepository<Person> {



    Person findByName(@Param("name") String name);

//    Person findByGlobalId(@Param("globalId") String globalId);

    Person findByIdCard(@Param("idCard") String idCard);

    @Query("MATCH (p:Person) WHERE p.name =~ ('(?i).*'+{name}+'.*') RETURN p")
    Collection<Person> findByNameContaining(@Param("name") String name);

}
