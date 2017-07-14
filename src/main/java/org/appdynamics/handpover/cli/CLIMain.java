package org.appdynamics.handpover.cli;

import com.appdynamics.ace.util.cli.api.api.CommandlineExecution;

/**
 * Created by stefan.marx on 07.12.16.
 */
public class CLIMain {
    public static void main(String[] args) {
        CommandlineExecution cle = new CommandlineExecution("handover-tool");
        cle.addCommand(new GUICommand());
        cle.addCommand(new CLICommand());

        cle.setHelpVerboseEnabled(true);
        int returnCode = cle.execute(args);


        System.exit(returnCode);


    }
}
