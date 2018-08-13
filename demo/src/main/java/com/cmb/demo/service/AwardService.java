package com.cmb.demo.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cmb.demo.domain.Activity;
import com.cmb.demo.domain.Award;
import com.cmb.demo.framework.utils.json.JsonUtil;
import com.cmb.demo.repository.ActivityRepository;
import com.cmb.demo.repository.AwardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AwardService {
    @Autowired
    private AwardRepository awardRepository;

    public JSONArray queryAll() {

        return JsonUtil.toJSONArray(awardRepository.findAll());
    }

    public JSONObject queryOne(JSONObject params) {

        String id=params.getString("id");

        return JSON.parseObject(JSON.toJSONString(awardRepository.findById(id)));
    }

    public void addOne(JSONObject params) {
        Award award = JSONObject.toJavaObject(params, Award.class);
        String id = UUID.randomUUID().toString();
        if(award.getId() == null) {
            award.setId(id);
        }

        awardRepository.save(award);

    }

    public void deleteOne(JSONObject params) {
        String id = params.getString("id");

        awardRepository.deleteById(id);
    }

    public void editOne(JSONObject params) {
        String id = params.getString("id");
        String name=params.getString("name");
        int count = params.getInteger("count");
        int probility =params.getInteger("probility");

        JSONObject tp = JSON.parseObject(JSON.toJSONString(awardRepository.findById(id)));
        Award award = JSONObject.toJavaObject(tp, Award.class);
        if(name != null)
            award.setName(name);
        if(count >= 0)
            award.setCount(count);
        if(probility >= 0)
            award.setProbility(probility);

        awardRepository.save(award);
    }
}
