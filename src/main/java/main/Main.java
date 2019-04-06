package main;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Main {

    public static void main(String[] args)
    {
        System.out.print("ciao");

        String url = "https://www.stoxx.com/discovery-search?category=flagship&superType=sector&indexFamily=standard";
        // Document doc = Jsoup.connect(url).get();
        //Document doc = Jsoup.connect(url).get();

        //Elements elements = doc.select("div"); //Get all div elements
        //Elements elements = doc.getElementsByClass("entry-content"); //Get all element with the specify class

        //Elements value = doc.getElementsByClass("daily-performance");
        // Elements value = doc.select("div#default-search-result");


        ghost(url);
    }

    public static void ghost(String urlString)
    {
        try
        {
            //Path format: C:\\directory\\phantomjs.exe
            String phantomJsDriver = "./lib/phantomjs.exe";
            System.setProperty("phantomjs.binary.path", phantomJsDriver); //percorso dove si trova il driver di PhantomJS
            WebDriver ghostDriver = new PhantomJSDriver();
            ghostDriver.get(urlString); //prendo il sito di cui devo eseguire il codice JavaScript
            Document doc = Jsoup.parse(ghostDriver.getPageSource()); //Eseguo il parsing della sorgente con JSoup

            Elements value = doc.getElementsByClass("daily-performance"); //cerco i campi che mi interessano
            Elements value2 = doc.getElementsByClass("box-heading");
            //box-heading classe del contenitore del titolo
           // System.out.printf("ciao");

            for(Element e : value)
                System.out.println(value.text());
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public static void httpRequest(String urlString) throws IOException
    {
        URL url = new URL(urlString);
        HttpURLConnection con = (HttpURLConnection) url.openConnection(); //creo la connessione
        con.setRequestMethod("GET"); //setto il metodo con cui viene effettuata la richiesta
        con.setRequestProperty("Content-Type", "application/json");

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();

        while((inputLine = in.readLine()) != null)
            content.append(inputLine);
        in.close();

        System.out.printf("ciao");
    }
}
