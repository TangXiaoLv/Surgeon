package com.tangxiaolv.sdk;

import com.surgeon.weaving.annotations.ReplaceAble;

public class StringUtils {

    @ReplaceAble(namespace = "com.tangxiaolv.sdk.StringUtils", function = "getThree")
    public static String getThree() {
        return "THREE";
    }
}
