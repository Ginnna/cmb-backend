package com.cmb.demo.web;

import com.cmb.demo.framework.core.web.Request;
import com.cmb.demo.framework.core.web.Response;
import com.cmb.demo.framework.core.web.ResponseType;
import com.cmb.demo.service.ActivityService;
import com.cmb.demo.service.AwardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class AwardController {
    @Autowired
    private AwardService awardService;

    @RequestMapping(value = "/awards")
    public Response queryAll(Request request) {
        Response response = new Response();

        try {

            response.status = ResponseType.SUCCESS;

            response.data = awardService.queryAll();
            response.message = "already return all awards.";

        } catch (Exception e) {
            e.printStackTrace();

            response.status = ResponseType.FAILURE;
            response.message = e.getMessage();
        }

        return response;
    }

    @RequestMapping(value = "/award")
    public Response queryOne(Request request) {
        Response response = new Response();

        try {

            response.status = ResponseType.SUCCESS;

            response.data = awardService.queryOne(request.getParams());
            response.message = "already return the award according to the id input";

        } catch (Exception e) {
            e.printStackTrace();

            response.status = ResponseType.FAILURE;
            response.message = e.getMessage();
        }

        return response;
    }

    @RequestMapping(value = "/award/add")
    public Response addOne(Request request) {
        Response response = new Response();

        try {

            response.status = ResponseType.SUCCESS;
            awardService.addOne(request.getParams());
            response.message = "already add the award according to the input";

        } catch (Exception e) {
            e.printStackTrace();

            response.status = ResponseType.FAILURE;
            response.message = e.getMessage();
        }

        return response;
    }

    @RequestMapping(value = "/award/delete")
    public Response deleteOne(Request request) {
        Response response = new Response();

        try {

            response.status = ResponseType.SUCCESS;
            awardService.deleteOne(request.getParams());
            response.message = "already delete the award according to the id input";

        } catch (Exception e) {
            e.printStackTrace();

            response.status = ResponseType.FAILURE;
            response.message = e.getMessage();
        }

        return response;
    }

    @RequestMapping(value = "/award/edit")
    public Response editOne(Request request) {
        Response response = new Response();

        try {

            response.status = ResponseType.SUCCESS;
            awardService.editOne(request.getParams());
            response.message = "already edit the award according to the id input";

        } catch (Exception e) {
            e.printStackTrace();

            response.status = ResponseType.FAILURE;
            response.message = e.getMessage();
        }

        return response;
    }
}
