package ultimate;

import java.util.Map;

public class UltimateMain {

    public static void main(String[] args)
    {
        Scraper scraper = StoxxScraper.createScraper();

        Map results = scraper.scrape(null);

        System.out.println("ciao");
    }
}
