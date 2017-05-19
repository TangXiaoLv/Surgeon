package com.surgeon.weaving.core.interfaces;

import com.surgeon.weaving.core.SurgeonMethod;

/**
 * Manager the same namespace method of ready to replace.
 */
public interface IMaster {
    SurgeonMethod find(String name);
}
