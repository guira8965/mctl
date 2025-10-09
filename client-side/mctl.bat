@echo off
:: author: guira8965 (github)

:: CHANGE THIS if you didn’t make a host alias in your SSH config:
set "HOST=user@host -p 6969"

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
echo  [3] Status
echo  [4] Exit
echo.
set /p choice="Select an option: "

if "%choice%"=="1" goto START
if "%choice%"=="2" goto STOP
if "%choice%"=="3" goto STATUS
if "%choice%"=="4" exit
goto MENU

:START
echo.
echo === Starting Moonlight on %HOST% ===
ssh %HOST% "~/mctl/mctl.sh start"
pause
goto MENU

:STOP
echo.
echo === Stopping Moonlight on %HOST% ===
ssh %HOST% "~/mctl/mctl.sh stop"
pause
goto MENU

:STATUS
echo.
echo === Stopping Moonlight on %HOST% ===
ssh %HOST% "~/mctl/mctl.sh status"
pause
goto MENU
