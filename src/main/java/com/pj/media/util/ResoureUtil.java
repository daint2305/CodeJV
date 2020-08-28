/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pj.media.util;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.apache.log4j.Logger;

/**
 *
 * @author Thanh_Long
 */
public class ResoureUtil {

    private Logger logger = Logger.getLogger(this.getClass());
    private Properties prop = null;

    private static ResoureUtil instance = null;

    public static synchronized ResoureUtil getInstance() {
        if (instance == null) {
            instance = new ResoureUtil();
        }
        return instance;
    }

    public ResoureUtil() {

        prop = new Properties();
        InputStream input = null;
        try {
            input = this.getClass().getResourceAsStream("/config.properties");
            prop.load(input);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e2) {
                    logger.error(e2.getMessage(), e2);
                }
            }
        }

    }

    public String getProperty(String propertyName) {
        if (prop == null) {
            return null;
        }
        return prop.getProperty(propertyName);
    }

}
