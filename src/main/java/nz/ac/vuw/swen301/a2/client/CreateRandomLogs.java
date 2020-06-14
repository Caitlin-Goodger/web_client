package nz.ac.vuw.swen301.a2.client;

import org.apache.log4j.*;
import java.lang.Math.*;

public class CreateRandomLogs {

    private static String[] messages = {"message", "MESSAGE", "the message", "Another message"};
    private static int messageLength = messages.length;

    public static void main (String[] args) {
        Resthome4LogsAppender appender = new Resthome4LogsAppender();
        Logger logger = Logger.getLogger(CreateRandomLogs.class);
        logger.addAppender(appender);
        try {
            while (true) {
                logger.log(getRandomLevel(), messages[((int) (Math.random() * messageLength))]);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {

        }

    }


    private static Level getRandomLevel() {
        int randomNumber = (int) (Math.random()*8);

        if(randomNumber == 1) {
            return Level.ALL;

        } else if(randomNumber == 2) {
            return Level.TRACE;
        } else if(randomNumber == 3) {
            return Level.DEBUG;
        } else if(randomNumber == 4) {
            return Level.INFO;
        } else if(randomNumber == 5) {
            return Level.WARN;
        } else if(randomNumber == 6) {
            return Level.ERROR;
        } else if(randomNumber == 7) {
            return Level.FATAL;
        } else {
            return Level.OFF;
        }
    }

}


