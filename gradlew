#!/usr/bin/env sh

##############################################################################
##
##  Gradle startup script for UN*X
##
##############################################################################

# Add default JVM options here. You can also use JAVA_OPTS and GRADLE_OPTS to pass JVM options to this script.
DEFAULT_JVM_OPTS='"-Xmx64m" "-Xms64m"'

APP_HOME="`dirname \"$0\"`"

# Resolve any "." and ".." in APP_HOME to make it absolute.
APP_HOME="`cd \"$APP_HOME\" && pwd`"

# Use the maximum available, or set MAX_FD != -1 to use that value.
MAX_FD="maximum"

warn_user() {
    echo "$*"
}

die() {
    echo
    echo "$*"
    echo
    exit 1
}

# OS specific support (must be 'true' or 'false').
cygwin=false
msys=false
darwin=false
case "`uname`" in
  CYGWIN*)
    cygwin=true
    ;;
  MSYS*)
    msys=true
    ;;
  Darwin*)
    darwin=true
    ;;
esac

# For Cygwin or MSYS, ensure paths are in UNIX format before anything is touched.
if $cygwin || $msys ; then
    APP_HOME=`cygpath -u "$APP_HOME"`
fi

# Determine the Java command to use to start the JVM.
if [ -n "$JAVA_HOME" ] ; then
    if [ -x "$JAVA_HOME/jre/sh/java" ] ; then
        # IBM's JDK on AIX uses strange locations
        JAVACMD="$JAVA_HOME/jre/sh/java"
    else
        JAVACMD="$JAVA_HOME/bin/java"
    fi
    if [ ! -x "$JAVACMD" ] ; then
        die "ERROR: JAVA_HOME is set to an invalid directory: $JAVA_HOME

Please set the JAVA_HOME variable in your environment to match the
location of your Java installation."
    fi
else
    JAVACMD="java"
    which java >/dev/null 2>&1 || die "ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH.

Please set the JAVA_HOME variable in your environment to match the
location of your Java installation."
fi

# Increase the maximum file descriptors if we can.
if [ "$MAX_FD" = "maximum" ] ; then
    ulimit -n `ulimit -Hn` 2> /dev/null
    if [ $? -ne 0 ] ; then
        MAX_FD=8192
    fi
fi
if [ "$MAX_FD" != "maximum" ] ; then
    ulimit -n $MAX_FD 2> /dev/null
fi

# Determine the arguments to pass to the JVM.
if [ -z "$JVM_OPTS" ] ; then
    JVM_OPTS="$DEFAULT_JVM_OPTS"
fi

# Determine the arguments to pass to Gradle.
if [ -z "$GRADLE_OPTS" ] ; then
    GRADLE_OPTS=""
fi

# For Cygwin or MSYS, convert arguments to Windows format before anything is touched.
if $cygwin || $msys ; then
    APP_HOME=`cygpath -w "$APP_HOME"`
    JVM_OPTS=`cygpath -w "$JVM_OPTS"`
    GRADLE_OPTS=`cygpath -w "$GRADLE_OPTS"`
fi

# For Darwin, add -XstartOnFirstThread to the JVM options.
if $darwin ; then
    JVM_OPTS="$JVM_OPTS -XstartOnFirstThread"
fi

# For Cygwin or MSYS, convert arguments to UNIX format before anything is touched.
if $cygwin || $msys ; then
    APP_HOME=`cygpath -u "$APP_HOME"`
    JVM_OPTS=`cygpath -u "$JVM_OPTS"`
    GRADLE_OPTS=`cygpath -u "$GRADLE_OPTS"`
fi

# For Cygwin or MSYS, ensure paths are in UNIX format before anything is touched.
if $cygwin || $msys ; then
    APP_HOME=`cygpath -u "$APP_HOME"`
fi

# For Cygwin or MSYS, convert arguments to Windows format before anything is touched.
if $cygwin || $msys ; then
    APP_HOME=`cygpath -w "$APP_HOME"`
    JVM_OPTS=`cygpath -w "$JVM_OPTS"`
    GRADLE_OPTS=`cygpath -w "$GRADLE_OPTS"`
fi

# For Darwin, add -XstartOnFirstThread to the JVM options.
if $darwin ; then
    JVM_OPTS="$JVM_OPTS -XstartOnFirstThread"
fi

# For Cygwin or MSYS, convert arguments to UNIX format before anything is touched.
if $cygwin || $msys ; then
    APP_HOME=`cygpath -u "$APP_HOME"`
    JVM_OPTS=`cygpath -u "$JVM_OPTS"`
    GRADLE_OPTS=`cygpath -u "$GRADLE_OPTS"`
fi

# For Cygwin or MSYS, ensure paths are in UNIX format before anything is touched.
if $cygwin || $msys ; then
    APP_HOME=`cygpath -u "$APP_HOME"`
fi

# For Cygwin or MSYS, convert arguments to Windows format before anything is touched.
if $cygwin || $msys ; then
    APP_HOME=`cygpath -w "$APP_HOME"`
    JVM_OPTS=`cygpath -w "$JVM_OPTS"`
    GRADLE_OPTS=`cygpath -w "$GRADLE_OPTS"`
fi

# For Darwin, add -XstartOnFirstThread to the JVM options.
if $darwin ; then
    JVM_OPTS="$JVM_OPTS -XstartOnFirstThread"
fi

# For Cygwin or MSYS, convert arguments to UNIX format before anything is touched.
if $cygwin || $msys ; then
    APP_HOME=`cygpath -u "$APP_HOME"`
    JVM_OPTS=`cygpath -u "$JVM_OPTS"`
    GRADLE_OPTS=`cygpath -u "$GRADLE_OPTS"`
fi

# For Cygwin or MSYS, ensure paths are in UNIX format before anything is touched.
if $cygwin || $msys ; then
    APP_HOME=`cygpath -u "$APP_HOME"`
fi

# For Cygwin or MSYS, convert arguments to Windows format before anything is touched.
if $cygwin || $msys ; then
    APP_HOME=`cygpath -w "$APP_HOME"`
    JVM_OPTS=`cygpath -w "$JVM_OPTS