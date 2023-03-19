rem I used this script to convert from html to md and vice versa
rem Don't forget to install pandoc before using it
rem the special in this small script is that it iterates through subdirs looking for given html file

@echo off

setlocal enableextensions enabledelayedexpansion

for /d %%f in (*) do (
    if exist "%%f\Requirements.html" (
        pandoc -f html -t markdown "%%f\Requirements.html" -o "%%f\README.md"
        echo Converted %%f\Testo.html to %%f\README.md
    )
)

endlocal
