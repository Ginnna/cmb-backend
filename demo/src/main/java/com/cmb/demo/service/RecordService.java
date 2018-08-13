package com.cmb.demo.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cmb.demo.domain.Record;
import com.cmb.demo.domain.Rule;
import com.cmb.demo.framework.utils.json.JsonUtil;
import com.cmb.demo.repository.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.RecoverableDataAccessException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RecordService {
    @Autowired
    private RecordRepository recordRepository;

    public JSONArray queryAll() {
        return JsonUtil.toJSONArray(recordRepository.findAll());
    }

    public JSONObject queryOne(JSONObject params) {
        String id = params.getString("id");

        return JSON.parseObject(JSON.toJSONString(recordRepository.findById(id)));
    }

    public JSONArray queryByUser(JSONObject params) {
        String user = params.getString("user");

        return JsonUtil.toJSONArray(recordRepository.findByUser(user));
    }

    public void addOne(JSONObject params) {
        Record record = JSONObject.toJavaObject(params, Record.class);
        String id = UUID.randomUUID().toString();
        if(record.getId() == null) {
            record.setId(id);
        }

        recordRepository.save(record);

    }

    public void deleteOne(JSONObject params) {
        String id = params.getString("id");

        recordRepository.deleteById(id);
    }

    public void editOne(JSONObject params) {
        String id = params.getString("id");
        //String activity=params.getString("activity");
        String user = params.getString("user");
        String time1 = params.getString("time1");
        String time2 = params.getString("time2");
        String content = params.getString("content");
        String cdk = params.getString("cdk");

        JSONObject tp = JSON.parseObject(JSON.toJSONString(recordRepository.findById(id)));
        Record record = JSONObject.toJavaObject(tp, Record.class);
//        if(activity != null)
//            rule.setActivity(activity);
        if (user != null)
            record.setUser(user);
        if (time1 != null)
            record.setTime1(time1);
        if (time2 != null)
            record.setTime2(time2);
        if (content != null)
            record.setContent(content);
        if (cdk != null)
            record.setCdk(cdk);

        recordRepository.save(record);
    }
}
