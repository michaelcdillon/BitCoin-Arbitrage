package org.bca.arbitrage;

import org.bca.exchange.Exchange;
import org.bca.util.HttpsRequest;

import org.json.JSONObject;

import java.util.logging.Logger;

public class MarketWatcher extends Thread {

    private static Logger log = Logger.getLogger (MarketWatcher.class.getName ());

    private Exchange exchange;

    private long      update_rate;

    public MarketWatcher (Exchange exchange, long update_rate) {
        this.exchange = exchange;
        this.update_rate = update_rate;
    }

    public void run () {
        while (true) {
            updateTrackerExchangeData ();
            try {
                sleep (this.update_rate);
            }
            catch (InterruptedException e) {
                log.fine ("This marketwatcher thread was interrupted while sleeping"); 
            }
        }
    }

    private void updateTrackerExchangeData () {
        String tracker_data = HttpsRequest.get (exchange.getTickerUrl ()); 
        JSONObject tracker_json = null;

        log.fine ("Tracker data Json: " + tracker_data);        

        try {
            tracker_json = new JSONObject (tracker_data);
            tracker_json = tracker_json.getJSONObject ("ticker");
        }
        catch (Exception e) {
            log.warning ("Couldn't update the tracker info for the exchange");
            return;
        }
        
        try {
            if (tracker_json.has ("sell"))
                this.exchange.setSell (Float.parseFloat (tracker_json.getString ("sell")));
        }
        catch (Exception e) {
            log.warning ("An error occured while parsing tracker data : sell");
        } 

        try {
            if (tracker_json.has ("buy"))
                this.exchange.setBuy (Float.parseFloat (tracker_json.getString ("buy")));
        }
        catch (Exception e) {
            log.warning ("An error occured while parsing tracker data : buy");
        }

        log.info (exchange.toString ());
    }
}
