package objects.ScedulingStuff.Jobs;

import contollers.MainController;
import objects.AccountStuff.AccountList;
import objects.lineChartStuff.DataNode;
import objects.lineChartStuff.DataNodeList;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;
import java.util.Scanner;

public class YearlyJob implements Job {
        @Override
        public void execute(JobExecutionContext context) throws JobExecutionException {
            JobDataMap data = context.getMergedJobDataMap();
            AccountList accounts = (AccountList) data.get("accounts");
            MainController controller = (MainController) data.get("controller");
            LinkedList<DataNodeList> yearNodeList = (LinkedList<DataNodeList>) data.get("yearNodeList");

            Date currDate = new Date();
            File file = new File("src/main/resources/files/ZuipStats/" + 1900 + currDate.getYear() + "-ZuipStats.txt");

            StringBuilder s = new StringBuilder();
            for (DataNodeList d: yearNodeList){
                s.append(d.toWrite());
            }

            try {
                if(file.createNewFile()){
                    FileWriter writer = new FileWriter(file);
                    writer.write(s.toString());
                    writer.close();
                } else {
                    Scanner sc = new Scanner(file);
                    while (sc.hasNextLine()){
                        s.append(sc.nextLine());
                    }
                    sc.close();
                    FileWriter writer = new FileWriter(file);
                    writer.write(s.toString());
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            accounts.updateYear();
            controller.write();
        }
}
