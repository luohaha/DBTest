package com.luohaha.tools;

import com.github.luohaha.luoORM.dbExecuter.DBExecuter;
import com.github.luohaha.luoORM.dbPool.DBPool;
import com.github.luohaha.luoORM.dbPool.FixedDBPool;

import java.io.IOException;
import java.util.concurrent.*;
import java.util.logging.Logger;

public class OBTest {
    private OBTestProperties obTestProperties;
    private DBPool pool;
    private Logger logger = Logger.getLogger("OBTest");
    private ScheduledExecutorService executorService;

    public OBTest(String file) throws IOException, ClassNotFoundException, OBTestPropertiesNotInitException {
        this.obTestProperties = new OBTestProperties(file);
        this.pool = new FixedDBPool(this.obTestProperties.getDriver(),
                                    this.obTestProperties.getUrl(),
                                    this.obTestProperties.getUser(),
                                    this.obTestProperties.getPassword());
        this.executorService = Executors.newScheduledThreadPool(1);
    }

    public void run() {
        try {
            this.executorService.scheduleAtFixedRate(() -> {
                        try {
                            DBExecuter.use(this.pool)
                                      .execute(this.obTestProperties.getSql());
                        } catch (OBTestPropertiesNotInitException e) {
                            logger.warning("DBExecuter execute fail" + e.toString());
                        }
                    },
                    this.obTestProperties.getSqlIntervalTimeMs(),
                    this.obTestProperties.getSqlIntervalTimeMs(),
                    TimeUnit.MILLISECONDS);
        } catch (OBTestPropertiesNotInitException e) {
            logger.warning("run fail" + e.toString());
        }
    }
}
