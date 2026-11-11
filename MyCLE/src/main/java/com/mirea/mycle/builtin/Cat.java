package com.mirea.mycle.builtin;

import com.mirea.mycle.api.CLEEngine;
import com.mirea.mycle.api.CLEUtility;
import com.mirea.mycle.api.VFSCore;
import com.mirea.mycle.impl.VFSCoreImplXML;

public class Cat extends CLEUtility {
    private VFSCore vfsCore;

    public Cat(CLEEngine cleEngine) {
        super(cleEngine);
        this.vfsCore = VFSCoreImplXML.Instance.get();
    }

    @Override
    public String execute(String[] flags, String[] params) {
        if (flags.length > 0) {
            if (flags[0].equals("v") || flags[0].equals("version")) return version();
            if (flags[0].equals("h") || flags[0].equals("help")) return help();
        }
        StringBuilder result = new StringBuilder();
        if (params.length > 0) {
            for (int i = 0; i < params.length; i++) {
                String content = vfsCore.readFile(params[i]);
                if (content == null) {
                    return String.format("Файла c именем \"%s\" не существует", params[i]);
                }
                result.append(content);
            }
        }
        return result.toString();
    }

    @Override
    public String[] getSupportedFlags() {
        return new String[0];
    }

    @Override
    public String getName() {
        return "cat";
    }

    @Override
    protected String version() {
        return "cat: version 0.1 (alpha)";
    }

    @Override
    protected String help() {
        return "cat (concatenate) - обьединяет и выводит содержимое указанного набора файлoв по порядку.\n" +
                "Примеры:\n" +
                "1) \"VFS > cat file.txt\" - выведет на экран содержимое файла \"file.txt\".\n" +
                "2) \"VFS > cat file1.txt file2.txt\" - выведет на экран обьединенное содержимое файлов \"file1.txt\" и \"file2.txt\"\n" +
                "Флаги:\n" +
                "\"-v\" или \"--version\" - показать версию утилиты.\n" +
                "\"-h\" или \"--help\" - показать справочную информацию об утилите.";
    }
}
