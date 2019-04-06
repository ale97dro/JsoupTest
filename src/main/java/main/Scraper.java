package main;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

public class Scraper
{
    private static String PHANTOM_JS_LIBRARY = "./lib/phantomjs.exe";
    private static String PHANTOM_JS_BIN_PATH = "phantomjs.binary.path";

    private String url;
    private String source;

    public static Scraper createScraper(String url)
    {
        Scraper scraper = new Scraper(url);
        scraper.source = scraper.createPhantom(scraper.url);

        return scraper;
    }

    private Scraper(String url)
    {
        System.setProperty(PHANTOM_JS_BIN_PATH, PHANTOM_JS_LIBRARY); //percorso dove si trova il driver di PhantomJS
    }

    public void scrape(String url, String elementName, ScrapingFunction function)
    {
        Document doc = Jsoup.parse(source); //Eseguo il parsing della sorgente con JSoup

        function.execute(doc);




        //Elements value = doc.getElementsByClass("daily-performance"); //cerco i campi che mi interessano
    }

    private String createPhantom(String url)
    {
        WebDriver ghostDriver = new PhantomJSDriver();
        ghostDriver.get(url); //prendo il sito di cui devo eseguire il codice JavaScript
        return ghostDriver.getPageSource();
    }
}
