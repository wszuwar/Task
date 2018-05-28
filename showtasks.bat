call runcrud.bat
if "%ERRORLEVEL%" == "0" goto runbrowser
echo Cannot run file
goto :fail

:runbrowser
call "C:\Program Files (x86)\Google\Chrome\Application\chrome.exe"
if "%ERRORLEVEL%" == "0" goto show

:show
start http://localhost:8080/crud/v1/task/getTasks
goto end

:fail
echo.
echo there were errors!

:end
echo.
echo SUCCES!