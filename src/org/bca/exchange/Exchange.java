package org.bca.exchange;

import java.util.logging.Logger;

public class Exchange {
    
    private String name;
    private String ticker_url;

    private float         sell;
    private float         buy;

    public Exchange (String name, String ticker_url) {
        this.name = name;
        this.ticker_url = ticker_url;
    }

    public float getSell () {
        return this.sell;
    }

    public float getBuy () {
        return this.buy;
    }

    public void setSell (float sell) {
        this.sell = sell;
    }

    public void setBuy (float buy) {
        this.buy = buy;
    }

    public String getTickerUrl () {
        return this.ticker_url;
    }

    public String toString () {
        return "Exchange: " + this.name + " Ticker: " + this.ticker_url + " Buy: " + this.buy + " Sell: " + this.sell;
    }
}
