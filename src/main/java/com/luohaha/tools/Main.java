package com.luohaha.tools;

import java.io.IOException;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) {
        Logger logger = Logger.getLogger("OBTest");
        try {
            OBTest obTest = new OBTest("");
            obTest.run();
        } catch (IOException e) {
            logger.severe("Main : " + e.toString());
        } catch (ClassNotFoundException e) {
            logger.severe("Main : " + e.toString());
        } catch (OBTestPropertiesNotInitException e) {
            logger.severe("Main : " + e.toString());
        }
    }
}
