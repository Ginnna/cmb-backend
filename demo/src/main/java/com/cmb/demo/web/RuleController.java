package com.cmb.demo.web;

import com.cmb.demo.framework.core.web.Request;
import com.cmb.demo.framework.core.web.Response;
import com.cmb.demo.framework.core.web.ResponseType;
import com.cmb.demo.service.ActivityService;
import com.cmb.demo.service.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class RuleController {
    @Autowired
    private RuleService ruleService;

    @RequestMapping(value = "/rules")
    public Response queryAll(Request request) {
        Response response = new Response();

        try {

            response.status = ResponseType.SUCCESS;

            response.data = ruleService.queryAll();
            response.message = "already return all rules.";

        } catch (Exception e) {
            e.printStackTrace();

            response.status = ResponseType.FAILURE;
            response.message = e.getMessage();
        }

        return response;
    }

    @RequestMapping(value = "/rule")
    public Response queryOne(Request request) {
        Response response = new Response();

        try {

            response.status = ResponseType.SUCCESS;

            response.data = ruleService.queryOne(request.getParams());
            response.message = "already return the rule according to the id input";

        } catch (Exception e) {
            e.printStackTrace();

            response.status = ResponseType.FAILURE;
            response.message = e.getMessage();
        }

        return response;
    }

    @RequestMapping(value = "/rule/add")
    public Response addOne(Request request) {
        Response response = new Response();

        try {

            response.status = ResponseType.SUCCESS;
            ruleService.addOne(request.getParams());
            response.message = "already add the rule according to the input";

        } catch (Exception e) {
            e.printStackTrace();

            response.status = ResponseType.FAILURE;
            response.message = e.getMessage();
        }

        return response;
    }

    @RequestMapping(value = "/rule/delete")
    public Response deleteOne(Request request) {
        Response response = new Response();

        try {

            response.status = ResponseType.SUCCESS;
            ruleService.deleteOne(request.getParams());
            response.message = "already delete the rule according to the id input";

        } catch (Exception e) {
            e.printStackTrace();

            response.status = ResponseType.FAILURE;
            response.message = e.getMessage();
        }

        return response;
    }

    @RequestMapping(value = "/rule/edit")
    public Response editOne(Request request) {
        Response response = new Response();

        try {

            response.status = ResponseType.SUCCESS;
            ruleService.editOne(request.getParams());
            response.message = "already edit the rule according to the id input";

        } catch (Exception e) {
            e.printStackTrace();

            response.status = ResponseType.FAILURE;
            response.message = e.getMessage();
        }

        return response;
    }
}
