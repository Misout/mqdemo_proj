package com.misout.common.util;

/**
 * @author Misout
 * @date 2019-06-20 20:53:50
 */
public class StringUtil {

    public boolean isEmpty(String msg) {
        if(msg == null || "".equals(msg)) {
            return true;
        }

        return false;
    }
}
