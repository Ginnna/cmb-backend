package com.cmb.demo.web;

import com.cmb.demo.framework.core.web.Request;
import com.cmb.demo.framework.core.web.Response;
import com.cmb.demo.framework.core.web.ResponseType;
import com.cmb.demo.service.RuleService;
import com.cmb.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/users")
    public Response queryAll(Request request) {
        Response response = new Response();

        try {

            response.status = ResponseType.SUCCESS;

            response.data = userService.queryAll();
            response.message = "already return all users.";

        } catch (Exception e) {
            e.printStackTrace();

            response.status = ResponseType.FAILURE;
            response.message = e.getMessage();
        }

        return response;
    }

    @RequestMapping(value = "/user")
    public Response queryOne(Request request) {
        Response response = new Response();

        try {

            response.status = ResponseType.SUCCESS;

            response.data = userService.queryOne(request.getParams());
            response.message = "already return the user according to the id input";

        } catch (Exception e) {
            e.printStackTrace();

            response.status = ResponseType.FAILURE;
            response.message = e.getMessage();
        }

        return response;
    }

    @RequestMapping(value = "/user/add")
    public Response addOne(Request request) {
        Response response = new Response();

        try {

            response.status = ResponseType.SUCCESS;
            userService.addOne(request.getParams());
            response.message = "already add the user according to the input";

        } catch (Exception e) {
            e.printStackTrace();

            response.status = ResponseType.FAILURE;
            response.message = e.getMessage();
        }

        return response;
    }

    @RequestMapping(value = "/user/delete")
    public Response deleteOne(Request request) {
        Response response = new Response();

        try {

            response.status = ResponseType.SUCCESS;
            userService.deleteOne(request.getParams());
            response.message = "already delete the user according to the id input";

        } catch (Exception e) {
            e.printStackTrace();

            response.status = ResponseType.FAILURE;
            response.message = e.getMessage();
        }

        return response;
    }

    @RequestMapping(value = "/user/edit")
    public Response editOne(Request request) {
        Response response = new Response();

        try {

            response.status = ResponseType.SUCCESS;
            userService.editOne(request.getParams());
            response.message = "already edit the user according to the id input";

        } catch (Exception e) {
            e.printStackTrace();

            response.status = ResponseType.FAILURE;
            response.message = e.getMessage();
        }

        return response;
    }
}
