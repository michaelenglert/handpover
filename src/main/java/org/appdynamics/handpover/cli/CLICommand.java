package org.appdynamics.handpover.cli;

import com.appdynamics.ace.util.cli.api.api.AbstractCommand;
import com.appdynamics.ace.util.cli.api.api.CommandException;
import com.appdynamics.ace.util.cli.api.api.OptionWrapper;
import org.apache.commons.cli.Option;
import org.appdynamics.handpover.config.Globals;
import org.appdynamics.handpover.export.Folder;
import org.appdynamics.handpover.export.Zip;
import org.appdynamics.handpover.rest.*;
import org.appdynamics.handpover.screenshots.Capture;


import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * Created by stefan.marx on 07.12.16.
 */
public class CLICommand extends AbstractCommand {

    public static final String URL_OPT = "url";
    public static final String USER = "user";
    public static final String PASSWORD = "password";
    public static final String ACCOUNT = "account";
    public static final String OUTPUT_FOLDER = "outputFolder";
    public static final String EXCLUDE = "exclude";

    @Override
    protected List<Option> getCLIOptionsImpl() {

        List<Option> opts = new ArrayList<Option>();
        Option o = null;

        opts.add(o= new Option(URL_OPT,true,"Controller URL (http://controller.host.com:8090)"));
        o.setRequired(true);

        opts.add(o= new Option(USER,true,"Username"));
        o.setRequired(true);

        opts.add(o= new Option(PASSWORD,true,"Password"));
        o.setRequired(true);

        opts.add(o = new Option(ACCOUNT,true,"Accountname (default:Customer1"));
        o.setRequired(false);

        opts.add(o = new Option(OUTPUT_FOLDER,true,"Output folder , temporary files (Default: ./output"));
        o.setRequired(false);

        String values = "";
        for (ExportTask t : ExportTask.values()) {
            if (values.length()>0) values +=", ";
            values += t.name();
        }
        opts.add(o = new Option(EXCLUDE,true,"Exclude Task ("+values+")"));
        o.setRequired(false);



        return opts ;
    }

    @Override
    protected int executeImpl(OptionWrapper options) throws CommandException {
        ExecutorService executor = Executors.newFixedThreadPool(5);
        Base base = new Base();
        String password = new String(options.getOptionValue(PASSWORD));

        Map<ExportTask,Runnable> execTasks = new HashMap<ExportTask,Runnable>();


        Globals.URL = options.getOptionValue(URL_OPT);

        if (!Globals.URL.contains("saas.appdynamics.com")) {
            execTasks.put(ExportTask.LOGS,(new RunnableProgress(new GetLogs(),"Server Logs")));
        }
        execTasks.put(ExportTask.HOMEPAGE,(new RunnableProgress(new Capture(),"capture")));
        execTasks.put(ExportTask.DASHBOARDS,(new RunnableProgress(new GetDashboards(),"Dashboards")));
        execTasks.put(ExportTask.APPLICATIONS,(new RunnableProgress(new GetApps(),"Application configs")));
        execTasks.put(ExportTask.SETTINGS,(new RunnableProgress(new GetSettings(),"Settings")));
        execTasks.put(ExportTask.AUDIT,(new RunnableProgress(new GetAudit(),"Audit Logs")));

        for (String ex: options.getOptionValues(EXCLUDE)) {
            ExportTask task = ExportTask.valueOf(ex.toUpperCase());
            if (task != null && execTasks.containsKey(task)) {
                execTasks.remove(task);
            }
        }

        Folder.createFolder(options.getOptionValue(OUTPUT_FOLDER, Globals.OUTPUT_FOLDER));
        try {

            base.doAuth(options.getOptionValue(USER), password, options.getOptionValue(ACCOUNT, "customer1"));

            for (Runnable r: execTasks.values()) {
                executor.execute(r);
            }


            executor.shutdown();
            while (!executor.isTerminated()) {
                Thread.sleep(1000);
                System.out.print(".");
           }
            Zip.zipDirectory();
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public String getName() {
        return "export";
    }

    @Override
    public String getDescription() {
        return "Export handover documents.";
    }
}
