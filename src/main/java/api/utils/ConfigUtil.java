package api.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigUtil {
    private static final Properties properties = new Properties();

    static {
        try (InputStream input = ConfigUtil.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                System.out.println("Sorry, unable to find config.properties");
            } else {
                properties.load(input);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static String getBaseUrl() {
        return properties.getProperty("base.url");
    }

    /**
     * Using system variables to securely store api keys. In real life scenario this approach could be replaced with AWS Secrets manager for example.
     * @param apiKeyType
     * @return API key
     */
    public static String getApiKey(String apiKeyType) {
        return switch (apiKeyType.toLowerCase()) {
            case "valid" -> System.getenv("FIXER_API_KEY");
            case "invalid" -> System.getenv("INVALID_API_KEY");
            case "forbidden" -> System.getenv("FORBIDDEN_API_KEY");
            case "rate_limited" -> System.getenv("RATE_LIMITED_API_KEY");
            default -> throw new IllegalArgumentException("Passed key type is unsupported.");
        };
    }
}
