@echo off
if not defined JAVA_HOME (
    echo Error: JAVA_HOME environment variable is not set.
    exit /b 1
)
echo JAVA_HOME is set to %JAVA_HOME%

call mvnw clean
call mvnw package

%JAVA_HOME%\bin\java -jar target\FootballManager-0.0.1-SNAPSHOT.jar

exit /b 0
