#!/bin/bash

dir=$1

fdupes -r $dir | awk 'NF == 0 {print "--------"; next}{print $1 " is duplicate"}'

