class Solution {
public:
    string interpret(string command) {
        string res = "";
        int i = 0, n = command.size();
        while (i < n) {
            char c = command[i];
            if (c == 'G') {
                res += "G";
                i += 1;
            } else if (c == '(' && command[i + 1] != ')') {
                res += "al";
                i += 4;
            } else {
                res += "o";
                i += 2;
            }
        }
        return res;
    }
};