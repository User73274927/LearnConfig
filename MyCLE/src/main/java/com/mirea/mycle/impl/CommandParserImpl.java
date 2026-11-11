package com.mirea.mycle.impl;

import com.mirea.mycle.api.CommandParser;

import java.util.regex.Pattern;

public class CommandParserImpl extends CommandParser {
    private static final Pattern utilityNameCheckPattern = Pattern.compile("[a-z][a-z0-9]*");
    private static final Pattern flagCheckPattern = Pattern.compile("(-[a-z])|(--[a-z]+)");

    private String[] tokens;
    private int flagCount, paramCount;

    private String utilityName;
    private String[] flags, params;

    @Override
    public boolean check(String command) {
        tokens = command.split(" ");
        flagCount = paramCount = 0;
        if (!utilityNameCheckPattern.matcher(tokens[0]).matches()) return false;

        int curr = 1;
        while (curr < tokens.length && tokens[curr].startsWith("-")) {
            if (!flagCheckPattern.matcher(tokens[curr]).matches()) return false;
            flagCount++;
            curr++;
        }
        while (curr < tokens.length) {
            if (tokens[curr].startsWith("-")) return false;
            paramCount++;
            curr++;
        }
        return true;
    }

    @Override
    public void parse(String command) {
        flags = new String[flagCount];
        params = new String[paramCount];
        utilityName = tokens[0];

        for (int i = 0; i < flagCount; i++) {
            flags[i] = tokens[i+1].substring(tokens[i+1].lastIndexOf('-')+1);
        }
        for (int i = 0; i < paramCount; i++) {
            params[i] = tokens[i+flagCount+1];
        }
    }

    @Override
    public String getUtilityName() {
        return utilityName;
    }

    @Override
    public String[] getFlags() {
        return flags;
    }

    @Override
    public String[] getParams() {
        return params;
    }
}
