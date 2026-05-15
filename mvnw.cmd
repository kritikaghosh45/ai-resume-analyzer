@echo off
setlocal
set MAVEN_PROJECTBASEDIR=%~dp0
set EXEC_MAVEN=%MAVEN_PROJECTBASEDIR%.mvn\wrapper\maven-wrapper.jar
if not exist "%EXEC_MAVEN%" (
  echo Maven wrapper jar not found.
  exit /b 1
)
java -cp "%EXEC_MAVEN%" -Dmaven.multiModuleProjectDirectory="%MAVEN_PROJECTBASEDIR%" org.apache.maven.wrapper.MavenWrapperMain %*
