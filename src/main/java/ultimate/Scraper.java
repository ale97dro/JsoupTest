package ultimate;

import java.util.List;
import java.util.Map;

public interface Scraper<T>
{
    Map<String, T> scrape(List<String> elementsToFind);
}
