package com.mirea.mycle.impl;

import com.google.gson.*;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;

public class MyCLEConfig {
    private Gson gson;
    private JsonObject jsonConfig;

    private MyCLEConfig() {

    }

    public void deserialize() {
        gson = new GsonBuilder().setPrettyPrinting().setFormattingStyle(FormattingStyle.PRETTY.withIndent("\t")).create();
        try (Reader reader = Files.newBufferedReader(Path.of("config/myCLE.json"))) {
            jsonConfig = gson.fromJson(reader, JsonObject.class);
        }
        catch (IOException e) {
            System.out.println("CLE: Не удалось считать конфигурационный файл.");
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("unchecked")
    public <T extends JsonElement>T getJsonElement(String keyPath, Class<T> type) {
        String[] keys = keyPath.split(">");
        JsonElement current = jsonConfig;
        for (String key : keys) {
            if (current instanceof JsonObject) {
                current = current.getAsJsonObject().get(key);
            }
            else {
                throw new RuntimeException();
            }
        }
        return (T) current;
    }

    public JsonObject getJsonElement(String keyPath) {
        return this.getJsonElement(keyPath, JsonObject.class);
    }

    public JsonObject getRoot() {
        return jsonConfig;
    }

    public void serialize() {
       try (Writer writer = Files.newBufferedWriter(Path.of("config/myCLE.json"))) {
            gson.toJson(jsonConfig, writer);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static final class Instance {
        private static MyCLEConfig instance = new MyCLEConfig();

        public static MyCLEConfig get() {
            return instance;
        }
    }
}
