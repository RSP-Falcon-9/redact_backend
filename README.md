# redact_backend
Backend pro systém Redact.

## Jak buildovat?
Teoreticky stačí mít nainstalovanou JDK(8) a pomocí konzole Gradle wrapper ./gradlew build.
Prakticky je lepší mít IDE jako například Eclipse nebo VSCode.

Dále je nutnost mít nainstalovaný MySQL a mít nakonfigurovanou URL pro databázový řadič.
Jak na to? Stačí vytvořit vedle zbuildovaného .jar souboru `application.properties`.
A do něj nastavit properties dle našeho souboru v `src\main\resources\application.properties`.
Tedy hodnoty `spring.datasource.url`, `spring.datasource.username` a `spring.datasource.password`.
