/*
 * Group Structure -
 *
 * |--Date
 *    |--Screenshot of All Browser of All Filenames
 */

package Screenshot;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * Created by admin on 4/24/15.
 */
public class ScreenSingleToGroup {
    public static void main(String arg[]) throws IOException {
        String Path = "C:\\Users\\aman\\Downloads\\TestingFiles\\Screenshots\\HCL\\";
        String SiteLevel = "9JulyStack_10JulyStack";

        File Folder = new File(Path + SiteLevel);
        File[] FolderFolders = Folder.listFiles();
        for (int i =0; i < FolderFolders.length; i++) {
            System.out.println(i + FolderFolders[i].getName());
            File FolderFolder = new File(Path + SiteLevel + "\\" + FolderFolders[i].getName());
            File[] FolderFolderFiles = FolderFolder.listFiles();
            for (int j = 0; j < FolderFolderFiles.length; j++) {
                System.out.println(j + FolderFolderFiles[j].getName());
                File Dir = new File(Path + "Group" + SiteLevel);
                if (!Dir.exists()) {
                    Dir.mkdir();
                }
                Path Original = Paths.get(Path + SiteLevel + "\\" + FolderFolders[i].getName() + "\\" + FolderFolderFiles[j].getName());
                Path Destination = Paths.get(Path + "Group" + SiteLevel + "\\" + FolderFolderFiles[j].getName());
                Files.copy(Original, Destination, StandardCopyOption.REPLACE_EXISTING);
            }
        }
    }
}