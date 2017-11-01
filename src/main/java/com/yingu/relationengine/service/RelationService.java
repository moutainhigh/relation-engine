package com.yingu.relationengine.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.yingu.relationengine.domain.Custom;
import com.yingu.relationengine.domain.Mobile;
import com.yingu.relationengine.domain.Person;
import com.yingu.relationengine.domain.relationships.AddressBook;
import com.yingu.relationengine.domain.relationships.CallRecord;
import com.yingu.relationengine.domain.relationships.Owner;
import com.yingu.relationengine.exception.BusinessException;
import com.yingu.relationengine.remote.feign.RuleEngineFeignClient;
import com.yingu.relationengine.repository.MobileRepository;
import com.yingu.relationengine.repository.PersonRepository;
import com.yingu.relationengine.repository.ThreePartyType;
import com.yingu.relationengine.rpc.mq.channel.ResponseThreeParty;
import com.yingu.relationengine.rpc.mq.channel.ThirdPartyResponse;
import com.yingu.relationengine.swagger.model.SuccessModel;
import com.yingu.relationengine.utils.JsonMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.inject.Inject;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
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


@Service
public class RelationService {
    protected Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    PersonRepository personRepository;

    @Autowired
    MobileRepository mobileRepository;

    @Autowired
    MobileService   mobileService;

    @Autowired
    PersonService   personService;

    @Inject
    RuleEngineFeignClient ruleengineFeignClient;

    /**
     * 保存客户通话记录
     * 1、解析
     * 2、建立关系 CallRecord
     * 3、保存关系
     * @param custom
     * @param result
     */
    public void saveCallRecords(final Custom custom, String result){
        try {
//            result = result.replaceAll("\\\\", "");
            List<CallRecord> list = JsonMapper.nonDefaultMapper().fromJson(result, new TypeReference<List<CallRecord>>() {});
            if(!ObjectUtils.isEmpty(list) && list.size()>0) {
                Person person = personRepository.findByIdCard(custom.getIdCard());

                Set<CallRecord> callRecords = new HashSet<>();
                for (CallRecord record : list) {
                    Mobile m = new Mobile();
                    m.setPhoneNumber(record.getTel());
                    m = mobileService.saveOrUpdate(m);
                    record.setMobile(m);
                    record.setPerson(person);
                    callRecords.add(record);
                }

                person.setCallRecord(callRecords);
                personRepository.save(person);
            }
        }catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
    }

    /**
     * 添加本人手机
     * @param custom
     */
    public void saveOwner(Custom custom){
        try {
            Mobile m = mobileRepository.findByPhoneNumber(custom.getMobile());
            if (ObjectUtils.isEmpty(m)) {
                m = new Mobile();
                m.setPhoneNumber(custom.getMobile());
                m = mobileService.saveOrUpdate(m);
            }

            Person person = personRepository.findByIdCard(custom.getIdCard());

            Owner owner = new Owner();
            owner.setPerson(person);
            owner.setMobile(m);

            Set<Owner> owners = new HashSet<>();
            owners.add(owner);

            person.setOwner(owners);
            personRepository.save(person);
        }catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
    }

    /**
     * 保存客户通讯录
     * @param custom
     * @param result
     */
    public void saveAddressBook(final Custom custom, String result){
        try {
            List<AddressBook> books = JsonMapper.nonDefaultMapper().fromJson(result, new TypeReference<List<AddressBook>>() {});
            if(!ObjectUtils.isEmpty(books) && books.size()>0) {

                Set<AddressBook> setbooks = new HashSet<>();
                Person person = personRepository.findByIdCard(custom.getIdCard());
                for (AddressBook book : books) {
                    Mobile m = new Mobile();
                    m.setPhoneNumber(book.getTel());
                    m = mobileService.saveOrUpdate(m);
                    book.setMobile(m);
                    book.setPerson(person);
                    setbooks.add(book);
                }

                person.setAddressBook(setbooks);
                personRepository.save(person);
            }
        }catch (Exception e){
            throw new BusinessException(e.getMessage());
        }

    }

