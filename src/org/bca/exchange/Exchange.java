package org.bca.exchange;

import java.util.logging.Logger;

public class Exchange {
    
    private static String name;
    private static String ticker_url;

    public Exchange (String name, String ticker_url) {
        this.name = name;
        this.ticker_url = ticker_url;
    }

    public String toString () {
        return "Exchange: " + this.name + " Ticker: " + this.ticker_url;
    }
}
