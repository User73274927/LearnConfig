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


