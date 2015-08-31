package Screenshot;

/**
 * Created by innoraft on 12/8/15.
 */

import com.esotericsoftware.yamlbeans.YamlReader;
import com.esotericsoftware.yamlbeans.YamlWriter;

import java.io.*;
import java.util.Map;

public class ScreenshotCompare {
    public static void main(String[] args) throws IOException, InterruptedException {
        // Give sitelevel with stack or driver
        String Structure = "Multiple";
        String SiteLevel1 = "23July";
        String SiteLevel2 = "18Aug";
        String ScreenshotThrough = "Stack";
        String Site = "HCL";
        String SitePath = "/home/innoraft/TestingFiles/Screenshots/" + Site + "/";

        ScreenshotFunctions func = new ScreenshotFunctions();

        // Merge folders
        func.MergeFolders(SiteLevel1, SiteLevel2, ScreenshotThrough, SitePath, Structure);
//        func.ChangeWidth(SiteLevel1, SiteLevel2, ScreenshotThrough, SitePath);

        // Screenshot comparision
        Process p = null;
        String Config_Path = "/home/innoraft/TestingFiles/Screenshots/";
        String Config_File = Config_Path + "configs/config_compare.yaml";
        File config = new File(Config_Path);

        String[] commands = {"wraith crop_images config_compare", "wraith compare_images config_compare", "wraith generate_thumbnails config_compare", "wraith generate_gallery config_compare"};
        File browsersDirectory = new File(SitePath + SiteLevel1 + ScreenshotThrough + "_" + SiteLevel2 + ScreenshotThrough);
        File[] browsers = browsersDirectory.listFiles();


        for (int i = 0; i < browsers.length; i++) {
            // The YamlReader class is used to deserialize YAML to Java objects.The "read" method reads the next YAML document and
            // deserializes it into HashMaps, ArrayLists, and Strings.
            YamlReader yamlReader = new YamlReader(new FileReader(Config_File));
            Object object = yamlReader.read();
            Map map = (Map)object;

            // The YamlWriter class is used to serialize Java objects to YAML. The "write" method automatically handles this by recognizing
            // public fields and bean getter methods.
            map.put("directory", Site + "/" + SiteLevel1 + ScreenshotThrough + "_" + SiteLevel2 + ScreenshotThrough + "/" + browsers[i].getName());
            YamlWriter yamlWriter = new YamlWriter(new FileWriter(Config_File));
            yamlWriter.write(map);
            yamlWriter.close();

            for (int j = 0; j < commands.length; j++) {
                System.out.println(commands[j]);
                // Method exec() executes the specified string command in a separate process with the specified environment and working directory.
                p = Runtime.getRuntime().exec(commands[j], null, config);
                // Method waitFor() causes the current thread to wait, if necessary, until the process represented by Process object has terminated.
                p.waitFor();

                // Method getInputStream() read from process in byte form and Method BufferedReader convert byte form to character form.
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(p.getInputStream()));

                String line = "";
                while ((line = bufferedReader.readLine())!= null) {
                    System.out.println(line);
                }

                if (j == 1) {
                    func.RenameFilesAfterScreenshotComparision(SitePath + SiteLevel1 + ScreenshotThrough + "_" + SiteLevel2 + ScreenshotThrough + "/" + browsers[i].getName());
                }
            }
        }
    }
}
