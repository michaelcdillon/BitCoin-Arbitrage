package org.bca.arbitrage;

import org.bca.config.Configuration;
import org.bca.exchange.Exchange;

import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.HashMap;

public class Arbitrage {

    private static Logger log = Logger.getLogger (Arbitrage.class.getName ());
    
    private ArrayList<Exchange> exchanges;
    
    private int                 tracker_hit_rate;
    
    public Arbitrage (ArrayList<Exchange> exchanges, HashMap<String, String> settings) {
        this.exchanges = exchanges;
        
        setSettings (settings); 

        run ();
    }

    private void run () {
        for (Exchange exchange : this.exchanges) {
            MarketWatcher mw = new MarketWatcher (exchange);
            mw.run ();    
            log.info (exchange.toString ());
        } 

    }

    private void setSettings (HashMap<String, String> settings) {
        String tracker_hit_rate_str = settings.get (Configuration.TRACKER_HIT_RATE_KEY);
        
        if (tracker_hit_rate_str != null) {
            try {
              this.tracker_hit_rate = Integer.parseInt (tracker_hit_rate_str); 
            }
            catch (Exception e) {
              log.severe ("Couldn't parse the tracker hit rate setting");
            }
        }
    }
}
