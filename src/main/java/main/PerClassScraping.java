package main;

import org.jsoup.nodes.Document;

public class PerClassScraping implements ScrapingFunction
{
    private String className;

    public PerClassScraping(String className)
    {
        this.className = className;
    }

    public void execute(Document doc)
    {

    }
}
