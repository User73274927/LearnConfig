package com.mirea.mycle.impl;

import com.google.gson.JsonArray;
import com.mirea.mycle.api.CLEEngine;

public class CLEEngineFactory {
    private static MyCLEConfig myCLEConfig = MyCLEConfig.Instance.get();

    public static CLEEngine createInstance() {
        CLEEngine cleEngine = new CLEEngineImpl();
        JsonArray utilityList = myCLEConfig.getJsonElement("utilities", JsonArray.class);
        for (int i = 0; i < utilityList.size(); i++) {
            String utilityClassName = utilityList.get(i).getAsString();
            cleEngine.loadUtility(utilityClassName);
        }
        return cleEngine;
    }
}
