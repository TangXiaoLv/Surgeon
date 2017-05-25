package com.tangxiaolv.sdk;

import com.surgeon.weaving.annotations.ReplaceAble;

public class StringUtils {

    @ReplaceAble
    public static String getThree() {
        return "THREE";
    }
}
