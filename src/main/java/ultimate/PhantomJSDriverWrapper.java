package ultimate;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

import java.util.List;

/**
 * Wrapper for PhantomJSDriver.
 * This class set by default the right properties to use the driver
 *
 * @author Alessandro Bianchi
 */
public class PhantomJSDriverWrapper implements Driver
{
    /**
     * Scraping library's string configuration
    */
    private static String PHANTOM_JS_LIBRARY_PATH = "./lib/phantomjs.exe";
    private static final String PHANTOM_JS_LIBRARY_PATH_PROPERTY = "phantomjs.binary.path";

    private WebDriver driver;

    /**
     * Create a new PhantomJS driver.
     * This driver will take the URL source
     *
     * @param url Site you want to scrape
     * @return PhantomJS driver
     */
    public static PhantomJSDriverWrapper createDriver(String url)
    {
        return new PhantomJSDriverWrapper(url);
    }

    /**
     * Private constructor
     * @param url Url of webpage you want to load into the driver
     */
    private PhantomJSDriverWrapper(String url)
    {
        System.setProperty(PHANTOM_JS_LIBRARY_PATH_PROPERTY, PHANTOM_JS_LIBRARY_PATH);

        driver = new PhantomJSDriver();
        driver.get(url);
    }

    /**
     * Get the library path.
     * By default, it returns './lib/phantomjs.exe'
     * @return Library path
     */
    public static String getPhantomJSLibraryPath()
    {
        return PHANTOM_JS_LIBRARY_PATH;
    }

    /**
     * Set a new library path
     * @param newPath
     */
    public static void setPhantomJsLibraryPath(String newPath)
    {
        PHANTOM_JS_LIBRARY_PATH = newPath;
    }

    /**
     * After perform some operations, you might need to update your driver's source.
     * With PhantomJS, you can perform this operation via "WindowHandle" system.
     */
    @Override
    public void updateSource()
    {
        for(String winHandle : driver.getWindowHandles())
            driver.switchTo().window(winHandle);

    }

    @Override
    public List<WebElement> findElements(String element)
    {
        return driver.findElements(By.className(element));
    }
}
