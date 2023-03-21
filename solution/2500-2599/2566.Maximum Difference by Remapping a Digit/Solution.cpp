class Solution {
public:
    int minMaxDifference(int num) {
        string s = to_string(num);
        string t = s;
        char first = s[0];
        for (char& c : s) {
            if (c == first) {
                c = '0';
            }
        }
        int mi = stoi(s);
        for (int i = 0; i < t.size(); ++i) {
            if (t[i] != '9') {
                char second = t[i];
                for (int j = i; j < t.size(); ++j) {
                    if (t[j] == second) {
                        t[j] = '9';
                    }
                }
                return stoi(t) - mi;
            }
        }
        return num - mi;
    }
};