package com.mirea.mycle.api;

public abstract class CLEUtility {
    public static final String[] EMPTY_FLAGS = new String[0];
    public static final String[] EMPTY_PARAMS = new String[0];

    protected final CLEEngine cleEngine;

    /**
     * Базовый конструктор CLEUtility.
     * @param cleEngine исполнительная среда, нужен для вызова других утилит.
     */
    public CLEUtility(CLEEngine cleEngine) {
        this.cleEngine = cleEngine;
    }

    /**
     * Доступные флаги, которые есть у всех CLE-утилит.
     */
    public static final String[] DEFAULT_FLAGS = {"h", "help", "v", "version"};

    /**
     * Метод выполнения утилиты c заданными флагами.
     * @param params параметры, задающие работу утилиты.
     * @return результат работы утилиты.
     */
    public abstract String execute(String[] flags, String[] params);

    /**
     * Возвращает набор доступных флагов.
     * @return массив названий флагов.
     */
    public abstract String[] getSupportedFlags();

    /**
     * Возвращает имя утилиты, по которому она будет вызываться в команде.
     * @return имя утилиты.
     */
    public abstract String getName();

    /**
     * Возвращает информацию о версии утилиты.
     * Вызывается при активированных флагах {@code -v} или {@code --version}
     */
    protected abstract String version();

    /**
     * Возвращает справочную информацию об утилите: выполняемая ею задача, описание параметров, включая параметры по умолчанию, примеры использования.
     * Вызываетсяе при активированных флагах {@code -h} или {@code --help}
     */
    protected abstract String help();

}
