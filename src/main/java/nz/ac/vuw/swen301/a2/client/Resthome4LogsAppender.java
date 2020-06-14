package nz.ac.vuw.swen301.a2.client;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.spi.LoggingEvent;

public class Resthome4LogsAppender extends AppenderSkeleton {


    public Resthome4LogsAppender() {

    }

    @Override
    protected void append(LoggingEvent event) {

    }

    @Override
    public void close() {

    }

    @Override
    public boolean requiresLayout() {
        return false;
    }
}