    /**
     * 消费MQ数据
     * 1、客户数据
     * 2、通讯录
     * 3、通话记录
     * @param body
     */
    public void dispose(ResponseThreeParty body) {
        if(body == null){
            return;
        }
        if (body.getStatus().equals(ThreePartyType.SUCCESS.getValue())) {
            LOGGER.info("三方返回消息-成功-{}", JsonMapper.nonDefaultMapper().toJson(body));
            //
            Custom custom = findCustomInfo(body.getGlobalId());
            if(   custom.getName().indexOf("殷秀辉") >= 0 ){
                return;
            }
            storageCustom(  custom);

            handle(         custom, body.getData());
            LOGGER.info("处理完毕-三方返回消息--{}", JsonMapper.nonDefaultMapper().toJson(body));
        } else if(body.getStatus().equals(ThreePartyType.FAIL.getValue())){
            LOGGER.error("三方返回消息-失败:{}", JsonMapper.nonDefaultMapper().toJson(body));
        }else {
            LOGGER.error("三方返回消息-失败-未知状态码:{}", JsonMapper.nonDefaultMapper().toJson(body));
        }
    }

    private Custom findCustomInfo(String globalId) {
        ResponseEntity<SuccessModel> responseEntity = null;
        try {
            responseEntity = ruleengineFeignClient.getCustomInfo(globalId, "01ccf6113bb042d1931475d8a52261c5", "01ccf6113bb042d1931475d8a52261c5");
        }catch (Exception e){
            LOGGER.error("storageCustom getCustomInfo Exception. {}", e.getMessage());
            throw new BusinessException("storageCustom getCustomInfo Exception. ");
        }

        LinkedHashMap data  = (LinkedHashMap) responseEntity.getBody().getData();
        if(ObjectUtils.isEmpty(data)) throw new BusinessException("无此客户 GlobalId："+ globalId);
        String name         = data.get("name").toString();
        String idCard       = data.get("idCard").toString();
        String bankCard     = "";
        try {
            bankCard        = data.get("bankCard").toString();
        }catch (Exception e){
            LOGGER.warn("storageCustom bankCard is null. {}", e.getMessage());
        }
        String mobile       = "";
        try {
            mobile          = data.get("mobile").toString();
        }catch (Exception e){
            LOGGER.warn("storageCustom mobile is null. {}", e.getMessage());
        }
        Custom custom = new Custom();
        custom.setGlobalId(     globalId    );
        custom.setName(         name        );
        custom.setIdCard(       idCard      );
        custom.setBankCard(     bankCard    );
        custom.setMobile(       mobile      );
        return custom;
    }

    private void storageCustom(Custom custom) {

        Person person = personRepository.findByIdCard(custom.getIdCard());
        if(ObjectUtils.isEmpty(person)) person = new Person();
        person.setName(     custom.getName()    );
        person.setIdCard(   custom.getIdCard()  );
        person.setBankCard( custom.getBankCard());
        personService.saveOrUpdate(person);

        // 保存 本人 Owner 移动电话
        saveOwner(custom);
    }


    /**
     * SM-A088 通讯录
     * SM-A089 通话记录
     */
    @Value("${bigresult:SM-A012,SM-A087,SM-A088,SM-A089,SM-A081}")
    private String BIG_RESULT;

    private void handle(Custom custom, List<ThirdPartyResponse> data) {
        for(ThirdPartyResponse cb : data){

            String attrCode = cb.getAttributeCode();
            Set<String> BIG_RESULTS = StringUtils.commaDelimitedListToSet(BIG_RESULT);
            if(!BIG_RESULTS.contains(attrCode)) continue;

            if(attrCode.equals("SM-A088")){
                saveAddressBook(custom, cb.getResult());
            }else if(attrCode.equals("SM-A088")){
                saveCallRecords(custom, cb.getResult());
            }

        }
    }
}
