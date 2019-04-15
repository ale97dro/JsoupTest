package ultimate;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class UltimateMain {

    public static void main(String[] args)
    {
        Scraper scraper = StoxxScraper.createScraper(PhatomJSDriverWrapper.createDriver(StoxxScraper.URL));

        Map<String, Double> results = scraper.scrape(null);


        String stock = "Electronic & electrical equipment";


        String[] split = stock.split(" ");
        List<String> validString = new ArrayList<>();
        boolean trendContains = false;

        for(String s : split)
        {
            boolean isAlphabetic = true;

            for(int i = 0; i < s.length(); i++)
                if(!Character.isAlphabetic(s.charAt(i)))
                    isAlphabetic = false;

            if(isAlphabetic)
            {
                validString.add(s);
            }
        }


        List<String> temp1 = new ArrayList<>(results.keySet());

        int trendNumber = temp1.size();

        List<String> finalTrend = new ArrayList<>();

        for(int i = 0; i < trendNumber; i++) //per ogni trend
        {
            for(String stockName : validString ) //per ogni stringa valida presente nel nome dello stock cercato
            {
                temp1 = cercaTrend(temp1, stockName);
            }

            finalTrend.addAll(temp1); //aggiungo alla lista finale i trend che "sopravvivono" alla ricerca
            temp1.clear();
        }

        /*
            IDEA PER TROVARE I TREND:
                - splitto il nome del trend in base agli spazi cercando di togliere la punteggiatura (o inserisco un nome creato apposta nel DB)
                - controllo poi che ogni valore derivante dallo split sia contenuto nel nome del megatrend (o in quello reale o in quello fittizio)


         */

        System.out.println("ciao");
    }

    public static List<String> cercaTrend(List<String> candidateTrends, String stockName)
    {
        List<String> foundTrend = new ArrayList<>();

        for(String trend : candidateTrends)
            if(trend.toUpperCase().contains(stockName.toUpperCase()))
                foundTrend.add(trend);

        return foundTrend;
    }
}
