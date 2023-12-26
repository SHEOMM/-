package servlet.request;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import servlet.request.header.RequestHeader;
import servlet.request.header.RequestHeaderMapper;
import servlet.request.header.RequestHeaderParser;

public class ServletRequestHandler extends Thread implements RequestHandler {
    private static final Logger log = LoggerFactory.getLogger(ServletRequestHandler.class);
    private RequestHeaderParser requestHeaderParser;
    private RequestHeaderMapper requestHeaderMapper;

    private Socket connection;

    public ServletRequestHandler(Socket connectionSocket) {
        this.connection = connectionSocket;
        this.requestHeaderParser = new RequestHeaderParser();
        this.requestHeaderMapper = new RequestHeaderMapper();
    }
    
    @Override
    public void run() {
        log.debug("New Client Connect! Connected IP : {}, Port : {}", connection.getInetAddress(),
                connection.getPort());

        try (InputStream in = connection.getInputStream(); OutputStream out = connection.getOutputStream()) {

            Map<String, List<String>> headers = requestHeaderParser.parse(in);
            RequestHeader requestHeader = requestHeaderMapper.map(headers);

            // TODO 사용자 요청에 대한 처리는 이 곳에 구현하면 된다.
            DataOutputStream dos = new DataOutputStream(out);
            byte[] body = "Hello World".getBytes();
            response200Header(dos, body.length);
            responseBody(dos, body);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    private void response200Header(DataOutputStream dos, int lengthOfBodyContent) {
        try {
            dos.writeBytes("HTTP/1.1 200 OK \r\n");
            dos.writeBytes("Content-Type: text/html;charset=utf-8\r\n");
            dos.writeBytes("Content-Length: " + lengthOfBodyContent + "\r\n");
            dos.writeBytes("\r\n");
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    private void responseBody(DataOutputStream dos, byte[] body) {
        try {
            dos.write(body, 0, body.length);
            dos.flush();
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }
}
