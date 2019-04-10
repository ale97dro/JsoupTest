package ultimate;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
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
    public static Scraper createScraper()
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

        // 1) prendo i nomi e il loro valore
        // 2) prendo gli indici delle pagine
        // 3) clicco sul link giusto e aggiorno
        // 4) ripeto il procedimento


        for(int i = 0; i < PAGES; i++)
        {
            //prendo nomi e valori
            List<WebElement> stocksName = getPageElements(driver, By.className("box-heading"));
            List<WebElement> stocksValue = getPageElements(driver, By.className("daily-performance"));


            //inserisco nomi e valori nella mappa
            for(int c = 0; c < stocksName.size(); c++)
            {
                String stockValue = stocksValue.get(c).getText();
                stockValue = stockValue.substring(0, stockValue.length() - 1);
                results.put(stocksName.get(c).getText(), Double.parseDouble(stockValue));
            }


            List<WebElement> pageButtons = getPageElements(driver, By.className("pagination_pagenumber_bg")); //qui ho 10 bottoni
            List<WebElement> linkButtons = getPageElements(pageButtons.get(computePageIndex(i)), By.tagName("a"));

            linkButtons.get(0).click(); //always return just one element (the link itself)

            updateDriverSource(driver);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }


        return results;
    }


    private List<WebElement> getPageElements(WebDriver driver, By by)
    {
        return driver.findElements(by);
    }

    private List<WebElement> getPageElements(WebElement element, By by)
    {
        return element.findElements(by);
    }

    private int computePageIndex(int base)
    {
        //TODO: implements
        return 0;
    }
}
