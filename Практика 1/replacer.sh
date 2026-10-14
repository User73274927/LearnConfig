#!/bin/bash

in=$1
out=$2

IFS=$'\n'
for line in $(cat $in)
do
    echo -e "$line" | sed 's/    /\t/g'
done > $out

