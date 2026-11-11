package com.mirea.mycle.impl;

import com.mirea.mycle.api.CLEEngine;
import com.mirea.mycle.api.CLEUtility;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class CLEEngineImpl extends CLEEngine {
    private Map<String, CLEUtility> utilityMap = new HashMap<>();

    CLEEngineImpl() {

    }

    @Override
    public void loadUtility(String className) {
        try {
            Class<?> c = Class.forName(className);
            Object instance = c.getConstructor(CLEEngine.class).newInstance(this);
            if (instance instanceof CLEUtility utility) {
                utilityMap.put(utility.getName(), utility);
            }
            else {
                throw new RuntimeException("instance is not inheritor of CLEUtility.");
            }
        } catch (ClassNotFoundException | InvocationTargetException | NoSuchMethodException | InstantiationException |
                 IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateUtilities() {

    }

    @Override
    public boolean check(String utilityName, String[] flags, String[] params) {
        if (!utilityMap.containsKey(utilityName)) return false;
        CLEUtility utility = utilityMap.get(utilityName);

        for (String flag : flags) {
            if (!containsFlag(utility, flag)) {
                return false;
            }
        }
        return true;
    }

    private boolean containsFlag(CLEUtility utility, String target) {
        return Arrays.asList(utility.getSupportedFlags()).contains(target) ||
                Arrays.asList(CLEUtility.DEFAULT_FLAGS).contains(target);
    }

    @Override
    public String run(String utilityName, String[] flags, String[] params) {
        return utilityMap.get(utilityName).execute(flags, params);
    }

    @Override
    public CLEUtility getUtilityByName(String utilityName) {
        return utilityMap.get(utilityName);
    }

    @Override
    public Collection<CLEUtility> getActiveUtilities() {
        return utilityMap.values();
    }
}
