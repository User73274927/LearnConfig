# MyCLE (My Command Line Environment)

MyCLE (My Command Line Environment) - учебный проект упрощенного эмулятора командной оболочки.
Вдохновляясь философией UNIX-систем, MyCLE спроектирован на множестве независимых и простых компонентов с единым интерфейсом ввода/вывода - утилит.
Такой подход делает мою командную оболочку максимально расширяемой и легкой к отладке.

## Build

Сборка проекта:

```bash
./gradlew build
```

На Windows:

```bat
gradlew.bat build
```

## Run

Для запуска программы.

Консоль:

```bash
./gradlew run --console=plain -q
```

CLE-скрипты

```bash
./gradlew run --args="/путь/к/скрипту.cle" --console=plain -q
```

На Windows:

CLE-консоль:

```bat
gradlew.bat run --console=plain -q
```

CLE-скрипты

```bat
gradlew.bat run --args="/путь/к/скрипту.cle" --console=plain -q
```

*Note: This command requires the Gradle Application Plugin to be configured.*