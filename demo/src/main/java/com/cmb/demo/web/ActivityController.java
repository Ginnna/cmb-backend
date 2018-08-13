package com.cmb.demo.web;

import com.cmb.demo.framework.core.web.Request;
import com.cmb.demo.framework.core.web.Response;
import com.cmb.demo.framework.core.web.ResponseType;
import com.cmb.demo.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class ActivityController {
    @Autowired
    private ActivityService activityService;

    @RequestMapping(value = "/activities")
    public Response queryAll(Request request) {
        Response response = new Response();

        try {

            response.status = ResponseType.SUCCESS;

            response.data = activityService.queryAll();
            response.message = "already return all activities.";

        } catch (Exception e) {
            e.printStackTrace();

            response.status = ResponseType.FAILURE;
            response.message = e.getMessage();
        }

        return response;
    }

    @RequestMapping(value = "/activity")
    public Response queryOne(Request request) {
        Response response = new Response();

        try {

            response.status = ResponseType.SUCCESS;

            response.data = activityService.queryOne(request.getParams());
            response.message = "already return the activity according to the id input";

        } catch (Exception e) {
            e.printStackTrace();

            response.status = ResponseType.FAILURE;
            response.message = e.getMessage();
        }

        return response;
    }

    @RequestMapping(value = "/activity/add")
    public Response addOne(Request request) {
        Response response = new Response();

        try {

            response.status = ResponseType.SUCCESS;
            activityService.addOne(request.getParams());
            response.message = "already add the activity according to the input";

        } catch (Exception e) {
            e.printStackTrace();

            response.status = ResponseType.FAILURE;
            response.message = e.getMessage();
        }

        return response;
    }

    @RequestMapping(value = "/activity/delete")
    public Response deleteOne(Request request) {
        Response response = new Response();

        try {

            response.status = ResponseType.SUCCESS;
            activityService.deleteOne(request.getParams());
            response.message = "already delete the activity according to the id input";

        } catch (Exception e) {
            e.printStackTrace();

            response.status = ResponseType.FAILURE;
            response.message = e.getMessage();
        }

        return response;
    }

    @RequestMapping(value = "/activity/edit")
    public Response editOne(Request request) {
        Response response = new Response();

        try {

            response.status = ResponseType.SUCCESS;
            activityService.editOne(request.getParams());
            response.message = "already edit the activity according to the id input";

        } catch (Exception e) {
            e.printStackTrace();

            response.status = ResponseType.FAILURE;
            response.message = e.getMessage();
        }

        return response;
    }
}
