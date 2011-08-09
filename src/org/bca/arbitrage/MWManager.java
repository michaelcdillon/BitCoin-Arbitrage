package org.bca.arbitrage;

import org.bca.config.Configuration;
import org.bca.exchange.Exchange;

import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.*;

public class MWManager {
    
    private ThreadPoolExecutor threadPool = null;
    private int                pool_size = 2;
    private int                max_pool_size = 2;
    private int                keep_alive_time = 10;
    private long               mw_update_rate = 1000;

    final ArrayBlockingQueue<Runnable> queue = new ArrayBlockingQueue<Runnable> (10);

    public MWManager (ArrayList<Exchange> exchanges, HashMap<String, String> settings) {
        this.pool_size = exchanges.size ();
        this.max_pool_size = this.pool_size;
        this.mw_update_rate = Long.parseLong (settings.get (Configuration.TICKER_HIT_RATE_KEY));

        this.threadPool = new ThreadPoolExecutor (this.pool_size, this.max_pool_size,
                keep_alive_time, TimeUnit.SECONDS, queue);

        for (Exchange exchange : exchanges) {
            this.threadPool.execute (new MarketWatcher (exchange, mw_update_rate));
        }
    }
}
