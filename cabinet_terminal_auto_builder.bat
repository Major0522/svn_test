@echo off
echo --------------------------------------
echo Project:cabinet_terminal
echo --------------------------------------
set curdir=%~dp0
cd /d %curdir%

cd cabinet_terminal_bootstrap
call mvn clean
cd ../cabinet_terminal_plugin_base
call mvn clean
cd ../cabinet_terminal_plugin_home
call mvn clean
cd ../cabinet_terminal_plugin_distribute
call mvn clean
cd ../cabinet_terminal_plugin_express
call mvn clean
cd ../cabinet_terminal_plugin_ad
call mvn clean
cd ../cabinet_terminal_plugin_member
call mvn clean
cd ../cabinet_terminal_plugin_monitor
call mvn clean

cd ../cabinet_terminal_plugin_base
call mvn install
cd ../cabinet_terminal_plugin_home
call mvn install
cd ../cabinet_terminal_plugin_distribute
call mvn install
cd ../cabinet_terminal_plugin_express
call mvn install
cd ../cabinet_terminal_plugin_ad
call mvn install
cd ../cabinet_terminal_plugin_member
call mvn install
cd ../cabinet_terminal_plugin_monitor
call mvn install
cd ../cabinet_terminal_bootstrap
call mvn install

cd ../cabinet_terminal_plugin_base
call exportDependencyJars.bat

cd ../cabinet_terminal_bootstrap
call exportDependencyJars.bat

cd ../ 
copy  cabinet_terminal_plugin_base\OUT_dependencyJars\*.jar release\lib\
copy  cabinet_terminal_bootstrap\OUT_dependencyJars\*.jar release\lib\

echo builder done!
pause