package ultimate;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StoxxScraper implements Scraper<Double>
{
    /**
     * Scraping library's string configuration
     */
    private static String PHANTOM_JS_LIBRARY_PATH = "./lib/phantomjs.exe";
    private static String PHANTOM_JS_LIBRARY_PATH_PROPERTY = "phantomjs.binary.path";

    /**
     * Scraping parameters
     */
    private static int PAGES = 14;
    private static String URL = "https://www.stoxx.com/discovery-search?category=flagship&superType=sector&indexFamily=standard";

    private String url;

    /**
     * Factory method for a new instance of StoxxScraper
     * @return new StoxxScraper instance
     */
    public Scraper createScraper()
    {
        System.setProperty(PHANTOM_JS_LIBRARY_PATH_PROPERTY, PHANTOM_JS_LIBRARY_PATH);

        return new StoxxScraper();
    }

    /**
     * Private constructor
     */
    private StoxxScraper()
    {

    }

    public static String getUrl()
    {
        return URL;
    }


    //PhantomJS driver

    /**
     * Create a new PhantomJS driver.
     * This driver will take the URL source
     * @return PhantomJS driver
     */
    private WebDriver createPhantomDriver()
    {
        WebDriver ghostDriver = new PhantomJSDriver();
        ghostDriver.get(URL);
        return ghostDriver;
    }

    /**
     * After perform some operations, you might need to update your driver's source.
     * With PhantomJS, you can perform this operation via "WindowHandle" system.
     * @param driver Driver you want to update
     * @return Previous handler
     */
    private String updateDriverSource(WebDriver driver)
    {
        String winHandleBefore = driver.getWindowHandle();

        for(String winHandle : driver.getWindowHandles())
            driver.switchTo().window(winHandle);

        return winHandleBefore;
    }


    //REAL SCRAPING OPERATION
    @Override
    public Map<String, Double> scrape(List<String> elementsToFind)
    {
        Map<String, Double> results = new HashMap<>();

        WebDriver driver = createPhantomDriver();

        By by = By.className("pagination_pagenumber_bg");


        return results;
    }



}
