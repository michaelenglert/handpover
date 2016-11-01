package org.appdynamics.handpover.export;

import org.appdynamics.handpover.config.Globals;

import java.io.File;

/**
 * Created by michi on 01.11.16.
 */
public class Folder {
    public static void createFolder(String folder){
        File createFolder = new File(folder);
        if (!createFolder.exists()) {
            Boolean result = createFolder.mkdir();
            if (!result) {
                throw new RuntimeException(Globals.ERROR_FOLDER);
            }
        }
    }

}
