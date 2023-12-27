package servlet.response.header;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import servlet.request.ServletRequestHandler;
import servlet.response.header.enums.StatusCode;

public class ResponseHeader {

    private static final Logger log = LoggerFactory.getLogger(ServletRequestHandler.class);

    private StringBuilder responseHeader;
    public OutputStream outputStream;

    public ResponseHeader(OutputStream outputStream) {
        this.outputStream = outputStream;
        this.responseHeader = new StringBuilder();
    }

    public void response(byte[] body, StatusCode statusCode){
        DataOutputStream dos = new DataOutputStream(this.outputStream);
        setStatusCode(statusCode);
        setHtmlHeader();
        setContentLength(body.length);
        buildResponseHeaders(dos);
        responseBody(dos, body);
    }

    public void response(StatusCode statusCode, String url){
        DataOutputStream dos = new DataOutputStream(this.outputStream);
        setStatusCode(statusCode);
        setLocations(url);
        buildResponseHeaders(dos);
    }

    public void response(byte[] body, StatusCode statusCode, String cookie){
        DataOutputStream dos = new DataOutputStream(this.outputStream);
        setStatusCode(statusCode);
        setCookie(cookie);
        buildResponseHeaders(dos);
        responseBody(dos, body);
    }

    public void response(StatusCode statusCode, String url, String cookie){
        DataOutputStream dos = new DataOutputStream(this.outputStream);
        setStatusCode(statusCode);
        setCookie(cookie);
        setLocations(url);
        buildResponseHeaders(dos);
    }

    public void setStatusCode(StatusCode statusCode){
        responseHeader.append("HTTP/1.1 ")
                .append(statusCode.getStatusCode())
                .append(" ").append(statusCode.name())
                .append("\r\n");
    }

    public void setCookie(String cookie){
        responseHeader.append("Set-Cookie: ").append(cookie).append(" \r\n");
    }

    public void setHtmlHeader(){
        responseHeader.append("Content-Type: text/html;charset=utf-8\r\n");
    }

    public void setContentLength(int length){
        responseHeader.append("Content-Length: ").append(length).append(" \r\n");
    }

    public void setLocations(String url){
        responseHeader.append("Location: ").append(url).append(" \r\n");
    }

    public void buildResponseHeaders(DataOutputStream dos){
        String header = responseHeader.append("\r\n").toString();
        try{
            dos.writeBytes(header);
        }catch (IOException e){
            log.error(e.getMessage());
        }
    }

    public void responseBody(DataOutputStream dos, byte[] body) {
        try {
            dos.write(body, 0, body.length);
            dos.flush();
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    public void responseCssHeader(DataOutputStream dos, int lengthOfBodyContent){
        try {
            dos.writeBytes("HTTP/1.1 200 OK \r\n");
            dos.writeBytes("Content-Type: text/css\r\n");
            dos.writeBytes("Content-Length: " + lengthOfBodyContent + "\r\n");
            dos.writeBytes("\r\n");
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

}
