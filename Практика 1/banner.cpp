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


