package com.mirea.mycle.api;

public abstract class CommandParser {
    /**
     * Проверяет пользовательскую команду на наличие синтаксических ошибок.
     * @param command команда на разбор.
     * @return false - если ошибок синтаксиса нет, true - если ошибки есть.
     */
    public abstract boolean check(String command);

    /**
     * Разбирает синтаксически корректную пользовательскую команду на составные элементы: название вызываемой утилиты, флаги, параметры.
     * Вызывается при условии, что метод {@link check} c тем же самым аргументом {@code command} возвращает {@code true}.
     * @param command команда на разбор.
     */
    public abstract void parse(String command);

    /**
     * Возвращает название утилиты последней разобранной команды.
     * Вызывается строго после метода {@link parse}.
     * @return название утилиты.
     */
    public abstract String getUtilityName();

    /**
     * Возвращает набор флагов последней разобранной команды.
     * Вызывается строго после метода {@link parse}.
     * @return массив флагов.
     */
    public abstract String[] getFlags();

    /**
     * Возвращает набор параметров последней разобранной команды.
     * Вызывается строго после метода {@link parse}.
     * @return массив параметров.
     */
    public abstract String[] getParams();
}
