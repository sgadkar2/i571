#!/bin/sh

#sets dir to directory containing this script
dir=`dirname $0`

#use $dir to access programs in this directory
#so that this script can be run from any directory.
javac -d $dir/build -cp $dir/jars/*.jar $dir/src/main/java/*.java
echo "Build Successful"


