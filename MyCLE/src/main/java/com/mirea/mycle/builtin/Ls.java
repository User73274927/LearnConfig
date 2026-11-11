package com.mirea.mycle.builtin;

import com.mirea.mycle.api.CLEEngine;
import com.mirea.mycle.api.CLEUtility;
import com.mirea.mycle.api.VFSCore;
import com.mirea.mycle.impl.VFSCoreImplXML;

public class Ls extends CLEUtility {
    private VFSCore vfsCore;

    public Ls(CLEEngine cleEngine) {
        super(cleEngine);
        this.vfsCore = VFSCoreImplXML.Instance.get();
    }

    @Override
    public String execute(String[] flags, String[] params) {
        if (flags.length > 0) {
            if (flags[0].equals("v") || flags[0].equals("version")) return version();
            if (flags[0].equals("h") || flags[0].equals("help")) return help();
        }
        return String.join("\n", vfsCore.readDirectory());
    }

    @Override
    public String[] getSupportedFlags() {
        return new String[0];
    }

    @Override
    public String getName() {
        return "ls";
    }

    @Override
    protected String version() {
        return "ls: version: 0.1 (alpha)";
    }

    @Override
    protected String help() {
        return "ls (list) - отображает все содержимое текущего каталога.\n" +
                "Примеры:\n" +
                "1) \"VFS > ls\" - Отобразить все содержимое директории VFS.\n" +
                "Флаги:\n" +
                "\"-v\" или \"--version\" - показать версию утилиты.\n" +
                "\"-h\" или \"--help\" - показать справочную информацию об утилите.";
    }
}
