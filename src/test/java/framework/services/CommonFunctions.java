package framework.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by USER on 10.05.2017.
 */
public class CommonFunctions {

    private static final String BROWSER_PROPERTIES_PATH="brouser.properties";

    private static final String OPINIONS_FILE_PATH="src/test/resources/opinions.csv";

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

    /**
     *  Метод с помощью регулярного выражения ищет
     *  названия классов для элементов с мнениями.
     *  Эти классы отличаются только окончанием.
     *  В коллекцию заносятся только уникальные названия классов
     * @param pageSourse
     * @return
     */
    public Set findClassesForOpinions(String pageSourse){
        Pattern p = Pattern.compile("([b]\\-[a-z_2-]+\\_(green|blue|red))");
        Matcher m = p.matcher(pageSourse);
        Set<String> classes= new LinkedHashSet<>();
        while(m.find()) {
            classes.add(pageSourse.substring(m.start(), m.end()));
        }
        return classes;
    }

    public void saveOpinionsInCsv(List data){

        Path path = Paths.get(OPINIONS_FILE_PATH);
        try {
            Files.write(path, data);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
