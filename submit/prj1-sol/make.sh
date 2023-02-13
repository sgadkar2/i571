#!/bin/sh

#sets dir to directory containing this script
dir=`dirname $0`

#use $dir to access programs in this directory
#so that this script can be run from any directory.
javac -d build -cp jars/*.jar src/main/java/*.java
echo "no build done"


