package com.javalog.log;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.*;


public class LoggingExample {
    static Logger logger = Logger.getLogger(LoggingExample.class.getName());

    public static void main(String[] args){
        /*try{
            LogManager.getLogManager().readConfiguration(new FileInputStream("mylogging.properties"));
        } catch (SecurityException | IOException e1){
            e1.printStackTrace();
        }*/
        logger.setLevel(Level.FINE);
        logger.addHandler(new ConsoleHandler());
        //add custom Handler
        logger.addHandler(new MyHandler());
        try {
            //FileHandler filename with max size and add number of log files limit
            Handler fileHandler = new FileHandler("logger.log",2000,5);
            fileHandler.setFormatter(new MyFormatter());
            //setting custom filter for FileHandler
            fileHandler.setFilter(new MyFilter());
            logger.addHandler(fileHandler);

            for (int i=0; i<1000; i++){
                //logging messages
                logger.log(Level.INFO, "Msg "+i);
            }
            logger.log(Level.CONFIG, "Config Data");
        } catch (SecurityException | IOException e){
            e.printStackTrace();
        }

    }
}
