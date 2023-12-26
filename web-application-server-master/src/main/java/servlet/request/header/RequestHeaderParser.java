package servlet.request.header;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import servlet.Parser;
import servlet.request.ServletRequestHandler;
import util.IOUtils;

public class RequestHeaderParser implements Parser {

    private static final Logger log = LoggerFactory.getLogger(ServletRequestHandler.class);

    @Override
    public Map<String, List<String>> parse(InputStream in) {
        InputStreamReader inputStreamReader = new InputStreamReader(in);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        Map<String, List<String>> headers = new HashMap<>();

        while(true){
            try{
                String rawHeader = bufferedReader.readLine();
                if(rawHeader.isEmpty())
                    break;

                List<String> values = new LinkedList<>();
                String[] parsedHeaders = rawHeader.split(" ");
                String key = parsedHeaders[0];
                if (key.endsWith(":")){
                    key = key.substring(0, key.length() - 1);
                }
                values.addAll(Arrays.asList(parsedHeaders).subList(1, parsedHeaders.length));
                headers.put(key, values);

            } catch (IOException e) {
                // TODO : handling readLine Parsing Error
                log.error(e.getMessage());
            }

        }
        String body = null;
        if(headers.containsKey("POST") && headers.containsKey("Content-Length")){
            int contentLength = Integer.parseInt(headers.get("Content-Length").get(0));
            try{
                 body = IOUtils.readData(bufferedReader, contentLength);
                 System.out.println("body = " + body);

            }catch (IOException e){
                log.debug(e.getMessage());
            }
        }
        List<String> bodies = new LinkedList<>();
        bodies.add(body);
        headers.put("body", bodies);

        return headers;
    }


}
