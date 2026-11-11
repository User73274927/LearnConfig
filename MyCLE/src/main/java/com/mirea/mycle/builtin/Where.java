package com.mirea.mycle.builtin;

import com.mirea.mycle.api.CLEUtility;
import com.mirea.mycle.api.VFSCore;
import com.mirea.mycle.impl.VFSCoreImplXML;

public class Which extends CLEUtility {
    private VFSCore vfsCore;

    public Which() {
        this.vfsCore = VFSCoreImplXML.Instance.get();
    }

    @Override
    public String execute(String[] flags, String[] params) {
        if (flags.length > 0) {
            if (flags[0].equals("v") || flags[0].equals("version")) return version();
            if (flags[0].equals("h") || flags[0].equals("help")) return help();
        }
        return "";
    }

    @Override
    public String[] getSupportedFlags() {
        return new String[0];
    }

    @Override
    public String getName() {
        return "";
    }

    @Override
    protected String version() {
        return ": version: 0.1 (alpha)";
    }

    @Override
    protected String help() {
        return "cd (change directory) - меняет текущую директорию на указанную в параметре.\n" +
                "Примеры:\n" +
                "1) \"VFS > cd home\" - перейти с директории \"vfs\" на \"home\".\n" +
                "2) \"Users > cd ..\" - перейти в родительский каталог.\n" +
                "Флаги:\n" +
                "\"-v\" или \"--version\" - показать версию утилиты.\n" +
                "\"-h\" или \"--help\" - показать справочную информацию об утилите.";
    }
}
