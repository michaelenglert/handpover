package org.appdynamics.handpover.rest;

import com.sun.jersey.api.client.ClientResponse;
import org.apache.poi.util.IOUtils;
import org.appdynamics.handpover.config.Globals;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * Created by michi on 01.09.16.
 */
public class GetLogs implements Runnable {
    public void run (){
        try {
            doGetControllerLogs();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static void doGetControllerLogs() throws Exception{
        ClientResponse response;

        response = Base.getClientResponse(Globals.URL + Globals.CONTROLLER_ROOT + Globals.API_CONTROLLER_LOGS);

        InputStream input = response.getEntityInputStream();

        byte[] byteArray = IOUtils.toByteArray(input);

        FileOutputStream fos = new FileOutputStream(new File(Globals.OUTPUT_FOLDER+Globals.CONTROLLER_LOG_FILES));
        fos.write(byteArray);
        fos.flush();
        fos.close();
    }
}
