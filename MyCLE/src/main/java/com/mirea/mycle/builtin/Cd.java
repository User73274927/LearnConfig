package com.mirea.mycle.builtin;

import com.mirea.mycle.api.CLEEngine;
import com.mirea.mycle.api.CLEUtility;
import com.mirea.mycle.api.VFSCore;
import com.mirea.mycle.impl.VFSCoreImplXML;

public class Cd extends CLEUtility {
    private VFSCore vfsCore;

    public Cd(CLEEngine cleEngine) {
        super(cleEngine);
        this.vfsCore = VFSCoreImplXML.Instance.get();
    }

    @Override
    public String execute(String[] flags, String[] params) {
        if (flags.length > 0) {
            if (flags[0].equals("v") || flags[0].equals("version")) return version();
            if (flags[0].equals("h") || flags[0].equals("help")) return help();
        }
        if (params.length > 0) {
            String[] dirs = params[0].split("/");

            if (params[0].startsWith("/")) {
                String backPath = cleEngine.getUtilityByName("where").execute(EMPTY_FLAGS, EMPTY_PARAMS);
                vfsCore.goToDirectory("/");

                for (int i = 1; i < dirs.length; i++) {
                    if (!vfsCore.goToDirectory(dirs[i])) {
                        execute(EMPTY_FLAGS, new String[]{backPath});
                        return "По такому пути нет указанной директории.";
                    }
                }
            }
            else {
                for (int i = 0; i < dirs.length; i++) {
                    if (!vfsCore.goToDirectory(dirs[i])) {
                        for (int j = 0; j < i; j++) {
                            vfsCore.goToDirectory("..");
                        }
                        return "По такому пути нет указанной директории.";
                    }
                }
            }
        }
        return "";
    }

    @Override
    public String[] getSupportedFlags() {
        return new String[0];
    }

    @Override
    public String getName() {
        return "cd";
    }

    @Override
    protected String version() {
        return "cd: version: 0.1 (alpha)";
    }

    @Override
    protected String help() {
        return "cd (change directory) - меняет текущую директорию на указанную в параметре.\n" +
                "Примеры:\n" +
                "1) \"VFS > cd home\" - перейти с директории \"VFS\" на \"home\".\n" +
                "2) \"Users > cd ..\" - перейти в родительский каталог.\n" +
                "Флаги:\n" +
                "\"-v\" или \"--version\" - показать версию утилиты.\n" +
                "\"-h\" или \"--help\" - показать справочную информацию об утилите.";
    }
}
