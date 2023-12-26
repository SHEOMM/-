package servlet.request;

import controller.Controller;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import servlet.request.header.RequestHeader;
import servlet.request.header.enums.Method;
import servlet.response.header.ResponseHeader;

public class HandlerMapping {

    private final Controller controller;
    public HandlerMapping(Controller controller) {
        this.controller = controller;
    }

    public void handlerMapping(RequestHeader requestHeader, OutputStream out){
        System.out.println(requestHeader.getUrl());
        if(requestHeader.getUrl().equals("/index.html")){
            ResponseHeader responseHeader = new ResponseHeader(out);
            controller.index(requestHeader, responseHeader);
        }
        if(requestHeader.getUrl().equals("/user/form.html")){
            ResponseHeader responseHeader = new ResponseHeader(out);
            controller.signUpPage(requestHeader, responseHeader);
        }
        if(requestHeader.getUrl().equals("/user/create") && requestHeader.getMethod() == Method.GET){
            ResponseHeader responseHeader = new ResponseHeader(out);
            controller.SignUpGet(requestHeader, responseHeader);
        }

        if(requestHeader.getUrl().equals("/user/create") && requestHeader.getMethod() == Method.POST){
            ResponseHeader responseHeader = new ResponseHeader(out);
            controller.SignUpPost(requestHeader, responseHeader);
        }
        if(requestHeader.getUrl().endsWith(".css")){
            DataOutputStream dos = new DataOutputStream(out);
            try{
                byte[] body = Files.readAllBytes(new File("./webapp" + requestHeader.getUrl()).toPath());
                ResponseHeader responseHeader = new ResponseHeader(out);
                responseHeader.responseCssHeader(dos, body.length);
                responseHeader.responseBody(dos, body);
            }
            catch (IOException e){
                System.out.println(e.getMessage());
            }

        }
    }
}
