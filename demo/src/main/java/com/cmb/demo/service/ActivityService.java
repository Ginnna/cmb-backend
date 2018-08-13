package com.cmb.demo.service;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cmb.demo.domain.Activity;
import com.cmb.demo.framework.utils.json.JsonUtil;
import com.cmb.demo.repository.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ActivityService {
    @Autowired
    private ActivityRepository activityRepository;

    public JSONArray queryAll() {

        return JsonUtil.toJSONArray(activityRepository.findAll());
    }

    public JSONObject queryOne(JSONObject params) {

        String id=params.getString("id");

        return JSON.parseObject(JSON.toJSONString(activityRepository.findById(id)));
    }

    public void addOne(JSONObject params) {
        Activity activity = JSONObject.toJavaObject(params, Activity.class);
        String id = UUID.randomUUID().toString();
        if(activity.getId() == null) {
            activity.setId(id);
        }

        activityRepository.save(activity);

    }

    public void deleteOne(JSONObject params) {
        String id = params.getString("id");

        activityRepository.deleteById(id);
    }

    public void editOne(JSONObject params) {
        String id = params.getString("id");
        String name=params.getString("name");
        String date1 = params.getString("date1");
        String date2 = params.getString("date2");

        JSONObject tp = JSON.parseObject(JSON.toJSONString(activityRepository.findById(id)));
        Activity activity = JSONObject.toJavaObject(tp, Activity.class);
        if(name != null)
            activity.setName(name);
        if(date1 != null)
            activity.setDate1(date1);
        if(date2 != null)
            activity.setDate2(date2);

        activityRepository.save(activity);
    }
}
