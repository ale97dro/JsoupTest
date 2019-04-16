package ultimate;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Execute the scraping of stoxx.com website.
 *
 * @author Alessandro Bianchi
 */
public class StoxxScraper implements Scraper
{
    /**
     * Scraping parameters
     */
    private static final int PAGES = 14;
    public static final String URL = "https://www.stoxx.com/discovery-search?category=flagship&superType=sector&indexFamily=standard";

    //private String url;
    private Driver driver;

    /**
     * Factory method for a new instance of StoxxScraper
     * @param driver THe driver you want to use for scrape the website
     * @return new StoxxScraper instance
     */
    public static Scraper createScraper(Driver driver)
    {
        return new StoxxScraper(driver);
    }

    /**
     * Private constructor
     */
    private StoxxScraper(Driver driver)
    {
        this.driver = driver;
    }

    @Override
    public Map<String, Double> scrape()
    {
        Map<String, Double> results = new HashMap<>();

        // 1) takes names and their values
        // 2) takes index of pages
        // 3) clikc on the right button and update
        // 4) repeat for all pages

        for(int i = 0; i < PAGES; i++)
        {
            //take names and values
            List<WebElement> stocksName = driver.findElements("box-heading");
            List<WebElement> stocksValue = driver.findElements("daily-performance");

            //insert names and values into the map
            for(int c = 0; c < stocksName.size(); c++)
            {
                try
                {
                    String stockValue = stocksValue.get(c).getText();
                    stockValue = stockValue.substring(0, stockValue.length() - 1);
                    results.put(stocksName.get(c).getText(), Double.parseDouble(stockValue));
                }
                catch (Exception ex)
                {
                    System.out.println("Stock value not available");
                }

            }
            
            List<WebElement> pageButtons = driver.findElements("pagination_pagenumber_bg");
            try {
                List<WebElement> linkButtons = getPageElements(pageButtons.get(computePageIndex(i)), By.tagName("a"));
                linkButtons.get(0).click(); //always return just one element (the link itself)
            }
            catch (Exception ex)
            {
                //this is the last page: no more page available!
            }

            driver.updateSource();

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        return results;
    }

    /**
     * Find child element of a WebElement
     * @param element Parent element
     * @param by Element you need to find in the parent
     * @return List of WebElement corresponding to tbe by parameter
     */
    private List<WebElement> getPageElements(WebElement element, By by)
    {
        return element.findElements(by);
    }

    private int computePageIndex(int base)
    {
        if(base < 4)
            return base;

        if(base <= 8)
            return 5;

        return base - 3;
    }
}
