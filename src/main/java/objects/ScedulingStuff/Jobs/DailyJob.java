package objects.ScedulingStuff.Jobs;

import contollers.MainController;
import objects.AccountStuff.AccountList;
import objects.AudioOutputOverHead;
import objects.lineChartStuff.DataNodeList;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;

public class DailyJob implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobDataMap data = context.getMergedJobDataMap();
        AccountList accounts = (AccountList) data.get("accounts");
        MainController controller = (MainController) data.get("controller");
        LinkedList<DataNodeList> monthNodeLists = (LinkedList<DataNodeList>) data.get("monthNodeLists");
        LinkedList<DataNodeList> yearNodeLists = (LinkedList<DataNodeList>) data.get("yearNodeLists");

        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("D");

        for (DataNodeList d : monthNodeLists) d.update(d.getDataOwner().getDrankThisMonth(), date.getDate());
        for (DataNodeList d : yearNodeLists) d.update(d.getDataOwner().getDrankThisYear(), Integer.parseInt(format.format(date)));

        GregorianCalendar calendar = new GregorianCalendar();
        calendar.add(GregorianCalendar.DATE, 1);

        if(accounts.isBirthday(calendar.getTime())){
            AudioOutputOverHead.playAudio("src/main/resources/sounds/bday.wav");
        }


        controller.writeDaily();
        controller.write();
    }
}
