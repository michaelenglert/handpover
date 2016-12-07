package org.appdynamics.handpover.cli;

import com.appdynamics.ace.util.cli.api.api.AbstractCommand;
import com.appdynamics.ace.util.cli.api.api.Command;
import com.appdynamics.ace.util.cli.api.api.CommandException;
import com.appdynamics.ace.util.cli.api.api.OptionWrapper;
import org.apache.commons.cli.Option;
import org.appdynamics.handpover.gui.EntryWindow;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by stefan.marx on 07.12.16.
 */
public class GUICommand extends AbstractCommand {
    @Override
    protected List<Option> getCLIOptionsImpl() {
        return new ArrayList<Option>();
    }

    @Override
    protected int executeImpl(OptionWrapper options) throws CommandException {
        EntryWindow dialog = new EntryWindow();
        dialog.pack();
        dialog.setVisible(true);

        return 0;
    }

    @Override
    public String getName() {
        return "startGui";
    }

    @Override
    public String getDescription() {
        return "Start GUI Dialog !";
    }
}
