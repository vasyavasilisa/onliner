package framework.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by USER on 10.05.2017.
 */
public class PropertyService {

    private static final String BROWSER_PROPERTIES_PATH="brouser.properties";

    public Properties readProperties(){
        File ff = new File(getClass().getClassLoader().getResource(BROWSER_PROPERTIES_PATH).getFile());
        FileInputStream fis= null;
        Properties property = new Properties();
        try {
            fis = new FileInputStream(ff);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            property.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return property;
    }
}
