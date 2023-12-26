package util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class HtmlReader {
    private static final String webAppUrls = "./webapp";
    public static byte[] readHtml(String urls){
        try{
            byte[] body = Files.readAllBytes(new File(webAppUrls + urls).toPath());
            return body;
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
}
