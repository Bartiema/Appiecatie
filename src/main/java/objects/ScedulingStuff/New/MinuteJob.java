package objects.ScedulingStuff.New;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.io.IOException;
import java.time.LocalTime;

public class MinuteJob implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobDataMap data = context.getMergedJobDataMap();
        boolean screenOn = (boolean) data.get("screen");
        Runtime rt = (Runtime) data.get("runtime");
        LocalTime timeOfLastAction = (LocalTime) data.get("time");
        LocalTime currentTime = LocalTime.now();
        LocalTime timeMax = timeOfLastAction.plusMinutes(15);

        if(timeMax.isBefore(timeOfLastAction) && currentTime.isAfter(timeMax)){
            currentTime = currentTime.plusMinutes(15);
            timeMax = timeMax.plusMinutes(15);
        }

        if(screenOn && currentTime.isAfter(timeMax)){
            try {
                Process process = rt.exec("xset dpms force off");
                screenOn = false;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
