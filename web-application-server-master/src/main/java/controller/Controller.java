package controller;

import db.DataBase;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import model.User;
import servlet.request.header.RequestHeader;
import servlet.response.header.ResponseHeader;
import servlet.response.header.enums.StatusCode;
import util.HtmlReader;

public class Controller {
    public void index(RequestHeader requestHeader, ResponseHeader responseHeader){
        byte[] responseBody = HtmlReader.readHtml(requestHeader.getUrl());
        responseHeader.response(responseBody, StatusCode.OK);
    }

    public void signUpPage(RequestHeader requestHeader, ResponseHeader responseHeader){
        byte[] responseBody = HtmlReader.readHtml(requestHeader.getUrl());
        responseHeader.response(responseBody, StatusCode.OK);
    }

    public void SignUpGet(RequestHeader requestHeader, ResponseHeader responseHeader){
        if(requestHeader.getGetBody() == null){
            responseHeader.response("올바른 값을 입력해주세요".getBytes(StandardCharsets.UTF_8), StatusCode.OK);
        }
        String name = requestHeader.getGetBody().get("name");
        String userId = requestHeader.getGetBody().get("userId");
        String password = requestHeader.getGetBody().get("password");
        String email = requestHeader.getGetBody().get("email");
        User user = new User(userId, password, name, email);
        DataBase.addUser(user);
    }

    public void SignUpPost(RequestHeader requestHeader, ResponseHeader responseHeader){
        String name = requestHeader.getPostBody().get("name");
        String userId = requestHeader.getPostBody().get("userId");
        String password = requestHeader.getPostBody().get("password");
        String email = requestHeader.getPostBody().get("email");
        User user = new User(userId, password, name, email);
        DataBase.addUser(user);
        byte[] responseBody = HtmlReader.readHtml("/index.html");
        responseHeader.response(responseBody, StatusCode.FOUND);
    }


}
