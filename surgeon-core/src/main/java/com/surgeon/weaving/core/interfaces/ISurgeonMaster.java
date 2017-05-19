package com.surgeon.weaving.core.interfaces;

import com.surgeon.weaving.masters.SurgeonMethod;

/**
 * Manager the same domain method of ready to replace.
 */
public interface ISurgeonMaster {
    SurgeonMethod find(String name);
}
