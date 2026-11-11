package com.mirea.mycle.builtin;

import com.google.gson.JsonObject;
import com.mirea.mycle.api.CLEEngine;
import com.mirea.mycle.api.CLEUtility;
import com.mirea.mycle.impl.MyCLEConfig;

public class Set extends CLEUtility {
    private final String[] SET_FLAGS = {"value", "list", "add"};
    private MyCLEConfig myCleConfig;

    public Set(CLEEngine cleEngine) {
        super(cleEngine);
        this.myCleConfig = MyCLEConfig.Instance.get();
    }

    @Override
    public String execute(String[] flags, String[] params) {
        if (flags.length > 0) {
            if (flags[0].equals("v") || flags[0].equals("version")) return version();
            if (flags[0].equals("h") || flags[0].equals("help")) return help();
        }
        if (flags[0].equals("value")) {
            if (params.length >= 2) {
                String path = params[0].substring(0, params[0].lastIndexOf('>'));
                String property = params[0].substring(params[0].lastIndexOf('>')+1);
                JsonObject jsonObject = (path.isEmpty()) ? myCleConfig.getRoot() : myCleConfig.getJsonElement(path);
                jsonObject.addProperty(property, params[1]);
                myCleConfig.serialize();
            }
        }
        if (flags[0].equals("list")) {
            if (flags[1].equals("add")) {
                String path = params[0].substring(0, params[0].lastIndexOf('>'));
                String property = params[0].substring(params[0].lastIndexOf('>')+1);
                JsonObject jsonObject = (path.isEmpty()) ? myCleConfig.getRoot() : myCleConfig.getJsonElement(path);
                jsonObject.getAsJsonArray(property).add(params[1]);
            }
        }
        return "";
    }

    @Override
    public String[] getSupportedFlags() {
        return SET_FLAGS;
    }

    @Override
    public String getName() {
        return "set";
    }

    @Override
    protected String version() {
        return "cd: version: 0.1 (alpha)";
    }

    @Override
    protected String help() {
        return "set - изменяет параметры конфигурационного файла MyCLE.\n" +
                "Примеры:\n" +
                "1) \"VFS > set vfs-path path/to/json\" - изменить параметр vfs-path на path/to/json.\n" +
                "Флаги:\n" +
                "\"-v\" или \"--version\" - показать версию утилиты.\n" +
                "\"-h\" или \"--help\" - показать справочную информацию об утилите.";
    }
}
