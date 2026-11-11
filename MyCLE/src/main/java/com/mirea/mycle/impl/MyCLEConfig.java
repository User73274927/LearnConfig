package com.mirea.mycle.impl;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class MyCLESettings {
    private MyCLESettings() {

    }

    public void start() throws ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document
    }

    public static final class Instance {
        private MyCLESettings instance = new MyCLESettings();

        public static MyCLESettings get() {
            return instance;
        }
    }
}
