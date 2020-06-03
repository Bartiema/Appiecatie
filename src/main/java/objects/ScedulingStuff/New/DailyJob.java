package objects.ScedulingStuff.New;

import contollers.MainController;
import objects.AccountStuff.AccountList;
import objects.lineChartStuff.DataNodeList;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.LinkedList;

public class DailyJob implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobDataMap data = context.getMergedJobDataMap();
        AccountList accounts = (AccountList) data.get("accounts");
        MainController controller = (MainController) data.get("controller");
        LinkedList<DataNodeList> dataNodeLists = (LinkedList<DataNodeList>) data.get("dataNodeLists");

        for (DataNodeList d : dataNodeLists) d.update();

        controller.writeDaily();
        controller.write();
    }
}
