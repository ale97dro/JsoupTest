package main;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

import java.util.List;

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

    public void scrape(String url, String stocksName, ScrapingFunction function)
    {
        Document doc = Jsoup.parse(source); //Eseguo il parsing della sorgente con JSoup

        Elements stocksNameTag = doc.getElementsByClass("box-heading");
        Elements stocksValue = doc.getElementsByClass("daily-performance");

        for(Element e : stocksNameTag)
        {
            //if(e.text().contains(stocksName))
        }
        //function.execute(doc);

        //CLICK
        //https://www.dev2qa.com/phantomjs-example/



        //Elements value = doc.getElementsByClass("daily-performance"); //cerco i campi che mi interessano
    }

    public void scrape(String url, List<String> stocksName)
    {

    }

    private String createPhantom(String url)
    {
        WebDriver ghostDriver = new PhantomJSDriver();
        ghostDriver.get(url); //prendo il sito di cui devo eseguire il codice JavaScript
        return ghostDriver.getPageSource();
    }
}
