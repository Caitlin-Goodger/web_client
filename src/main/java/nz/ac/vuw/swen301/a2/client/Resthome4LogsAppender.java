package nz.ac.vuw.swen301.a2.client;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.spi.LoggingEvent;

public class Resthome4LogsAppender extends AppenderSkeleton {

    private String logServiceURL = "http://localhost:8080/resthome4logs/logs";
    private int maxSize = 1000;

    public Resthome4LogsAppender() {

    }

    @Override
    protected void append(LoggingEvent event) {

    }

    private String getLogServiceURL() {
        return logServiceURL;
    }

    private void setLogServiceURL(String logurl) {
        logServiceURL = logurl;
    }

    private int getMaxSize() {
        return maxSize;
    }

    private void setMaxSize( int newMaxSize) {
        maxSize = newMaxSize;
    }

    @Override
    public void close() {

    }

    @Override
    public boolean requiresLayout() {
        return false;
    }
}
