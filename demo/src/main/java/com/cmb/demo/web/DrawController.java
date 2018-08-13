package com.cmb.demo.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cmb.demo.domain.Award;
import com.cmb.demo.domain.User;
import com.cmb.demo.framework.core.web.Request;
import com.cmb.demo.framework.core.web.Response;
import com.cmb.demo.framework.core.web.ResponseType;
import com.cmb.demo.repository.AwardRepository;
import com.cmb.demo.repository.RecordRepository;
import com.cmb.demo.repository.UserRepository;
import com.cmb.demo.service.AwardService;
import com.cmb.demo.service.RecordService;
import com.cmb.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@CrossOrigin
@RestController
public class DrawController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AwardService awardService;
    @Autowired
    private AwardRepository awardRepository;

    @Autowired
    private RecordService recordService;
    @Autowired
    private RecordRepository recordRepository;

    @RequestMapping(value = "/draw/user")
    public Response draw(Request request){
        Response response = new Response();
        try {

            response.status = ResponseType.SUCCESS;

            //判断资格
            if(!decide(request)) {
                response.message = "sorry the user doesn't have any chance to draw.";
                return response;
            }

            //抽奖算法实现，返回奖品位置
            int result = compute();
            JSONObject Json = new JSONObject();
            Json.put("loc", result);
            response.data=Json;

            /**
             * 修改数据库
             * user抽奖次数-1
             * 奖品count-1
             * 中奖记录+1
             */
            edit_user(request);
            edit_award(result);
            edit_record(request, result);

            response.message = "already draw the awards and return.";

        } catch (Exception e) {
            e.printStackTrace();

            response.status = ResponseType.FAILURE;
            response.message = e.getMessage();
        }
        return response;
    }

    public boolean decide(Request request) {
        /**
         * 用户抽奖次数判断
         * 小于、等于0，则无法抽奖
         */
        JSONObject user_json = userService.queryOne(request.getParams());
        User user = JSONObject.toJavaObject(user_json, User.class);

        if(user.getNumber() <= 0 ) {
            return false;
        }
        else
            return true;
    }

    public int compute() {
        JSONArray awardArray = awardService.queryAll();
        int length=awardArray.size();
        /**
         * probility存放每一阶段的奖品概率
         * count存放各类奖品数量
         */
        ArrayList<Integer> probility = new ArrayList();
        ArrayList<Integer> count = new ArrayList();
        int probility_sum=0;
        for(int i=0;i<length;i++) {
            int temp = awardArray.getJSONObject(i).getInteger("probility");
            probility_sum += temp;
            probility.add(probility_sum);
        }
        for(int i=0;i<length;i++){
            int temp= awardArray.getJSONObject(i).getInteger("count");
            count.add(temp);
        }

//            for(int i=0;i<probility.size();i++)
//                System.out.println(probility.get(i));
//            for(int i=0;i<count.size();i++)
//                System.out.println(count.get(i));

        int result=-1;
        do {
            int random=(int) (Math.random() * 100);
            //System.out.println(random);
            for(int i=0;i<length;i++) {
                if(random < probility.get(i)) {
                    result=i;
                    break;
                }
            }
        } while(count.get(result) <= 0);

        return result;
    }

    void edit_user(Request request) {
        JSONObject user_json = userService.queryOne(request.getParams());
        User user = JSONObject.toJavaObject(user_json, User.class);
        int number = user.getNumber();
        number -= 1;
        user.setNumber(number);

        JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(user));
        userService.editOne(jsonObject);
        //userRepository.save(user);
    }

    void edit_award(int result) {
        JSONObject award_id = new JSONObject();
        award_id.put("id",String.valueOf(result));
        JSONObject award_json = awardService.queryOne(award_id);
        Award award = JSONObject.toJavaObject(award_json, Award.class);
        int count = award.getCount();
        count -= 1;
        award.setCount(count);

        JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(award));
        awardService.editOne(jsonObject);

    }

    void edit_record(Request request, int result) {
        JSONObject record = new JSONObject();

        String user_id = request.getParams().getString("id");
        record.put("user", user_id);

        JSONObject award_id = new JSONObject();
        award_id.put("id",String.valueOf(result));
        JSONObject award_json = awardService.queryOne(award_id);
        Award award = JSONObject.toJavaObject(award_json, Award.class);
        record.put("content", award.getName());

        /**
         * TODO
         * 中奖时间、活动结束时间
         * 抽奖记录中，CDK设置
         */

        recordService.addOne(record);
    }
}
