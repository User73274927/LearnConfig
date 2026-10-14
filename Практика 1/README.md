Здесь представлен отчет по выполненным заданиям практической работы 1.

---

## Задание 1

```bash
grep -Eo "^_?[a-z]+" /etc/passwd | sort -k1
```

## Задание 2

```bash
grep -Eo "[a-z]{1,}\s[0-9]{1,}" /etc/protocols| awk '{print $2"\t"$1}' | sort -k1 -n | column -t
```

## Задание 3

```C++
#include <iostream>  
#include <string>  
using namespace std;  
  
int main(int argc, char* argv[]) {  
    int n = (argc >= 2) ? strlen(argv[1]) : 0;  
    string text = (argc >= 2) ? argv[1] : "";  
    string border(n+2, '-');  
  
    border = '+' + border + '+';  
    cout << border << endl;  
    cout << "| " << text << " |" << endl;  
    cout << border << endl;  
    return 0;  
}
```

## Задание 4

```C++
#include <iostream>
#include <string>
#include <set>
#include <fstream>
using namespace std;

int main(int argc, char* argv[]) {
    ifstream ss(argv[1]);
    set<string> identifiers;
    string s;

    while (getline(ss, s)) {
        int i = 0;
        for (int j = 0; j < s.size(); j++) {
            if (!(isalnum(s[j]) || isdigit(s[j]))) {
                string token = s.substr(i, j-i);
                if (!token.empty() && !isdigit(token[0])) identifiers.insert(token);
                i = j+1;
            }
        }
    }
    for (string id : identifiers) {
        cout << id << " ";
    }
    cout << endl;
    return 0;
}
```

## Задание 5

```bash
#!/bin/bash

file=$1

chmod +xrw $file

sudo cp $file /usr/local/bin/copied
```
## Задание 6

```bash
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
```

## Задание 7

```bash
#!/bin/bash  
  
dir=$1  
  
fdupes -r $dir | awk 'NF == 0 {print "--------"; next}{print $1 " is duplicate"}'
```

## Задание 8

```bash
#!/bin/bash

dir="$(dirname "$(readlink -f "$0")")"
mkdir -p "$dir/archive"

for f in "$dir"/*; do
    if [ -f "$f" ]; then
        if [ "${f##*.}" = "$1" ]; then
            echo "$f"
            cp "$f" "$dir/archive"
        fi
    fi
done

tar -cjvf archive.tar archive
rm -rf "$dir/archive"
```

## Задание 9

```bash
#!/bin/bash

in=$1
out=$2

IFS=$'\n'
for line in $(cat $in)
do
    echo -e "$line" | sed 's/    /\t/g'
done > $out
```

## Задание 10

```bash
#!/bin/bash

dir=$1

for file in "$dir"/*
do
    if ! [ -s "$file" ]
    then
        echo "$file"
    fi
done
```