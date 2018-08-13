package com.cmb.demo.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cmb.demo.domain.Rule;
import com.cmb.demo.domain.User;
import com.cmb.demo.framework.utils.json.JsonUtil;
import com.cmb.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public JSONArray queryAll() {
        return JsonUtil.toJSONArray(userRepository.findAll());
    }

    public JSONObject queryOne(JSONObject params) {
        String id = params.getString("id");

        return JSON.parseObject(JSON.toJSONString(userRepository.findById(id)));
    }

    public void addOne(JSONObject params) {
        User user = JSONObject.toJavaObject(params, User.class);
        String id = UUID.randomUUID().toString();
        if(user.getId() == null) {
            user.setId(id);
        }

        userRepository.save(user);

    }

    public void deleteOne(JSONObject params) {
        String id = params.getString("id");

        userRepository.deleteById(id);
    }

    public void editOne(JSONObject params) {
        String id = params.getString("id");
        String phone = params.getString("phone");
        int number = params.getInteger("number");

        JSONObject tp = JSON.parseObject(JSON.toJSONString(userRepository.findById(id)));
        User user = JSONObject.toJavaObject(tp, User.class);
        if(phone != null)
            user.setPhone(phone);
        if(number > 0)
            user.setNumber(number);

        userRepository.save(user);
    }
}
