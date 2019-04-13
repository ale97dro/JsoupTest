package ultimate;

import java.util.Map;

public class UltimateMain {

    public static void main(String[] args)
    {
        Scraper scraper = StoxxScraper.createScraper(PhatomJSDriverWrapper.createDriver(StoxxScraper.URL));

        Map results = scraper.scrape(null);


        /*
            IDEA PER TROVARE I TREND:
                - splitto il nome del trend in base agli spazi cercando di togliere la punteggiatura (o inserisco un nome creato apposta nel DB)
                - controllo poi che ogni valore derivante dallo split sia contenuto nel nome del megatrend (o in quello reale o in quello fittizio)


         */

        System.out.println("ciao");
    }
}
