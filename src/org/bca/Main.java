package org.bca;

import org.bca.config.Configuration;
import org.bca.exchange.Exchange;
import org.bca.arbitrage.Arbitrage;;

import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    private static Logger log = Logger.getLogger (Main.class.getName ());

    private Configuration config;

    public static void main (String[] args) {
        Configuration config = null;

        try {
            config = new Configuration ();
        }
        catch (Exception e) {
            log.severe ("Couldn't start BitCoin-Arbitrage, a configuration file issue needs to be resolved");
            System.exit (1);
        }
        
        ArrayList<Exchange> exchanges = config.getExchanges ();
        HashMap<String, String> settings = config.getSettings ();

        Arbitrage arbitrage = new Arbitrage (exchanges, settings);
    }
}
