package com.yingu.relationengine.web.rest.test;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import com.codahale.metrics.annotation.Timed;
import com.google.common.collect.Lists;
import com.yingu.relationengine.domain.Person;
import com.yingu.relationengine.repository.PersonRepository;
import com.yingu.relationengine.web.rest.vm.LoggerVM;
import org.apache.commons.collections4.IteratorUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Controller for view and managing Log Level at runtime.
 */
@RestController
@RequestMapping("/api/person")
public class PersonResource {

    @Autowired
    PersonRepository personRepository;

    @GetMapping(value = "/list" , produces="application/json;charset=utf-8")
    @ResponseBody
    public List<Person> getList() {

        List<Person> persons = StreamSupport.stream(   personRepository.findAll().spliterator(), false).collect(Collectors.toList());

        Iterable<Person>  iterable = personRepository.findAll( 10);
//        return Lists.newArrayList(iterable);

        return persons;
    }

}
