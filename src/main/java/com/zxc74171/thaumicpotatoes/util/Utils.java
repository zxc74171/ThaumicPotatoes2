package com.zxc74171.thaumicpotatoes.util;

import com.zxc74171.thaumicpotatoes.Reference;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Utils {

    private static Logger logger;

    public static Logger getLogger() {
        if(logger == null) {
            logger= LogManager.getFormatterLogger(Reference.MODID);
        }
        return logger;
    }
}
