package ultimate;

import org.junit.Test;
import static org.junit.Assert.*;

public class PhantomJSDriverWrapperTest {

    private String testUrl = "https://www.stoxx.com/discovery-search?category=flagship&superType=sector&indexFamily=standard";

    @Test
    public void creationTest()
    {
        PhantomJSDriverWrapper driver = PhantomJSDriverWrapper.createDriver(testUrl);

        String libraryPath = "./lib/phantomjs.exe";
        assertEquals(libraryPath, PhantomJSDriverWrapper.getPhantomJSLibraryPath());

        String newPath = "./testPath";
        PhantomJSDriverWrapper.setPhantomJsLibraryPath(newPath);
        assertEquals(newPath, PhantomJSDriverWrapper.getPhantomJSLibraryPath());
    }

    @Test
    public void driverEqualityTest()
    {
        Driver genericDriver1 = PhantomJSDriverWrapper.createDriver(testUrl);
        Driver genericDriver2 = PhantomJSDriverWrapper.createDriver(testUrl);

        assertNotEquals(genericDriver1, genericDriver2);

        assertTrue(genericDriver1 instanceof PhantomJSDriverWrapper);
        assertTrue(genericDriver2 instanceof PhantomJSDriverWrapper);
    }
}
