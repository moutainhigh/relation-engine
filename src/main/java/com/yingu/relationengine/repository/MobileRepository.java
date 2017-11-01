package com.yingu.relationengine.repository;

import com.yingu.relationengine.domain.Mobile;
import com.yingu.relationengine.domain.Person;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
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

@Repository
public interface MobileRepository extends GraphRepository<Mobile> {


    Mobile findByPhoneNumber(@Param("phoneNumber") String phoneNumber);

    @Query("MATCH (m:Mobile) WHERE m.phoneNumber =~ ('(?i).*'+{phoneNumber}+'.*') RETURN m")
    Collection<Mobile> findByNameContaining(@Param("phoneNumber") String phoneNumber);


    @Query("MATCH (m:Mobile)-[ACTED_IN]-(p:Person) WHERE p.name = {0} RETURN m")
    Set<Mobile> findByCallRecord(String name);

}
