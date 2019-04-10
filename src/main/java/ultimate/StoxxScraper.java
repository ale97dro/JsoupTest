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
public class StoxxScraper implements Scraper<Double>
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
    public Map<String, Double> scrape(List<String> elementsToFind)
    {
        Map<String, Double> results = new HashMap<>();

        // 1) prendo i nomi e il loro valore
        // 2) prendo gli indici delle pagine
        // 3) clicco sul link giusto e aggiorno
        // 4) ripeto il procedimento


        for(int i = 0; i < PAGES; i++)
        {
            //prendo nomi e valori
            List<WebElement> stocksName = driver.findElements("box-heading");
            List<WebElement> stocksValue = driver.findElements("daily-performance");

            //inserisco nomi e valori nella mappa
            for(int c = 0; c < stocksName.size(); c++)
            {
                String stockValue = stocksValue.get(c).getText();
                stockValue = stockValue.substring(0, stockValue.length() - 1);
                results.put(stocksName.get(c).getText(), Double.parseDouble(stockValue));
            }


            List<WebElement> pageButtons = driver.findElements("pagination_pagenumber_bg");
            List<WebElement> linkButtons = getPageElements(pageButtons.get(computePageIndex(i)), By.tagName("a"));

            linkButtons.get(0).click(); //always return just one element (the link itself)

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
        //TODO: implements
        return 0;
    }
}
