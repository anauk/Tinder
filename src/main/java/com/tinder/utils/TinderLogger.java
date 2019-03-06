package com.tinder.utils;



import com.tinder.Interface.Logger;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;


public class TinderLogger implements Logger {

    @Override
    public void info(String msg) {
        try {
            Date now = new Date();
            String currentTime = TinderLogger.df.format(now);
            FileWriter fileWriter = new FileWriter(logFile, true);
            fileWriter.write(currentTime + " [DEBUG] " + msg
                    + System.getProperty("line.separator"));
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void error(String msg) {
        try {
            Date now = new Date();
            String currentTime = TinderLogger.df.format(now);
            FileWriter fileWriter = new FileWriter(logFile, true);
            fileWriter.write(currentTime + " [ERROR] " + msg
                    + System.getProperty("line.separator"));
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
