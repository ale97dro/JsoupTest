package main;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.*;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScraperOld
{
    private static String PHANTOM_JS_LIBRARY = "./lib/phantomjs.exe";
    private static String PHANTOM_JS_BIN_PATH = "phantomjs.binary.path";
    private static String CHROME_DRIVER = "./lib/chromedriver.exe";
    private static String CHROME_DRIVER_KEY = "webdriver.chrome.driver";

    private static int PAGES = 14;

    private String url;
    private String source;

    public static ScraperOld createScraper(String url)
    {
        ScraperOld scraper = new ScraperOld(url);


        return scraper;
    }

    private ScraperOld(String url)
    {
        System.setProperty(PHANTOM_JS_BIN_PATH, PHANTOM_JS_LIBRARY); //percorso dove si trova il driver di PhantomJS
        this.url = url;
    }

    public Double stockValue(String stockName) throws Exception
    {

        Document doc = Jsoup.parse(createPhantom(url)); //Eseguo il parsing della sorgente con JSoup; parsing eseguito per ogni pagina
        //todo: ricordarsi che devo scorrermi le pagine
        for(int page = 0; page < PAGES; page++)
        {
            //source = createPhantom(url);


            Elements stocksNameTag = doc.getElementsByClass("box-heading");
            Elements stocksValue = doc.getElementsByClass("daily-performance");
            //Elements pages = doc.getElementsByClass("pagination_pagenumber_bg");


            for (int i = 0; i < stocksNameTag.size(); i++)
            {
                if (false) //todo: trovare un metodo di fare il matching
                {
                    String value = stocksValue.get(i).text();
                    value = value.substring(0, value.length() - 1);
                    return Double.parseDouble(value);
                }
            }

            //chiedo la nuova pagine cliccando sopra il giusto bottone (page+1)

        }

        doc = Jsoup.parse(newPage(url, 0));
        Elements stocksNameTag = doc.getElementsByClass("box-heading");
        System.out.println("ciao");

        throw new Exception();

        //CLICK
        //https://www.dev2qa.com/phantomjs-example/
        //Elements value = doc.getElementsByClass("daily-performance"); //cerco i campi che mi interessano
    }

    public void scrape(String url, List<String> stocksName) throws Exception
    {
        //List<Double> values = new ArrayList<Double>();

        Map<String, Double> values = new HashMap<String, Double>();

        for(String s : stocksName)
            values.put(s, stockValue(s));
    }

    private String createPhantom(String url)
    {
        WebDriver ghostDriver = new PhantomJSDriver();
        ghostDriver.get(url); //prendo il sito di cui devo eseguire il codice JavaScript
        return ghostDriver.getPageSource();
    }

    private String newPage(String url, int page) throws IOException {
        WebDriver ghostDriver = new PhantomJSDriver();
        ghostDriver.get(url); //prendo il sito di cui devo eseguire il codice JavaScript

        By by = By.className("pagination_pagenumber_bg");
        List<WebElement> links = ghostDriver.findElements(by);
        //ghostDriver.findE



        for(WebElement y : links)
            System.out.println(y.getAttribute("onclick"));

        //links.get(0).click();

        By children = By.tagName("a");
        List<WebElement> aTag = links.get(0).findElements(children);

        aTag.get(0).click();

        //Per prendere la pagina dopo
        String winHandleBefore = ghostDriver.getWindowHandle();

        for(String winHandle : ghostDriver.getWindowHandles())
        {
            ghostDriver.switchTo().window(winHandle);
        }

        //((PhantomJSDriver) ghostDriver).executeScript("paginateTile('2')");


        //ghostDriver.

        File src = ((TakesScreenshot)ghostDriver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(src, new File("./test.jpg"), false);

    //        Actions x = new Actions(ghostDriver);
    //        x.moveToElement(aTag.get(0));
    //        x.click();
    //        x.perform();

//        JavascriptExecutor js = (JavascriptExecutor)ghostDriver;
//        js.executeScript("paginateTile('2','tile')", aTag.get(0));


       // By resultListByXPath = By.xpath("//ol[@class=\"mb-15 reg searchCenterMiddle\"]/li");
//        By newStock = By.className("box-heading");
//        List<WebElement> resultElementList = ghostDriver.findElements(newStock);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String source = ghostDriver.getPageSource();
        Document doc = Jsoup.parse(source);
        Elements stocksNameTag = doc.getElementsByClass("box-heading");

        for(Element w : stocksNameTag)
            System.out.println(w.text());

//        for(WebElement y : resultElementList)
//            System.out.println(y.getText());

        return ghostDriver.getPageSource();
    }
}
