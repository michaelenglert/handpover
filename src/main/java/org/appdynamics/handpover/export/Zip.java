package org.appdynamics.handpover.export;

import org.apache.commons.io.FileUtils;
import org.appdynamics.handpover.config.Globals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created by michi on 03.09.16.
 */
@SuppressWarnings("ResultOfMethodCallIgnored")
public class Zip {
    public static void zipDirectory() throws Exception {
        FileOutputStream fos = new FileOutputStream(Globals.OUTPUT_FILE);
        ZipOutputStream zos = new ZipOutputStream(fos);
        File sourceDir = new File(Globals.OUTPUT_FOLDER);
        zipSubDirectory("", sourceDir, zos);
        zos.close();
        new File(Globals.PHANTOMJS_LOG_FILE).delete();
        FileUtils.deleteDirectory(new File(Globals.OUTPUT_FOLDER));
    }

    private static void zipSubDirectory(String basePath, File dir, ZipOutputStream zos) throws Exception {
        byte[] buffer = new byte[4096];
        File[] files = dir.listFiles();

        assert files != null;
        for (File file : files) {
            if (file.isDirectory()) {
                String path = basePath + file.getName() + "/";
                zos.putNextEntry(new ZipEntry(path));
                zipSubDirectory(path, file, zos);
                zos.closeEntry();
            }
            else {
                FileInputStream fis = new FileInputStream(file);
                zos.putNextEntry(new ZipEntry(basePath + file.getName()));
                int length;

                while ((length = fis.read(buffer)) > 0) {
                    zos.write(buffer, 0, length);
                }
                zos.closeEntry();
                fis.close();
            }
        }
    }

}
