Repozytorium zawiera przykład z zajęć, nie jest to mój kod. Poniżej w pliku README można znaleźć odnośnik do oryginalnego źródła.

# jdbc-example

Project showing connecting to database using JDBC.
Projekt prezentujący połączenie z bazą danych przy pomocy JDBC.

## Wstęp

1. Projekt do działania potrzebuje serwer bazy danych MySQL działający lokalnie na porcie 3306 (domyślny dla MySQL).
2. Aplikacja łączy się z bazą o nazwie `jdbc_example` logując się na użytkownika `root` bez hasła.

## Przykłady

Plik `src/main/resources/schema.sql` zawiera zapytania SQL przygotowujące bazę danych (tabele i dane), które trzeba wykonać na bazie danych. `src/main/java/jdbc/JdbcHelper.java` zawiera konfigurację połączenia (url, nazwę użytkownika bazy danych i hasło) oraz funkcję tworzącą połączenie z bazą.

 `src/main/java/jdbc/examples/JdbcStatementExamples.java` zawiera przykłady wykonywania zapytań do bazy danych (CRUD) - dodawanie, odczytywanie, aktualizowanie i usuwanie.

## Uwagi
W przypadku pytań bądź uwag polecam zgłaszanie ich przy pomocy mechanizmu [GitHub issues](https://github.com/piotrgajow/jdbc-example/issues/new)
