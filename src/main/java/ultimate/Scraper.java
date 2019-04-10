package ultimate;

import java.util.List;
import java.util.Map;

/**
 *
 * @author Alessandro Bianchi
 * @param <T>
 */
public interface Scraper<T>
{
    Map<String, T> scrape(List<String> elementsToFind);
}
