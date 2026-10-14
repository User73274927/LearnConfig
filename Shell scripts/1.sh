#!/bin/bash

echo "Enter directory path:"
read path

for file in $path/*
do
    echo "$file"
done
