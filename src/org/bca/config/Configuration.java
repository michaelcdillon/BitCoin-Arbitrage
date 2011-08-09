package org.bca.config;

import org.bca.exchange.Exchange;

import java.util.logging.Logger;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.HashMap;

import org.apache.commons.configuration.XMLConfiguration;
import org.apache.commons.configuration.HierarchicalConfiguration;
import org.apache.commons.configuration.ConfigurationException;


public class Configuration {

    private static final Logger log = Logger.getLogger (Configuration.class.getName ());
    
    private static final String CONFIG_FILE = "config/bca-config.xml";

    private static final String EXCHANGE_KEY = "exchanges.exchange";
    private static final String NAME_KEY     = "name";
    private static final String TICKER_KEY   = "ticker-url";

    public  static final String TICKER_HIT_RATE_KEY = "settings.tracker-hit-rate";
        
    private XMLConfiguration config;
         
    public Configuration () throws Exception{
        try {
            config = new XMLConfiguration (CONFIG_FILE); 
        }
        catch (ConfigurationException e) {
            log.severe ("An error occured loading the configuration file.");
            log.severe (e.toString ());
            throw new Exception ("Configuration Exception");
        }
    }

    public ArrayList<Exchange> getExchanges () {
        ArrayList<Exchange>       exchanges     = new ArrayList<Exchange> (); 
        List                      exchanges_xml = config.configurationsAt (EXCHANGE_KEY);
        Exchange                  new_exchange  = null;
        HierarchicalConfiguration exchange_xml  = null;
        String                    name          = null;
        String                    ticker_url    = null;

        for (Object configuration : exchanges_xml) {
            exchange_xml = (HierarchicalConfiguration) configuration;
            name = exchange_xml.getString (NAME_KEY);
            ticker_url = exchange_xml.getString (TICKER_KEY);
            new_exchange = new Exchange (name, ticker_url);    
            exchanges.add (new_exchange);
        }
        

        return exchanges;
    }

    public HashMap<String, String> getSettings () {
        HashMap<String, String> settings = new HashMap<String, String> ();
        
        String ticker_hit_rate = config.getString (TICKER_HIT_RATE_KEY); 
        if (ticker_hit_rate != null)
            settings.put (TICKER_HIT_RATE_KEY, ticker_hit_rate);
        
        return settings;
    }
}
