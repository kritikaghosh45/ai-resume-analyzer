#!/usr/bin/env sh
# ----------------------------------------------------------------------------
# Maven wrapper script
# ----------------------------------------------------------------------------

MAVEN_PROJECTBASEDIR="$(cd "$(dirname "$0")" && pwd)"
EXEC_MAVEN="${MAVEN_PROJECTBASEDIR}/.mvn/wrapper/maven-wrapper.jar"

if [ ! -f "$EXEC_MAVEN" ]; then
  echo "Maven wrapper jar not found."
  exit 1
fi

exec java -cp "$EXEC_MAVEN" -Dmaven.multiModuleProjectDirectory="$MAVEN_PROJECTBASEDIR" org.apache.maven.wrapper.MavenWrapperMain "$@"
