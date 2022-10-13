package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertiesFileUtil {
    private static final Properties properties = new Properties();

    /**
     * This method is used to get the properties inside any .properties file using the given path
     *
     * @param propertiesFilePath the path of the properties file
     * @return the properties inside the given properties file
     * @throws IOException general class of exceptions produced by failed or interrupted I/O operations
     */
    public static Properties getProperties(String propertiesFilePath) throws IOException {
        setProperties(propertiesFilePath);
        return properties;
    }

    /**
     * This method is used to get the properties inside the configuration file
     *
     * @return the properties inside the configuration file
     */
    public static Properties getProperties() {
        return properties;
    }

    /**
     * This method is used to extract the data from the given properties file
     *
     * @param propertiesFilePath the path of the properties file
     * @throws IOException general class of exceptions produced by failed or interrupted I/O operations
     */
    public static void setProperties(String propertiesFilePath) throws IOException {
        var reader = new BufferedReader(new FileReader(propertiesFilePath));
        properties.load(reader);
        reader.close();
    }
}
