package main;

import org.jsoup.nodes.Document;

public interface ScrapingFunction
{
    void execute(Document doc);
}
