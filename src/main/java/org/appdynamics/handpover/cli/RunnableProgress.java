package org.appdynamics.handpover.cli;

import org.appdynamics.handpover.screenshots.Capture;

/**
 * Created by stefan.marx on 07.12.16.
 */
public class RunnableProgress implements Runnable {
    private Runnable _runnable;
    private String _title;

    public RunnableProgress(Runnable runnable, String title) {
        _runnable = runnable;
        _title = title;
    }

    @Override
    public void run() {
        long now = System.currentTimeMillis();
        System.out.println("Starting :"+_title);
        _runnable.run();
        long time = (System.currentTimeMillis()-now)/1000;
        System.out.println("Finished :"+_title + " "+time+" seconds");


    }
}
