package com.mirea.mycle.api;

/**
 * Ядро CLE, предоставляющий полный набор операций над виртуальной файловой системой VFS.
 */
public abstract class VFSCore {

    /**
     * Совершает переход из текущей директории в соседнюю по имени.
     * Переход в родительскую директорию осуществляется передачей в аргумент {@code dirName} служебной команды {@code ..}
     * Переход в корневую директорию осуществляется передачей в аргумент {@code dirName} служебной команды {@code /}
     * Если переход в указанный каталог невозможен, то текущая директория сохраняется.
     * @param dirName имя директории.
     * @return {@code true} если переход прошел успешно, иначе {@code false}.
     */
    public abstract boolean goToDirectory(String dirName);

    /**
     * Возвращает имя текущей директории.
     * @return имя директории.
     */
    public abstract String getCurrentDirectory();

    /**
     * Считывает текущий каталог и возвращает набор всех файлов и директорий.
     * @return набор всех файлов и директорий.
     */
    public abstract String[] readDirectory();

    /**
     * Считывает указанный файл и возвращает его содержимое.
     * Если такого файла нет, то возвращает {@code null}.
     * @param fileName имя файла, который нужно считать.
     * @return содержимое файла.
     */
    public abstract String readFile(String fileName);

    /**
     * Проверяет является ли указанный элемент vfs файлом.
     * Если такого элемента нет, то вернуть {@code false}.
     * @param fileName имя vfs элемента
     * @return {@code true} если элемент является файлом, иначе {@code false}.
     */
    public abstract boolean isFile(String fileName);

}
