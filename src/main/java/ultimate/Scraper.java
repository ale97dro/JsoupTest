package ultimate;

import java.util.List;
import java.util.Map;

/**
 *
 * @author Alessandro Bianchi
 */
public interface Scraper
{
    Map<String, Double> scrape(List<String> elementsToFind);
}
