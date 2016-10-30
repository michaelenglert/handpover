package org.appdynamics.handpover.export;

import org.appdynamics.handpover.config.Globals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created by michi on 03.09.16.
 */
public class Zip {
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static void zipFiles() throws Exception{
        File sourceFile = new File(Globals.OUTPUT_FOLDER);
        File[] files = sourceFile.listFiles();
        FileOutputStream fos = new FileOutputStream(Globals.OUTPUT_FILE);

        ZipOutputStream zos = new ZipOutputStream(fos);

        assert files != null;
        for (File file : files) {

            byte[] buffer = new byte[1024];

            FileInputStream fis = new FileInputStream(file);

            zos.putNextEntry(new ZipEntry(file.getName()));

            int length;

            while ((length = fis.read(buffer)) > 0) {
                zos.write(buffer, 0, length);
            }

            zos.closeEntry();

            fis.close();

        }

        zos.close();

        for (File file : files){
            file.delete();
        }

        new File(Globals.OUTPUT_FOLDER).delete();
        new File("phantomjsdriver.log").delete();
    }

}
