package ultimate;

import org.junit.Test;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.mock;

public class StoxxScraperTest {

    @Test
    public void creationTest()
    {
        Driver driver = mock(Driver.class);

        Scraper scraper1 = StoxxScraper.createScraper(driver);
        Scraper scraper2 = StoxxScraper.createScraper(driver);


        assertNotNull(scraper1);
        assertNotNull(scraper2);

        assertNotEquals(scraper1, scraper2);
    }

    @Test
    public void urlScrapingTest()
    {
        String url = "https://www.stoxx.com/discovery-search?category=flagship&superType=sector&indexFamily=standard";

        assertEquals(url, StoxxScraper.URL);
    }
}
