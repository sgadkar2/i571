#!/bin/sh

#sets dir to directory containing this script
dir=`dirname $0`

#use $dir/ as prefix to run any programs in this dir
#so that this script can be run from any directory.
java -cp $dir/build:$dir/jars/json-20220924.jar Parser

