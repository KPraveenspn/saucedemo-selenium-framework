package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * ConfigReader - Reads configuration values from config.properties file
 * This class uses Singleton pattern to ensure only one instance exists
 */
public class ConfigReader {

    private static Properties properties;
    private static final String CONFIG_FILE_PATH = "src/test/resources/config.properties";

    // Static block - loads properties file when class is first loaded
    static {
        try {
            FileInputStream fis = new FileInputStream(CONFIG_FILE_PATH);
            properties = new Properties();
            properties.load(fis);
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load config.properties file");
        }
    }

    /**
     * Get property value by key
     * @param key - property name from config.properties
     * @return property value as String
     */
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    /**
     * Get browser name from config
     */
    public static String getBrowser() {
        return properties.getProperty("browser");
    }

    /**
     * Get application URL from config
     */
    public static String getAppURL() {
        return properties.getProperty("appURL");
    }

    /**
     * Get implicit wait time from config
     */
    public static int getImplicitWait() {
        return Integer.parseInt(properties.getProperty("implicitWait"));
    }

    /**
     * Get explicit wait time from config
     */
    public static int getExplicitWait() {
        return Integer.parseInt(properties.getProperty("explicitWait"));
    }
}