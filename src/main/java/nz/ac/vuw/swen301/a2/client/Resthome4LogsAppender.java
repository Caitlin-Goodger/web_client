package nz.ac.vuw.swen301.a2.client;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.spi.LoggingEvent;
import org.json.JSONObject;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import java.util.ArrayList;
import java.util.UUID;

public class Resthome4LogsAppender extends AppenderSkeleton {

    private String logServiceURL = "http://localhost:8080/resthome4logs/logs";
    private int maxSize = 1000;
    private long discardCount = 0;
    ArrayList<LoggingEvent> logs = new ArrayList();

    public Resthome4LogsAppender() {

    }

    @Override
    protected void append(LoggingEvent event) {
        if(logs.size() > maxSize) {
            logs.remove(maxSize);
            discardCount++;
        }
        logs.add(event);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", UUID.randomUUID().toString());
        jsonObject.put("level", event.getLevel().toString());
        jsonObject.put("timestamp", event.getTimeStamp());
        jsonObject.put("thread",event.getThreadName());
        jsonObject.put("message", event.getMessage());

        String jsonString = jsonObject.toString();

        try {

            HttpClient httpClient = HttpClientBuilder.create().build();
            HttpPost request = new HttpPost(logServiceURL);
            request.setEntity(new StringEntity(jsonString));
            request.setHeader("Accept", "application/json");
            request.setHeader("Content-type", "application/json");
            httpClient.execute(request);
            System.out.println(jsonString);
        } catch (Exception e ) {
            System.out.println("Exception");
        }

    }



    public String getLogServiceURL() {
        return logServiceURL;
    }

    public void setLogServiceURL(String logurl) {
        logServiceURL = logurl;
    }

    public int getMaxSize() {
        return maxSize;
    }

    public void setMaxSize( int newMaxSize) {
        maxSize = newMaxSize;
    }

    public long getDiscardCount() {
        return discardCount;
    }

    public void setDiscardCount(long newDiscardCount) {
        discardCount = newDiscardCount;
    }

    @Override
    public void close() {

    }

    @Override
    public boolean requiresLayout() {
        return false;
    }
}
