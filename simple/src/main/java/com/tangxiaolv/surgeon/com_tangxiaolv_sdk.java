package com.tangxiaolv.surgeon;

import com.surgeon.weaving.core.interfaces.ISurgeonMaster;
import com.surgeon.weaving.masters.SurgeonMethod;

import java.util.HashMap;

public class com_tangxiaolv_sdk implements ISurgeonMaster {

    private final HashMap mapping;

    public com_tangxiaolv_sdk() throws NoSuchMethodException {
        this.mapping = new HashMap();
        mapping.put("getA.extre", new SurgeonMethod(
                NewStringFactort.class,
                NewStringFactort.class.getMethod("getA",Object.class),
                "",
                ""
                ));

    }

    public SurgeonMethod find(String name) {
        return (SurgeonMethod) mapping.get(name);
    }
}
