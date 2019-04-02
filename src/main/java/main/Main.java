package main;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Main {

    public static void main(String[] args)
    {
        System.out.print("ciao");


        try
        {
            String url = "";
            Document doc = Jsoup.connect(url).get();

            //Elements elements = doc.select("div"); //Get all div elements

            Elements elements = doc.getElementsByClass("entry-content"); //Get all element with the specify class

            System.out.printf("ciao");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
