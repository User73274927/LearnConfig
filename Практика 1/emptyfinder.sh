#!/bin/bash

dir=$1

for file in "$dir"/*
do
    if ! [ -s "$file" ]
    then
        echo "$file"
    fi
done
