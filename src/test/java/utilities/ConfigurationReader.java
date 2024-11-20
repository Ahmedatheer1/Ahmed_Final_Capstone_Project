package utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationReader {

    private static Properties configFile;//declaring the configFile

    //static block gets loaded first thing in the memory
    static {
        String path = "src/test/resources/configuration.properties";
        try {
            FileInputStream  fileInputStream = new FileInputStream(path);//loading the file
            configFile = new Properties();//we are instantiating the configFile object
            configFile.load(fileInputStream);
            fileInputStream.close();//close the stream
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * This method will return the value of the key that was passed as a parameter
     * from the property file
     * @param key the key from the property file
     * @return
     */
    public static String getPropertyValue(String key){
        return configFile.getProperty(key);
    }

}