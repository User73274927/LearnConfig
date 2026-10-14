#!/bin/bash

file=$1
extension="${file##*.}"

if [[ $extension == "c" || $extension == "cpp" ]]; then
    if head -1 "$file" | grep -Eq "^(//|/\*)"; then
        echo "YES"
    else
        echo "NO"
    fi
elif [ $extension = "py" ]; then
    if head -1 "$file" | grep -Eq "^(#|\"\"\")"; then
        echo "YES"
    else
        echo "NO"
    fi
else
    echo "Undefined extension"
fi
