@echo off
:: author: guira8965 (github)

:: CHANGE THIS if you didn’t make a host alias in your SSH config:
set "HOST=gui@192.168.2.1 -p 6969"

:: optional delay to ensure SSH starts cleanly
setlocal enabledelayedexpansion

:MENU
cls
echo.
echo =====================================
echo                  mct
echo =====================================
echo.
echo  [1] Start Moonlight
echo  [2] Stop Moonlight
echo  [3] Exit
echo.
set /p choice="Select an option: "

if "%choice%"=="1" goto START
if "%choice%"=="2" goto STOP
if "%choice%"=="3" exit
goto MENU

:START
echo.
echo === Starting Moonlight on %HOST% ===
ssh %HOST% "/home/gui/mctl/mctl.sh start"
pause
goto MENU

:STOP
echo.
echo === Stopping Moonlight on %HOST% ===
ssh %HOST% "/home/gui/mctl/mctl.sh stop"
pause
goto MENU
