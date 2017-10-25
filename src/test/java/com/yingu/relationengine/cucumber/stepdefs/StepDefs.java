package com.yingu.relationengine.cucumber.stepdefs;

import com.yingu.relationengine.RelationengineApp;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.ResultActions;

import org.springframework.boot.test.context.SpringBootTest;

@WebAppConfiguration
@SpringBootTest
@ContextConfiguration(classes = RelationengineApp.class)
public abstract class StepDefs {

    protected ResultActions actions;

}
