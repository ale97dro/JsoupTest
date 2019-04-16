package ultimate;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * This are the operations you usually can perform on a generic driver.
 * @author Alessandro Bianchi
 */
public interface Driver
{
    /**
     * Update the driver' source
     */
    void updateSource();

    /**
     * Find the elements accordingly to the specify string
     * @param element element you want to find in source
     * @return List of found elements
     */
    List<WebElement> findElements(String element);
}
