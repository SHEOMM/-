package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import servlet.request.header.RequestHeader;
import servlet.response.header.ResponseHeader;
import util.HtmlReader;

public class Controller {



    public void index(RequestHeader requestHeader, ResponseHeader responseHeader){
        String responseBody = HtmlReader.readHtml("/index.html");
        responseHeader.response(responseBody.getBytes(StandardCharsets.UTF_8));


    }
}
