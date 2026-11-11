package com.mirea.mycle.builtin;

import com.mirea.mycle.api.CLEEngine;
import com.mirea.mycle.api.CLEUtility;
import com.mirea.mycle.api.VFSCore;
import com.mirea.mycle.impl.VFSCoreImplXML;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Where extends CLEUtility {
    private VFSCore vfsCore;

    public Where(CLEEngine cleEngine) {
        super(cleEngine);
        this.vfsCore = VFSCoreImplXML.Instance.get();
    }

    @Override
    public String execute(String[] flags, String[] params) {
        if (flags.length > 0) {
            if (flags[0].equals("v") || flags[0].equals("version")) return version();
            if (flags[0].equals("h") || flags[0].equals("help")) return help();
        }
        List<String> path = new ArrayList<>();
        do {
            path.add(vfsCore.getCurrentDirectory());
        }
        while (vfsCore.goToDirectory(".."));
        Collections.reverse(path);
        return "/" + String.join("/", path.subList(1, path.size()));
    }

    @Override
    public String[] getSupportedFlags() {
        return new String[0];
    }

    @Override
    public String getName() {
        return "where";
    }

    @Override
    protected String version() {
        return "where: version: 0.1 (alpha)";
    }

    @Override
    protected String help() {
        return "where - возвращает абсолютный путь (путь от корневого каталога) к текущей директории.\n" +
                "Примеры:\n" +
                "1) \"User > where\" - выведет на экран абсолютный путь к каталогу \"User\" (Например: /Home/User).\n" +
                "Флаги:\n" +
                "\"-v\" или \"--version\" - показать версию утилиты.\n" +
                "\"-h\" или \"--help\" - показать справочную информацию об утилите.";
    }
}
