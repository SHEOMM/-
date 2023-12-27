package controller;

import db.DataBase;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Collection;
import java.util.List;
import java.util.Map;
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

    public void signUpGet(RequestHeader requestHeader, ResponseHeader responseHeader){
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

    public void signUpPost(RequestHeader requestHeader, ResponseHeader responseHeader){
        String name = requestHeader.getPostBody().get("name");
        String userId = requestHeader.getPostBody().get("userId");
        String password = requestHeader.getPostBody().get("password");
        String email = requestHeader.getPostBody().get("email");
        User user = new User(userId, password, name, email);
        DataBase.addUser(user);
        responseHeader.response(StatusCode.REDIRECT, "/index.html");
    }

    public void signInGet(RequestHeader requestHeader, ResponseHeader responseHeader){
        byte[] responseBody = HtmlReader.readHtml(requestHeader.getUrl());
        responseHeader.response(responseBody, StatusCode.OK);
    }

    public void signInPost(RequestHeader requestHeader, ResponseHeader responseHeader){
        String userId = requestHeader.getPostBody().get("userId");
        String password = requestHeader.getPostBody().get("password");
        try{
            User user = DataBase.findUserById(userId);
            if (user.getPassword().equals(password)){
                responseHeader.response(StatusCode.REDIRECT, "/index.html", "logined=true");
            }else{
                throw new NullPointerException();
            }
        }catch (NullPointerException e){
            byte[] responseBody = HtmlReader.readHtml("/user/login_failed.html");
            responseHeader.response(responseBody, StatusCode.OK, "logined=false");
        }

    }
    public void list(RequestHeader requestHeader, ResponseHeader responseHeader){
        Map<String, String> cookie = requestHeader.getCookie();
        if(cookie.isEmpty() || cookie.get("logined").equals("false")){
            byte[] responseBody = HtmlReader.readHtml("/index.html");
            responseHeader.response(responseBody, StatusCode.OK);
            return;
        }
        Collection<User> allUsers = DataBase.findAll();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<table>");
        for(User user : allUsers){
            stringBuilder.append("<tr>");
            stringBuilder.append("<td>" + user.getUserId() + "</td>");
            stringBuilder.append("<td>" + user.getName() + "</td>");
            stringBuilder.append("<td>" + user.getEmail() + "</td>");
        }
        stringBuilder.append("</table>");
        byte[] responseBody = stringBuilder.toString().getBytes(StandardCharsets.UTF_8);
        responseHeader.response(responseBody, StatusCode.OK);
    }


}
