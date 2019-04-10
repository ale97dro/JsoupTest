package ultimate;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 *
 * @author Alessandro Bianchi
 */
public interface Driver
{
    void updateSource();
    List<WebElement> findElements(String element);
}
