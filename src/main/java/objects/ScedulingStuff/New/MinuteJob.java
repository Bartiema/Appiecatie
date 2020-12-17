package objects.ScedulingStuff.New;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.awt.*;
import java.io.IOException;
import java.time.LocalTime;

public class MinuteJob implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobDataMap data = context.getMergedJobDataMap();
        Runtime rt = (Runtime) data.get("runtime");
        LocalTime timeOfLastAction = (LocalTime) data.get("time");
        LocalTime currentTime = LocalTime.now();
        LocalTime timeMax = timeOfLastAction.plusMinutes(30);

        boolean screenOn = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices().length != 0;
        if(screenOn && currentTime.isAfter(timeMax)){
            try {
                Process process = rt.exec("xset dpms force off");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
