package com.mirea.mycle.impl;

import com.mirea.mycle.api.CLEEngine;
import com.mirea.mycle.api.CommandParser;
import com.mirea.mycle.api.MyCLE;
import com.mirea.mycle.api.VFSCore;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class MyCLEImpl extends MyCLE {
    private VFSCore vfsCore;
    private MyCLEConfig myCLEConfig;
    private CommandParser commandParser;
    private CLEEngine cleEngine;

    @Override
    public void launch(String[] args) {
        myCLEConfig = MyCLEConfig.Instance.get();
        myCLEConfig.deserialize();

        try {
            String vfsPath = myCLEConfig.getRoot().get("vfs-path").getAsString();
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document vfsSource = builder.parse(new File(vfsPath));
            VFSCoreImplXML.Instance.create(vfsSource);
        }
        catch (ParserConfigurationException | IOException | SAXException e) {
            throw new RuntimeException(e);
        }

        vfsCore = VFSCoreImplXML.Instance.get();
        cleEngine = CLEEngineFactory.createInstance();
        commandParser = new CommandParserImpl();

        if (args.length == 0) {
            runCLEConsole();
        }
        else {
            try {
                runCLEScript("src/main/resources/scripts/1.cle");
            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        myCLEConfig.serialize();
    }

    private void runCLEConsole() {
        Scanner input = new Scanner(System.in);
        String command;
        while (true) {
            System.out.printf("%s > ", vfsCore.getCurrentDirectory());
            command = input.nextLine();
            if (command.equals("exit")) {
                break;
            }
            else {
                if (!command.isEmpty()) {
                    if (!commandParser.check(command)) {
                        System.out.println("CLE: Введенная команда синтаксически неверна.");
                        continue;
                    }
                    commandParser.parse(command);
                    if (!cleEngine.check(commandParser.getUtilityName(), commandParser.getFlags(), commandParser.getParams())) {
                        System.out.println("CLE: Введенная команда семантически неверна.");
                        continue;
                    }
                    try {
                        String output = cleEngine.run(commandParser.getUtilityName(), commandParser.getFlags(), commandParser.getParams());
                        if (!(output == null || output.isEmpty())) {
                            System.out.println(output);
                        }
                    }
                    catch (Exception e) {
                        System.out.println("CLE: Утилита завершилась с ошибкой.");
                        e.printStackTrace(System.out);
                    }
                }
            }
        }
        input.close();
    }

    private void runCLEScript(String cleScript) throws IOException {
        BufferedReader scriptReader = new BufferedReader(new FileReader(cleScript));
        String command;

        int commandCounter = 1;
        while ((command = scriptReader.readLine()) != null) {
            if (!command.isEmpty()) {
                if (!commandParser.check(command)) {
                    System.out.println("CLE: Введенная команда синтаксически неверна.");
                    System.out.printf("%d. %s\n", commandCounter, command);
                    break;
                }
                commandParser.parse(command);
                if (!cleEngine.check(commandParser.getUtilityName(), commandParser.getFlags(), commandParser.getParams())) {
                    System.out.println("CLE: Введенная команда семантически неверна.");
                    System.out.printf("%d. %s\n", commandCounter, command);
                    break;
                }
                try {
                    String output = cleEngine.run(commandParser.getUtilityName(), commandParser.getFlags(), commandParser.getParams());
                    if (!(output == null || output.isEmpty())) {
                        System.out.println(output);
                    }
                }
                catch (Exception e) {
                    System.out.println("CLE: Утилита завершилась с ошибкой.");
                    System.out.printf("%d. %s\n", commandCounter, command);
                    e.printStackTrace(System.out);
                    break;
                }
            }
            commandCounter++;
        }
        scriptReader.close();

    }
}
