@echo off
REM Install your Maven-built JAR into the local repo
echo Running Maven install into Jenkins's local repository…
mvn jar:jar install:install help:evaluate -Dexpression=project.name

REM Read project name and version from the POM
for /f "delims=" %%N in ('mvn -q -DforceStdout help:evaluate -Dexpression=project.name') do set "NAME=%%N"
for /f "delims=" %%V in ('mvn -q -DforceStdout help:evaluate -Dexpression=project.version') do set "VERSION=%%V"

REM Run the built JAR so you can see its output in the console
echo Launching JAR target\%NAME%-%VERSION%.jar…
java -jar target\%NAME%-%VERSION%.jar