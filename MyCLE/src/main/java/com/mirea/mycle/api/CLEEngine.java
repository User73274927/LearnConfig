package com.mirea.mycle.api;

import java.util.Collection;

public abstract class CLEEngine {
    /**
     * Метод загрузки утилиты путем создания и регистрации экземпляра указанного класса.
     * @param className полное название класса утилиты (обязательно должен наследовать базовый класс {@link CLEUtility}).
     */
    public abstract void loadUtility(String className);

    /**
     * Обновляет все данные утилит по конфигурационному файлу {@code myCLE.xml}.
     * Подгружает отсутствующие утилиты если нужно.
     */
    public abstract void updateUtilities();

    /**
     * Проверяет синтаксически корректную команду на наличие семантических ошибок.
     * @param utilityName название вызываемой утилиты.
     * @param flags массив флагов команды.
     * @param params массив параметров команды.
     * @return false - если семантических ошибок нет, true - если ошибки есть.
     */
    public abstract boolean check(String utilityName, String[] flags, String[] params);

    /**
     * Выполняет корректно написанную команду, вызывая нужные утилиты.
     * @param utilityName название вызываемой утилиты.
     * @param flags массив флагов команды.
     * @param params массив параметров команды.
     * @return выходные данные.
     */
    public abstract String run(String utilityName, String[] flags, String[] params);

    /**
     * Возвращает утилиту по имени. Если утилита не найдена, то вернуть {@code null}.
     * @param utilityName имя утилиты.
     * @return искомая утилита.
     */
    public abstract CLEUtility getUtilityByName(String utilityName);

    /**
     * Возвращает нобор всех установленных утилит.
     * @return java-коллекция всех утилит.
     */
    public abstract Collection<CLEUtility> getActiveUtilities();
}
