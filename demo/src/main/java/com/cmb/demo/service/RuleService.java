package com.cmb.demo.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cmb.demo.domain.Activity;
import com.cmb.demo.domain.Rule;
import com.cmb.demo.framework.utils.json.JsonUtil;
import com.cmb.demo.repository.RuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RuleService {
    @Autowired
    private RuleRepository ruleRepository;

    public JSONArray queryAll() {
        return JsonUtil.toJSONArray(ruleRepository.findAll());
    }

    public JSONObject queryOne(JSONObject params) {
        String id = params.getString("id");

        return JSON.parseObject(JSON.toJSONString(ruleRepository.findById(id)));
    }

    public void addOne(JSONObject params) {
        Rule rule = JSONObject.toJavaObject(params, Rule.class);
        String id = UUID.randomUUID().toString();
        if(rule.getId() == null) {
            rule.setId(id);
        }

        ruleRepository.save(rule);

    }

    public void deleteOne(JSONObject params) {
        String id = params.getString("id");

        ruleRepository.deleteById(id);
    }

    public void editOne(JSONObject params) {
        String id = params.getString("id");
        //String activity=params.getString("activity");
        String content = params.getString("content");


        JSONObject tp = JSON.parseObject(JSON.toJSONString(ruleRepository.findById(id)));
        Rule rule = JSONObject.toJavaObject(tp, Rule.class);
//        if(activity != null)
//            rule.setActivity(activity);
        if(content != null)
            rule.setContent(content);

        ruleRepository.save(rule);
    }

}
