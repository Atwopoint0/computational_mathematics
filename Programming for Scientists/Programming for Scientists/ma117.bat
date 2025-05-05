@echo off
set path=%path%;%~dp0\jdk1.6.0_18\bin
set path=%~dp0\jdk1.6.0_18\jre\bin;%path%
set classpath=.;%~dp0\uwarwick.ma.jar
cmd.exe