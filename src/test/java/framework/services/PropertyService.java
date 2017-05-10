package framework.services;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by USER on 10.05.2017.
 */
public class PropertyService {

    private static final String BROWSER_PROPERTIES_PATH="src/test/resources/brouser.properties";

    public Properties readProperties(){
        FileInputStream fis= null;
        Properties property = new Properties();
        try {
            fis = new FileInputStream(BROWSER_PROPERTIES_PATH);
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
