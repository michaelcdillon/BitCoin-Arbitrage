package org.bca.arbitrage;

import org.bca.config.Configuration;
import org.bca.exchange.Exchange;

import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.HashMap;

public class Arbitrage {

    private static Logger log = Logger.getLogger (Arbitrage.class.getName ());
    
    private ArrayList<Exchange> exchanges;

    private MWManager           market_watcher_manager = null;
    
    public Arbitrage (ArrayList<Exchange> exchanges, HashMap<String, String> settings) {
        this.exchanges = exchanges;
        
        setSettings (settings); 
        
        market_watcher_manager = new MWManager (exchanges, settings);

        run ();
    }

    private void run () {
        while (true) {

        }
    }

    private void setSettings (HashMap<String, String> settings) {
    /*    String ticker_hit_rate_str = settings.get (Configuration.TICKER_HIT_RATE_KEY);
        
        if (ticker_hit_rate_str != null) {
            try {
              this.ticker_hit_rate = Integer.parseInt (ticker_hit_rate_str); 
            }
            catch (Exception e) {
              log.severe ("Couldn't parse the ticker hit rate setting");
            }
        }
     */
    }
}
