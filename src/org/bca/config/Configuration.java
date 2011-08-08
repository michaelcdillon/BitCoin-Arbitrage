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

    public  static final String TRACKER_HIT_RATE_KEY = "settings.tracker-hit-rate";
        
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
        HierarchicalConfiguration exchange_xml  = null;
        String                    name          = null;
        String                    ticker_url    = null;

        for (Iterator it = exchanges_xml.iterator (); it.hasNext ();) {
            exchange_xml = (HierarchicalConfiguration) it.next();
            
            name = exchange_xml.getString (NAME_KEY);
            ticker_url = exchange_xml.getString (TICKER_KEY);
            
            exchanges.add (new Exchange (name, ticker_url));
        }

        return exchanges;
    }

    public HashMap<String, String> getSettings () {
        HashMap<String, String> settings = new HashMap<String, String> ();
        
        String tracker_hit_rate = config.getString (TRACKER_HIT_RATE_KEY); 
        if (tracker_hit_rate != null)
            settings.put (TRACKER_HIT_RATE_KEY, tracker_hit_rate);
        
        return settings;
    }
}
