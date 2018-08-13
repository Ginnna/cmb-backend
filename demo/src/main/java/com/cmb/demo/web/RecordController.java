package com.cmb.demo.web;

import com.cmb.demo.framework.core.web.Request;
import com.cmb.demo.framework.core.web.Response;
import com.cmb.demo.framework.core.web.ResponseType;
import com.cmb.demo.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class RecordController {
    @Autowired
    private RecordService recordService;

    @RequestMapping(value = "/records")
    public Response queryAll(Request request) {
        Response response = new Response();

        try {

            response.status = ResponseType.SUCCESS;

            response.data = recordService.queryAll();
            response.message = "already return all records.";

        } catch (Exception e) {
            e.printStackTrace();

            response.status = ResponseType.FAILURE;
            response.message = e.getMessage();
        }

        return response;
    }

    @RequestMapping(value = "/record")
    public Response queryOne(Request request) {
        Response response = new Response();

        try {

            response.status = ResponseType.SUCCESS;

            response.data = recordService.queryOne(request.getParams());
            response.message = "already return the record according to the id input";

        } catch (Exception e) {
            e.printStackTrace();

            response.status = ResponseType.FAILURE;
            response.message = e.getMessage();
        }

        return response;
    }

    @RequestMapping(value = "/record/user")
    public Response queryByUser(Request request) {
        Response response = new Response();

        try {

            response.status = ResponseType.SUCCESS;

            response.data = recordService.queryByUser(request.getParams());
            response.message = "already return the record according to the id input";

        } catch (Exception e) {
            e.printStackTrace();

            response.status = ResponseType.FAILURE;
            response.message = e.getMessage();
        }

        return response;
    }

    @RequestMapping(value = "/record/add")
    public Response addOne(Request request) {
        Response response = new Response();

        try {

            response.status = ResponseType.SUCCESS;
            recordService.addOne(request.getParams());
            response.message = "already add the record according to the input";

        } catch (Exception e) {
            e.printStackTrace();

            response.status = ResponseType.FAILURE;
            response.message = e.getMessage();
        }

        return response;
    }

    @RequestMapping(value = "/record/delete")
    public Response deleteOne(Request request) {
        Response response = new Response();

        try {

            response.status = ResponseType.SUCCESS;
            recordService.deleteOne(request.getParams());
            response.message = "already delete the record according to the id input";

        } catch (Exception e) {
            e.printStackTrace();

            response.status = ResponseType.FAILURE;
            response.message = e.getMessage();
        }

        return response;
    }

    @RequestMapping(value = "/record/edit")
    public Response editOne(Request request) {
        Response response = new Response();

        try {

            response.status = ResponseType.SUCCESS;
            recordService.editOne(request.getParams());
            response.message = "already edit the record according to the id input";

        } catch (Exception e) {
            e.printStackTrace();

            response.status = ResponseType.FAILURE;
            response.message = e.getMessage();
        }

        return response;
    }
}
