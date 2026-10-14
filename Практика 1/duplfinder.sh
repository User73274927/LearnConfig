#!/bin/bash

file=$1

function dfs {
    local dir=$1
    shopt -s nullglob
    for f in "$dir"/*; do
        if [ -d "$f" ]; then
            echo "$f is directory"
            dfs "$f"
        fi
    done
}

dfs "$file"
