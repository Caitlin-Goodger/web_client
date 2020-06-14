package nz.ac.vuw.swen301.a2.client;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.spi.LoggingEvent;
import org.json.JSONObject;

import java.util.ArrayList;

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
